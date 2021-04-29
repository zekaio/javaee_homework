package cn.zekaio.api.service.impl;

import cn.zekaio.api.dao.FollowDao;
import cn.zekaio.api.dao.UserDao;
import cn.zekaio.api.exception.BusinessException;
import cn.zekaio.api.pojo.Follow;
import cn.zekaio.api.pojo.User;
import cn.zekaio.api.service.UserService;
import cn.zekaio.api.util.PojoToMapUtil;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private FollowDao followDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PojoToMapUtil pojoToMapUtil;

    // 创建用户
    @Override
    public Result createUser(Map<String, String> params) {
        User user = userDao.getUserByUsername(params.get("username"));
        String username = params.get("username");
        String password = params.get("password");
        String checkPwd = params.get("check_pwd");
        if (!password.equals(checkPwd)) {
            throw new BusinessException(400, "两次输入密码不一致");
        }
        if (user != null) {
            throw new BusinessException(409, "用户名已存在");
        }
        String passwordHash = bCryptPasswordEncoder.encode(params.get("password"));
        String uuid = UUID.randomUUID().toString();
        userDao.createUser(username, uuid, passwordHash);
        return Result.success("注册成功");
    }

    // 修改用户信息
    @Override
    public Result updateUserInfo(Map<String, String> params, HttpSession session) {
        String userId = session.getAttribute("user_id").toString();

        User user = userDao.getUserByUserId(userId);

        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }

        String username = params.get("username");
        String sex = params.get("sex");
        String grade = params.get("grade");
        String major = params.get("major");
        String description = params.get("description");

        if (!params.get("username").equals(user.getUsername())) {
            User other = userDao.getUserByUsername(params.get("username"));
            if (other != null) {
                throw new BusinessException(409, "用户名已存在");
            }
        }

        userDao.updateUserInfo(userId, username, sex, grade, major, description);
        return Result.success("修改成功");
    }

    // 获取用户信息
    @Override
    public Result getUserInfo(String username, String uuid, HttpSession session) {
        User user;
        if (username != null) {
            user = userDao.getUserByUsername(username);
        } else if (uuid != null) {
            user = userDao.getUserByUUID(uuid);
        } else {
            user = userDao.getUserByUserId(session.getAttribute("user_id").toString());
        }

        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        return Result.success(pojoToMapUtil.getUserMap(user));
    }

    @Override
    public Result searchUsers(String keyword, String lastUserUUID, Integer limit) {
        return null;
    }

    // 关注或取关用户
    @Override
    public Result followUser(Map<String, String> params, HttpSession session) {
        String username = params.get("username");
        String status = params.get("status");

        if (username == null) {
            throw new BusinessException(400, "用户名错误");
        }

        if (status == null) {
            throw new BusinessException(400, "请选择要进行的操作");
        }
        Integer intStatus = status.equals("true") ? 1 : 0;
        String userId = session.getAttribute("user_id").toString();
        User me = userDao.getUserByUserId(userId);
        if (me == null) {
            throw new BusinessException(404, "当前登录用户不存在");
        }
        if (me.getUsername().equals(username)) {
            throw new BusinessException(400, "无法关注自己");
        }

        User other = userDao.getUserByUsername(username);
        if (other == null) {
            throw new BusinessException(404, "用户不存在");
        }

        Follow follow = followDao.getFollowById(me.getUserId(), other.getUserId());

        if (follow == null) {
            if (intStatus.equals(0)) {
                throw new BusinessException(400, "未关注该用户");
            }
            followDao.createFollow(me.getUserId(), other.getUserId());
            userDao.updateUserFollowNum(me.getUserId(), me.getFollowNum() + 1);
            userDao.updateUserFansNum(other.getUserId(), other.getFansNum() + 1);
        } else if (follow.getStatus().equals(intStatus)) {
            throw new BusinessException(400, String.format("%s关注该用户", intStatus == 1 ? "已" : "未"));
        } else {
            followDao.updateFollowStatus(follow.getFollowId(), intStatus);
            if (intStatus.equals(0)) {
                userDao.updateUserFollowNum(me.getUserId(), me.getFollowNum() - 1);
                userDao.updateUserFansNum(other.getUserId(), other.getFansNum() - 1);
            } else {
                userDao.updateUserFollowNum(me.getUserId(), me.getFollowNum() + 1);
                userDao.updateUserFansNum(other.getUserId(), other.getFansNum() + 1);
            }
        }

        return Result.success("成功");
    }

    private User getUserByUsernameOrUUID(String uuid, String username) {
        User user = null;
        if (username != null) {
            user = userDao.getUserByUsername(username);
        }
        if (uuid != null) {
            user = userDao.getUserByUUID(uuid);
        }

        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }

        return user;
    }

    // 获取关注列表
    @Override
    public Result getFollowList(String uuid, String username, Integer lastFollowId, Integer limit) {
        User user = getUserByUsernameOrUUID(uuid, username);

        List<Follow> followList = followDao.getFollowList(user.getUserId(), limit, lastFollowId);
        List<Map<String, Object>> ret = new ArrayList<>();
        for (Follow follow : followList) {
            Map<String, Object> map = new HashMap<>();

            map.put("followed", true);
            map.putAll(pojoToMapUtil.getFollowInfo(follow));

            ret.add(map);
        }

        return Result.success(ret);
    }

    // 获取粉丝列表
    @Override
    public Result getFansList(String uuid, String username, Integer lastFollowId, Integer limit) {
        User user = getUserByUsernameOrUUID(uuid, username);

        List<Follow> followList = followDao.getFansList(user.getUserId(), limit, lastFollowId);
        List<Map<String, Object>> ret = new ArrayList<>();
        for (Follow follow : followList) {
            Map<String, Object> map = new HashMap<>();

            Follow f = followDao.getFollowById(follow.getFollowedUserId(), follow.getUserId());
            map.put("followed", f != null);
            map.putAll(pojoToMapUtil.getFollowInfo(follow));

            ret.add(map);
        }

        return Result.success(ret);
    }
}

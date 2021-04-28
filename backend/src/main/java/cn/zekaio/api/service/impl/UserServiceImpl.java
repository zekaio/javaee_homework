package cn.zekaio.api.service.impl;

import cn.zekaio.api.dao.UserDao;
import cn.zekaio.api.exception.BusinessException;
import cn.zekaio.api.pojo.User;
import cn.zekaio.api.service.UserService;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        return Result.success(user);
    }

    @Override
    public Result searchUsers(String keyword, String lastUserUUID, Integer limit) {
        return null;
    }

    @Override
    public Result followUser(Map<String, String> params, HttpSession session) {

        return null;
    }

    @Override
    public Result getFollowList(String uuid, String username, Integer lastFollowId, Integer limit) {
        return null;
    }

    @Override
    public Result getFansList(String uuid, String username, Integer lastFollowId, Integer limit) {
        return null;
    }
}

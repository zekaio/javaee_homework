package cn.zekaio.api.service.impl;

import cn.zekaio.api.dao.UserDao;
import cn.zekaio.api.exception.BusinessException;
import cn.zekaio.api.pojo.User;
import cn.zekaio.api.service.SessionService;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 登录
    @Override
    public Result login(Map<String, String> params, HttpServletRequest request) {
        User user = userDao.getUserByUsername(params.get("username"));
        System.out.println(user);
        if (user == null) {
            throw new BusinessException(400, "用户名或密码错误");
        }
        if (!bCryptPasswordEncoder.matches(params.get("password"), user.getPassword())) {
            throw new BusinessException(400, "用户名或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user.getUserId());
        return Result.success("登录成功");

    }

    // 退出登录
    @Override
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user_id");
        return Result.success("退出成功");
    }
}

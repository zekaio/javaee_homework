package cn.zekaio.api.interceptor;

import cn.zekaio.api.exception.BusinessException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 登录检测中间件
public class LoginInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
       if (request.getMethod().equals("OPTIONS")) return true;

       HttpSession session = request.getSession();
       if (session.getAttribute("user_id") == null) {
           throw new BusinessException(401, "请先登录");
       }
       return true;
   }
}

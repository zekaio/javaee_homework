package cn.zekaio.api.controller;

import cn.zekaio.api.service.SessionService;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;


    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, String> params, HttpServletRequest request) {
        return sessionService.login(params, request);

    }

    @DeleteMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        return sessionService.logout(request);
    }
}

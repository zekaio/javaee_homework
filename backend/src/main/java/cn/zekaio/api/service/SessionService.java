package cn.zekaio.api.service;

import cn.zekaio.api.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SessionService {
    Result login(Map<String, String> params, HttpServletRequest request);

    Result logout(HttpServletRequest request);
}

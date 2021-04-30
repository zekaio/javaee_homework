package cn.zekaio.api.exception;

import cn.zekaio.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

// 全局错误处理
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessExceptionHandler(BusinessException e, HttpServletResponse response) {
        logger.error(e.getMessage());

        response.setStatus(e.getCode());
        return Result.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e, HttpServletResponse response) {
        logger.error(e.getMessage());

        response.setStatus(500);
        return Result.error("服务器错误");
    }
}
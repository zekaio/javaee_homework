package cn.zekaio.api.exception;

import cn.zekaio.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result errorHandler(BusinessException e, HttpServletResponse response) {
        logger.error(e.getMessage());

        response.setStatus(e.getCode());
        return Result.error(e);
    }
}
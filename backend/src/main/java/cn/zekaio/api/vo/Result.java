package cn.zekaio.api.vo;

import cn.zekaio.api.exception.BusinessException;
import lombok.Data;

@Data
public class Result {
    private boolean success;
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        r.setData(data);
        return r;
    }

    public static Result error(BusinessException e) {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(e.getCode());
        r.setMessage(e.getMessage());
        return r;
    }

    public static Result error(String message) {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(500);
        r.setMessage(message);
        return r;
    }
}
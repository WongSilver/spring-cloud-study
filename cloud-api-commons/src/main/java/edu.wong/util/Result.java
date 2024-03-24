package edu.wong.util;

import edu.wong.enumerate.ReturnCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {

    private Integer code;
    private String massage;
    private T data;
    private long timestamp;

    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data) {
        return custom(ReturnCodeEnum.RC200.getCode(), ReturnCodeEnum.RC200.getMessage(), data);
    }

    public static <T> Result<T> fail(Integer code, String massage) {
        return custom(code, massage, null);
    }

    public static <T> Result<T> fail() {
        return custom(ReturnCodeEnum.RC999.getCode(), ReturnCodeEnum.RC999.getMessage(), null);
    }

    public static <T> Result<T> fail(String massage) {
        return custom(9999, massage, null);
    }


    public static <T> Result<T> custom(Integer code, String massage, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMassage(massage);
        result.setData(data);
        return result;
    }
}

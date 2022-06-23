package com.qks.common.VO;

/**
 * @author 15998
 */
public class ResultHelper {
    public static <T> ResultVO<T> success(T data, Integer code, String msg) {
        return new ResultVO<T>(data, code, msg);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(data, 0, "success");
    }

    public static <T> ResultVO<T> success(T data, String msg) {
        return new ResultVO<T>(data, 0, msg);
    }

    public static <T> ResultVO<T> error(Integer code, String msg) {
        return new ResultVO<T>(code, msg);
    }
}

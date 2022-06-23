package com.qks.common.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 15998
 */
@Data
@NoArgsConstructor
public class ResultVO<T> implements Serializable {
    private T data;
    private Integer code;
    private String msg;

    public ResultVO(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

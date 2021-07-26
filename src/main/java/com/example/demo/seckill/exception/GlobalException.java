package com.example.demo.seckill.exception;


import com.example.demo.utils.Response.RespVo;
import com.example.demo.utils.Response.RespVoEnum;

/**
 * 全局异常
 */

public class GlobalException  extends RuntimeException{

    private RespVoEnum respVoEnum;

    public GlobalException(RespVoEnum respVoEnum) {
        this.respVoEnum = respVoEnum;
    }

    public GlobalException() {
    }

    public RespVoEnum getRespVoEnum() {
        return respVoEnum;
    }

    public void setRespVoEnum(RespVoEnum respVoEnum) {
        this.respVoEnum = respVoEnum;
    }
}

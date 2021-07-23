package com.example.demo.utils.Response;

public class RespVo {

    private long code;
    private String message;
    private Object obj;

    public RespVo(long code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return obj;
    }

    public static RespVo success(){
         return new RespVo(RespVoEnum.SUCCESS.getCode(),RespVoEnum.SUCCESS.getMessage(), null);
    }


    public static RespVo success(Object obj){
        return new RespVo(RespVoEnum.SUCCESS.getCode(),RespVoEnum.SUCCESS.getMessage(),obj);
    }

    public static RespVo error(RespVoEnum respVoEnum){
        return new RespVo(respVoEnum.getCode(), respVoEnum.getMessage(), null);
    }

    public static RespVo error(RespVoEnum respVoEnum,Object obj){
        return new RespVo(respVoEnum.getCode(), respVoEnum.getMessage(), obj);

    }
}

package com.example.demo.seckill.exception;


import com.example.demo.utils.Response.RespVo;
import com.example.demo.utils.Response.RespVoEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespVo ExcetionHandler(Exception e){

        if( e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return RespVo.error(ex.getRespVoEnum());
        }else if(e instanceof BindException){
            //绑定异常
            BindException ex = (BindException) e;
            RespVo respVo = RespVo.error(RespVoEnum.BIND_ERROR);
            respVo.setMessage("参数校验异常:"+ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respVo;
        }

        return RespVo.error(RespVoEnum.ERROR);
    }

}

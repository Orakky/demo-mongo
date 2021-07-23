package com.example.demo.seckill.validator;


import com.example.demo.seckill.common.ValidateUtil;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号码校验规则
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;





    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(required){
            //必填

        }else{
            //非必填
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return ValidateUtil.isMobile(value);
            }
        }

        return false;
    }

}

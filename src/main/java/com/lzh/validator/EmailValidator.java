package com.lzh.validator;

import com.lzh.validator.annotation.ValidEmail;
import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Author linzhihao
 * @Date 2022/10/26 6:18 下午
 * @Description
 */
public class EmailValidator implements ConstraintValidator<ValidEmail,String> {

    private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[_A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * 校验结果处理
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return validateEmail(s);
    }

    /**
     * 校验逻辑
     * @param email
     * @return
     */
    private boolean validateEmail(final String email){
        val pattern = Pattern.compile(EMAIL_PATTERN);
        val matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

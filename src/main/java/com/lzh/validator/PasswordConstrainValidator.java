package com.lzh.validator;

import com.lzh.validator.annotation.ValidPassword;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@RequiredArgsConstructor
public class PasswordConstrainValidator implements ConstraintValidator<ValidPassword,String> {

    private final SpringMessageResolver springMessageResolver;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        //给验证器配置消息解析器以及格式规则
        val validator = new PasswordValidator(springMessageResolver,Arrays.asList(
                //密码长度8-30
                new LengthRule(8,30),
                //要求至少含有1个大写英文字母
                new CharacterRule(EnglishCharacterData.UpperCase,1),
                //要求至少含有1个小写英文字母
                new CharacterRule(EnglishCharacterData.LowerCase,1),
                //要求至少含有1个特殊字符
                new CharacterRule(EnglishCharacterData.Special,1),
                //要求不能带有连续5个以上顺序字符
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical,5,false),
                //要求不能带有连续5个顺序数字
                new IllegalSequenceRule(EnglishSequenceData.Numerical,5,false),
                //要求不能带有连续5个键盘顺序字符 例如QWERT
                new IllegalSequenceRule(EnglishSequenceData.USQwerty,5,false),
                //要求不能带有空格字符
                new WhitespaceRule()
        ));
        val result = validator.validate(new PasswordData(password));
        //关闭默认的校验消息
        constraintValidatorContext.disableDefaultConstraintViolation();
        //把失败结果拼接，构建模板
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.join(",",validator.getMessages(result)))
                //添加自定义消息
                .addConstraintViolation();
        return result.isValid();
    }
}

package com.xxxx.seckill.vo;

import com.xxxx.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author QBX
 * @date 2022/2/26
 */
@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min=32)
    private String password;
}

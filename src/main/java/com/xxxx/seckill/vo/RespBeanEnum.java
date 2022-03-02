package com.xxxx.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 公共返回对象枚举
 * @author QBX
 * @date 2022/2/26
 */
@Getter
@ToString
@AllArgsConstructor
public enum  RespBeanEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登录模块
    LOGIN_ERROR(500210,"用户名或密码错误"),
    MOBILE_ERROR(500211,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数校验异常"),
    MOBILE_NOT_EXIST(500213,"手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214,"密码更新失败"),
    SESSION_ERROR(500215,"会话过期"),
    //秒杀模块5005xx
    EMPTY_STOCK(500500,"库存不足"),
    REPEATE_ERROR(500501,"每人限购一件"),
    REQUEST_ILLEGAL(500502,"请求非法"),
    ERROR_CAPTCHA(500503,"验证码错误"),
    ACCESS_LIMIT(500504,"访问过于频繁，请稍后再试"),
    //订单模块
    ORDER_NOT_EXIST(500301,"订单不存在");
    private final Integer code;
    private final String message;
}

package com.xxxx.seckill.config;

import com.xxxx.seckill.pojo.User;

/**
 * 专门存放线程的池子，存放私有信息,全局变量
 *
 * @author QBX
 * @date 2022/3/1
 */
public class UserContext {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}

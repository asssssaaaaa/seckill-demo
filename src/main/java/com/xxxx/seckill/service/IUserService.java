package com.xxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.vo.LoginVO;
import com.xxxx.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-02-26
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);


    /**
     * 根据cookie获取用户
     * @param userTicket
     * @return
     */
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);


    /**
     * 修改密码
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    public RespBean updatePassword(String userTicket,String password, HttpServletRequest request, HttpServletResponse response);
}

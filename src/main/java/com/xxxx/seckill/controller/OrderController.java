package com.xxxx.seckill.controller;


import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.service.IOrderService;
import com.xxxx.seckill.service.impl.OrderServiceImpl;
import com.xxxx.seckill.vo.OrderDetailVO;
import com.xxxx.seckill.vo.RespBean;
import com.xxxx.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-02-27
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 订单详情
     *
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user, Long orderId) {
        if (null == user) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVO detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }
}



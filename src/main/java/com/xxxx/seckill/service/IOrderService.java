package com.xxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.seckill.pojo.Order;
import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.vo.GoodsVO;
import com.xxxx.seckill.vo.OrderDetailVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-02-27
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVO goodsVO);

    OrderDetailVO detail(Long orderId);

    String createPath(User user, Long goodsId);

    boolean checkPath(User user, Long goodsId, String path);

    boolean checkCaptcha(User user, Long goodsId, String captcha);
}

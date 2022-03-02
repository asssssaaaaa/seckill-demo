package com.xxxx.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.seckill.exception.GlobalException;
import com.xxxx.seckill.mapper.OrderMapper;
import com.xxxx.seckill.pojo.*;
import com.xxxx.seckill.service.IGoodsService;
import com.xxxx.seckill.service.IOrderService;
import com.xxxx.seckill.service.ISeckillGoodsService;
import com.xxxx.seckill.service.ISeckillOrderService;
import com.xxxx.seckill.utils.MD5Util;
import com.xxxx.seckill.utils.UUIDUtil;
import com.xxxx.seckill.vo.GoodsVO;
import com.xxxx.seckill.vo.OrderDetailVO;
import com.xxxx.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-02-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 秒杀(解决库存超卖问题)
     *
     * @param user
     * @param goodsVO
     * @return
     */
    @Override
    public Order seckill(User user, GoodsVO goodsVO) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //获取秒杀的商品信息
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goodsVO.getId()));
        seckillGoodsService.update(new UpdateWrapper<SeckillGoods>()
                .setSql("stock_count = stock_count-1")
                .eq("goods_id", goodsVO.getId())
                .gt("stock_count", 0));
        if (seckillGoods.getStockCount() < 1) {
            //判断是否还有库存
            valueOperations.set("isStockEmpty:" + goodsVO.getId(), "0");
            return null;
        }
        //将商品信息赋值给秒杀订单信息
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVO.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goodsVO.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsVO.getSeckillPrice());
        //支付平台
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goodsVO.getId());
        seckillOrderService.save(seckillOrder);
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goodsVO.getId(), seckillOrder);
        return order;
    }

    @Override
    public OrderDetailVO detail(Long orderId) {
        if (null == orderId) {
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVO goodsVO = goodsService.findGoodsVOByGoodsId(order.getGoodsId());
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrder(order);
        orderDetailVO.setGoodsVO(goodsVO);
        return orderDetailVO;
    }

    /**
     * 获取秒杀地址
     *
     * @param user
     * @param goodsId
     * @return
     */
    @Override
    public String createPath(User user, Long goodsId) {
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        //短暂性的数据存在缓存中
        redisTemplate.opsForValue().set("seckillPath:" + user.getId() + ":" + goodsId, str, 60, TimeUnit.SECONDS);
        return str;
    }

    /**
     * 校验秒杀地址
     *
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    @Override
    public boolean checkPath(User user, Long goodsId, String path) {
        if (user == null || goodsId < 0 || path == null) {
            return false;
        }
        String redisPath = (String) redisTemplate.opsForValue().get("seckillPath:" + user.getId() + ":" + goodsId);
        return redisPath.equals(path);
    }

    /**
     * 校验验证码
     *
     * @param user
     * @param goodsId
     * @param captcha
     * @return
     */
    @Override
    public boolean checkCaptcha(User user, Long goodsId, String captcha) {
        if (goodsId < 0 || captcha == null) {
            return false;
        }
        String result = (String) redisTemplate.opsForValue().get("captcha:" + user.getId() + ":" + goodsId);
        return result.equals(captcha);
    }
}

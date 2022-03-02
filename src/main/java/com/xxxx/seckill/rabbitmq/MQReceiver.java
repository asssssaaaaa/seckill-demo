package com.xxxx.seckill.rabbitmq;

import com.rabbitmq.tools.json.JSONUtil;
import com.xxxx.seckill.pojo.SeckillMessage;
import com.xxxx.seckill.pojo.SeckillOrder;
import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.service.IGoodsService;
import com.xxxx.seckill.service.IOrderService;
import com.xxxx.seckill.utils.JsonUtil;
import com.xxxx.seckill.vo.GoodsVO;
import com.xxxx.seckill.vo.RespBean;
import com.xxxx.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author QBX
 * @date 2022/2/28
 */
@Slf4j
@Service
public class MQReceiver {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IOrderService orderService;

//    @RabbitListener(queues = "queue")
//    public void receive(Object msg) {
//        log.info("接收消息：" + msg);
//    }
//
//    //    @RabbitListener(queues = "queue_fanout01")
////    public void receive01(Object msg){
////        log.info("queue01接收消息："+ msg);
////    }
////
////    @RabbitListener(queues = "queue_fanout02")
////    public void receive02(Object msg){
////        log.info("queue02接收消息："+ msg);
////    }
//    @RabbitListener(queues = "queue_direct01")
//    public void receive01(Object msg) {
//        log.info("QUEUE01接受消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct02")
//    public void receive02(Object msg) {
//        log.info("QUEUE02接受消息：" + msg);
//    }
//
//    public void receive03(Object msg) {
//        log.info("QUEUE01接受消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic02")
//    public void receive04(Object msg) {
//        log.info("QUEUE02接受消息：" + msg);
//    }
//
//    public void receive05(Message message) {
//        log.info("QUEUE01接受Message对象：" + message);
//        log.info("QUEUE01接受消息：" + new String(message.getBody()));
//    }
//    @RabbitListener(queues = "queue_header02")
//    public void receive06(Message message) {
//        log.info("QUEUE02接受Message对象：" + message);
//        log.info("QUEUE02接受消息：" + new String(message.getBody()));
//    }


    @RabbitListener(queues = "seckillQueue")
    public void receive(Message message) {
        log.info("接收到的消息：" + message);
        //获取消息体
        byte[] body = message.getBody();
        String s = new String(body);
        //将json字符串转换为对象
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(s, SeckillMessage.class);
        Long goodId = seckillMessage.getGoodId();
        User user = seckillMessage.getUser();
        GoodsVO goodsVO = goodsService.findGoodsVOByGoodsId(goodId);
        if (goodsVO.getGoodsStock() < 1) {
            return;
        }
        //redis中存在数据，则表示重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodId);
        if (seckillOrder != null) {
            return;
        }
        //下单操作
        orderService.seckill(user, goodsVO);
    }

}

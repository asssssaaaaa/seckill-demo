package com.xxxx.seckill.controller;


import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.rabbitmq.MQSender;
import com.xxxx.seckill.vo.RespBean;
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
 * @since 2022-02-26
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MQSender mqSender;

    /**
     * @param user
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }

//    /**
//     * 消息发送者
//     */
//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq() {
//        mqSender.send("Hello");
//    }
//
//    /**
//     * fanout模式，发送消息到交换机上，所有与交换机绑定的都能接收到消息
//     */
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public void mq01() {
//        mqSender.send("Hello");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/direct01")
//    @ResponseBody
//    public void mq02() {
//        mqSender.send01("Hello,Red");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/direct02")
//    @ResponseBody
//    public void mq03() {
//        mqSender.send02("Hello,Green");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/topic01")
//    @ResponseBody
//    public void mq04() {
//        mqSender.send01("Hello,Red");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/topic02")
//    @ResponseBody
//    public void mq05() {
//        mqSender.send02("Hello,Green");
//    }
//
//    @RequestMapping("/mq/header01")
//    @ResponseBody
//    public void mq06() {
//        mqSender.send01("Hello,header01");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/header02")
//    @ResponseBody
//    public void mq07() {
//        mqSender.send02("Hello,header02");
//    }

}

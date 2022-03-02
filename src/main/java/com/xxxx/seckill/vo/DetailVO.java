package com.xxxx.seckill.vo;

import com.xxxx.seckill.pojo.Goods;
import com.xxxx.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 详情返回对象
 * @author QBX
 * @date 2022/2/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailVO {

    private User user;

    private GoodsVO goodsVO;

    private Integer seckillStatus;

    private Integer remainSecond;
}

package com.xxxx.seckill.vo;

import com.xxxx.seckill.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QBX
 * @date 2022/2/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO {
    private Order order;

    private GoodsVO goodsVO;
}

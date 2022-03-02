package com.xxxx.seckill.vo;

import com.xxxx.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author QBX
 * @date 2022/2/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO extends Goods {

    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}

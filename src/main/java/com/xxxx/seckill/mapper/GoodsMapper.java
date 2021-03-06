package com.xxxx.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.seckill.pojo.Goods;
import com.xxxx.seckill.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2022-02-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVO> findGoodsVO();

    GoodsVO findGoodsVOByGoodsId(Long goodsId);
}

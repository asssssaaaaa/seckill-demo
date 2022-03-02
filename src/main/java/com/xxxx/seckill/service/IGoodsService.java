package com.xxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.seckill.pojo.Goods;
import com.xxxx.seckill.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-02-27
 */
public interface IGoodsService extends IService<Goods> {

    public List<GoodsVO> findGoodsVO();


    public GoodsVO findGoodsVOByGoodsId(Long goodsId);
}

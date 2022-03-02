package com.xxxx.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.seckill.mapper.GoodsMapper;
import com.xxxx.seckill.pojo.Goods;
import com.xxxx.seckill.service.IGoodsService;
import com.xxxx.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-02-27
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public List<GoodsVO> findGoodsVO() {
        return goodsMapper.findGoodsVO();
    }

    @Override
    public GoodsVO findGoodsVOByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVOByGoodsId(goodsId);
    }
}

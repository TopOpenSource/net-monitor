package com.ruoyi.poor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.domain.Industry;
import com.ruoyi.poor.mapper.IndustryMapper;
import com.ruoyi.poor.service.IIndustryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IIndustryService {

    @Override
    public List<Map<String, Object>> groupByVillageId() {
        QueryWrapper<Industry> wrapper = new QueryWrapper<>();
        wrapper.select("village_id as villageId, COUNT(*) as count")
                .groupBy("village_id");
        return this.baseMapper.selectMaps(wrapper);
    }
}

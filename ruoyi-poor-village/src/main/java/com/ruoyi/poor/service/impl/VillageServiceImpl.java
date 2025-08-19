package com.ruoyi.poor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.convert.VillageConvert;
import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.VillageDto;
import com.ruoyi.poor.mapper.VillageMapper;
import com.ruoyi.poor.service.IIndustryService;
import com.ruoyi.poor.service.IVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VillageServiceImpl extends ServiceImpl<VillageMapper, Village> implements IVillageService {

    @Autowired
    private IIndustryService industryService;
    @Override
    public List<VillageDto> selectList(VillageDto dto) {
        QueryWrapper<Village> queryWrapper = new QueryWrapper<Village>();
        List<Village> villages = this.baseMapper.selectList(queryWrapper);
        List<VillageDto> villageDtoList = VillageConvert.INSTANCE.convert(villages);

        List<Map<String, Object>> maps = industryService.groupByVillageId();

        return villageDtoList;
    }
}

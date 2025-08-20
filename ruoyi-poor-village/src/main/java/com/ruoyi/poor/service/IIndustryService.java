package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Industry;
import com.ruoyi.poor.dto.IndustryDto;

import java.util.List;
import java.util.Map;

public interface IIndustryService extends IService<Industry> {
    /**
     * 根据村 分组统计
     * @return
     */
    public List<Map<String, Object>> groupByVillageId();

    IndustryDto getIndustryById(Long id);

    List<IndustryDto> selectList(IndustryDto dto);

    Long saveOrUpdateIndustry(IndustryDto dto);
}

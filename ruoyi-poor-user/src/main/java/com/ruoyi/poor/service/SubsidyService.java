package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyDto;

import java.util.List;
import java.util.Map;

public interface SubsidyService extends IService<Subsidy> {
    /**
     * 按年统计 各个类型的补助
     * @param dto
     */
    List<Subsidy> selSubsidyGroupType(SubsidyDto dto);

    /**
     * 统计各年 各个类型的补助
     * @param dto
     */
    List<SubsidyDto> selSubsidyGroupYearType(SubsidyDto dto);
}

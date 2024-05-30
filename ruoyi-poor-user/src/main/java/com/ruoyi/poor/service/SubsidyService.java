package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyAllYearDto;
import com.ruoyi.poor.dto.SubsidyAnalysisDto;
import com.ruoyi.poor.dto.SubsidyDto;

import java.io.OutputStream;
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
    SubsidyAllYearDto selSubsidyGroupYearType(SubsidyDto dto);

    List<SubsidyDto> selSubsidyList(SubsidyDto dto);

    /**
     * 统计分析
     * @param dto
     * @return
     */
    List<SubsidyAnalysisDto> selSubsidyAnalysis(SubsidyDto dto);

    /**
     * 统计分析-导出
     * @param dto
     */
    void analysisExport(SubsidyDto dto, OutputStream outputStream);
}

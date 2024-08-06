package com.ruoyi.poor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyAnalysisDto;
import com.ruoyi.poor.dto.SubsidyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubsidyMapper extends BaseMapper<Subsidy> {


    /**
     * 按照补助类型、时间 统计金额
     * @param dto
     * @return
     */
    List<SubsidyDto> selSubsidyGroupYearType(SubsidyDto dto);

    /**
     * 按照补助类型、时间 统计人数
     * @param dto
     * @return
     */
    List<SubsidyDto> selUserGroupYearType(SubsidyDto dto);

    /**
     * 按照计算最大最小的年
     * @param dto
     * @return
     */
    SubsidyDto getMaxMinYear(SubsidyDto dto);

    /**
     * 统计补助类型
     * @param dto
     * @return
     */
    List<SubsidyDto> getSubsidyTypes(SubsidyDto dto);

    List<SubsidyDto> selSubsidyList(SubsidyDto dto);

    List<SubsidyAnalysisDto> selSubsidyAnalysis(SubsidyDto dto);

    void delNoBind();

    /**
     * 查询各项补助的费用
     * @param cardId
     * @return
     */
    @Select("select t.subsidy_type,count(*) as count from poor_subsidy t " +
            "where t.card_id=#{cardId} " +
            "GROUP BY t.subsidy_type ")
    List<SubsidyDto> selCountBySubsidyType(@Param("cardId") String cardId);
}

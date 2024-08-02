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
    @Select(" SELECT" +
            " t.subsidy_type," +
            " SUM( t.money ) as money" +
            " FROM" +
            " poor_subsidy t " +
            " where t.card_id=#{cardId} and YEAR(t.subsidy_date)=#{year}" +
            " GROUP BY" +
            " t.subsidy_type")
    List<Subsidy> selSubsidyGroupType(SubsidyDto dto);

    @Select("SELECT " +
            " t.subsidy_type, " +
            " YEAR(t.subsidy_date) as year, " +
            " SUM( t.money ) as money " +
            "FROM " +
            " poor_subsidy t  " +
            " where t.card_id=#{cardId} " +
            "GROUP BY " +
            " t.subsidy_type, " +
            " YEAR(t.subsidy_date) " +
            "order by subsidy_type")
    List<SubsidyDto> selSubsidyGroupYearType(SubsidyDto dto);

    @Select("SELECT" +
            "  max(YEAR(t.subsidy_date)) as maxYear, " +
            " min(YEAR(t.subsidy_date)) as minYear " +
            " FROM" +
            " poor_subsidy t " +
            " where t.card_id=#{cardId}")
    SubsidyDto getMaxMinYear(SubsidyDto dto);


    @Select("select DISTINCT(t1.subsidy_type) as subsidy_type,t2.dict_label as subsidyTypeCN  " +
            "from poor_subsidy t1 left join sys_dict_data t2 on t1.subsidy_type=t2.dict_value " +
            "where t2.dict_type='subsidy_type'  and t1.card_id=#{cardId}")
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

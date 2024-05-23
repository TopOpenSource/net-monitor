package com.ruoyi.poor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyDto;
import org.apache.ibatis.annotations.Mapper;
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
}

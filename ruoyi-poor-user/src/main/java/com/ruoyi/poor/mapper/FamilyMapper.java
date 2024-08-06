package com.ruoyi.poor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.dto.FamilyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyMapper extends BaseMapper<Family> {
    List<FamilyDto> selectFamilyList(FamilyDto family);

    FamilyDto selectFamilyById(@Param("id") Long id);

    /**
     * 统计每个村的贫困户数量
     * @param family
     * @return
     */
    List<FamilyDto> selUserCountGroupByVillage(FamilyDto family);
}

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

    List<String> selectFamilys(@Param("masterCardId") String masterCardId,@Param("familyId") Long familyId);
}

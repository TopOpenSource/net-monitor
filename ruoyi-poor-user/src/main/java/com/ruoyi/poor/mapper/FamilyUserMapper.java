package com.ruoyi.poor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyUserMapper extends BaseMapper<FamilyUser> {
    List<UserDto> selectFamilyUserList(@Param("familyId") Long familyId);
}

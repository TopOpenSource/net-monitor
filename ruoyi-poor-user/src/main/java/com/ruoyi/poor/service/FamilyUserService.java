package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.dto.FamilyUserDto;
import com.ruoyi.poor.dto.UserDto;

import java.util.List;

public interface FamilyUserService extends IService<FamilyUser> {
    List<UserDto> selectFamilyUserList(Long familyId);

    void saveOrUpdateFamilyUser(FamilyUserDto dto);

    void deleteFamilyUser(FamilyUserDto dto);
}

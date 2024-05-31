package com.ruoyi.poor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.dto.FamilyUserDto;
import com.ruoyi.poor.dto.UserDto;
import com.ruoyi.poor.mapper.FamilyUserMapper;
import com.ruoyi.poor.service.FamilyUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class FamilyUserServiceImpl extends ServiceImpl<FamilyUserMapper, FamilyUser> implements FamilyUserService {

    @Override
    public List<UserDto> selectFamilyUserList(Long familyId) {
        return this.baseMapper.selectFamilyUserList(familyId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateFamilyUser(FamilyUserDto dto) {
        //删除原有的关系
        QueryWrapper<FamilyUser> wrapper = new QueryWrapper<>();
        wrapper.eq("family_id", dto.getFamilyId());
        wrapper.eq("card_id", dto.getCardId());
        this.baseMapper.delete(wrapper);

        FamilyUser familyUser = dto.parseToFamliyUser();
        this.baseMapper.insert(familyUser);
    }

    @Override
    public void deleteFamilyUser(FamilyUserDto dto) {
        QueryWrapper<FamilyUser> wrapper = new QueryWrapper<>();
        wrapper.eq("family_id", dto.getFamilyId());
        wrapper.eq("card_id", dto.getCardId());
        this.baseMapper.delete(wrapper);
    }
}

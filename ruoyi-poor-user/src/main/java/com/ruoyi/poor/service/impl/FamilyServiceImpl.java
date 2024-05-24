package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.mapper.FamilyMapper;
import com.ruoyi.poor.service.FamilyService;
import com.ruoyi.poor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {

    @Autowired
    private UserService userService;

    @Override
    public FamilyDto selectFamilyById(Long id) {
        FamilyDto dto=this.baseMapper.selectFamilyById(id);
        dto.setCardIds(this.baseMapper.selectFamilys(dto.getMasterCardId(),id));
        return dto;
    }

    @Override
    public List<FamilyDto> selectFamilyList(FamilyDto dto) {
        return this.baseMapper.selectFamilyList(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateFamily(FamilyDto dto) {
        Long familyId=dto.getId();

        Family family = new Family();
        if(dto.getId()==null){
            familyId=IdUtil.getSnowflakeNextId();
            family.setId(familyId);
        }

        //设置user外键
        List<String> cardIds = dto.getCardIds();
        cardIds.add(dto.getMasterCardId());

        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("family_id",familyId);
        updateWrapper.in("card_id",cardIds);
        userService.update(updateWrapper);

        family.setMasterCardId(dto.getMasterCardId());
        family.setAddress(dto.getAddress());
        family.setVillage(dto.getVillage());
        family.setFamilyCount(cardIds.size());
        this.saveOrUpdate(family);
    }
}

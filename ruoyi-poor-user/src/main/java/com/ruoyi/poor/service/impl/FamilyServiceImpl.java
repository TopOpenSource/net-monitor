package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.dto.FamilyUserDto;
import com.ruoyi.poor.mapper.FamilyMapper;
import com.ruoyi.poor.service.FamilyService;
import com.ruoyi.poor.service.FamilyUserService;
import com.ruoyi.poor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {

    @Autowired
    private UserService userService;

    @Autowired
    private FamilyUserService familyUserService;

    @Override
    public FamilyDto selectFamilyById(Long id) {
        FamilyDto dto = this.baseMapper.selectFamilyById(id);
        return dto;
    }

    @Override
    public List<FamilyDto> selectFamilyList(FamilyDto dto) {
        return this.baseMapper.selectFamilyList(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateFamily(FamilyDto dto) {
        Long familyId = dto.getId();
        if (dto.getId() == null) {
            familyId = IdUtil.getSnowflakeNextId();

        }

        Family family = new Family();
        family.setId(familyId);
        family.setFamilyNo(dto.getFamilyNo());
        family.setMasterCardId(dto.getMasterCardId());
        family.setAddress(dto.getAddress());
        family.setVillage(dto.getVillage());
        this.saveOrUpdate(family);


        //删除原户主
        QueryWrapper<FamilyUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("family_id", familyId);
        queryWrapper.eq("relation_type", "0");
        familyUserService.remove(queryWrapper);

        //插入户主
        FamilyUser master = new FamilyUser();
        master.setId(IdUtil.getSnowflakeNextId());
        master.setFamilyId(familyId);
        master.setCardId(dto.getMasterCardId());
        master.setRelationType("0");
        familyUserService.save(master);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importData(MultipartFile file) throws IOException {
        List<FamilyUserDto> dataList = EasyExcel.read(file.getInputStream()).head(FamilyUserDto.class).headRowNumber(1).sheet(0).doReadSync();

        for (FamilyUserDto familyDto : dataList) {
            if(StringUtils.isNotEmpty(familyDto.getMasterCardId()) && StringUtils.isNotEmpty(familyDto.getCardId())){
                //户主身份证号
                String masterCardId = familyDto.getMasterCardId();
                //成员身份证号
                String cardId = familyDto.getCardId();
                //与户主关系
                String relationType = familyDto.getRelationType();


                QueryWrapper<Family> familyQueryWrapper = new QueryWrapper<>();
                familyQueryWrapper.select("id");
                familyQueryWrapper.eq("master_card_id", masterCardId);
                Family family = this.baseMapper.selectOne(familyQueryWrapper);

                //新增家庭
                if (family == null) {
                    family = new Family();
                    family.setId(IdUtil.getSnowflakeNextId());
                    family.setMasterCardId(masterCardId);
                    this.saveOrUpdate(family);
                }

                //新增或保存
                familyDto.setFamilyId(family.getId());
                familyUserService.saveOrUpdateFamilyUser(familyDto);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByFamilyId(Long familyId) {
        //删除家庭
        this.removeById(familyId);

        //删除家庭成员
        QueryWrapper<FamilyUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("family_id", familyId);
        familyUserService.remove(userQueryWrapper);
    }
}

package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.dto.FamilyImportDto;
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
        master.setFamilyNo(family.getFamilyNo());
        master.setCardId(dto.getMasterCardId());
        master.setRelationType("0");
        familyUserService.save(master);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importData(MultipartFile file) throws IOException {
        List<FamilyImportDto> dataList = EasyExcel.read(file.getInputStream()).head(FamilyImportDto.class).headRowNumber(1).sheet(0).doReadSync();

        for (FamilyImportDto familyDto : dataList) {
            if(StringUtils.isNotEmpty(familyDto.getFamilyNo()) && StringUtils.isNotEmpty(familyDto.getCardId())){
                //户主身份证号
                String familyNo = familyDto.getFamilyNo();

                //新增家庭
                QueryWrapper<Family> familyQueryWrapper = new QueryWrapper<>();
                familyQueryWrapper.select("id");
                familyQueryWrapper.eq("family_no", familyNo);
                Family family = this.baseMapper.selectOne(familyQueryWrapper);

                if (family == null) {
                    family = new Family();
                    family.setId(IdUtil.getSnowflakeNextId());
                    family.setFamilyNo(familyNo);
                }
                family.setVillage(familyDto.getVillage());
                //户主
                if(familyDto.getRelationType().equals("0")){
                    family.setMasterCardId(familyDto.getCardId());
                }
                this.saveOrUpdate(family);


                //插入关联关系
                FamilyUserDto  familyUserDto = new FamilyUserDto();
                familyUserDto.setFamilyId(family.getId());
                familyUserDto.setFamilyNo(familyDto.getFamilyNo());
                familyUserDto.setCardId(familyDto.getCardId());
                familyUserDto.setRelationType(familyDto.getRelationType());
                familyUserService.saveOrUpdateFamilyUser(familyUserDto);

                //若用户信息为空则 插入用户信息
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("card_id", familyDto.getCardId());
                long count = userService.count(queryWrapper);
                if (count < 1) {
                   User user=new User();
                   user.setCardId(familyDto.getCardId());
                   user.setId(IdUtil.getSnowflakeNextId());
                   user.setName(familyDto.getName());
                   user.setDisability("0");
                   userService.saveOrUpdateUser(user);
                }

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

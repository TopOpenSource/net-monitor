package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.dto.FamilyDto;

import java.util.List;

public interface FamilyService extends IService<Family> {

    FamilyDto selectFamilyById(Long id);

    List<FamilyDto> selectFamilyList(FamilyDto dto);

    void saveOrUpdateFamily(FamilyDto dto);
}

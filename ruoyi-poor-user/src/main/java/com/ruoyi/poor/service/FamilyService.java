package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.dto.FamilyDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FamilyService extends IService<Family> {

    FamilyDto selectFamilyById(Long id);

    List<FamilyDto> selectFamilyList(FamilyDto dto);

    void saveOrUpdateFamily(FamilyDto dto);

    void importData(MultipartFile file) throws IOException;

    void removeByFamilyId(Long familyId);
}

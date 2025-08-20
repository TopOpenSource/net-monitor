package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.VillageDto;

import java.util.List;

public interface IVillageService extends IService<Village> {

    List<VillageDto> selectList(VillageDto dto);

    Long saveOrUpdateVillage(VillageDto domain);
    void removeVillageById(Long id);

    VillageDto getVillageById(Long id);
}

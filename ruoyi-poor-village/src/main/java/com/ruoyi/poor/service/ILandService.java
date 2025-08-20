package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Land;
import com.ruoyi.poor.dto.LandDto;

public interface ILandService extends IService<Land> {
    LandDto getLandByVillageId(Long villageId);

    Long saveOrUpdateLand(LandDto domain);
}

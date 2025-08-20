package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Build;
import com.ruoyi.poor.dto.BuildDto;

import java.util.List;
import java.util.Map;

public interface IBuildService extends IService<Build> {
    List<BuildDto> selectList(BuildDto dto);

    BuildDto getBuildById(Long id);

    Long saveOrUpdateBuild(BuildDto dto);

    List<Map<String, Object>> groupByVillageId();
}

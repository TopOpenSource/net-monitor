package com.ruoyi.poor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.poor.domain.Build;
import com.ruoyi.poor.domain.Industry;
import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.BuildDto;
import com.ruoyi.poor.dto.VillageDto;
import com.ruoyi.poor.service.IBuildService;
import com.ruoyi.poor.service.ILandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poor/build")
public class BuildControoler extends BaseController {
    @Autowired
    private IBuildService buildService;



    @GetMapping("info/{id}")
    public BuildDto list(@PathVariable("id") Long id) {
        return buildService.getBuildById(id);
    }

    @GetMapping("/list")
    public TableDataInfo list(BuildDto dto) {
        startPage();
        List<BuildDto> list = buildService.selectList(dto);
        return getDataTable(list);
    }

    @PostMapping("saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody BuildDto dto) {
        Long id=buildService.saveOrUpdateBuild(dto);
        return AjaxResult.success(id);
    }

}

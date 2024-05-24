package com.ruoyi.poor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poor/family")
public class FamilyController extends BaseController {
    @Autowired
    private FamilyService familyService;


    @GetMapping("info/{id}")
    public FamilyDto list(@PathVariable("id") Long id) {
        return familyService.selectFamilyById(id);
    }

    @GetMapping("/list")
    public TableDataInfo list(FamilyDto dto) {
        startPage();
        List<FamilyDto> familyDtos = familyService.selectFamilyList(dto);
        return getDataTable(familyDtos);
    }


    @PostMapping("saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody FamilyDto domain) {
        familyService.saveOrUpdateFamily(domain);
        return AjaxResult.success(0);
    }

    @DeleteMapping("del/{id}")
    public AjaxResult del(@PathVariable("id") Long id) {
        familyService.removeById(id);
        return AjaxResult.success(0);
    }


}

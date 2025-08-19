package com.ruoyi.poor.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.dto.VillageDto;
import com.ruoyi.poor.service.IVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poor/village")
public class VillageController extends BaseController {

    @Autowired
    private IVillageService villageService;

    @GetMapping("info/{id}")
    public Village list(@PathVariable("id") Long id) {
        return villageService.getById(id);
    }

//    @GetMapping("/list")
//    public TableDataInfo list(VillageDto dto) {
//        startPage();
//        List<FamilyDto> familyDtos = villageService.selectList(dto);
//        return getDataTable(familyDtos);
//    }
//
//
//    @PostMapping("saveOrUpdate")
//    public AjaxResult saveOrUpdate(@RequestBody FamilyDto domain) {
//        familyService.saveOrUpdateFamily(domain);
//        return AjaxResult.success(0);
//    }
//
//    @DeleteMapping("del/{id}")
//    public AjaxResult del(@PathVariable("id") Long id) {
//        familyService.removeByFamilyId(id);
//        return AjaxResult.success(0);
//    }
}

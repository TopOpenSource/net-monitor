package com.ruoyi.poor.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.poor.domain.Village;
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
    public VillageDto list(@PathVariable("id") Long id) {
        return villageService.getVillageById(id);
    }

    @GetMapping("/list")
    public TableDataInfo list(VillageDto dto) {
        startPage();
        List<VillageDto> list = villageService.selectList(dto);
        return getDataTable(list);
    }


    @PostMapping("saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody VillageDto dto) {
        Long id=villageService.saveOrUpdateVillage(dto);
        return AjaxResult.success(id);
    }

    @DeleteMapping("del/{id}")
    public AjaxResult del(@PathVariable("id") Long id) {
        villageService.removeVillageById(id);
        return AjaxResult.success(0);
    }
}

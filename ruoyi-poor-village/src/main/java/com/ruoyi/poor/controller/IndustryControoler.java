package com.ruoyi.poor.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.poor.dto.BuildDto;
import com.ruoyi.poor.dto.IndustryDto;
import com.ruoyi.poor.service.IBuildService;
import com.ruoyi.poor.service.IIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poor/industry")
public class IndustryControoler extends BaseController {
    @Autowired
    private IIndustryService industryService;

    @GetMapping("info/{id}")
    public IndustryDto list(@PathVariable("id") Long id) {
        return industryService.getIndustryById(id);
    }

    @GetMapping("/list")
    public TableDataInfo list(IndustryDto dto) {
        startPage();
        List<IndustryDto> list = industryService.selectList(dto);
        return getDataTable(list);
    }

    @PostMapping("saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody IndustryDto dto) {
        Long id=industryService.saveOrUpdateIndustry(dto);
        return AjaxResult.success(id);
    }

}

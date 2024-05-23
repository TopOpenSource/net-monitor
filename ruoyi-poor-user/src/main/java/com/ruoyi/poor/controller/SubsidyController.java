package com.ruoyi.poor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.dto.SubsidyDto;
import com.ruoyi.poor.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/poor/subsidy")
public class SubsidyController extends BaseController {
    @Autowired
    private SubsidyService subsidyService;

    @PostMapping("list")
    public List<Subsidy> saveAndImport(@RequestBody SubsidyDto dto) {
        QueryWrapper<Subsidy> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getCardId())){
            queryWrapper.eq("card_id",dto.getCardId());
        }

        if(StringUtils.isNotEmpty(dto.getSubsidyType())){
            queryWrapper.eq("subsidy_type",dto.getSubsidyType());
        }

        //查询某一年
        if(dto.getYear()!=null){
            queryWrapper.eq("YEAR(subsidy_date)",dto.getYear());
        }
        return subsidyService.list(queryWrapper);
    }


    @PostMapping("selGroupType")
    public List<Subsidy> selSubsidyGroupType(@RequestBody SubsidyDto dto) {
        return subsidyService.selSubsidyGroupType(dto);
    }

    @PostMapping("selGroupYearType")
    public List<SubsidyDto> selSubsidyGroupYearType(@RequestBody SubsidyDto dto) {
        return subsidyService.selSubsidyGroupYearType(dto);
    }



}

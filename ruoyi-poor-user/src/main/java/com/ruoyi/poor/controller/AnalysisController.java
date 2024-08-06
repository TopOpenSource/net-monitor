package com.ruoyi.poor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.dto.SubsidyAllYearDto;
import com.ruoyi.poor.dto.SubsidyAnalysisDto;
import com.ruoyi.poor.dto.SubsidyDto;
import com.ruoyi.poor.mapper.FamilyMapper;
import com.ruoyi.poor.service.AnalysisService;
import com.ruoyi.poor.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/poor/analysis")
public class AnalysisController extends BaseController {
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private SubsidyService subsidyService;
    @Autowired
    private FamilyMapper familyMapper;

    /**
     * 统计每项补助的每年的金额
     */
    @GetMapping("selSubsidyGroupYearType")
    public SubsidyAllYearDto selSubsidyList() {
        return subsidyService.selSubsidyGroupYearType(null);
    }

    /**
     * 统计每项补助的每年的人数
     */
    @GetMapping("selUserCountGroupYearType")
    public SubsidyAllYearDto selUserCountGroupYearType() {
        return subsidyService.selUserCountGroupYearType(null);
    }


    /**
     * 统计每个村的贫困户数量
     */
    @GetMapping("selUserCountGroupByVillage")
    public List<FamilyDto> selUserCountGroupByVillage() {
        return  familyMapper.selUserCountGroupByVillage(null);
    }
}

package com.ruoyi.poor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.dto.FamilyDto;
import com.ruoyi.poor.dto.SubsidyAllYearDto;
import com.ruoyi.poor.dto.SubsidyAnalysisDto;
import com.ruoyi.poor.dto.SubsidyDto;
import com.ruoyi.poor.service.SubsidyService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/poor/subsidy")
public class SubsidyController extends BaseController {
    @Autowired
    private SubsidyService subsidyService;

    @PostMapping("list")
    public List<Subsidy> list(@RequestBody SubsidyDto dto) {
        QueryWrapper<Subsidy> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dto.getCardId())) {
            queryWrapper.eq("card_id", dto.getCardId());
        }

        if (StringUtils.isNotEmpty(dto.getSubsidyType())) {
            queryWrapper.eq("subsidy_type", dto.getSubsidyType());
        }

        //查询某一年
        if (dto.getYear() != null) {
            queryWrapper.eq("YEAR(subsidy_date)", dto.getYear());
        }

        queryWrapper.orderByDesc("subsidy_type","subsidy_date");
        return subsidyService.list(queryWrapper);
    }



    @PostMapping("selGroupYearType")
    public SubsidyAllYearDto selSubsidyGroupYearType(@RequestBody SubsidyDto dto) {
        return subsidyService.selSubsidyGroupYearType(dto);
    }

    /**
     * 统计分析
     *
     * @param dto
     * @return
     */
    @PostMapping("selSubsidyAnalysis")
    public List<SubsidyAnalysisDto> selSubsidyAnalysis(@RequestBody SubsidyDto dto) {
        return subsidyService.selSubsidyAnalysis(dto);
    }

    @PostMapping("analysisExport")
    public void analysisExport(SubsidyDto dto, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, "统计清单.xlsx");
        subsidyService.analysisExport(dto, response.getOutputStream());
        //FileUtils.writeBytes(RuoYiConfig.getUploadPath() + path, response.getOutputStream());
    }


    /**
     * 查询数据列表--原始数据
     *
     * @param dto
     * @return
     */

    @GetMapping("selSubsidyList")
    public TableDataInfo selSubsidyList(SubsidyDto dto) {
        startPage();
        List<SubsidyDto> results = subsidyService.selSubsidyList(dto);
        return getDataTable(results);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("del/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        subsidyService.removeBatchByIds(Arrays.asList(ids));
        return AjaxResult.success(0);
    }


    @DeleteMapping("delNoBind")
    public AjaxResult delNoBind() {
        subsidyService.delNoBind();
        return AjaxResult.success(0);
    }


    /**
     * 更新
     *
     * @param subsidy
     * @return
     */
    @PostMapping("update")
    public AjaxResult update(@RequestBody Subsidy subsidy) {
        subsidyService.saveOrUpdate(subsidy);
        return AjaxResult.success(0);
    }

    @GetMapping("info/{id}")
    public Subsidy get(@PathVariable Long id) {
        return subsidyService.getById(id);
    }

    /**
     * 查询各项补助的费用
     */
    @GetMapping("selCountBySubsidyType/{cardId}")
    public List<SubsidyDto> selCountBySubsidyType(@PathVariable String cardId) {
        return subsidyService.selCountBySubsidyType(cardId);
    }
}

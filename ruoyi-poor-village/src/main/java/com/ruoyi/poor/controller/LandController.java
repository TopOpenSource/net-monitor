package com.ruoyi.poor.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.poor.domain.Land;
import com.ruoyi.poor.dto.LandDto;
import com.ruoyi.poor.service.ILandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poor/land")
public class LandController extends BaseController {

    @Autowired
     private ILandService landService;

    @GetMapping("info/{villageId}")
    public LandDto getLandByVillageId(@PathVariable("villageId") Long villageId) {
        return landService.getLandByVillageId(villageId);
    }

    @PostMapping("saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody LandDto domain) {
        Long id=landService.saveOrUpdateLand(domain);
        return AjaxResult.success(id);
    }
}

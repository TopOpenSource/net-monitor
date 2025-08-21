package com.ruoyi.net.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poor/village")
public class NetPackageController extends BaseController {

//    @Autowired
//    private IVillageService villageService;
//
//    @GetMapping("info/{id}")
//    public VillageDto list(@PathVariable("id") Long id) {
//        return villageService.getVillageById(id);
//    }
//
//    @GetMapping("/list")
//    public TableDataInfo list(VillageDto dto) {
//        startPage();
//        List<VillageDto> list = villageService.selectList(dto);
//        return getDataTable(list);
//    }
//
//
//    @PostMapping("saveOrUpdate")
//    public AjaxResult saveOrUpdate(@RequestBody VillageDto dto) {
//        Long id=villageService.saveOrUpdateVillage(dto);
//        return AjaxResult.success(id);
//    }
//
//    @DeleteMapping("del/{id}")
//    public AjaxResult del(@PathVariable("id") Long id) {
//        villageService.removeVillageById(id);
//        return AjaxResult.success(0);
//    }
}

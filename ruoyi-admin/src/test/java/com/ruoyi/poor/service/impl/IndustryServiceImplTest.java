package com.ruoyi.poor.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.poor.dto.VillageDto;
import com.ruoyi.poor.service.IIndustryService;
import com.ruoyi.poor.service.IVillageService;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
public class IndustryServiceImplTest {

    @Autowired
    private IIndustryService industryService;

    @Autowired
    private IVillageService villageService;

    @Test
    public void groupByVillageId() {
        List<Map<String, Object>> maps = industryService.groupByVillageId();
        System.out.println(JSON.toJSONString(maps));

    }

    @Test
    public void testMapstruct() {
        List<VillageDto> villageDtos = villageService.selectList(null);
        System.out.println(JSON.toJSONString(villageDtos));
    }

    @Test
    public void convert(){

    }
}
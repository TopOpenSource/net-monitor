package com.sdstc.village;

import com.ruoyi.poor.convert.VillageConvert;
import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.VillageDto;

import java.util.ArrayList;
import java.util.List;

public class MapstructTest {
    public static void main(String[] args) {
        Village village1 = new Village();
        village1.setAdmin("admin1");


        Village village2 = new Village();
        village2.setAdmin("admin2");

        List<Village> villageList = new ArrayList<>();
        villageList.add(village1);
        villageList.add(village2);

        List<VillageDto> convert = VillageConvert.INSTANCE.convert2ListDto(villageList);
        System.out.println(convert);
    }
}

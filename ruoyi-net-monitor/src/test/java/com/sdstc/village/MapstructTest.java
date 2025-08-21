package com.sdstc.village;

import com.ruoyi.net.convert.NetPackageConvert;
import com.ruoyi.net.domain.NetPackage;
import com.ruoyi.net.dto.NetPackageDto;

import java.util.ArrayList;
import java.util.List;

public class MapstructTest {
    public static void main(String[] args) {
        NetPackage netPackage1 = new NetPackage();
        netPackage1.setAdmin("admin1");


        NetPackage netPackage2 = new NetPackage();
        netPackage2.setAdmin("admin2");

        List<NetPackage> netPackageList = new ArrayList<>();
        netPackageList.add(netPackage1);
        netPackageList.add(netPackage2);

        List<NetPackageDto> convert = NetPackageConvert.INSTANCE.convert2ListDto(netPackageList);
        System.out.println(convert);
    }
}

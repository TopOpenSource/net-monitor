package com.ruoyi.net.convert;

import com.ruoyi.net.domain.NetPackage;
import com.ruoyi.net.dto.NetPackageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NetPackageConvert {
    NetPackageConvert INSTANCE = Mappers.getMapper(NetPackageConvert.class);


     NetPackageDto convert2Dto(NetPackage bean);

     List<NetPackageDto> convert2ListDto(List<NetPackage> bean);

     NetPackage convert2Entity(NetPackageDto bean);

     List<NetPackage> convert2Entity(List<NetPackageDto> bean);
}

package com.ruoyi.poor.convert;

import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.VillageDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VillageConvert {
    VillageConvert INSTANCE = Mappers.getMapper(VillageConvert.class);


     VillageDto convert2Dto(Village bean);

     List<VillageDto> convert2ListDto(List<Village> bean);

     Village convert2Entity(VillageDto bean);

     List<Village> convert2Entity(List<VillageDto> bean);
}

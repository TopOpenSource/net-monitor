package com.ruoyi.poor.convert;

import com.ruoyi.poor.domain.Village;
import com.ruoyi.poor.dto.VillageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VillageConvert {
    VillageConvert INSTANCE = Mappers.getMapper(VillageConvert.class);
    Village convert(VillageDto bean);
    VillageDto convert(Village bean);

    List<VillageDto> convert(List<Village> bean);
}

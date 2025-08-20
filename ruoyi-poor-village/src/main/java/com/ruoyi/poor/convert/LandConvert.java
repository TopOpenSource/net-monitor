package com.ruoyi.poor.convert;

import com.ruoyi.poor.domain.Build;
import com.ruoyi.poor.domain.Land;
import com.ruoyi.poor.dto.BuildDto;
import com.ruoyi.poor.dto.LandDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LandConvert {
    LandConvert INSTANCE = Mappers.getMapper(LandConvert.class);

     LandDto convert2Dto(Land bean);

     List<LandDto> convert2ListDto(List<Land> bean);

     Land convert2Entity(LandDto bean);

     List<Land> convert2ListEntity(List<LandDto> bean);
}

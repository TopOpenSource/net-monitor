package com.ruoyi.poor.convert;

import com.ruoyi.poor.domain.Build;
import com.ruoyi.poor.dto.BuildDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BuildConvert {
    BuildConvert INSTANCE = Mappers.getMapper(BuildConvert.class);

     BuildDto convert2Dto(Build bean);

     List<BuildDto> convert2ListDto(List<Build> bean);

     Build convert2Entity(BuildDto bean);

     List<Build> convert2ListEntity(List<Build> bean);
}

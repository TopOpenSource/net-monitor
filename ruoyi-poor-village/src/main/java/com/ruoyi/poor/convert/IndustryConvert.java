package com.ruoyi.poor.convert;

import com.ruoyi.poor.domain.Industry;
import com.ruoyi.poor.dto.IndustryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IndustryConvert {
    IndustryConvert INSTANCE = Mappers.getMapper(IndustryConvert.class);

     IndustryDto convert2Dto(Industry bean);

     List<IndustryDto> convert2ListDto(List<Industry> bean);

     Industry convert2Entity(IndustryDto bean);
}

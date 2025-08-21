package com.ruoyi.net.convert;

import com.ruoyi.net.domain.NetIpLocation;
import com.ruoyi.net.dto.NetIpLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NetIpLocationConvert {
    NetIpLocationConvert INSTANCE = Mappers.getMapper(NetIpLocationConvert.class);


     NetIpLocationDto convert2Dto(NetIpLocation bean);

     List<NetIpLocationDto> convert2ListDto(List<NetIpLocation> bean);

     NetIpLocation convert2Entity(NetIpLocationDto bean);

     List<NetIpLocation> convert2Entity(List<NetIpLocationDto> bean);
}

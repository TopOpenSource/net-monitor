package com.ruoyi.net.convert;

import com.ruoyi.net.domain.NetDns;
import com.ruoyi.net.dto.NetDnsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NetDnsConvert {
    NetDnsConvert INSTANCE = Mappers.getMapper(NetDnsConvert.class);


     NetDnsDto convert2Dto(NetDns bean);

     List<NetDnsDto> convert2ListDto(List<NetDns> bean);

     NetDns convert2Entity(NetDnsDto bean);

     List<NetDns> convert2Entity(List<NetDnsDto> bean);
}

package com.ruoyi.net.convert;

import com.ruoyi.net.domain.NetFlow;
import com.ruoyi.net.dto.NetFlowDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NetFlowConvert {
    NetFlowConvert INSTANCE = Mappers.getMapper(NetFlowConvert.class);


     NetFlowDto convert2Dto(NetFlow bean);

     List<NetFlowDto> convert2ListDto(List<NetFlow> bean);

     NetFlow convert2Entity(NetFlowDto bean);

     List<NetFlow> convert2Entity(List<NetFlowDto> bean);
}

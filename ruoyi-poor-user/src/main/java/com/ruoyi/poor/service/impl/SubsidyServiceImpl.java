package com.ruoyi.poor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyDto;
import com.ruoyi.poor.mapper.SubsidyMapper;
import com.ruoyi.poor.service.SubsidyService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubsidyServiceImpl extends ServiceImpl<SubsidyMapper, Subsidy> implements SubsidyService {
    @Autowired
    private ISysDictTypeService dictTypeService;

    @Override
    public List<Subsidy> selSubsidyGroupType(SubsidyDto dto) {
        return this.baseMapper.selSubsidyGroupType(dto);
    }

    @Override
    public List<SubsidyDto> selSubsidyGroupYearType(SubsidyDto dto) {
        List<SysDictData> subsidyTypeList = dictTypeService.selectDictDataByType("subsidy_type");
        //获取年份跨度
        SubsidyDto maxMinYear = this.baseMapper.getMaxMinYear(dto);
        //获取类型-年份-补贴
        List<SubsidyDto> subsidyDtoList= this.baseMapper.selSubsidyGroupYearType(dto);

        /**
         * 数据处理
         */
        List<SubsidyDto> typeDtoList=new ArrayList<>();
        //遍历 类型
        subsidyTypeList.forEach(type -> {
            SubsidyDto typeDto=new SubsidyDto();
            typeDto.setSubsidyType(type.getDictValue());
            typeDto.setSubsidyTypeCN(type.getDictLabel());

            List<SubsidyDto> yearDtoList=new ArrayList<>();
            //遍历年份 没有的设为0
            for(Integer i=maxMinYear.getMaxYear();i>=maxMinYear.getMinYear();i--){
                SubsidyDto yearDto=new SubsidyDto();
                yearDto.setYear(i);
                yearDto.setMoney(new BigDecimal(0));
                //过滤数据
                List<SubsidyDto> data = subsidyDtoList.stream().filter(subsidyDto -> {
                    return (subsidyDto.getYear().equals(yearDto.getYear()) ) && (subsidyDto.getSubsidyType().equals(typeDto.getSubsidyType()));
                }).collect(Collectors.toList());

                if(data.size()>0){
                    yearDto.setMoney(data.get(0).getMoney());
                }
                yearDtoList.add(yearDto);
            }

            typeDto.setYearDataList(yearDtoList);
            typeDtoList.add(typeDto);
        });



        return typeDtoList;
    }
}

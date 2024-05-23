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
        //类型
        List<SubsidyDto> subsidyTypes = this.baseMapper.getSubsidyTypes(dto);
        //获取年份跨度
        SubsidyDto maxMinYear = this.baseMapper.getMaxMinYear(dto);

        //获取类型-年份-补贴
        List<SubsidyDto> subsidyDtoList= this.baseMapper.selSubsidyGroupYearType(dto);

        /**
         * 类型数据处理
         */
        List<SubsidyDto> typeDtoList=new ArrayList<>();
        //遍历 类型
        subsidyTypes.forEach(type -> {
            SubsidyDto typeDto=new SubsidyDto();
            typeDto.setSubsidyType(type.getSubsidyType());
            typeDto.setSubsidyTypeCN(type.getSubsidyTypeCN());

            List<BigDecimal> moneyDataList=new ArrayList<>();
            //遍历年份 没有的设为0
            for(Integer i=maxMinYear.getMaxYear();i>=maxMinYear.getMinYear();i--){
                BigDecimal money=new BigDecimal(0);
                //过滤数据
                List<SubsidyDto> data = subsidyDtoList.stream().filter(subsidyDto -> {
                    return (subsidyDto.getYear().equals(i) ) && (subsidyDto.getSubsidyType().equals(typeDto.getSubsidyType()));
                }).collect(Collectors.toList());

                if(data.size()>0){
                    money=data.get(0).getMoney();
                }
                moneyDataList.add(money);
            }
            typeDto.setMoneyDataList(moneyDataList);
            typeDtoList.add(typeDto);
        });

        return typeDtoList;
    }
}

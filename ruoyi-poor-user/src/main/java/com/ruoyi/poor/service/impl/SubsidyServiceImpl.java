package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyAllYearDto;
import com.ruoyi.poor.dto.SubsidyDto;
import com.ruoyi.poor.mapper.SubsidyMapper;
import com.ruoyi.poor.service.SubsidyService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
    public SubsidyAllYearDto selSubsidyGroupYearType(SubsidyDto dto) {
        //类型
        List<SubsidyDto> subsidyTypes = this.baseMapper.getSubsidyTypes(dto);

        //无数据则返回空
        if (subsidyTypes.size() <1) {
            return null;
        }


        //获取年份跨度
        SubsidyDto maxMinYear = this.baseMapper.getMaxMinYear(dto);

        //获取类型-年份-补贴
        List<SubsidyDto> subsidyDtoList= this.baseMapper.selSubsidyGroupYearType(dto);



        int[] years=NumberUtil.range(maxMinYear.getMinYear(), maxMinYear.getMaxYear());
        SubsidyAllYearDto subsidyAllYearDto = new SubsidyAllYearDto();
        subsidyAllYearDto.setYears(years);

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
            for(int i=0;i<years.length;i++){
                BigDecimal money=new BigDecimal(0);
                //过滤数据
                int finalI=years[i];
                List<SubsidyDto> data = subsidyDtoList.stream().filter(subsidyDto -> {
                    return (subsidyDto.getYear().intValue()==finalI) && (subsidyDto.getSubsidyType().equals(typeDto.getSubsidyType()));
                }).collect(Collectors.toList());

                if(data.size()>0){
                    money=data.get(0).getMoney();
                }
                moneyDataList.add(money);
            }
            typeDto.setMoneyDataList(moneyDataList);
            typeDtoList.add(typeDto);
        });

        subsidyAllYearDto.setSubsidyDtos(typeDtoList);
        return subsidyAllYearDto;
    }

    @Override
    public List<SubsidyDto> selSubsidyList(SubsidyDto dto) {
        return this.baseMapper.selSubsidyList(dto);
    }
}

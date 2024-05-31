package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.dto.SubsidyAllYearDto;
import com.ruoyi.poor.dto.SubsidyAnalysisDto;
import com.ruoyi.poor.dto.SubsidyDto;
import com.ruoyi.poor.mapper.SubsidyMapper;
import com.ruoyi.poor.service.SubsidyService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
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

    @Override
    public List<SubsidyAnalysisDto> selSubsidyAnalysis(SubsidyDto dto) {
        List<SubsidyAnalysisDto> subsidyAnalysisDto = this.baseMapper.selSubsidyAnalysis(dto);
        return subsidyAnalysisDto;
    }

    @Override
    public void analysisExport(SubsidyDto dto, OutputStream outputStream) {
        //读取字典--补贴类型
        Map<String,String> dictDataMap=new LinkedHashMap<>();
        List<SysDictData> sysDictDataList = dictTypeService.selectDictDataByType("subsidy_type");
        sysDictDataList.forEach(sysDictData -> {
            dictDataMap.put(sysDictData.getDictValue(),sysDictData.getDictLabel());
        });
        //与户主关系
        Map<String,String> relationTypeMap=new LinkedHashMap<>();
        List<SysDictData> relationTypeList = dictTypeService.selectDictDataByType("relation_type");
        relationTypeList.forEach(sysDictData -> {
            relationTypeMap.put(sysDictData.getDictValue(),sysDictData.getDictLabel());
        });

        //数据处理
        List<SubsidyAnalysisDto> subsidyAnalysisDto = this.baseMapper.selSubsidyAnalysis(dto);

        EasyExcel.write(outputStream)
                // 这里放入动态头
                .head(head(sysDictDataList))
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("sheet1")
                .doWrite(dataParse(subsidyAnalysisDto,dictDataMap,relationTypeMap));
    }

    // 数据转换
    private List<List<String>> dataParse(List<SubsidyAnalysisDto> dataList,Map<String,String> dictDataMap,Map<String,String> relationTypeMap){
        List<List<String>> contentList = Lists.newArrayList();

        for(SubsidyAnalysisDto data:dataList){
            List<String> dataFormat=new ArrayList<>(Arrays.asList(data.getFamilyNo(),data.getUserName(), data.getCardId(),relationTypeMap.get(data.getRelationType()),data.getSubsidyDate()));
            for(String key:dictDataMap.keySet()){
                dataFormat.add(this.getDictLabel(data,key).toString());
            }
            contentList.add(dataFormat);
        }
        return contentList;
    }

    private BigDecimal getDictLabel(SubsidyAnalysisDto data,String dictType){
        for (SubsidyDto dto:data.getSubsidyList()){
            if(dto.getSubsidyType().equals(dictType)){
                return dto.getMoney();
            }
        }
        return new BigDecimal(0);
    }


    //获取头部
    private static List <List<String>> head(List<SysDictData> sysDictDataList){
        List<List<String>> headTitles = Lists.newArrayList();
        headTitles.add(Arrays.asList("户编号"));
        headTitles.add(Arrays.asList("姓名"));
        headTitles.add(Arrays.asList("身份证号"));
        headTitles.add(Arrays.asList("与户主关系"));
        headTitles.add(Arrays.asList("日期"));


        for(SysDictData sysDictData:sysDictDataList){
            headTitles.add(Arrays.asList(sysDictData.getDictLabel()));
        }

        return headTitles;
    }

}

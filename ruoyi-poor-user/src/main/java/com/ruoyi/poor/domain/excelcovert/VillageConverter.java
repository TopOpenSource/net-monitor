package com.ruoyi.poor.domain.excelcovert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.service.ISysDictTypeService;

import java.util.List;

public class VillageConverter implements Converter<String> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String value = "0";
        String str = cellData.getStringValue();


        ISysDictTypeService dictTypeService = SpringUtils.getBean("sysDictTypeService");
        List<SysDictData> sysDictDataList = dictTypeService.selectDictDataByType("village");

        for (SysDictData sysDictData : sysDictDataList) {
            if(str.equals(sysDictData.getDictLabel())){
                return sysDictData.getDictValue();
            }
        }

        return value;
    }

    @Override
    public WriteCellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                            GlobalConfiguration globalConfiguration) {
        String str = "";

        ISysDictTypeService dictTypeService = SpringUtils.getBean("sysDictTypeService");
        List<SysDictData> sysDictDataList = dictTypeService.selectDictDataByType("village");

        for (SysDictData sysDictData : sysDictDataList) {
            if(value.equals(sysDictData.getDictValue())){
                return new WriteCellData(sysDictData.getDictLabel());
            }
        }
        return new WriteCellData(str);
    }
}

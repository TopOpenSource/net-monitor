package com.ruoyi.poor.domain.excelcovert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

@SuppressWarnings("rawtypes")
public class YesOrNoConverter implements Converter<String> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        String value = "0";
        String str = cellData.getStringValue();
        if ("是".equals(str)) {
            value = "1";
        } else if ("否".equals(str)) {
            value = "0";
        }
        return value;
    }

    @Override
    public WriteCellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                            GlobalConfiguration globalConfiguration) {
        String str = "";
        if ("1" == value) {
            str = "是";
        } else if ("0" == value) {
            str = "否";
        }
        return new WriteCellData(str);
    }
}

package com.ruoyi.poor.dto;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringStringConverter;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.domain.excelcovert.RelationTypeConverter;
import com.ruoyi.poor.domain.excelcovert.VillageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyImportDto {

    @ExcelProperty(value = "户编号",converter = StringStringConverter.class)
    private String familyNo;

    //成员编号
    @ExcelProperty(value = "身份证号",converter = StringStringConverter.class)
    private String cardId;

    //关系类型
    @ExcelProperty(value = "与户主关系",converter = RelationTypeConverter.class)
    private String relationType;


    @ExcelProperty(value = "行政村",converter = VillageConverter.class)
    private String village;

    @ExcelProperty(value = "姓名",converter = StringStringConverter.class)
    private String name;

}

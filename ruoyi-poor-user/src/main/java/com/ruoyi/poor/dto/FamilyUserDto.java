package com.ruoyi.poor.dto;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringStringConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.poor.domain.FamilyUser;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.domain.excelcovert.RelationTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyUserDto {

    @ExcelProperty(value = "户主身份证号",converter = StringStringConverter.class)
    private String masterCardId;

    //户号
    private Long familyId;

    private String familyNo;

    //成员编号
    @ExcelProperty(value = "家庭成员身份证号",converter = StringStringConverter.class)
    private String cardId;

    //关系类型
    @ExcelProperty(value = "与户主关系",converter = RelationTypeConverter.class)
    private String relationType;

    public FamilyUser parseToFamliyUser(){
        FamilyUser familyUser = new FamilyUser();
        familyUser.setFamilyId(this.familyId);
        familyUser.setFamilyNo(this.familyNo);
        familyUser.setCardId(this.cardId);
        familyUser.setRelationType(this.relationType);
        familyUser.setId(IdUtil.getSnowflakeNextId());
        return familyUser;
    }
}

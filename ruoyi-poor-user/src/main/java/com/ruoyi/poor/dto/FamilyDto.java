package com.ruoyi.poor.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringStringConverter;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.poor.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyDto{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ExcelProperty(value = "户主身份证号",converter = StringStringConverter.class)
    private String masterCardId;

    //户号
    private String familyNo;

    private User master;

    private String address;



    /**
     * 村庄
     */
    private String village;

    /**
     * 户主姓名
     */
    private String masterName;

    /**
     * 家人数量
     */
    private Integer familyCount;

    @ExcelProperty(value = "成员身份证号",converter = StringStringConverter.class)
    private String cardId;
}

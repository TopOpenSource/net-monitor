package com.ruoyi.poor.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.poor.domain.excelcovert.SexConverter;
import com.ruoyi.poor.domain.excelcovert.YesOrNoConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poor_user")
public class User extends BaseEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("family_id")
    private Long familyId;

    @ExcelProperty(value = "身份证号")
    @TableField("card_id")
    private String cardId;

    @ExcelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ExcelProperty(value = "性别", converter = SexConverter.class)
    @TableField("sex")
    private String sex;

    @ExcelProperty(value = "出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 是否残疾
     */
    @ExcelProperty(value = "是否残疾", converter = YesOrNoConverter.class)
    @TableField("disability")
    private String disability;

    /**
     * 是否在世
     */
    @ExcelProperty(value = "是否在世", converter = YesOrNoConverter.class)
    @TableField("live")
    private String live;
}

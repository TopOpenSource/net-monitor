package com.ruoyi.poor.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poor_subsidy")
public class Subsidy extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;


    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("data_file_id")
    private Long dataFileId;


    @ExcelProperty(value  = "身份证号")
    @TableField("card_id")
    private String cardId;

    /**
     * 金额
     */
    @ExcelProperty(value  = "金额")
    @TableField("money")
    private BigDecimal money;

    /**
     * 月份
     */
    @TableField("year_month")
    private LocalDate yearMonth;

    /**
     * 补贴类型
     */
    @TableField("subsidy_type")
    private String subsidyType;
}

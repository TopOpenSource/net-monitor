package com.ruoyi.poor.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsidyDto {
    private String cardId;
    private String subsidyType;
    private String subsidyTypeCN;
    private Integer year;
    private BigDecimal money;
    /**
     * 根据类型分组
     */
    private List<SubsidyDto> yearDataList;

    private List<BigDecimal> moneyDataList;
    private List<Long> yearDataIdList;

    private Integer minYear;
    private Integer maxYear;
}

package com.ruoyi.poor.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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



    private List<BigDecimal> moneyDataList;

    private Integer minYear;
    private Integer maxYear;



    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dataFileId;
    /**
     * 月份
     */
    @JsonFormat(pattern = "yyyy-MM")
    private Date subsidyDate;

    private String userName;

    private String fileName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long fileId;


    //数据分析模块使用
    private Integer unit;
    @JsonFormat(pattern = "yyyy-MM")
    private List<Date> dateRange;

    /**
     * 无绑定，查询出没有绑定人员的数据
     */
    private Integer noName;
}

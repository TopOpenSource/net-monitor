package com.ruoyi.poor.domain;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poor_family")
public class Family extends BaseEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    /**
     * 名称
     */
    @ExcelProperty(value = "户主")
    @TableField("family_name")
    private String familyName;

    /**
     * 户主
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("master_id")
    private Long masterId;

    @ExcelProperty(value = "地址")
    @TableField("address")
    private String address;

    /**
     * 村庄
     */
    @ExcelProperty(value = "村庄")
    @TableField("village")
    private String village;
}

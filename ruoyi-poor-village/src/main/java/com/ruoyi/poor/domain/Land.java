package com.ruoyi.poor.domain;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poor_village_land")
public class Land extends BaseEntity {
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    /**
     * 农业/陆地面积（单位：亩、平方米等，需统一）
     */
    @TableField("area_land")
    private BigDecimal areaLand;

    /**
     * 林地面积
     */
    @TableField("area_forest")
    private BigDecimal areaForest;

    /**
     * 房屋建筑面积
     */
    @TableField("area_house")
    private BigDecimal areaHouse;

    /**
     * 道路面积
     */
    @TableField("area_road")
    private BigDecimal areaRoad;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 所属村庄ID
     */
    @TableField("village_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long villageId;
}

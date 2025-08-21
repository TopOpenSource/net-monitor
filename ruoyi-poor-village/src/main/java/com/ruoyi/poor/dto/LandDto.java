package com.ruoyi.poor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandDto {
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 农业/陆地面积（单位：亩、平方米等，需统一）
     */
    private BigDecimal areaLand;

    /**
     * 林地面积
     */
    private BigDecimal areaForest;

    /**
     * 房屋建筑面积
     */
    private BigDecimal areaHouse;

    /**
     * 道路面积
     */
    private BigDecimal areaRoad;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属村庄ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long villageId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> imageIds;
}

package com.ruoyi.poor.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VillageDto extends BaseDto{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 村庄名称
     */
    private String name;

    /**
     * 村民数量
     */
    private Integer villagerCount;

    /**
     * 家庭/户数
     */
    private Integer familyCount;

    /**
     * 管理员/负责人
     */
    private String admin;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 企业数量
     */
    private Long industryCount;

    private List<Long> imageIds;
}

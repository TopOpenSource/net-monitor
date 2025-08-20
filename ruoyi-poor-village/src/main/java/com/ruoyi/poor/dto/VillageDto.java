package com.ruoyi.poor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VillageDto {
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
}

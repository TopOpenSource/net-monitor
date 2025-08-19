package com.ruoyi.poor.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("poor_village")
public class Village extends BaseEntity {
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 村庄名称
     */
    @TableField("name")
    private String name;

    /**
     * 村民数量
     */
    @TableField("villager_count")
    private Integer villagerCount;

    /**
     * 家庭/户数
     */
    @TableField("family_count")
    private Integer familyCount;

    /**
     * 管理员/负责人
     */
    @TableField("admin")
    private String admin;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;
}

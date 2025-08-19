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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poor_village_industry")
public class Industry extends BaseEntity {
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;



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

    /**
     * 所属行业
     */
    @TableField("industry")
    private String industry;

    /**
     * 员工数量
     */
    @TableField("employee_count")
    private Integer employeeCount;

    /**
     * 成立日期
     */
    @TableField("founded_date")
    private java.time.LocalDate foundedDate;

    /**
     * 状态（如：正常、停业、注销等）
     */
    @TableField("state")
    private String state;

    /**
     * 所属村庄ID
     */
    @TableField("village_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long villageId;
}

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
@TableName("poor_family_user")
public class FamilyUser extends BaseEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("family_id")
    private Long familyId;

    /**
     * 身份证号
     */
    @TableField("card_id")
    private String cardId;

    /**
     * 户主关系
     */
    @TableField("relation_type")
    private String relationType;

}

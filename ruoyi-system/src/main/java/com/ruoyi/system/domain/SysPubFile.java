package com.ruoyi.system.domain;


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
@TableName("sys_file")
public class SysPubFile extends BaseEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    @TableField("path")
    private String path;

    @TableField("type")
    private String type;

    @TableField("size")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long size;

    @TableField("ref_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long refId;

    @TableField("ref_type")
    private String refType;
}

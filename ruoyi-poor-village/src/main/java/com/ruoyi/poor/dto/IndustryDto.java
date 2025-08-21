package com.ruoyi.poor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndustryDto {
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 名称
     */
    private String name;



    /**
     * 管理员/负责人
     */
    private String admin;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 员工数量
     */
    private Integer employeeCount;

    /**
     * 成立日期
     */
    private java.time.LocalDate foundedDate;

    /**
     * 状态（如：正常、停业、注销等）
     */
    private String state;

    /**
     * 所属村庄ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long villageId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> imageIds;
}

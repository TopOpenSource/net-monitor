package com.ruoyi.poor.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poor_data_file")
public class DataFile extends BaseEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    @TableField("file_name")
    private String fileName;

    @TableField("file_path")
    private String filePath;

    /**
     * 补贴类型
     */
    @TableField("subsidy_type")
    private String subsidyType;

    /**
     * 数据量
     */
    @TableField("data_count")
    private Integer dataCount;

    /**
     * sheet
     */
    @TableField("sheet_name")
    private String sheetName;

    @TableField("title_index")
    private Integer titleIndex;

    @TableField("data_index")
    private Integer dataIndex;


    @TableField("subsidy_date")
    @JsonFormat(pattern = "yyyy-MM")
    private Date subsidyDate;
}

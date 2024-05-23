package com.ruoyi.poor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 数据文件
 */

@Data
@NoArgsConstructor
public class DataFileDto {
    private String filePath;
    private List<String> sheetNames;

    public DataFileDto(String filePath, List<String> sheetNames) {
        this.filePath = filePath;
        this.sheetNames = sheetNames;
    }


}

package com.ruoyi.poor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 补助统计--统计每年的 每个类型的数据
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsidyAllYearDto {
    /**
     * 年
     */
    private List<Long> yearDataIdList;

    /**
     * 类型
     */
    private List<SubsidyDto> subsidyDtos;
}

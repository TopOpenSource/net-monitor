package com.ruoyi.poor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsidyAnalysisDto {
    private String familyNo;
    private String relationType;
    private String subsidyDate;
    private String userName;
    private String userId;
    private String cardId;
    //private Map<String, BigDecimal> typeMap;
    private List<SubsidyDto> subsidyList;
}

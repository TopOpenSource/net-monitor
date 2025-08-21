package com.ruoyi.net.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetIpLocationDto extends BaseDto{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * IP地址（IPv4或IPv6）
     */
    private String ip;

    /**
     * 所属大洲（如 Asia）
     */
    private String continent;

    /**
     * 大洲代码（如 AS）
     */
    private String continentCode;

    /**
     * 国家名称（如 China）
     */
    private String country;

    /**
     * 国家代码（如 CN）
     */
    private String countryCode;

    /**
     * 城市名称（如 Beijing）
     */
    private String city;

    /**
     * 纬度（decimal，如 39.9042）
     */
    private BigDecimal lat;

    /**
     * 经度（decimal，如 116.4074）
     */
    private BigDecimal lon;
}

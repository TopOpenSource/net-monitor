package com.ruoyi.net.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("monitor_ip_location")
public class NetIpLocation extends BaseEntity {
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    /**
     * IP地址（IPv4或IPv6）
     */
    @TableField("ip")
    private String ip;

    /**
     * 所属大洲（如 Asia）
     */
    @TableField("continent")
    private String continent;

    /**
     * 大洲代码（如 AS）
     */
    @TableField("continentCode")
    private String continentCode;

    /**
     * 国家名称（如 China）
     */
    @TableField("country")
    private String country;

    /**
     * 国家代码（如 CN）
     */
    @TableField("countryCode")
    private String countryCode;

    /**
     * 城市名称（如 Beijing）
     */
    @TableField("city")
    private String city;

    /**
     * 纬度（decimal，如 39.9042）
     */
    @TableField("lat")
    private BigDecimal lat;

    /**
     * 经度（decimal，如 116.4074）
     */
    @TableField("lon")
    private BigDecimal lon;


}

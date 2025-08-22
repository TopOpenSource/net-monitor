package com.ruoyi.net.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "monitor_ip_location")
public class NetIpLocation{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Field("id")
    private Long id;

    /**
     * IP地址（IPv4或IPv6）
     */
    @Field("ip")
    private String ip;

    /**
     * 所属大洲（如 Asia）
     */
    @Field("continent")
    private String continent;

    /**
     * 大洲代码（如 AS）
     */
    @Field("continentCode")
    private String continentCode;

    /**
     * 国家名称（如 China）
     */
    @Field("country")
    private String country;

    /**
     * 国家代码（如 CN）
     */
    @Field("countryCode")
    private String countryCode;

    /**
     * 城市名称（如 Beijing）
     */
    @Field("city")
    private String city;

    /**
     * 纬度（decimal，如 39.9042）
     */
    @Field("lat")
    private BigDecimal lat;

    /**
     * 经度（decimal，如 116.4074）
     */
    @Field("lon")
    private BigDecimal lon;


}

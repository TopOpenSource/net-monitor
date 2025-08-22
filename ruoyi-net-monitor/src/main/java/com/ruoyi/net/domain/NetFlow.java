package com.ruoyi.net.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "monitor_flow")
public class NetFlow extends BaseEntity{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Field("id")
    private Long id;


    /**
     * 源IP地址
     */
    @Field("source_ip")
    private String sourceIp;

    @Field("source_port")
    private Integer sourcePort;
    /**
     * 目标IP地址
     */
    @Field("target_ip")
    private String targetIp;

    @Field("target_port")
    private Integer targetPort;

    /**
     * 协议类型 (TCP, UDP, ICMP, etc.)
     */
    @Field("protocol")
    private String protocol;

    /**
     * 数据包发送时间
     */
    @Field("send_time")
    private Date sendTime;

    /**
     * 数据包长度（字节）
     */
    @Field("length")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long length;

    /**
     * 原始数据包ID（可关联PCAP解析记录）
     */
    @Field("package_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long packageId;
}

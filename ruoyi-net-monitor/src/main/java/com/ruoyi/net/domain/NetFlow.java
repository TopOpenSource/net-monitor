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

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("monitor_flow")
public class NetFlow extends BaseEntity{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;


    /**
     * 源IP地址
     */
    @TableField("source_ip")
    private String sourceIp;

    /**
     * 目标IP地址
     */
    @TableField("target_ip")
    private String targetIp;

    /**
     * 协议类型 (TCP, UDP, ICMP, etc.)
     */
    @TableField("protocol")
    private String protocol;

    /**
     * 数据包发送时间
     */
    @TableField("send_time")
    private Date sendTime;

    /**
     * 数据包长度（字节）
     */
    @TableField("length")
    private Double length;

    /**
     * 原始数据包ID（可关联PCAP解析记录）
     */
    @TableField("package_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long packageId;
}

package com.ruoyi.net.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetFlowDto extends BaseDto{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;


    /**
     * 源IP地址
     */
    private String sourceIp;

    /**
     * 目标IP地址
     */
    private String targetIp;

    /**
     * 协议类型 (TCP, UDP, ICMP, etc.)
     */
    private String protocol;

    /**
     * 数据包发送时间
     */
    private Date sendTime;

    /**
     * 数据包长度（字节）
     */
    private Double length;

    /**
     * 原始数据包ID（可关联PCAP解析记录）
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long packageId;
}

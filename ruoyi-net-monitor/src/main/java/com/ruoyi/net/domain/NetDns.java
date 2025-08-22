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
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "monitor_dns")
public class NetDns{
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Field("id")
    private Long id;

    @Field("ip")
    private String ip;

    @Field("url")
    private String url;

    @Field("name")
    private String name;
}

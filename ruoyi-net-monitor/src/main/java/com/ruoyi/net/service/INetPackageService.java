package com.ruoyi.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.net.domain.NetPackage;

public interface INetPackageService extends IService<NetPackage> {
    Integer analyzePcap(Long packageId) throws Exception;
}

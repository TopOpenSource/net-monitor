package com.ruoyi.net.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.net.domain.NetFlow;
import com.ruoyi.net.domain.NetPackage;
import com.ruoyi.net.mapper.NetPackageMapper;
import com.ruoyi.net.service.INetFlowService;
import com.ruoyi.net.service.INetPackageService;
import com.ruoyi.net.utils.PcapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetPackageServiceImpl extends ServiceImpl<NetPackageMapper, NetPackage> implements INetPackageService {

    @Value("${ruoyi.tshark}")
    private String tshake;

    @Autowired
    private INetFlowService netFlowService;

    @Override
    public Integer analyzePcap(Long packageId) throws Exception {
        List<NetFlow> netFlows = PcapUtil.analyzePcap(tshake, "D:/sss.pcapng", packageId);
        netFlowService.saveBatch(netFlows);
        return 0;
    }
}

package com.ruoyi.net.service.impl;

import com.ruoyi.net.domain.NetFlow;
import com.ruoyi.net.repository.INetFlowRepository;
import com.ruoyi.net.service.INetFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetFlowServiceImpl implements INetFlowService {

    @Autowired
    private INetFlowRepository nfRepository;

    @Override
    public void saveBatch(List<NetFlow> netFlows) {
        nfRepository.saveAll(netFlows);
    }
}

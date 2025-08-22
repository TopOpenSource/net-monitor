package com.ruoyi.net.service;

import com.ruoyi.net.domain.NetFlow;

import java.util.List;

public interface INetFlowService {
    void saveBatch(List<NetFlow> netFlows);
}

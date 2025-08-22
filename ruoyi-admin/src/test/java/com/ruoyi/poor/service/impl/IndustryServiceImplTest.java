package com.ruoyi.poor.service.impl;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.net.domain.NetFlow;
import com.ruoyi.net.repository.INetFlowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
public class IndustryServiceImplTest {

    @Autowired
    private INetFlowRepository inetFlowRepository;

    @Test
    public void test(){
        NetFlow netFlow = new NetFlow();
        netFlow.setId(1L);
        netFlow.setTargetPort(345);
        inetFlowRepository.insert(netFlow);
    }

}
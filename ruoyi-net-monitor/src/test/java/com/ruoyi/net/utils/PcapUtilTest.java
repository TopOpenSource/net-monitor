package com.ruoyi.net.utils;

import com.ruoyi.net.domain.NetFlow;

import java.util.List;

public class PcapUtilTest {

    public static void main(String[] args) throws Exception {
      List<NetFlow> flowList=  PcapUtil.analyzePcap("D:\\develop\\WiresharkPortable64\\App\\Wireshark\\tshark.exe","D:/sss.pcapng");

      System.out.println(flowList);
    }
}
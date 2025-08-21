package com.ruoyi.net.utils;

import com.ruoyi.net.domain.NetFlow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcapUtil {

    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");

    public static List<NetFlow> analyzePcap(String tsharkPath,String pcapPath) throws Exception {
        if (IS_WINDOWS) {
            return analyzePcapWindows(tsharkPath,pcapPath);
        } else {
            return analyzePcapLinux(pcapPath);
        }
    }


    /**
     * windows 执行 tshark
     * @param pcapPath
     * @return
     * @throws Exception
     */
    public static List<NetFlow> analyzePcapWindows(String tsharkPath,String pcapPath) throws Exception {
        List<NetFlow> packets = new ArrayList<>();

        ProcessBuilder pb = new ProcessBuilder(
                tsharkPath,
                "-r", pcapPath,
                "-T", "fields",
                "-e", "frame.time_epoch",
                "-e", "ip.src",
                "-e", "tcp.srcport",
                "-e", "udp.srcport",
                "-e", "ip.dst",
                "-e", "tcp.dstport",
                "-e", "udp.dstport",
                "-e", "frame.protocols",
                "-e", "frame.len",
                "-E", "separator=\t",
                "-E", "header=y",
                "-E", "quote=n"
        );

        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                NetFlow packet = parseLine(line);
                if (packet != null) {
                    packets.add(packet);
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("tshark 执行失败，退出码: " + exitCode);
        }
        return packets;
    }


    /**
     * linux执行
     * @param pcapPath
     * @return
     * @throws Exception
     */
    public static List<NetFlow> analyzePcapLinux(String pcapPath) throws Exception {
        List<NetFlow> packets = new ArrayList<>();

        ProcessBuilder pb = new ProcessBuilder(
                "tshark",
                "-r", pcapPath,
                "-T", "fields",
                "-e", "frame.time_epoch",  // 新增：时间戳
                "-e", "ip.src",
                "-e", "tcp.srcport",
                "-e", "udp.srcport",
                "-e", "ip.dst",
                "-e", "tcp.dstport",
                "-e", "udp.dstport",
                "-e", "frame.protocols",
                "-e", "frame.len",
                "-E", "separator=\t",
                "-E", "header=y",
                "-E", "quote=n"
        );

        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                NetFlow packet = parseLine(line);
                if (packet != null) {
                    packets.add(packet);
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("tshark 执行失败，退出码: " + exitCode);
        }

        return packets;
    }

    private static  NetFlow parseLine(String line) {
        String[] fields = line.split("\t", -1);
        if (fields.length < 9) return null; // 原8个 + 时间 = 9

        NetFlow packet = new NetFlow();

        String timeEpoch = safeGet(fields, 0);
        if (timeEpoch != null) {
            try {
                double epochSeconds = Double.parseDouble(timeEpoch);
                long millis = (long) (epochSeconds * 1000); // 转为毫秒
                packet.setSendTime(new Date(millis));
            } catch (Exception e) {
                packet.setSendTime(new Date()); // 解析失败则用当前时间
            }
        } else {
            packet.setSendTime(new Date());
        }

        // 源IP
        packet.setSourceIp(safeGet(fields, 1));
        // 源端口
        Integer srcPort = safeGetPort(fields, 2, 3);
        packet.setSourcePort(srcPort);
        // 目的IP
        packet.setTargetIp(safeGet(fields, 4));
        // 目的端口
        Integer dstPort = safeGetPort(fields, 5, 6);
        packet.setTargetPort(dstPort);
        // 协议
        String protocols = safeGet(fields, 7);
        if (protocols != null && !protocols.isEmpty()) {
            String[] protoArray = protocols.split(":");
            packet.setProtocol(protoArray[protoArray.length - 1]);
        }
        // 长度
        try {
            packet.setLength(Long.parseLong(safeGet(fields, 8)));
        } catch (Exception e) {
            packet.setLength(0L);
        }

        return packet;
    }

    private static String safeGet(String[] arr, int index) {
        if (index >= arr.length || arr[index] == null || arr[index].trim().isEmpty()) {
            return null;
        }
        return arr[index].trim();


    }

    private static Integer safeGetPort(String[] fields, int tcpIndex, int udpIndex) {
        String tcp = safeGet(fields, tcpIndex);
        String udp = safeGet(fields, udpIndex);
        if (tcp != null) return Integer.parseInt(tcp);
        if (udp != null) return Integer.parseInt(udp);
        return null;
    }
}

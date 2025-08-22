package com.ruoyi.net.utils;

import net.cz88.czdb.DbSearcher;
import net.cz88.czdb.QueryType;

import java.io.File;
import java.io.IOException;

public class QQWryUtilTest {
    public static void main(String[] args) throws Exception {
        DbSearcher searcher = new DbSearcher("D://qqwry.dat", QueryType.MEMORY, "0577f27d-3164-3d84-b31a-9f7bfa7d86ef");

        searcher.close();
    }

}
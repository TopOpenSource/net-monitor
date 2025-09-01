package com.ruoyi.net.utils;

import net.cz88.czdb.DbSearcher;
import net.cz88.czdb.QueryType;

import java.io.File;
import java.io.IOException;

public class QQWryUtilTest {
    public static void main(String[] args) throws Exception {
        DbSearcher searcher = new DbSearcher("D://czdb//cz88_public_v4.czdb", QueryType.MEMORY, "Oma6VnnftYo+1vjaMjMtbw==");
        String search = searcher.search("13.35.37.84");
        System.out.println(search);
        searcher.close();
    }

}
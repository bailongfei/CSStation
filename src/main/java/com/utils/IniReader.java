package com.utils;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取ini工具
 */
public class IniReader {

    static Map<String, String> readIniGetDBInfo() {
        try {
            Map<String, String> dbMap = new HashMap<>();
            File file = new File("dbConfig.ini");
            if (!file.exists() && !file.isDirectory()) {
                dbMap = null;
            } else {
                Ini.Section section = new Ini(file).get("section1");
                dbMap.put("driver", section.get("driver"));
                dbMap.put("url", section.get("url"));
                dbMap.put("userName", section.get("userName"));
                dbMap.put("password", section.get("password"));
            }
            return dbMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readIniGetSrvGroupId() {
        String srvGroupIds = null;
        try {
            File file = new File("dbConfig.ini");
            if (!file.exists() && !file.isDirectory()) {
                srvGroupIds = "-1";
            } else {
                Ini.Section section = new Ini(file).get("section2");
                srvGroupIds = section.get("srvGroup_Id");
            }
            return srvGroupIds;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] readIniNoLetter(){
        String[] srvGroupIdArr = null;
        try {
            File file = new File("dbConfig.ini");
            if (!file.exists() && !file.isDirectory()) {
                return null;
            } else {
                Ini.Section section = new Ini(file).get("section2");
                String srvGroupIds = section.get("noLetter");
                srvGroupIdArr = srvGroupIds.split(",");
            }
            return srvGroupIdArr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

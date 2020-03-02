package com.himoyi.img_api.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
    private static Properties p = new Properties();
    static {
        InputStream in = null;
        try {
            in = PropertiesUtils.class.getResourceAsStream("/main.properties");
        } catch (Exception e) {
            throw new RuntimeException("主配置文件不存在！");
        }
        try {
            p.load(in);
        } catch (IOException e) {
            throw new RuntimeException("主配置文件加载失败！");
        }
    }

    private static String host = p.getProperty("Host");
    private static String name = p.getProperty("Name");
    private static String user = p.getProperty("User");
    private static String password = p.getProperty("Password");
    private static String Address = p.getProperty("Address");
    private static String Username = p.getProperty("Username");
    private static String Userpassword = p.getProperty("Userpassword");

    public static String getUsername() {
        return Username;
    }

    public static String getUserpassword() {
        return Userpassword;
    }

    public static String getAddress() {
        return Address;
    }

    public static String getHost() {
        return host;
    }

    public static String getName() {
        return name;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}

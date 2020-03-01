package com.himoyi.img_api.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UpYunResponseUtils {
    public static List<String> toList(String response) {
        List<String> list = new LinkedList<String>();

        String[] strings = response.split("\n");
        for (String string : strings) {
            list.add(string.split("\t")[0]);
        }
        return list;
    }

    public static List<String> getPath(String path, List<String> list) {
        List<String> path_list = new LinkedList<>();

        for (String s : list) {
            path_list.add("/" + path + "/" + s);
        }

        return path_list;
    }
}

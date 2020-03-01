package com.himoyi.img_api.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class BeanUtilsPlus {
    public static <T> T toBean(Map map, Class<T> tclass) {
        T t;
        try {
            t = tclass.newInstance();
            BeanUtils.populate(t, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}

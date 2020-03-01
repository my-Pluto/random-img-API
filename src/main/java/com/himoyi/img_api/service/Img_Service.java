package com.himoyi.img_api.service;

import com.himoyi.img_api.dao.ImgDao;
import com.himoyi.img_api.domain.Img;
import com.himoyi.img_api.domain.Params;
import com.himoyi.img_api.utils.PropertiesUtils;

public class Img_Service {
    public String get_Address(Params params) {
        ImgDao imgDao = new ImgDao();
        String type = params.getType();
        Img img = new Img();
        if (type.equals("all"))
            img = imgDao.get();
        else
            img = imgDao.get(type);
        String path = PropertiesUtils.getHost() + img.getPath() + img.getName();
        return path;
    }

    public void update() {

    }
}

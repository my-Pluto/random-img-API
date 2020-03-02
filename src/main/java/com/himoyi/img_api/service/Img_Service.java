package com.himoyi.img_api.service;

import com.himoyi.img_api.dao.ImgDao;
import com.himoyi.img_api.domain.Img;
import com.himoyi.img_api.domain.Params;
import com.himoyi.img_api.memory.Memory;
import com.himoyi.img_api.memory.MemoryFactory;
import com.himoyi.img_api.utils.PropertiesUtils;

import java.util.LinkedList;
import java.util.List;

public class Img_Service {

    /**
     * 获得随机图片地址
     * @param params
     * @return
     */
    public String get_Address(Params params) {
        ImgDao imgDao = new ImgDao();
        String type = params.getType();
        Img img = new Img();
        if (type.equals("all"))
            img = imgDao.get();
        else
            img = imgDao.get(type);
        String path = PropertiesUtils.getHost() + "/" + img.getPath() + "/" + img.getName();
        return path;
    }

    /**
     * 更新数据库
     * @param params
     */
    public void update(Params params) {
        String username = params.getUser();
        String password = params.getPassword();

        if (username.equals(PropertiesUtils.getUsername())
                && password.equals(PropertiesUtils.getUserpassword())) {
            Memory memory = new MemoryFactory().getMemory();

            ImgDao imgDao = new ImgDao();

            List<String> img_path = memory.getPath();
            List<String> img_md5 = new LinkedList<>();
            for (String s : img_path) {
                String md5 = memory.getMD5(s);
                img_md5.add(md5);
            }
            System.out.println(img_path);
            System.out.println(img_md5);
            for (int i = 0; i < img_md5.size(); i++) {
                String md5 = img_md5.get(i);
                boolean check = imgDao.check(md5);
                if (!check) {
                    String[] img_data = img_path.get(i).split("/");
                    Img img = new Img(img_data[1], img_data[2], md5);
                    imgDao.update(img);
                }
            }
        } else {
            throw new RuntimeException("权限不足！");
        }
    }
}

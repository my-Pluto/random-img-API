package com.himoyi.img_api.memory;

import com.himoyi.img_api.utils.PropertiesUtils;
import com.himoyi.img_api.utils.UpYunResponseUtils;
import com.upyun.RestManager;
import okhttp3.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MemoryImplByUpYun implements Memory {

    /**
     * 获取文件目录信息
     * @return
     */
    @Override
    public List<String> getPath() {
        //连接又拍云存储空间，连接信息读取自配置文件
        RestManager manager = new RestManager(PropertiesUtils.getAddress(),
                PropertiesUtils.getUser(), PropertiesUtils.getPassword());

        //根目录
        String dirPath = "/";

        //参数列表，此时不需要
        Map<String, String> params = new HashMap<String, String>();
        //结果信息
        List<String> list = new LinkedList<>();

        try {
            //获取目录信息
            Response response = manager.readDirIter(dirPath, null);
            String string = response.body().string();
            List<String> path = UpYunResponseUtils.toList(string);

            //获取各个目录下文件信息，并保存入list
            for (String s : path) {
                dirPath = "/" + s;
                string = manager.readDirIter(dirPath, null).body().string();
                list = UpYunResponseUtils.getPath(s, UpYunResponseUtils.toList(string));
            }
        } catch (Exception e) {
            throw new RuntimeException("获取目录文件信息失败");
        }
        return list;
    }

    /**
     * 获取文件MD5
     * @param path
     * @return
     */
    @Override
    public String getMD5(String path) {
        RestManager manager = new RestManager(PropertiesUtils.getAddress(),
                PropertiesUtils.getUser(), PropertiesUtils.getPassword());
        try {
            return manager.getFileInfo(path).headers().get("Content-Md5");
        } catch (Exception e) {
            throw new RuntimeException("获取文件MD5失败");
        }
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    @Override
    public boolean delete(String path) {
        RestManager manager = new RestManager(PropertiesUtils.getAddress(),
                PropertiesUtils.getUser(), PropertiesUtils.getPassword());

        try {
            Response response = manager.deleteFile(path, null);
            if(!response.isSuccessful())
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

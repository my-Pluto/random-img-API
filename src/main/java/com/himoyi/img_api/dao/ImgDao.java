package com.himoyi.img_api.dao;

import com.himoyi.img_api.domain.Img;
import com.himoyi.img_api.utils.TxQuaryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Random;

public class ImgDao {

    /**
     * 获取随机图片链接，类型不限
     * @return
     */
    public Img get() {
        TxQuaryRunner txQuaryRunner = new TxQuaryRunner();

        String sql = "select count(*) from img";
        try {
            //获取数据总条数
            Integer count = txQuaryRunner.query(sql, new ScalarHandler<Integer>());
            if (count == 0) {
                throw new RuntimeException("不存在图片，请检查各项信息是否正确！");
            }
            //生成随机数
            int limit = new Random().nextInt(count);

            //获取信息
            sql = "SELECT * FROM img LIMIT " + count + ", 1";
            Img img = txQuaryRunner.query(sql, new BeanHandler<Img>(Img.class));

            return img;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取指定类型的随机图片
     * @param type
     * @return
     */
    public Img get(String type) {
        TxQuaryRunner txQuaryRunner = new TxQuaryRunner();

        String sql = "SELECT COUNT(*) FROM img WHERE path LIKE '" + type + "'";

        try {
            //获取指定类型的数据总条数
            Integer count = txQuaryRunner.query(sql, new ScalarHandler<Integer>());
            if (count == 0)
                throw new RuntimeException("不存在该类型图片");

            //生成随机数
            int limit = new Random().nextInt(count);

            //获取信息
            sql = "SELECT * FROM img WHERE path LIKE '" + type + "' LIMIT " + limit + ",1";
            Img img = txQuaryRunner.query(sql, new BeanHandler<Img>(Img.class));

            return img;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

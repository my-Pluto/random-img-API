package com.himoyi.img_api.servlet;

import com.himoyi.img_api.domain.Params;
import com.himoyi.img_api.service.Img_Service;
import com.himoyi.img_api.utils.BeanUtilsPlus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "img", urlPatterns = "/img")
public class img extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Img_Service img_service = new Img_Service();

        //获得参数
        Params params = BeanUtilsPlus.toBean(request.getParameterMap(), Params.class);


        try {
            //选取随机图片
            if (!params.getUpdate().equals("yes")) {
                String address = img_service.get_Address(params);
                response.sendRedirect(address);
            }
            else {
                //更新数据库
                img_service.update();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

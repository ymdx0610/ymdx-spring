package com.ymdx.mvc.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: MyServlet
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-11 15:40
 * @Version: 1.0
 **/
//@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init ...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service ...");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy ...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理请求...");
        resp.getWriter().println("hello, world!");
    }

}

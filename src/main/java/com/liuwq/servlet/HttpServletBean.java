package com.liuwq.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/24 0024 下午 2:47
 * @version: V1.0
 */
public class HttpServletBean extends HttpServlet {

    @Override
    public final void init() throws ServletException {
        initServletBean();
    }

    protected void initServletBean() throws ServletException {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doService(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }

}

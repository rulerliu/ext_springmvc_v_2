package com.liuwq.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/24 0024 下午 2:46
 * @version: V1.0
 */
public abstract class FrameworkServlet extends HttpServletBean {

    @Override
    protected final void initServletBean() throws ServletException {
        onRefresh();
    }


    protected void onRefresh() {
        // For subclasses: do nothing by default.
    }

    protected abstract void doService(HttpServletRequest request, HttpServletResponse response) throws Exception;

}

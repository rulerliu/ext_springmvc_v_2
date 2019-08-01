package com.liuwq.servlet.web.impl;

import com.liuwq.servlet.DispatcherServlet;
import com.liuwq.servlet.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/24 0024 下午 2:43
 * @version: V1.0
 */
public class AbstractDispatcherServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 1.开始注册DispatcherServlet
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet());
        // 拦截所有的请求
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setLoadOnStartup(1);
    }
}

package com.liuwq.handler;

import com.liuwq.view.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/8/1 0001 上午 9:58
 * @version: V1.0
 */
public class HandlerExecutionChain {

    private final HandlerMethod handler;

    public HandlerExecutionChain(HandlerMethod handler) {
        this.handler = handler;
    }

    public ModelAndView handler() throws InvocationTargetException, IllegalAccessException {
        // 1. 使用java的反射机制执行我们请求方法
        Method method = handler.getMethod();
        Object bean = handler.getBean();
        // 2.执行我们的请求的方法
        Object viewName = method.invoke(bean, null);
        ModelAndView modelAndView = new ModelAndView((String) viewName);
        return modelAndView;
    }

}

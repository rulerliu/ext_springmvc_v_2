package com.liuwq.handler;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/26 0026 下午 5:23
 * @version: V1.0
 */
public class HandlerMethod {

    // 请求方法对应的bean对象
    private Object bean;
    private Method method;
    private Object args[];

    public HandlerMethod(Object bean, Method method, Object args[]) {
        this.bean = bean;
        this.method = method;
        this.args = args;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}

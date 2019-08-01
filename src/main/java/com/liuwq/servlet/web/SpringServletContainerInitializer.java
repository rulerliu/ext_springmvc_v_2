package com.liuwq.servlet.web;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 1. 容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类，子接口等）传递过来；
 *
 * 2. 要使用ServletContainerInitializer就必须在对应的jar包的META-INF/services 目录创建一个名为javax.servlet.ServletContainerInitializer的文件
 *    文件内容指定具体的ServletContainerInitializer实现类。
 */
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {

    /**
     * onStartup servlet容器初始化的时候就会调用该方法
     *
     * @param classInfos 获取 WebApplicationInitializer 所有的子类
     * @param ctx
     * @throws ServletException
     */
    public void onStartup(Set<Class<?>> classInfos, ServletContext ctx) throws ServletException {
        for (Class<?> classInfo : classInfos) {
            try {
                Method onStartup = classInfo.getDeclaredMethod("onStartup", ServletContext.class);
                onStartup.invoke(classInfo.newInstance(), ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

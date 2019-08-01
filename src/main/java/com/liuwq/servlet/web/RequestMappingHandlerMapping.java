package com.liuwq.servlet.web;

import com.liuwq.annotation.ComponentScan;
import com.liuwq.annotation.Controller;
import com.liuwq.annotation.RequestMapping;
import com.liuwq.config.SpringMvcConfig;
import com.liuwq.handler.HandlerMethod;
import com.liuwq.utils.ReflexUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/26 0026 下午 5:16
 * @version: V1.0
 */
public class RequestMappingHandlerMapping {

    // 在初始化SpringMVC Bean对象、url与方法关联存放到Map集合中
    private Map<String, HandlerMethod> registry = new ConcurrentHashMap<>();

    public void initHandlerMappings() {
        // 1.获取配置类上面的扫包范围
        ComponentScan componentScanAnnotation = SpringMvcConfig.class.getDeclaredAnnotation(ComponentScan.class);
        if (componentScanAnnotation == null) {
            return;
        }

        String componentScanValue = componentScanAnnotation.value();
        if (StringUtils.isBlank(componentScanValue)) {
            return;
        }

        // 2.获取扫包的所有类
        Set<Class<?>> classes = ReflexUtils.getClasses(componentScanValue);
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }

        for (Class<?> classInfo : classes) {
            Controller controllerAnnotation = classInfo.getDeclaredAnnotation(Controller.class);
            // 3.判断类上是否有@Controller注解
            if (controllerAnnotation == null) {
                continue;
            }

            // 4.获取类的所有方法
            Method[] allMethods = classInfo.getDeclaredMethods();
            if (allMethods == null || allMethods.length == 0) {
                return;
            }
            for (Method method : allMethods) {
                RequestMapping requestMappingAnnotation = method.getDeclaredAnnotation(RequestMapping.class);
                if (requestMappingAnnotation == null) {
                    continue;
                }
                String requestMappingValue = requestMappingAnnotation.value();
                if (StringUtils.isEmpty(requestMappingValue)) {
                    continue;
                }

                registry.put(requestMappingValue, new HandlerMethod(newInstance(classInfo), method, null));
            }

        }

    }

    private Object newInstance(Class classInfo) {
        try {
            Object value = classInfo.newInstance();
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public HandlerMethod getHandlerMethod(String url) {
        return registry.get(url);
    }
}

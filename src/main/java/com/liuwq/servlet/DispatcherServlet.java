package com.liuwq.servlet;

import com.liuwq.handler.HandlerExecutionChain;
import com.liuwq.handler.HandlerMethod;
import com.liuwq.servlet.web.RequestMappingHandlerMapping;
import com.liuwq.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/24 0024 下午 2:45
 * @version: V1.0
 */
public class DispatcherServlet extends FrameworkServlet {

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public DispatcherServlet() {
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
    }

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    protected void initStrategies() {
        requestMappingHandlerMapping.initHandlerMappings();
    }

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doDispatch(request, response);
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(">>>doDispatch");
        // 1.根据url查找对应的Handler
        HandlerExecutionChain handler = getHandler(request);
        if (handler == null) {
            noHandlerFound(request, response);
            return;
        }

        // 2.使用java的反射机制执行请求方法 返回对应的modelAndView
        ModelAndView modelAndView = handler.handler();

        // 3.开始渲染视图层
        processDispatchResult(request, response, handler, modelAndView);
    }

    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerMethod handlerMethod = requestMappingHandlerMapping.getHandlerMethod(request.getRequestURI());
        if (handlerMethod == null) {
            return null;
        }
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain(handlerMethod);
        return handlerExecutionChain;
    }

    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new Exception("没有查找到对应的请求");
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                       HandlerExecutionChain mappedHandler, ModelAndView modelAndView) throws Exception {
        render(modelAndView, request, response);
    }

    protected void render(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = modelAndView.getViewName();
        request.getRequestDispatcher("/WEB-INF/view/" + viewName + ".jsp").forward(request, response);
    }

}

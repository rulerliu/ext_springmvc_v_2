package com.liuwq.view;

public class ModelAndView {
    // 跳转页面名称
    private String viewName;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}

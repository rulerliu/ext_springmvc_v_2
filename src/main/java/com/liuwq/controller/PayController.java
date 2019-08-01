package com.liuwq.controller;

import com.liuwq.annotation.Controller;
import com.liuwq.annotation.RequestMapping;

@Controller
public class PayController {

    @RequestMapping("/pay")
    public String pay() {
        return "pay";
    }
}

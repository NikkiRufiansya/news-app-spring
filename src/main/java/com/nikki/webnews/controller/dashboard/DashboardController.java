package com.nikki.webnews.controller.dashboard;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        return "dashboard";
    }
}

package ru.whitebeef.ipwhois.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.whitebeef.ipwhois.data.WhoisData;
import ru.whitebeef.ipwhois.services.IPWhoisService;

@Controller("/")
public class IndexController {

    private final IPWhoisService ipWhoisService;

    @Autowired
    public IndexController(IPWhoisService ipWhoisService) {
        this.ipWhoisService = ipWhoisService;
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("info", WhoisData.fromJson(ipWhoisService.getResponse(request.getRemoteAddr())));
        return "index";
    }

    @PostMapping
    public String ipWhois(@RequestParam String ip, Model model) {
        model.addAttribute("info", WhoisData.fromJson(ipWhoisService.getResponse(ip)));
        return "index";
    }

}

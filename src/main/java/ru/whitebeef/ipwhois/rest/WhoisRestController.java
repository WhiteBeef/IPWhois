package ru.whitebeef.ipwhois.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.whitebeef.ipwhois.data.WhoisData;
import ru.whitebeef.ipwhois.services.IPWhoisService;

@RestController
public class WhoisRestController {

    private final IPWhoisService ipWhoisService;

    @Autowired
    public WhoisRestController(IPWhoisService ipWhoisService) {
        this.ipWhoisService = ipWhoisService;
    }

    @GetMapping("/whois")
    public WhoisData index(@RequestParam String ip, Model model) {
        return WhoisData.fromJson(ipWhoisService.getResponse(ip));
    }

}

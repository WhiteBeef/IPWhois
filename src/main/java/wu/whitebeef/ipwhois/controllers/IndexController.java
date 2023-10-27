package wu.whitebeef.ipwhois.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wu.whitebeef.ipwhois.data.WhoisData;
import wu.whitebeef.ipwhois.services.IPWhoisService;

@Controller("/")
public class IndexController {

    private final IPWhoisService ipWhoisService;

    @Autowired
    public IndexController(IPWhoisService ipWhoisService) {
        this.ipWhoisService = ipWhoisService;
    }

    @GetMapping
    public String index(Model model) {
        System.out.println("hui");
        return "index";
    }

    @PostMapping
    public String ipWhois(@RequestParam String ip, Model model) {
        model.addAttribute("info", WhoisData.fromJson(ipWhoisService.getResponse(ip)));
        return "index";
    }

}

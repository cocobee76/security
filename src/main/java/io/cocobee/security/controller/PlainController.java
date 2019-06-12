package io.cocobee.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plain")
public class PlainController {

    @GetMapping(value = "/index")
    public String index(@RequestParam String str){
        return "Hello " + str;
    }

}

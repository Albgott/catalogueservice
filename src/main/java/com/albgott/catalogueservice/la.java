package com.albgott.catalogueservice;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class la {

    @GetMapping("/la")
    public void lala(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
    }
}

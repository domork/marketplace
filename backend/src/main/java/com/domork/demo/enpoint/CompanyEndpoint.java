package com.domork.demo.enpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyEndpoint {


    @Autowired
    public CompanyEndpoint() {
    }

    @GetMapping(value="/company")
    public int getCompany(){
        System.out.println("The get request works just fine");
        return 1;
    }
}

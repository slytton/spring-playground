package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gschool on 2/16/17.
 */
@RestController
public class PagesController {
    @GetMapping("/hello")
    public BasicClass hello() {
        return new BasicClass() ;
    }

    public class BasicClass {
        private String one;
        public String two;
        BasicClass() {
            one = "this is one";
            two = "this is two";
        }
    }
}



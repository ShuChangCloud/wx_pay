package com.xmcc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-19 9:50
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello() {
        logger.info("spring boot hello world");
        return "deldwadwaete";
    }

}

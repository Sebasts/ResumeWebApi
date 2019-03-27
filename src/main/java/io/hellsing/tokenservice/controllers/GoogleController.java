package io.hellsing.tokenservice.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.hellsing.tokenservice.service.GoogleTokenVerifierService;

@RestController
public class GoogleController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private GoogleTokenVerifierService gtvs;
    
    @RequestMapping(value = "/api/tokenservice/202", method = RequestMethod.POST )
    @ResponseBody
    public boolean greeting(String token) {
    	return gtvs.verifyOauthToken(token);
    }
}

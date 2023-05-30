package com.example.push.api.client.controller;

import com.example.push.api.client.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/push/client/token")
@RestController
public class TokenController {

    private HttpHeaders headers;
    private Map body;

    public TokenController() {
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
    }

    @Autowired
    TokenService tokenService;

    @PostMapping("/save")
    public ResponseEntity saveToken(@RequestBody Map<String, Object> paramMap) throws Exception {

        body = tokenService.tokenSave(paramMap);

        return new ResponseEntity(body, headers, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteToken(@RequestBody Map<String, Object> paramMap) throws Exception {

        body = tokenService.tokenDelete(paramMap);

        return new ResponseEntity(body, headers, HttpStatus.OK);
    }

    @PostMapping("/ntfcStng")
    public ResponseEntity ntfcStng(@RequestBody Map<String, Object> paramMap) throws Exception {

        body = tokenService.ntfcStng(paramMap);

        return new ResponseEntity(body, headers, HttpStatus.OK);
    }


}

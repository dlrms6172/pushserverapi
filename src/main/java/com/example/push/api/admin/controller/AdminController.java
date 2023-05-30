package com.example.push.api.admin.controller;

import com.example.push.api.admin.service.AdminService;
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

@RequestMapping("/push/admin")
@RestController
public class AdminController {

    private HttpHeaders headers;
    private Map body;

    public AdminController() {
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
    }

    @Autowired
    AdminService adminService;

    @PostMapping("/sendNotification")
    public ResponseEntity sendNotification(@RequestBody Map<String, Object> paramMap) throws Exception {

        body = adminService.sendNotification(paramMap);

        return new ResponseEntity(body, headers, HttpStatus.OK);
    }

}

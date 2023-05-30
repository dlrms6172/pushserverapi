package com.example.push.api.admin.service;

import java.util.Map;

public interface AdminService {


    // 알림 발송
    public Map sendNotification(Map<String,Object> paramMap) throws Exception;

}

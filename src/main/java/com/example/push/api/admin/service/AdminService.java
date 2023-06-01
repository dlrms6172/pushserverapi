package com.example.push.api.admin.service;

import java.util.Map;

public interface AdminService {


    // 알림 발송
    public Map sendNotification(Map<String,Object> paramMap) throws Exception;

    // 알림내역 저장
    public Map saveNotificationHistory(Map<String,Object> paramMap) throws Exception;

}

package com.example.push.api.client.service;

import java.util.Map;

public interface TokenService {

    // 사용자 - 토큰 저장 token save
    public Map tokenSave(Map<String,Object> paramMap) throws Exception;

    // 사용자 - 토큰 삭제 token delete
    public Map tokenDelete(Map<String,Object> paramMap) throws Exception;

    // 사용자 - 알림 설정 notification settings
    public Map ntfcStng(Map<String,Object> paramMap) throws Exception;
}

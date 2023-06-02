package com.example.push.api.client.service.impl;

import com.example.push.api.client.mapper.TokenMapper;
import com.example.push.api.client.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    private Map<String, Object> result;

    @Autowired
    TokenMapper tokenMapper;

    /**
     * @param paramMap project_id
     *                 user_id
     *                 device_token
     *                 user_type
     *                 platform
     *                 reg_dt
     *                 notification_status
     *                 login_status
     * @return
     * @throws Exception
     */
    @Override
    public Map tokenSave(Map<String, Object> paramMap) throws Exception {
        result = new HashMap<>();

        // 기존 계정 유무 확인
        int updateToken = 0;
        updateToken = tokenMapper.updateToken(paramMap);

        if (updateToken == 0) {
            // 사용자 정보 저장
            int insertToken = 0;

            // 사용자 알림사용여부 저장
            int insertNotificationStatus = 0;

            insertToken = tokenMapper.insertToken(paramMap);
            insertNotificationStatus = tokenMapper.insertNotificationStatus(paramMap);

            result.put("insertToken", insertToken);
            result.put("insertUesYn", insertNotificationStatus);
        } else {
            result.put("updateToken", updateToken);
        }


        return result;
    }

    @Override
    public Map tokenDelete(Map<String, Object> paramMap) throws Exception {
        result = new HashMap<>();

        int deleteToken = 0;
        deleteToken = tokenMapper.deleteToken(paramMap);


        result.put("deleteToken", deleteToken);

        return result;
    }

    @Override
    public Map ntfcStng(Map<String, Object> paramMap) throws Exception {
        result = new HashMap<>();

        int updateNotificationStatus = 0;
        updateNotificationStatus = tokenMapper.updateNotificationStatus(paramMap);

        result.put("updateNotificationStatus", updateNotificationStatus);

        return result;
    }
}

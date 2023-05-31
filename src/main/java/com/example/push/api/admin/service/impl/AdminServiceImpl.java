package com.example.push.api.admin.service.impl;

import com.example.push.api.admin.mapper.AdminMapper;
import com.example.push.api.admin.service.AdminService;
import com.example.push.api.common.config.FirebaseConfig;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    FirebaseConfig firebaseConfig;

    private Map<String, Object> result;

    @Override
    public Map sendNotification(Map<String, Object> paramMap) throws Exception {
        result = new HashMap<>();

        // firebase 초기화
        firebaseConfig.initialize();
        //project_id
        Map<String,Object> projectId = adminMapper.selectProjectId(paramMap);
        FirebaseApp path = FirebaseApp.getInstance(projectId.get("projectNm").toString());

        // 토큰 조회
        List<Map<String, Object>> userTokenList = adminMapper.selectUserTokenList(paramMap);

        if (userTokenList != null) {
            List<String> settingTokenList = new ArrayList<>();

            for (Map<String, Object> item : userTokenList) {
                settingTokenList.add(item.get("deviceToken").toString());
            }

            // Fcm service

            BatchResponse response;

            response = FirebaseMessaging.getInstance(path).sendMulticast(
                    MulticastMessage.builder().
                            setNotification(
                                    Notification.builder()
                                            .setTitle("title")
                                            .setBody("body")
                                            .setImage("image")
                                            .build())
                            .putData("data1", "data1")
                            .putData("data2", "data2")
                            .addAllTokens(settingTokenList)
                            .build());

            result.put("result",response.getResponses());


        } else {
            result.put("result", "토큰이 없습니다.");
        }


        return result;
    }
}

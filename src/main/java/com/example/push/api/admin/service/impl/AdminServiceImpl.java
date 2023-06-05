package com.example.push.api.admin.service.impl;

import com.example.push.api.admin.mapper.AdminMapper;
import com.example.push.api.admin.service.AdminService;
import com.example.push.api.common.config.FirebaseConfig;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    FirebaseConfig firebaseConfig;

    private Map<String, Object> result;

    @Override
    public Map sendNotification(Map<String, Object> paramMap) throws Exception {
        result = new LinkedHashMap<>();


        //project_id 조회
        Map<String, Object> projectId = adminMapper.selectProjectId(paramMap);

        if (projectId != null) {

            // firebase를 사용할 프로젝트 선택
            FirebaseApp path = FirebaseApp.getInstance(projectId.get("projectNm").toString());

            // 토큰 조회
            List<Map<String, Object>> userTokenList = adminMapper.selectUserTokenList(paramMap);

            if (userTokenList != null) {
                List<String> settingTokenList = new ArrayList<>();

                for (Map<String, Object> item : userTokenList) {
                    settingTokenList.add(item.get("deviceToken").toString());
                }

                // 조회된 토큰 수
                result.put("settingTokenCnt",settingTokenList.size());

                // 알림 조회
                Map<String,Object> notification = adminMapper.selectNotification(paramMap);

                System.out.println(notification);


                // 알림 발송 로직
                BatchResponse response;

                // path : 사용할 프로젝트
                response = FirebaseMessaging.getInstance(path).sendMulticast(
                        MulticastMessage.builder().
                                setNotification(
                                        Notification.builder()
                                                .setTitle(notification.get("title").toString())
                                                .setBody(notification.get("body").toString())
                                                .setImage(notification.get("image").toString())
                                                .build())
                                .putData("projectId", notification.get("projectId").toString())
                                .putData("notificationCd", notification.get("notificationCd").toString())
                                .addAllTokens(settingTokenList)
                                .build());



                result.put("sendTokenCnt",response.getSuccessCount());


                List<SendResponse> responses = response.getResponses();
                List<Map<String,Object>> succesToken = new ArrayList<>();
                List<Map<String,Object>> failureToken = new ArrayList<>();

                for (int i = 0; i < responses.size(); i++) {
                    Map<String,Object> tokenMap = new LinkedHashMap<>();

                    if (!responses.get(i).isSuccessful()) {
                        tokenMap.put("token",settingTokenList.get(i));
                        tokenMap.put("exception",response.getResponses().get(i).getException().getMessagingErrorCode());

                        failureToken.add(tokenMap);
                    } else {
                        tokenMap.put("token",settingTokenList.get(i));

                        succesToken.add(tokenMap);
                    }
                }

                result.put("successCount", response.getSuccessCount());
                result.put("failureCount", response.getFailureCount());
                result.put("succesToken",succesToken);
                result.put("failureToken",failureToken);


            } else {
                result.put("result", "토큰이 존재하지 않습니다.");
            }

        } else {
            result.put("result", "프로젝트가 존재하지 않습니다.");
        }

        return result;
    }

    @Override
    public Map saveNotificationHistory(Map<String, Object> paramMap) throws Exception {
        result = new LinkedHashMap<>();

        int insertNotificationHistory = 0;
        insertNotificationHistory = adminMapper.insertNotificationHistory(paramMap);


        result.put("result",insertNotificationHistory);

        return result;
    }
}

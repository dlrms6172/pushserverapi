package com.example.push.api.client.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface TokenMapper {
    // 기존에 정보가 있으면 등록일자,로그인 여부 업데이트
    Integer updateToken(Map<String,Object> paramMap) throws Exception;

    // 토큰 저장
    Integer insertToken(Map<String,Object> paramMap) throws Exception;

    // 알림 수신여부 저장
    Integer insertNotificationStatus(Map<String,Object> paramMap) throws Exception;

    // 토큰 삭제
    Integer deleteToken(Map<String,Object> paramMap) throws Exception;

    // 알림 수신여부 변경
    Integer updateNotificationStatus(Map<String,Object> paramMap) throws Exception;
}

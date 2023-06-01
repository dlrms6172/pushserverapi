package com.example.push.api.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminMapper {

    // 유저 토큰리스트 선택
    List selectUserTokenList(Map<String,Object> paramMap) throws Exception;

    // 프로젝트ID 선택
    Map selectProjectId(Map<String,Object> paramMap) throws Exception;

    // 알림내역 저장
    int insertNotificationHistory(Map<String,Object> paramMap) throws Exception;
}

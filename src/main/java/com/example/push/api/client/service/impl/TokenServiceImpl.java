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

    @Override
    public Map tokenSave(Map<String, Object> paramMap) throws Exception {
        result = new HashMap<>();

        // 기존 계정 유무 확인
        int updateToken = 0;
        updateToken = tokenMapper.updateToken(paramMap);

        if (updateToken == 0) {

            int insertToken = 0;
            int insertUesYn = 0;

            insertToken = tokenMapper.insertToken(paramMap);
            insertUesYn = tokenMapper.insertUseYn(paramMap);
        }


        return result;
    }

    @Override
    public Map tokenDelete(Map<String, Object> paramMap) throws Exception {
        result = new HashMap<>();

        int deleteToken = 0;
        deleteToken = tokenMapper.deleteToken(paramMap);


        result.put("result",deleteToken);

        return result;
    }
}

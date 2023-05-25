package com.example.push.api.client.service;

import java.util.Map;

public interface TokenService {

    public Map tokenSave(Map<String,Object> paramMap) throws Exception;

    public Map tokenDelete(Map<String,Object> paramMap) throws Exception;
}

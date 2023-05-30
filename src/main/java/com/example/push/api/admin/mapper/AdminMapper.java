package com.example.push.api.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminMapper {

    List<Map<String,Object>> userTokenList(Map<String,Object> paramMap) throws Exception;
}

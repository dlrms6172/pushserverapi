package com.example.push.api.common.config;

import com.google.common.base.CaseFormat;

import java.util.LinkedHashMap;

public class CamelHashMap extends LinkedHashMap {

    @Override
    public Object put(Object key, Object value){
        return super.put(toLowerCamel((String) key), value);
    }

    private static String toLowerCamel(String key){
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
    }
}

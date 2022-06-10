package com.hiwei.test.agent;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MockConfig {
    private static Map<String, Map<String, Object>> mockData = new HashMap<>();

    public static String add(){
        return "MockConfig";
    }

    public static void parseConfig(String configStr){
        JSONObject config = JSONObject.parseObject(configStr);
        Set<String> classNameSet = config.keySet();
        classNameSet.forEach(className->{
            Map<String,Object> methodMock = new HashMap<>();
            JSONObject methods = config.getJSONObject(className);
            Set<String> mSet = methods.keySet();
            mSet.forEach(m->{
                methodMock.put(m,methods.get(m));
            });
            mockData.put(className,methodMock);
        });
    }

    public static boolean isMock(String className){
        return !(mockData.get(className)==null);
    }


        public static Object getMockData(String className,String methodName){
        Map<String, Object> stringJSONObjectMap = mockData.get(className);
        if(stringJSONObjectMap!=null){
            return stringJSONObjectMap.get(methodName);
        }
        return null;
    }
}

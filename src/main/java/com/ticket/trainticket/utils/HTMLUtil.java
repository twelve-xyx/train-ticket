package com.ticket.trainticket.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class HTMLUtil {

    private static final Logger logger = LoggerFactory.getLogger(HTMLUtil.class);


    private static final CloseableHttpClient httpclient;
    public static final String CHARSET = "UTF-8";


    static{
        RequestConfig config = RequestConfig.custom().setConnectTimeout(15000).setSocketTimeout(13000).build();
        httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }



    public static String sendGet(String url){
        RestTemplate restTemplate = SpringContextUtils.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Chrome/69.0.3497.81 Safari/537.36");
        org.springframework.http.HttpEntity<MultiValueMap<String,Object>> entity =new org.springframework.http.HttpEntity<>(null,headers);
        ResponseEntity<String> forEntity = restTemplate.exchange(url, HttpMethod.GET, entity ,String.class, new HashMap<>());
        if(HttpStatus.OK.equals(forEntity.getStatusCode())){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String executeInfo = forEntity.getBody();
            return executeInfo;
        }
        return null;
    }
    public static String sendPostForJson(String requestUrl, JSONObject fullJson) {
        RestTemplate restTemplate = SpringContextUtils.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");
        org.springframework.http.HttpEntity httpEntity = new org.springframework.http.HttpEntity(fullJson,headers);
        String reson = restTemplate.postForObject(requestUrl,httpEntity
                , String.class);
        return  reson;
    }

}

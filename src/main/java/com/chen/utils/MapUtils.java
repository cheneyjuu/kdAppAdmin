package com.chen.utils;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.chen.config.Constants.MAP_KEY;


/**
 * @author chen
 */
public class MapUtils {
    private static final Logger log = LoggerFactory.getLogger(MapUtils.class);

    public static Map<String, Object> convertAddress(final String address) throws IOException, ParseException {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder?address=" + address + "&output=json&key=" + MAP_KEY);
            try (final CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                log.info("地址解析返回 ==> code: {}, reason: {}", response.getCode(), response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "UTF-8");
                log.info("地址解析内容==> {}", content);
                Gson gson = new Gson();
                Map<String, Object> json = gson.fromJson(content, Map.class);
                Map<String, Object> resultMap = (Map<String, Object>) json.get("result");
                return (Map<String, Object>) resultMap.get("location");
            }
        }
    }

    public static void drawMap(String url) throws IOException, ParseException {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(url);
            try (final CloseableHttpResponse response = httpclient.execute(httpGet)) {
                log.info("地址解析返回 ==> code: {}, reason: {}", response.getCode(), response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "UTF-8");
                log.info("地址解析内容==> {}", content);
            }
        }
    }

    public static void main(String[] args) {
        String url = "https://restapi.amap.com/v3/staticmap?zoom=15&size=500*500&paths=2,0x0000ff,1,,:120.36434,31.49055;120.36434,31.49055;120.36434,31.49055;120.36434,31.49055&key=ec9acbe492bb8092937e341e197df061";
        try {
            MapUtils.drawMap(url);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

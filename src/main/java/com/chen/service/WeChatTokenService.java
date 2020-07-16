package com.chen.service;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

/**
 * @author chen
 **/
public interface WeChatTokenService {
    @GET("/sns/jscode2session")
    Call<Map<String, Object>> getAppToken(@Query("appid") String appid, @Query("secret") String secret, @Query("js_code") String js_code, @Query("grant_type") String grantType);

    @GET("/cgi-bin/token")
    Call<Map<String, Object>> getAppToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret);


    @POST("/cgi-bin/message/template/send")
    Call<Map<String, Object>> reserveNotice(@Query("access_token") String accessToken, @Body Map<String, Object> param);


    @POST("/cgi-bin/message/subscribe/send")
    Call<Map<String, Object>> reserveNoticeApp(@Query("access_token") String accessToken, @Body Map<String, Object> param);

    /**
     * 获取微信用户信息，携带 unionId 信息
     *
     * @param accessToken accessToken
     * @param openId      openId
     * @param lang        默认：zh_CN
     * @return Call<WechatUserResponse>
     */
    @GET("/cgi-bin/user/info")
    Call<Map<String, Object>> getWechatUserInfoWithUnionId(@Query("access_token") String accessToken, @Query("openid") String openId, @Query("lang") String lang);
}

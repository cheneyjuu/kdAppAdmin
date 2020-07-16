package com.chen.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author chen
 **/
public class RetrofitFactory {
    private RetrofitFactory() {
    }

    public static Retrofit getInstance() {
        return RetrofitHolder.RETROFIT;
    }

    private static class RetrofitHolder {
        private static final Retrofit RETROFIT = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.weixin.qq.com/")
            .build();
    }
}

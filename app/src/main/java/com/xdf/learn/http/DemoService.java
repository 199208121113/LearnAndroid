package com.xdf.learn.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DemoService {
    @GET("API/getconfigsNew.aspx")
    Call<String> testHttpGet(@Query("appid") String param);
}

package me.ewriter.dc4android.apis;

import me.ewriter.dc4android.models.AccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zubin on 16/6/11.
 */
public interface DiyCodeApi {

    // oauth2.0认证，返回accessToken
    @FormUrlEncoded
    @POST("oauth/token")
    public void auth(
            @Field("code") String code,
            @Field("grant_type") String grantType,
            @Field("redirect_uri") String redirect_uri,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            Call<AccessToken> call);

    // 用于验证返回的accesstoken是否正确
//    @GET("/api/v3/hello.json")
//    Call<HelloEntity> getHelloEntity(@Query("access_token") String access_token);
}

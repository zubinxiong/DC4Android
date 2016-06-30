package me.ewriter.dc4android.apis;

import me.ewriter.dc4android.models.AccessToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zubin on 16/6/11.
 */
public interface DiyCodeApi {

    // TODO: 16/6/27 除了获取token的方法定义了accesstoken entity，其它均未定义

    /**
     *  oauth2.0认证，返回accessToken
     * @param code
     * @param grantType  authorization_code
     * @param redirect_uri 申请时填写的跳转url
     * @param client_id
     * @param client_secret
     * @return
     */
    @FormUrlEncoded
    @POST("oauth/token")
    public Call<AccessToken> auth(@Field("code") String code, @Field("grant_type") String grantType,
            @Field("redirect_uri") String redirect_uri, @Field("client_id") String client_id,
            @Field("client_secret") String client_secret);

    /**
     * 用于验证返回的accesstoken是否正确
     * @param access_token
     * @return
     */
    @GET("/api/v3/hello.json")
    public Call<AccessToken> getHelloEntity(@Query("access_token") String access_token);


    /**
     * 记录用户 Device 信息，用于 Push 通知
     * 每次打开App 的时候调用此 API 以便更新 Token 的 last_actived_at
     * 让服务器知道这个设备还活着, Push 会忽略两周未更新的设别
     * @param platform ["ios", "android"]
     * @param token  accesstoken
     * @return
     */
    @POST("oauth/devices.json")
    public Call<AccessToken> updateDevices(@Query("platform") String platform, @Query("token") String token);

    /**
     * 删除 Devices 信息，请注意在用户登出或删除应用的时候调用，以便确保清理掉
     * @param platform ［“ios”， “android”］
     * @param token
     * @return
     */
    @DELETE("oauth/deveices.json")
    public Call<AccessToken> deleteDevices(@Query("platform") String platform, @Query("token") String token);

    /**
     * 赞了某个主题或某个回复
     * @param obj_type ［“topic”， “reply”］
     * @param obj_id   主题 id 或 用户 id ？
     * @return
     */
    @POST("oauth/likes.json")
    public Call<AccessToken> likeSomething(@Query("obj_type") String obj_type, @Query("obj_id") int obj_id);


    /**
     * 取消点赞
     * @param obj_type  ［“topic”， “reply”］
     * @param obj_id   主题 id 或 用户 id ？
     * @return
     */
    @DELETE("oauth/likes.json")
    public Call<AccessToken> dislikeSomething(@Query("obj_type") String obj_type, @Query("obj_id") int obj_id);
}

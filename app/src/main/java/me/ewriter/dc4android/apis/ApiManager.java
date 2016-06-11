package me.ewriter.dc4android.apis;

import me.ewriter.dc4android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zubin on 16/6/11.
 */
public class ApiManager {

    // 官网上给的oauth链接是https的，拷过来没仔细看，在最后发送post拿token的时候一直报下面的错
    // javax.net.ssl.SSLPeerUnverifiedException: Hostname diycode.cc not verified:
    public static final String API_BASE_URL = "http://diycode.cc/";

    private static DiyCodeApi mDiyCodeApi;

    private static DiyCodeApi getDiyCodeApi() {
        initDiyCodeApi();
        return mDiyCodeApi;
    }

    private static void initDiyCodeApi() {
        if (mDiyCodeApi == null) {

            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = builder.client(client).build();

            mDiyCodeApi = retrofit.create(DiyCodeApi.class);

        }
    }
}

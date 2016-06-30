package me.ewriter.dc4android;

import android.content.Intent;
import android.mtp.MtpConstants;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.ewriter.dc4android.apis.ApiManager;
import me.ewriter.dc4android.app.MyConstants;
import me.ewriter.dc4android.models.AccessToken;
import me.ewriter.dc4android.utils.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int OAUTH_REQUEST_CODE = 9900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnLogin(View view) {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivityForResult(intent, OAUTH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OAUTH_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.getStringExtra("code_url") != null) {
                String code_url = data.getStringExtra("code_url");
                String code = Uri.parse(code_url).getQueryParameter("code");
                if (code != null) {
                    // http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html 这篇文章上面没说
                    // 拿accessToken的时候要传secret，实际在使用的时候发现如果不传就无法使用
                    Call<AccessToken> call = ApiManager.getDiyCodeApi()
                            .auth(code, MyConstants.OAUTH_GRANT_TYPE, MyConstants.REDIRECT_URI,
                                    MyConstants.CLIENT_ID, MyConstants.CLIENT_SECRET);

                    call.enqueue(new Callback<AccessToken>() {
                        @Override
                        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                            LogUtil.d(LogUtil.ZUBIN, "token = " + response.body().getAccessToken());
                        }

                        @Override
                        public void onFailure(Call<AccessToken> call, Throwable t) {
                            LogUtil.d(LogUtil.ZUBIN, "onFailure = " + t.toString());
                        }
                    });


                } else if (Uri.parse(code_url).getQueryParameter("error") != null) {
                    // show fail toast
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

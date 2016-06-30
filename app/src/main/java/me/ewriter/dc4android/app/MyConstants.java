package me.ewriter.dc4android.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by zubin on 16/6/30.
 */
public class MyConstants {

    public static final String APP_PATH = Environment.getExternalStorageState() +
            File.separator + "DC4Android" + File.separator;

    public static final String CACHE_FILE_PATH = APP_PATH + "cache";


    // DiyCode 申请的api 相关
    public static final String CLIENT_ID = "686f506c";
    public static final String CLIENT_SECRET = "7bdc3184b480060c90a127bc54a70521345209ee77212bf136a65914dcb42120";
    public static final String REDIRECT_URI = "your://abc.com";


    public static final String OAUTH_GRANT_TYPE = "authorization_code";



}

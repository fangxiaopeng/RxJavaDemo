package com.example.rxjavademo.rxretrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Title:       LogIntercept
 * <p>
 * Package:     com.example.rxjavademo.rxretrofit
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2018/6/26 上午10:24
 * <p>
 * Description:
 * <p>
 * <p>
 * Modification History:
 * <p>
 * Date       Author       Version      Description
 * -----------------------------------------------------------------
 * 2018/6/26    fxp       1.0         First Created
 * <p>
 * Github:  https://github.com/fangxiaopeng
 */
public class LogIntercept implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("OKHTTP", ": Request URl>> " + request.url().toString()+"\n");
        return  chain.proceed(request);
    }

}

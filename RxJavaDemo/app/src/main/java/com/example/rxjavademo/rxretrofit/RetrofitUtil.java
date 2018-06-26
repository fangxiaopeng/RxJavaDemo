package com.example.rxjavademo.rxretrofit;

import com.alibaba.fastjson.JSON;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Title:       RetrofitUtil
 * <p>
 * Package:     com.example.rxjavademo.rxretrofit
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2018/6/26 上午10:20
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
public class RetrofitUtil {

    private static Retrofit mRetrofit;

    private static volatile RetrofitUtil instance;

    /**
     * 单例模式
     *
     * @return instance
     */
    private static RetrofitUtil getInstance() {
        if (null == instance) {
            synchronized (RetrofitUtil.class) {
                if (null == instance) {
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    private RetrofitUtil() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .readTimeout(30, TimeUnit.SECONDS)//读取超时
                .connectTimeout(30, TimeUnit.SECONDS)//连接超时
                .writeTimeout(30, TimeUnit.SECONDS)//写入超时
                .addNetworkInterceptor(new LogIntercept())
//                .cache(cache)//缓存
                .sslSocketFactory(createSSLSocketFactory())
                .retryOnConnectionFailure(true)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_URL)
                .client(okHttpClient)
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static ApiService getApiService() {
        return RetrofitUtil.getInstance().create(ApiService.class);
    }

    private <T> T create(final Class<T> service) {
        return getRetrofit().create(service);
    }

    private static Retrofit getRetrofit() {
        return mRetrofit;
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");//SSL TLS
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


    /**
     * delete 请求返回 解密后的  Observable, 带请求参数
     */
    static <T> Observable<T> DeleteJsonByObservable(String json, String url, final Class<T> tClass) {
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/json; text/json; charset=utf-8"), json);
        return getApiService().DeleteObservable(mRequestBody, url).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post 请求返回 Observable, 带Body请求参数 返回String
     */
    static Observable<String> tokenDeleteObservable(String token, String url, HashMap<String, Object> map) {
        return getApiService().DeleteObservable(token, url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /*PostBodyObservable 与 PostFieldObservable 一样*/

    /**
     * post 请求返回 Observable, 带Body请求参数 返回String
     */
    public static Observable<String> PostBodyObservable(String json, String url) {
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return getApiService().PostRequestBodyObservable(mRequestBody, url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post 请求返回 Observable, 带Body请求参数 返回String
     */
    static <T> Observable<T> PostBodyObservable(String json, String url, final Class<T> tClass) {
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return getApiService().PostRequestBodyObservable(mRequestBody, url).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post Field 请求返回  Observable, 带请求参数 返回String
     */
    static Observable<String> PostFieldObservable(String url, HashMap<String, Object> map) {
        return getApiService().PostFieldObservable(url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post Field 请求返回  Observable, 带请求参数 返回 tClass
     */
    static <T> Observable<T> PostFieldObservable(String url, HashMap<String, Object> map, final Class<T> tClass) {
        return getApiService().PostQueryObservable(url, map).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * post Query 请求返回  Observable, 带请求参数 返回String
     */
    static Observable<String> PostQueryObservable(String url, HashMap<String, Object> map) {
        return getApiService().PostQueryObservable(url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post Query 请求返回  Observable, 带请求参数 返回 tClass
     */
    static <T> Observable<T> PostQueryObservable(String url, HashMap<String, Object> map, final Class<T> tClass) {
        return getApiService().PostQueryObservable(url, map).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * post Query 请求返回  Observable, 带请求参数 返回String
     */
    static Observable<String> tokenPostQueryObservable(String token, String url, HashMap<String, Object> map) {
        return getApiService().PostQueryObservable(token, url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post Query 请求返回  Observable, 带请求参数 返回tClass
     */
    static <T> Observable<T> tokenPostQueryObservable(String token, String url, HashMap<String, Object> map, final Class<T> tClass) {
        return getApiService().PostQueryObservable(token, url, map).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * get 请求返回  Observable, 带请求参数 返回String
     */
    public static Observable<String> GetObservable(String url, HashMap<String, Object> map) {
        return getApiService().GetObservable(url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get 请求返回  Observable, 带请求参数 返回tClass
     */
    static <T> Observable<T> GetObservable(String url, HashMap<String, Object> map, final Class<T> tClass) {
        return getApiService().GetObservable(url, map).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get 请求返回  Observable, 带请求参数 返回tClass
     */
    static <T> Observable<T> GetHeadsObservable(Map<String, String> headmap, String url, HashMap<String, Object> map, final Class<T> tClass) {
        return getApiService().GetHeadsObservable(headmap, url, map).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get 请求返回  Observable, 带请求参数 返回tClass
     */
    static Observable<String> GetHeadsObservable(Map<String, String> headmap, String url, HashMap<String, Object> map) {
        return getApiService().GetHeadsObservable(headmap, url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * get 请求返回  Observable, 带请求参数 返回String
     */
    static Observable<String> tokenGetObservable(String token, String url, HashMap<String, Object> map) {
        return getApiService().GetObservable(token, url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get 请求返回  Observable, 带请求参数 返回tClass
     */
    static <T> Observable<T> tokenGetObservable(String token, String url, HashMap<String, Object> map, final Class<T> tClass) {
        return getApiService().GetObservable(token, url, map).map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
                return JSON.parseObject(s, tClass);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * put 请求返回 Observable, 带Body请求参数 返回String
     */
    static Observable<String> PutBodyObservable(String token, String json, String url) {
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return getApiService().PutRequestBodyObservable(token, mRequestBody, url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}

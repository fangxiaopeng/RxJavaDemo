package com.example.rxjavademo.rxretrofit;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Title:       ApiService
 * <p>
 * Package:     com.example.rxjavademo.rxretrofit
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2018/6/25 下午11:44
 * <p>
 * Description:
 * <p>
 * <p>
 * Modification History:
 * <p>
 * Date       Author       Version      Description
 * -----------------------------------------------------------------
 * 2018/6/25    fxp       1.0         First Created
 * <p>
 * Github:  https://github.com/fangxiaopeng
 */
public interface ApiService {

    @FormUrlEncoded
    @POST
    Observable<String> PostFieldObservable(@Url String url, @FieldMap Map<String, Object> map);

    @POST
    Observable<String> PostQueryObservable(@Url String url, @QueryMap Map<String, Object> map);

    @POST
    Observable<String> PostQueryObservable(@Header("sh-token-sign") String authorization, @Url String url, @QueryMap Map<String, Object> map);

    @GET
    Observable<String> GetObservable(@Url String url, @QueryMap HashMap<String, Object> map);

    @GET
    Observable<String> GetHeadsObservable(@HeaderMap() Map<String, String>headmap, @Url String url, @QueryMap HashMap<String, Object> map);

    @GET
    Observable<String> GetObservable(@Header("sh-token-sign") String authorization, @Url String url, @QueryMap HashMap<String, Object> map);

    @PUT
    Observable<String> PutRequestBodyObservable(@Header("sh-token-sign") String authorization, @Body RequestBody mRequestBody, @Url String url);

    @DELETE
    Observable<String> DeleteObservable(@Body RequestBody mRequestBody, @Url String url);

    @POST
    Observable<String> PostRequestBodyObservable(@Body RequestBody mRequestBody, @Url String url);

    @POST
    Observable<String> PostRequestBodyObservable(@Header("sh-token-sign") String authorization, @Body RequestBody mRequestBody, @Url String url);

    @DELETE
    Observable<String> DeleteObservable(@Header("sh-token-sign") String authorization, @Url String url, @QueryMap HashMap<String, Object> map);


    @FormUrlEncoded
    @POST("/{Path}/{method}")
    Observable<String> post(@Path("Path") String path, @Path("method") String method, @FieldMap Map<String, Object> map);

    @POST
    Observable<String> uploadImage(@Url String url, @Body RequestBody file);

    @FormUrlEncoded
    @POST("/{Change}")
    Observable<String> PostJsonByObservable(@Path("Change") String change, @FieldMap HashMap<String, Object> map);

}


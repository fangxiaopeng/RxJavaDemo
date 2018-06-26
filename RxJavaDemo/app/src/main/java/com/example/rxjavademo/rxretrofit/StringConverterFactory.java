package com.example.rxjavademo.rxretrofit;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Title:       StringConverterFactory
 * <p>
 * Package:     com.example.rxjavademo.rxretrofit
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2018/6/25 下午11:58
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
public class StringConverterFactory extends Converter.Factory {

    private final String TAG = "StringConverterFactory";

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ConfigurationServiceConverter();
    }

    final class ConfigurationServiceConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(ResponseBody value) throws IOException {
            String result = value.string();
            Log.e(TAG, "result=" + result + "\n");
            return result;
        }
    }
}
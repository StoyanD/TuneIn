package com.stoyan.tunein.network;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stoyan on 2/9/18.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(NetworkConstants.TUNE_IN_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    public Converter.Factory provideConverter(){
        return GsonConverterFactory.create();
    }

    /**
     * Add '?render=json' to every call for JSON return body
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(){
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("render", "json")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }
}

package com.restervator.api;


import com.restervator.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The api client to retrieve the remote data, based on Retrofit
 *
 * @see <a href="https://square.github.io/retrofit>https://square.github.io/retrofit</a></a>
 * inspired by @see <a href="https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23">Retrofit— A simple Android tutorial</a>
 */
public class ApiClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://developers.zomato.com";

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    /**
     * @return OkHttpClient
     * @see <a href="https://square.github.io/okhttp/">OkHttpClient</a>
     */
    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }


    /**
     * the key is stored in a file called .api.key.properties
     *
     * @return http interceptor for adding the api key to each http request
     * @see <a href="https://square.github.io/okhttp/interceptors/">OkHttp Interceptor</a>
     */
    private static Interceptor getInterceptor() {
        return chain -> {
            Request modifiedRequest = chain.request().newBuilder()
                    .addHeader("user-key", BuildConfig.apikey)
                    .build();

            return chain.proceed(modifiedRequest);
        };
    }

    /**
     * @return http body interceptor
     */
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}

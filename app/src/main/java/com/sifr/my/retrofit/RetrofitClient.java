package com.sifr.my.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaid on 12/26/2017.
 */

public class RetrofitClient {

//    public static final String BASE_URL = "http://13.235.94.29/api/";  //  local aws reapmind
//    public static final String BASE_site = "http://13.235.94.29/";  //  local aws reapmind

  public static final String BASE_URL = "https://sifr.in/";  // live google cloud
//    public static final String BASE_site = "http://34.93.206.30/";  // live google cloud
//    private static Retrofit retrofit = null;

  public static RetrofitInterface getClient() {

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();

        return chain.proceed(request);
      }
    });

    Gson gson = new GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create();

    Retrofit adapter = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createDefaultOkHttpClient())
            .build();
    //Creating object for our interface
    RetrofitInterface api = adapter.create(RetrofitInterface.class);
    return api; // return the APIInterface object
  }

  private static OkHttpClient createDefaultOkHttpClient() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(60, TimeUnit.SECONDS)
//                .build();
//        return client;
    return new OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
  }
}
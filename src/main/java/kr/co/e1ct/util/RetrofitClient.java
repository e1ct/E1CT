package kr.co.e1ct.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.co.e1ct.services.EslipAPIs;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String ESLIP_API_URL = "https://scon.icpa.or.kr/";

    public static EslipAPIs getEslipAPIService(){
        return getInstance(ESLIP_API_URL).create(EslipAPIs.class);
    }

    private static Retrofit getInstance(String url){

        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

}

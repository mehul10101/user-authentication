package com.project.userAuthentication.clients.serviceGenerator;

import com.project.userAuthentication.clients.BookingService;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class BookingServiceGenerator {

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Retrofit retrofitDriver = new Retrofit.Builder().baseUrl("http://localhost:7030")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    public static BookingService getService(){
        return retrofitDriver.create(BookingService.class);
    }

}

package com.data;

import com.models.Posts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private Iposts iposts;
    private static RetrofitClient INSTANCE;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        iposts = retrofit.create(Iposts.class);
    }

    public static RetrofitClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitClient();
        }
        return INSTANCE;
    }

    public Single<List<Posts>> getPosts() {
        return iposts.getPosts();
    }
}

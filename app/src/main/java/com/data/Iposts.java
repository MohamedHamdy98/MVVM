package com.data;

import com.models.Posts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Iposts {
    @GET("posts")
    Single<List<Posts>> getPosts();
}

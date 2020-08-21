package com.data;

import com.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Iposts {
    @GET("posts")
    Call<List<Posts>> getPosts();
}

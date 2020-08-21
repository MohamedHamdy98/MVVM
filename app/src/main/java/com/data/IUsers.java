package com.data;

import com.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IUsers {
    @GET("users")
    Call<List<User>> getUsers();
}

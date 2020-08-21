package com.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.data.RetrofitClient;
import com.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {

    public MutableLiveData<List<Posts>> mutableLiveData = new MutableLiveData<>();

    public void getPosts() {
        RetrofitClient.getINSTANCE().getPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }
}

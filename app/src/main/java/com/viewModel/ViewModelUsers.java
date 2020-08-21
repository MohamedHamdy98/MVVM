package com.viewModel;

import androidx.lifecycle.MutableLiveData;


import com.data.UsersClient;
import com.models.Address;
import com.models.Company;
import com.models.Geo;
import com.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelUsers extends androidx.lifecycle.ViewModel {

    public MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

    public void getUsers(){
        UsersClient.getINSTANCE().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}

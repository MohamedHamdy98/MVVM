package com.data;



import com.models.Address;
import com.models.Company;
import com.models.Geo;
import com.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersClient {
    private IUsers iUsers;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static UsersClient INSTANCE;

    public UsersClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iUsers = retrofit.create(IUsers.class);
    }

    public static UsersClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UsersClient();
        }
        return INSTANCE;
    }

    public Call<List<User>> getUsers() {
        return iUsers.getUsers();
    }
}

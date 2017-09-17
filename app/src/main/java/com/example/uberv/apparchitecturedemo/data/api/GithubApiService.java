package com.example.uberv.apparchitecturedemo.data.api;

import com.example.uberv.apparchitecturedemo.data.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiService {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String username);
}

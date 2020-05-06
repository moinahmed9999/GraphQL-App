package com.moin.graphqlapp.Network;

import com.google.gson.JsonObject;
import com.moin.graphqlapp.Models.Data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("graphql")
    Call<Data> getUser(@Body JsonObject body);
}

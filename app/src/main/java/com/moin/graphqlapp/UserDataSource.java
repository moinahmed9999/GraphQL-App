package com.moin.graphqlapp;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.gson.JsonObject;
import com.moin.graphqlapp.Models.Data;
import com.moin.graphqlapp.Models.UserModel;
import com.moin.graphqlapp.Network.ApiProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Integer, UserModel> {

    public static final int FIRST_PAGE_OFFSET = 0;

    public static final int PAGE_SIZE = 5;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModel> callback) {
        JsonObject body = new JsonObject();
        int offset = FIRST_PAGE_OFFSET;
        body.addProperty("query", "query MyQuery { User (limit: 5, offset: " + offset + ") { user_id name university education course}}");

        ApiProvider.getApi().getUser(body).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.body()!=null) {
                    callback.onResult(response.body().getData().getUser(),null, FIRST_PAGE_OFFSET + 5);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel> callback) {
        JsonObject body = new JsonObject();
        int offset = params.key;
        body.addProperty("query", "query MyQuery { User (limit: 5, offset: " + offset + ") { user_id name university education course}}");

        ApiProvider.getApi().getUser(body).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.body()!=null) {
                    // if offset is greater than 0 (5, 10, ...etc)
                    // then we have a previous [set of users] to be loaded
                    Integer adjacentPageKey = (params.key > 0) ? params.key -5 : null;

                    callback.onResult(response.body().getData().getUser(), adjacentPageKey);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel> callback) {
        JsonObject body = new JsonObject();
        int offset = params.key;
        body.addProperty("query", "query MyQuery { User (limit: 5, offset: " + offset + ") { user_id name university education course}}");

        ApiProvider.getApi().getUser(body).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.body()!=null) {
                    // if response is not null "may be" we have some more data to load
                    Integer adjacentPageKey = params.key + 5;

                    callback.onResult(response.body().getData().getUser(), adjacentPageKey);
                } else {
                    // if response is null we "definitely" don't have more data to load
                    Integer adjacentPageKey = null;

                    // fake entry to denote end of list because the "callback.onResult()" method
                    // requires a non-null list
                    List<UserModel> list = new ArrayList<>();
                    list.add(new UserModel("-1","END", "END", "END", "END"));

                    callback.onResult(list, null);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }
}

package com.moin.graphqlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.gson.JsonObject;
import com.moin.graphqlapp.Models.Data;
import com.moin.graphqlapp.Models.UserData;
import com.moin.graphqlapp.Models.UserModel;
import com.moin.graphqlapp.Network.Api;
import com.moin.graphqlapp.Network.ApiProvider;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MAIN ACTIVITY";

    private ApolloClient apolloClient;
    private Api api;

    //getting recyclerview
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        apolloClient = ApolloConnector.setupApollo();
//        api = ApiProvider.getApi();

        //setting up recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our UserViewModel
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        //creating the Adapter
        final UserAdapter adapter = new UserAdapter(this);

        //observing the userPagedList from view model
        userViewModel.userPagedList.observe(this, new Observer<PagedList<UserModel>>() {
            @Override
            public void onChanged(PagedList<UserModel> userModels) {
                adapter.submitList(userModels);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);

    }

    void retrofitQuery() {
        JsonObject body = new JsonObject();
        int offset = 5;
        body.addProperty("query", "query MyQuery { User (limit: 5, offset: " + offset + ") { user_id name university education course}}");

        // works-----------------------------------------------

        api.getUser(body).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, retrofit2.Response<Data> response) {
                String str = response.body().toString();
                Log.d(TAG, "onResponse: \n" + str);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

        // doesn't work-----------------------------------------------

//        api.getUser(body).enqueue(new Callback<UserData>() {
//            @Override
//            public void onResponse(Call<UserData> call, retrofit2.Response<UserData> response) {
////                UserModel user = response.body().getUser().get(0);
//                String str = response.body().toString();
//                Log.d(TAG, "onResponse: \n" + str);
//            }
//
//            @Override
//            public void onFailure(Call<UserData> call, Throwable t) {
//                Log.d(TAG, "onFailure: \n");
//            }
//        });

        // works-----------------------------------------------

//        api.getUser(body).enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
//                Log.d(TAG, "onResponse: \n" + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.d(TAG, "onFailure: \n");
//            }
//        });
    }

    void apolloClientQuery() {
        apolloClient.query(MyQuery.builder().build()).enqueue(new ApolloCall.Callback<MyQuery.Data>() {
            String str="";
            @Override
            public void onResponse(@NotNull Response<MyQuery.Data> response) {
                for (MyQuery.User user : response.getData().User) {
                    str+="user_id: " + user.user_id + ", name: " + user.name
                            + ", university: " + user.university + ", education: " + user.education
                            + ", course: " + user.course+"\n";
                }

                Log.d(TAG, "onResponse: \n" + str);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    void apolloClientMutation() {
        apolloClient.mutate(MyMutation.builder().build()).enqueue(new ApolloCall.Callback<MyMutation.Data>() {
            String str="";
            @Override
            public void onResponse(@NotNull Response<MyMutation.Data> response) {
                for (MyMutation.Returning returning : response.getData().insert_User.returning) {
                    str+="user_id: " + returning.user_id + ", name: " + returning.name + "\n";
                }
                Log.d(TAG, "onResponse: \n" + str);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
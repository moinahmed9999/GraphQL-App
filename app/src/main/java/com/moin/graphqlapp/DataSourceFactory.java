package com.moin.graphqlapp;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.moin.graphqlapp.Models.UserModel;

public class DataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, UserModel>> userLiveDataSource =
            new MutableLiveData<>();

    @Override
    public DataSource create() {
        //getting our data source object
        UserDataSource userDataSource = new UserDataSource();

        //posting the dataSource to get the values
        userLiveDataSource.postValue(userDataSource);

        //returning the dataSource
        return userDataSource;
    }

    //getter for userLiveDataSource
    public MutableLiveData<PageKeyedDataSource<Integer, UserModel>> getUserLiveDataSource() {
        return userLiveDataSource;
    }
}

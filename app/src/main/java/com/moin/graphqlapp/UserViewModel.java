package com.moin.graphqlapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.moin.graphqlapp.Models.UserModel;

public class UserViewModel extends ViewModel {

    //creating livedata for PagedList and PagedKeyedDataSource
    LiveData<PagedList<UserModel>> userPagedList;
    LiveData<PageKeyedDataSource<Integer, UserModel>> liveDataSource;

    public UserViewModel() {
        //getting our data source factory
        DataSourceFactory userDataSourceFactory = new DataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = userDataSourceFactory.getUserLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(UserDataSource.PAGE_SIZE).build();

        //Building the paged list
        userPagedList = (new LivePagedListBuilder(userDataSourceFactory, pagedListConfig))
                .build();
    }
}

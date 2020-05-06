package com.moin.graphqlapp.Models;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("data")
    private UserData data;

    public UserData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=\n" + data +
                '}';
    }

    public void setData(UserData data) {
        this.data = data;
    }
}

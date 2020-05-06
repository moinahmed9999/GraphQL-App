package com.moin.graphqlapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserData {
    @SerializedName("User")
    private List<UserModel> User;

    public List<UserModel> getUser() {
        return User;
    }

    public void setUser(List<UserModel> user) {
        User = user;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "User=\n" + User +
                '}';
    }
}

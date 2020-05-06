package com.moin.graphqlapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UserModel {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("name")
    private String name;

    @SerializedName("university")
    private String university;

    @SerializedName("education")
    private String education;

    @SerializedName("course")
    private String course;

    public UserModel() {
    }

    public UserModel(String user_id, String name, String university, String education, String course) {
        this.user_id = user_id;
        this.name = name;
        this.university = university;
        this.education = education;
        this.course = course;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", education='" + education + '\'' +
                ", course='" + course + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(getUser_id(), userModel.getUser_id()) &&
                Objects.equals(getName(), userModel.getName()) &&
                Objects.equals(getUniversity(), userModel.getUniversity()) &&
                Objects.equals(getEducation(), userModel.getEducation()) &&
                Objects.equals(getCourse(), userModel.getCourse());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

package com.example.instagramclone.model.m.profileModel;

public class UserInfo {

    private String userName;
    private String birthday;
    private String email;
    private String gender;
    private String uId;
    private String phone;

    public UserInfo() {
    }

    public UserInfo(String userName, String birthday, String email, String gender, String uId, String phone) {
        this.userName = userName;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.uId = uId;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "userInfo{" +
                "userName='" + userName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", uId='" + uId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

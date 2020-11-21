package com.example.instagramclone.model.m.profileModel;

public class UpdateProfile {
    // this class update the profile Info
    // like display name, website, description/bio, email,phone,gender,birthday...
    // as update has  mixed item in (user) & (user_account_settings) directory of Firebase FireStore so I use another custom class
    // for update this item instead  of "UserInfo" & "UserSetting" class.......

    private String name;
    private String website;
    private String description;
    private String email;
    private String phone;
    private String gender;
    private String birthday;

    public UpdateProfile() {
    }

    public UpdateProfile(String name, String website, String description, String email, String phone, String gender, String birthday) {
        this.name = name;
        this.website = website;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}

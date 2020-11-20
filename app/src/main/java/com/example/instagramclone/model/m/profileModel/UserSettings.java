package com.example.instagramclone.model.m.profileModel;

public class UserSettings {

    private String description;
    private String displayName;
    private String following;
    private String followers;
    private String posts;
    private String profilePhoto;
    private String userName;
    private String website;
    public boolean isSuccess;

    public UserSettings() {
    }

    public UserSettings(String description, String displayName, String following, String followers, String posts, String profilePhoto, String userName, String website) {
        this.description = description;
        this.displayName = displayName;
        this.following = following;
        this.followers = followers;
        this.posts = posts;
        this.profilePhoto = profilePhoto;
        this.userName = userName;
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "accountSettings{" +
                "description='" + description + '\'' +
                ", displayName='" + displayName + '\'' +
                ", following='" + following + '\'' +
                ", followers='" + followers + '\'' +
                ", posts='" + posts + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", userName='" + userName + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}

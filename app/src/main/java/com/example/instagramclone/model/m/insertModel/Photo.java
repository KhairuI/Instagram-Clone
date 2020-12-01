package com.example.instagramclone.model.m.insertModel;

public class Photo {
    private String caption;
    private String postDate;
    private String imagePath;
    private String photoId;
    private String userId;
    private String tags;

    public Photo() {
        // empty constructor
    }

    public Photo(String caption, String postDate, String imagePath, String photoId, String userId, String tags) {
        this.caption = caption;
        this.postDate = postDate;
        this.imagePath = imagePath;
        this.photoId = photoId;
        this.userId = userId;
        this.tags = tags;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

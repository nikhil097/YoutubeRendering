package com.app.nikhil.youtuberenderingapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoItem implements Serializable {

    @SerializedName("videoTitle")
    String videoTitle;

    @SerializedName("videoLink")
    String videoLink;

    public String getVideoTitle() {

        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}

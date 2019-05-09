package com.app.nikhil.youtuberenderingapp;

import com.google.gson.annotations.SerializedName;

public class VideoItem {

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

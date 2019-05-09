package com.app.nikhil.youtuberenderingapp.ApiResponseBody;

import com.app.nikhil.youtuberenderingapp.POJO.VideoItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LinkResponseBody {


    @SerializedName("videoLinks")
    ArrayList<VideoItem> videoItems;


    public ArrayList<VideoItem> getVideoItems() {
        return videoItems;
    }

    public void setVideoItems(ArrayList<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }
}

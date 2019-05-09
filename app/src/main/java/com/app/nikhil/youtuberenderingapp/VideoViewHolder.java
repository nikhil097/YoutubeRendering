package com.app.nikhil.youtuberenderingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    TextView videoTitle;
    YouTubePlayerView youTubePlayerView;
    YouTubeThumbnailView youTubeThumbnailView;
    Button videoPlayButton;


    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        videoTitle=itemView.findViewById(R.id.videoTitle);
        youTubePlayerView=itemView.findViewById(R.id.youtubeVideoView);
        youTubeThumbnailView=itemView.findViewById(R.id.youtubeThumbnailView);
        videoPlayButton=itemView.findViewById(R.id.videoPlayButton);

    }

}

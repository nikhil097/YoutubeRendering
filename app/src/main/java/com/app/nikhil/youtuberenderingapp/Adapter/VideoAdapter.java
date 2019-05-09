package com.app.nikhil.youtuberenderingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.nikhil.youtuberenderingapp.POJO.VideoItem;
import com.app.nikhil.youtuberenderingapp.R;
import com.app.nikhil.youtuberenderingapp.Rest.Config;
import com.app.nikhil.youtuberenderingapp.UI.YoutubePlayerActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>{

    Context context;
    ArrayList<VideoItem> videoItemList;

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder videoViewHolder, int i) {

        final VideoItem videoItem=videoItemList.get(i);

        videoViewHolder.videoTitle.setText(videoItem.getVideoTitle());

        videoViewHolder.videoPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context,YoutubePlayerActivity.class).putExtra("videoItem",videoItem));


            }
        });




        videoViewHolder.youTubeThumbnailView.initialize(Config.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoItem.getVideoLink());
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return videoItemList.size();
    }

    public VideoAdapter(Context context, ArrayList<VideoItem> videoItemList) {
        this.context = context;
        this.videoItemList = videoItemList;
    }


}

package com.app.nikhil.youtuberenderingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public abstract class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

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
          //      videoViewHolder.youTubePlayerView.setVisibility(View.VISIBLE);
                videoViewHolder.videoPlayButton.setVisibility(View.GONE);
        //        videoViewHolder.youTubeThumbnailView.setVisibility(View.GONE);
            //    playVideo(videoViewHolder.youTubePlayerView,videoItem);

                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, Config.YOUTUBE_API_KEY, videoItem.getVideoLink());
                context.startActivity(intent);

            }
        });




        videoViewHolder.youTubeThumbnailView.initialize(Config.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoItem.videoLink);
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

    public class VideoViewHolder extends RecyclerView.ViewHolder{


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


    public abstract void playVideo(YouTubePlayerView youTubePlayerView,VideoItem videoItem);

}

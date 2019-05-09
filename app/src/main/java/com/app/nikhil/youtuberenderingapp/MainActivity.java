package com.app.nikhil.youtuberenderingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class MainActivity extends YouTubeBaseActivity  {

    LinkApiService linkApiService;
    RecyclerView youtubeVideosRv;
    YouTubePlayer player;

    YouTubePlayerView previousPlayerView;
    YouTubeThumbnailView previousThumbnailView;
    Button previousPlayButton;
    VideoViewHolder previousViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkApiService = new LinkApiService();

        youtubeVideosRv=findViewById(R.id.youtubeRv);

        fetchVideoItems();

    }


    private void fetchVideoItems()
    {
        linkApiService.getVideoItems(new ResponseCallback<LinkResponseBody>() {
            @Override
            public void success(LinkResponseBody linkResponseBody) {

                ArrayList<VideoItem> videoItems=linkResponseBody.getVideoItems();

                setupVideosRecyclerView(videoItems);

            }

            @Override
            public void failure(LinkResponseBody linkResponseBody) {

            }
        });

    }


    private void setupVideosRecyclerView(ArrayList<VideoItem> videoItemsList)
    {
        VideoAdapter videoAdapter= new VideoAdapter(this, videoItemsList) {
            @Override
            public void playVideo(VideoViewHolder videoViewHolder, final VideoItem videoItem) {


                if (previousViewHolder!=null)
                {

                }


              previousViewHolder=videoViewHolder;




                videoViewHolder.youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                        if (player!=null)
                        {
                            player.release();
                            player=null;
                        }

                        player=youTubePlayer;

                        player.loadVideo(videoItem.getVideoLink());
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
            }
        };

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        youtubeVideosRv.setLayoutManager(mLayoutManager);
        youtubeVideosRv.setItemAnimator(new DefaultItemAnimator());
        youtubeVideosRv.setAdapter(videoAdapter);

    }


}

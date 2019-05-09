package com.app.nikhil.youtuberenderingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class MainActivity extends YouTubeBaseActivity {

    LinkApiService linkApiService;
    RecyclerView youtubeVideosRv;
    YouTubePlayer player;

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
            public void playVideo(YouTubePlayerView youTubePlayerView, final VideoItem videoItem) {
                youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
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

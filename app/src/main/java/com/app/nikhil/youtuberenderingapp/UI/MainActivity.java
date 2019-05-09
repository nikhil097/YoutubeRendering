package com.app.nikhil.youtuberenderingapp.UI;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.app.nikhil.youtuberenderingapp.ApiResponseBody.LinkResponseBody;
import com.app.nikhil.youtuberenderingapp.R;
import com.app.nikhil.youtuberenderingapp.Rest.ResponseCallback;
import com.app.nikhil.youtuberenderingapp.Rest.LinkApiService;
import com.app.nikhil.youtuberenderingapp.Adapter.VideoAdapter;
import com.app.nikhil.youtuberenderingapp.POJO.VideoItem;
import com.app.nikhil.youtuberenderingapp.Adapter.VideoViewHolder;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class MainActivity extends YouTubeBaseActivity  {

    LinkApiService linkApiService;
    RecyclerView youtubeVideosRv;

    ProgressBar videosFetchingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkApiService = new LinkApiService();

        youtubeVideosRv=findViewById(R.id.youtubeRv);
        videosFetchingBar=findViewById(R.id.videoFetchingBar);

        fetchVideoItems();

    }


    private void fetchVideoItems()
    {
        linkApiService.getVideoItems(new ResponseCallback<LinkResponseBody>() {
            @Override
            public void success(LinkResponseBody linkResponseBody) {

                ArrayList<VideoItem> videoItems=linkResponseBody.getVideoItems();

                videosFetchingBar.setVisibility(View.GONE);

                setupVideosRecyclerView(videoItems);

            }

            @Override
            public void failure(LinkResponseBody linkResponseBody) {

            }
        });

    }


    private void setupVideosRecyclerView(ArrayList<VideoItem> videoItemsList)
    {
        VideoAdapter videoAdapter= new VideoAdapter(this, videoItemsList);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        youtubeVideosRv.setLayoutManager(mLayoutManager);
        youtubeVideosRv.setItemAnimator(new DefaultItemAnimator());
        youtubeVideosRv.setAdapter(videoAdapter);

    }


}

package com.cloudinary.academy_course.Fragments.VideoFeed;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.academy_course.databinding.ItemImageBinding;
import com.cloudinary.academy_course.databinding.VideoFeedItemBinding;
import com.cloudinary.android.cldvideoplayer.CldVideoPlayer;

import java.net.MalformedURLException;
import java.net.URL;

@UnstableApi
public class VideoViewHolder extends RecyclerView.ViewHolder {
    PlayerView videoView;

    public VideoViewHolder(@NonNull VideoFeedItemBinding binding) {
        super(binding.getRoot());
//        videoView = binding.playerView; // Replace with your PlayerView ID
    }

    public void bindVideo(String videoUrl, int screenHeight) {
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        layoutParams.height = screenHeight;
        videoView.setLayoutParams(layoutParams);

        try {
            CldVideoPlayer player = new CldVideoPlayer(videoView.getContext(), new URL(videoUrl));
            videoView.setPlayer(player.getPlayer());
            videoView.setControllerAutoShow(false);
            videoView.hideController();
            player.getPlayer().play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
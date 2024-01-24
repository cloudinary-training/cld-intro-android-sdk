package com.cloudinary.academy_course.Fragments.VideoFeed;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.cloudinary.academy_course.Fragments.transform_fragment.ImageViewHolder;
import com.cloudinary.academy_course.databinding.ItemImageBinding;
import com.cloudinary.academy_course.databinding.VideoFeedFragmentBinding;
import com.cloudinary.academy_course.databinding.VideoFeedItemBinding;
import com.cloudinary.android.cldvideoplayer.CldVideoPlayer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
@UnstableApi
public class VideoFeedAdapter extends RecyclerView.Adapter {

    private final List<String> videoUrls;
    private Context context;

    VideoFeedItemBinding binding;

    public VideoFeedAdapter(Context context, List<String> videoUrls) {
        this.context = context;
        this.videoUrls = videoUrls;
    }

    private int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = VideoFeedItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlayerView playerView = binding.playerView;

        String videoUrl = videoUrls.get(position);
        int screenHeight = getScreenHeight(context);

        ViewGroup.LayoutParams layoutParams = playerView.getLayoutParams();
        layoutParams.height = screenHeight;
        playerView.setLayoutParams(layoutParams);

        try {
            CldVideoPlayer player = new CldVideoPlayer(playerView.getContext(), new URL(videoUrl));
            player.getPlayer().setRepeatMode(Player.REPEAT_MODE_ALL);
            playerView.setPlayer(player.getPlayer());

            playerView.hideController();

            playerView.setUseController(false);

            player.getPlayer().play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }
}
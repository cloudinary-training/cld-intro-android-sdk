package com.cloudinary.academy_course.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.UploadWidgetFragmentBinding;
import com.cloudinary.academy_course.databinding.VideoPlayerFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.cldvideoplayer.CldVideoPlayer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class VideoPlayerFragment extends Fragment {

    private VideoPlayerFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = VideoPlayerFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setVideoPlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private void setVideoPlayer() {
        CldVideoPlayer player = null;
        player = new CldVideoPlayer(Objects.requireNonNull(getContext()),
                (MediaManager.get().url().resourceType("video").transformation(new Transformation<>().quality("auto")).generate("glacier")));
        binding.playerView.setPlayer(player.getPlayer());
        player.getPlayer().play();
    }
}





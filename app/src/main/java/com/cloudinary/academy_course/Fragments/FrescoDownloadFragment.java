package com.cloudinary.academy_course.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.academy_course.databinding.FrescoDownloadFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.download.fresco.FrescoDownloadRequestBuilderFactory;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Objects;

public class FrescoDownloadFragment extends Fragment {

    private FrescoDownloadFragmentBinding binding;

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Fresco.initialize(Objects.requireNonNull(getActivity()));
        binding = FrescoDownloadFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageViewWithFrescoIntegration();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private void setImageViewWithFrescoIntegration() {
        MediaManager.get().setDownloadRequestBuilderFactory(new FrescoDownloadRequestBuilderFactory());
        SimpleDraweeView imageView = binding.frescoDownloadImageview;
        MediaManager.get().download(Objects.requireNonNull(getActivity())).load(MediaManager.get().url().generate("coconuts")).into(imageView);
    }

}
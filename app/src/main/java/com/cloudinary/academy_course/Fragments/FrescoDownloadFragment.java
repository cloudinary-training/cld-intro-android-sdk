package com.cloudinary.academy_course.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.academy_course.databinding.FrescoDownloadFragmentBinding;
import com.cloudinary.academy_course.databinding.GlideDownloadFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.download.fresco.FrescoDownloadRequestBuilderFactory;
import com.cloudinary.android.download.glide.GlideDownloadRequestBuilderFactory;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoDownloadFragment extends Fragment {

    private FrescoDownloadFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Fresco.initialize(getActivity());
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

    private void setImageViewWithFrescoIntegration() {
        MediaManager.get().setDownloadRequestBuilderFactory(new FrescoDownloadRequestBuilderFactory());
        SimpleDraweeView imageView = binding.frescoDownloadImageview;
        MediaManager.get().download(getActivity()).load(MediaManager.get().url().generate("coconuts")).into(imageView);;
    }

}
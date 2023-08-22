package com.cloudinary.academy_course.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.GlideDownloadFragmentBinding;
import com.cloudinary.academy_course.databinding.GlideIntegrationFragmentBinding;
import com.cloudinary.android.CloudinaryRequest;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.ResponsiveUrl;

public class GlideDownloadFragment extends Fragment {

    private GlideDownloadFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = GlideDownloadFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageViewWithGlideIntegration();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setImageViewWithGlideIntegration() {
        ImageView imageView = binding.glideDownloadImageview;
        GlideApp.with(this).load(MediaManager.get().url().generate("sample.jpg")).into(imageView);;
    }

}
package com.cloudinary.academy_course.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.OptimizationFragmentBinding;
import com.cloudinary.academy_course.databinding.TransformFragmentBinding;
import com.cloudinary.android.MediaManager;

public class OptimizationFragment extends Fragment {

    private OptimizationFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = OptimizationFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setNoOptimizationImageView("lorikeet");
        setOptimizationImageView("lorikeet");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setNoOptimizationImageView(String publicId) {
        String URL = MediaManager.get().url().generate(publicId);
        ImageView transformImageview = binding.optimizationOriginalImageview;
        Glide.with(this).load(URL).into(transformImageview);
    }

    private void setOptimizationImageView(String publicId) {
        String URL = MediaManager.get().url().transformation(new Transformation().fetchFormat("webp").quality("auto").dpr("auto")).generate(publicId);
        ImageView transformImageview = binding.optimizationOptimizedImageview;
        Glide.with(this).load(URL).into(transformImageview);
    }

}



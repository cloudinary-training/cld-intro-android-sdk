package com.cloudinary.academy_course.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.TransformFragmentBinding;
import com.cloudinary.android.MediaManager;

public class TransformFragment extends Fragment {

    private TransformFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TransformFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageView("lorikeet");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setImageView(String publicId) {
        String URL = MediaManager.get().url().transformation(new Transformation().effect("grayscale")).generate(publicId);
        ImageView transformImageview = binding.transformImageview;
        Glide.with(this).load(URL).into(transformImageview);
    }

}



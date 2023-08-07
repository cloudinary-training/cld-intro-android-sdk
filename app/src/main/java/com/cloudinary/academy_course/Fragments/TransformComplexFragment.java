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
import com.cloudinary.academy_course.databinding.TransformComplexFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.transformation.TextLayer;

public class TransformComplexFragment extends Fragment {

    private TransformComplexFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TransformComplexFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageView("cheetah");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void setImageView(String publicId) {
        String URL = MediaManager.get().url().transformation(new Transformation()
                .effect("cartoonify").chain()
                .radius("max").chain()
                .effect("outline:100").color("lightgreen").chain()
                .background("lightblue").chain()
                .height(300).crop("scale")).generate(publicId);

        ImageView transformComplexImageview = binding.transformComplexImageview;
        Glide.with(this).load(URL).into(transformComplexImageview);




    }

}
package com.cloudinary.academy_course.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.Fragments.transform_fragment.TransformRecycleAdapter;
import com.cloudinary.academy_course.Fragments.transform_fragment.TransformRecycleClick;
import com.cloudinary.academy_course.databinding.TransformComplexFragmentBinding;
import com.cloudinary.android.MediaManager;

import java.util.Arrays;
import java.util.Objects;

public class TransformComplexFragment extends Fragment {

    private TransformComplexFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TransformComplexFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String url = MediaManager.get().url().transformation(new Transformation().gravity("auto").effect("sharpen:400").radius("max").fetchFormat("auto").quality("auto")).generate("lorikeet");
        setMainImageView(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private void setMainImageView(String url) {
        ImageView imageView = binding.complexTransformMainImage;
        Glide.with(Objects.requireNonNull(getActivity())).load(url).into(imageView);
    }
}
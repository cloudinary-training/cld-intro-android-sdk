package com.cloudinary.academy_course.Fragments;

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

import java.util.Arrays;

public class TransformComplexFragment extends Fragment implements TransformRecycleClick  {

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
        setMainImageView("https://res.cloudinary.com/adimizrahi2/image/upload/butterfly.jpg");
        initRecycleView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setMainImageView(String url) {
        ImageView imageView = binding.complexTransformMainImage;
        Glide.with(getActivity()).load(url).into(imageView);
    }

    private void initRecycleView() {
        RecyclerView recyclerView = binding.complexTransformRecyclerView;
        TransformRecycleAdapter adapter = new TransformRecycleAdapter(this, Arrays.asList("sample","sample", "sample", "sample"));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void itemClicked(String url) {
        setMainImageView(url);
    }


}
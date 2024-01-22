package com.cloudinary.academy_course.Fragments.transform_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.academy_course.databinding.TransformFragmentBinding;

import java.util.Arrays;

public class TransformFragment extends Fragment implements TransformRecycleClick {

    private TransformFragmentBinding binding;
    private RecyclerView recyclerView;

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
        initRecycleView();
        setMainImageView("https://res.cloudinary.com/adimizrahi2/image/upload/v1705927762/butterfly.jpg");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setMainImageView(String url) {
        ImageView imageView = binding.transformMainImage;
        Glide.with(getActivity()).load(url).into(imageView);
    }

    private void initRecycleView() {
        recyclerView = binding.transformRecyclerView;
        TransformRecycleAdapter adapter = new TransformRecycleAdapter(this, Arrays.asList("sample","sample", "sample", "sample"));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void itemClicked(String url) {
        setMainImageView(url);
    }
}



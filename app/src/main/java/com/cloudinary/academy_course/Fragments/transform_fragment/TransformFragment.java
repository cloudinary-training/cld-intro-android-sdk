package com.cloudinary.academy_course.Fragments.transform_fragment;

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
import com.cloudinary.academy_course.databinding.TransformFragmentBinding;
import com.cloudinary.android.MediaManager;

import java.util.Arrays;
import java.util.List;

public class TransformFragment extends Fragment implements TransformRecycleClick {

    private TransformFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TransformFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();
        String url = MediaManager.get().url().generate("lorikeet");
        setMainImageView(url);
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
        RecyclerView recyclerView = binding.transformRecyclerView;
        TransformRecycleAdapter adapter = new TransformRecycleAdapter(this, buildTransformUrls());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapter);
    }

    private List<String> buildTransformUrls() {
        String lorikeetOriginalUrl = MediaManager.get().url().generate("lorikeet");
        String lorikeetSharpenUrl = MediaManager.get().url().transformation(new Transformation().effect("sharpen:400")).generate("lorikeet");
        String lorikeetGrayscaleUrl = MediaManager.get().url().transformation(new Transformation().effect("grayscale")).generate("lorikeet");
        String lorikeetBlurUrl = MediaManager.get().url().transformation(new Transformation().effect("blur:500")).generate("lorikeet");
        String lorikeetColorizeUrl = MediaManager.get().url().transformation(new Transformation().color("violet").effect("colorize:30")).generate("lorikeet");
        return Arrays.asList(lorikeetOriginalUrl, lorikeetSharpenUrl, lorikeetGrayscaleUrl, lorikeetBlurUrl, lorikeetColorizeUrl);
    }

    @Override
    public void itemClicked(String url) {
        setMainImageView(url);
    }
}



package com.cloudinary.academy_course.Fragments;

import static java.util.Objects.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.academy_course.databinding.GlideDownloadFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.download.glide.GlideDownloadRequestBuilderFactory;

public class GlideDownloadFragment extends Fragment {

    private GlideDownloadFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
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

    @SuppressLint("UseRequireInsteadOfGet")
    private void setImageViewWithGlideIntegration() {
        MediaManager.get().setDownloadRequestBuilderFactory(new GlideDownloadRequestBuilderFactory());
        ImageView imageView = binding.glideDownloadImageview;
        MediaManager.get().download(requireNonNull(getActivity())).load(MediaManager.get().url().generate("swing")).into(imageView);
    }

}
package com.cloudinary.academy_course.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.GlideIntegrationFragmentBinding;
import com.cloudinary.academy_course.databinding.WelcomeFragmentBinding;
import com.cloudinary.android.CloudinaryRequest;
import com.cloudinary.android.ResponsiveUrl;

public class GlideIntegrationFragment extends Fragment {

    private GlideIntegrationFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = GlideIntegrationFragmentBinding.inflate(inflater, container, false);
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
        ImageView imageView = binding.glideIntegrationImageview;
        GlideApp.with(imageView)
                .load(new CloudinaryRequest.Builder("sample")
                        .transformation(new Transformation().effect("blur"))
                        .responsive(ResponsiveUrl.Preset.AUTO_FILL)
                        .build())
                .into(imageView);
    }

}
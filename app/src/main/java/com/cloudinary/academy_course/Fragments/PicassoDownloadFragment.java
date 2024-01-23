package com.cloudinary.academy_course.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.academy_course.databinding.GlideDownloadFragmentBinding;
import com.cloudinary.academy_course.databinding.PicassoDownloadFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.download.glide.GlideDownloadRequestBuilderFactory;
import com.cloudinary.android.download.picasso.PicassoDownloadRequestBuilderFactory;

public class PicassoDownloadFragment extends Fragment {

    private PicassoDownloadFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = PicassoDownloadFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageViewWithPicassoIntegration();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setImageViewWithPicassoIntegration() {
        MediaManager.get().setDownloadRequestBuilderFactory(new PicassoDownloadRequestBuilderFactory());
        ImageView imageView = binding.picassoDownloadImageview;
        MediaManager.get().download(getActivity()).load("https://res.cloudinary.com/adimizrahi2/image/upload/v1705927762/butterfly.jpg").into(imageView);;
    }

}
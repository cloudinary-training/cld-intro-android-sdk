package com.cloudinary.academy_course.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.Utils.Utils;
import com.cloudinary.academy_course.databinding.OptimizationFragmentBinding;
import com.cloudinary.android.MediaManager;

import java.io.ByteArrayOutputStream;

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
        String url = MediaManager.get().url().generate(publicId);
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        int fileLength = byteArray.length;

                        ImageView imageView = binding.optimizationOriginalImageview;
                        imageView.setImageBitmap(bitmap);

                        String imageDimensions = Utils.getImageWidhtAndHeightString(imageView.getDrawable());
                        String imageSize = Utils.getImageSize(fileLength);
                        binding.optimizationOriginalTextviewDimensions.setText(String.format("%s - ", imageDimensions));
                        binding.optimizationOriginalTextviewSize.setText(imageSize);
                    }
                });
    }

    private void setOptimizationImageView(String publicId) {
        String url = MediaManager.get().url().transformation(new Transformation().crop("scale").width(800).fetchFormat("avif").quality("auto").dpr("auto")).generate(publicId);
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        int fileLength = byteArray.length;

                        ImageView imageView = binding.optimizationOptimizedImageview;
                        imageView.setImageBitmap(bitmap);

                        String imageDimensions = Utils.getImageWidhtAndHeightString(imageView.getDrawable());
                        String imageSize = Utils.getImageSize(fileLength);
                        binding.optimizationOptimizedTextviewDimensions.setText(String.format("%s - ", imageDimensions));
                        binding.optimizationOptimizedTextviewSize.setText(imageSize);
                    }
                });
    }

}



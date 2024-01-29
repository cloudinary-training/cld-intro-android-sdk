package com.cloudinary.academy_course.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.Utils.Utils;
import com.cloudinary.academy_course.databinding.OptimizationFragmentBinding;
import com.cloudinary.academy_course.databinding.TransformFragmentBinding;
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
                        int bytes = bitmap.getByteCount();
                        double megabytes = bytes / (1024.0 * 1024.0);

                        // Get the length of the bitmap's compressed data in bytes
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        int fileLength = byteArray.length;

                        // Now 'fileLength' represents the length of the image file in bytes

                        ImageView imageView = binding.optimizationOriginalImageview;
                        imageView.setImageBitmap(bitmap);

                        String imageDimensions = Utils.getImageWidhtAndHeightString(imageView.getDrawable());
                        String imageSize = Utils.getImageSize(fileLength);
                        binding.optimizationOriginalTextview.setText(imageDimensions + " " + imageSize);
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
                        int bytes = bitmap.getByteCount();
                        double megabytes = bytes / (1024.0 * 1024.0);

                        // Get the length of the bitmap's compressed data in bytes
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        int fileLength = byteArray.length;

                        // Now 'fileLength' represents the length of the image file in bytes

                        ImageView imageView = binding.optimizationOptimizedImageview;
                        imageView.setImageBitmap(bitmap);

                        String imageDimensions = Utils.getImageWidhtAndHeightString(imageView.getDrawable());
                        String imageSize = Utils.getImageSize(fileLength);
                        binding.optimizationOptimizedTextview.setText(imageDimensions + " " + imageSize);
                    }
                });
    }

}



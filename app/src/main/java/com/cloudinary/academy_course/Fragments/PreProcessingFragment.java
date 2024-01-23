package com.cloudinary.academy_course.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cloudinary.academy_course.databinding.PreprocessFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.preprocess.BitmapDecoder;
import com.cloudinary.android.preprocess.BitmapEncoder;
import com.cloudinary.android.preprocess.DimensionsValidator;
import com.cloudinary.android.preprocess.ImagePreprocessChain;
import com.cloudinary.android.preprocess.Limit;

import java.util.Map;

public class PreProcessingFragment extends Fragment {

    private PreprocessFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PreprocessFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOriginalImage();
        preProcessImage();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setOriginalImage() {
        ImageView originalImageView = binding.preprocessingOriginalImageview;
        Glide.with(this).load("https://res.cloudinary.com/adimizrahi2/image/upload/v1705927762/butterfly.jpg").into(originalImageView);
    }

    private void preProcessImage() {
        Uri fileUri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/drawable/butterfly");
        String requestId = MediaManager.get().upload(fileUri)
                .unsigned("unsigned-image")
                .preprocess(new ImagePreprocessChain()
                        .loadWith(new BitmapDecoder(200, 200))
                        .addStep(new Limit(200, 200))
                        .addStep(new DimensionsValidator(10,10,200,200))
                        .saveWith(new BitmapEncoder(BitmapEncoder.Format.JPEG, 80)))
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d("Course", "Upload starting");
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Log.d("Course", "Upload progress");
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String publicId = (String) resultData.get("public_id");
                        setImageView(publicId);
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.d("Course", "Upload error");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                })
                .dispatch(getActivity());
    }

    private void setImageView(String publicId) {
        String URL = MediaManager.get().url().generate(publicId);
        ImageView uploadImageview = binding.preprocessingOptimizedImageview;
        Glide.with(this).load(URL).into(uploadImageview);
    }

}
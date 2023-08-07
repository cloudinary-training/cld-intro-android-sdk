package com.cloudinary.academy_course.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cloudinary.academy_course.databinding.UploadLargeFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.Map;

public class UploadLargeFragment extends Fragment {

    private UploadLargeFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = UploadLargeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        uploadLargeImage();
    }


    private void uploadLargeImage() {
        Uri fileUri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/drawable/green");
        String requestId = MediaManager.get().upload(fileUri)
                .unsigned("unsigned-image")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d("Academy Course", "starting large upload!!");
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Log.d("Academy Course", "large upload in progress");
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String publicId = (String) resultData.get("public_id");
                        Log.d("Academy Course", "large upload completed!!");
                        setImageView(publicId);
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.d("Academy Course", "there has been an error");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                })
                .dispatch();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getImageView() {
        return imageView;
    }
}
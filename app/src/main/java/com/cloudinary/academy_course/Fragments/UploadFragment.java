package com.cloudinary.academy_course.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.UploadFragmentBinding;
import com.cloudinary.academy_course.databinding.WelcomeFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.Map;

public class UploadFragment extends Fragment {

    private UploadFragmentBinding binding;

    private static final int PICK_IMAGE_REQUEST = 12345;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = UploadFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUploadButton();
    }

    private void setUploadButton() {
        Button uploadButton = binding.uploadButton;
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        intent.setType("image/*");

        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadImage(Uri fileUri) {
//         Uri fileUri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/drawable/butterfly");
        String requestId = MediaManager.get().upload(fileUri)
                .unsigned("unsigned-image")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d("Academy Course", "starting upload!!");
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Log.d("Academy Course", "upload in progress");
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        binding.uploadProgressbar.setVisibility(View.INVISIBLE);
                        String publicId = (String) resultData.get("public_id");
                        Log.d("Academy Course", "completed!!");
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


    private void setImageView(String publicId) {
        String URL = MediaManager.get().url().generate(publicId);
        ImageView uploadImageview = binding.uploadImageview;
        Glide.with(this).load(URL).into(uploadImageview);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            uploadImage(selectedImageUri);
            binding.uploadProgressbar.setVisibility(View.VISIBLE);
        }
    }
}



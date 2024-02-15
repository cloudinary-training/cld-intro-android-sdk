package com.cloudinary.academy_course.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cloudinary.academy_course.R;
import com.cloudinary.academy_course.Utils.Utils;
import com.cloudinary.academy_course.databinding.PreprocessFragmentBinding;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.preprocess.BitmapDecoder;
import com.cloudinary.android.preprocess.BitmapEncoder;
import com.cloudinary.android.preprocess.DimensionsValidator;
import com.cloudinary.android.preprocess.ImagePreprocessChain;
import com.cloudinary.android.preprocess.Limit;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Objects;

/** @noinspection ALL*/
public class PreProcessingFragment extends Fragment {

    private PreprocessFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
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

    @SuppressLint("SetTextI18n")
    private void setOriginalImage() {
        ImageView originalImageView = binding.preprocessingOriginalImageview;
        originalImageView.setImageResource(R.drawable.coffee_with_a_view);

        TextView originalTextView = binding.preprocessingOriginalTextview;

        originalImageView.setDrawingCacheEnabled(true);

        Bitmap bitmap = ((BitmapDrawable)originalImageView.getDrawable()).getBitmap();

        originalImageView.setDrawingCacheEnabled(false);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        int fileLength = byteArray.length;

        String imageDimensions = Utils.getImageWidhtAndHeightString(originalImageView.getDrawable());
        String imageSize = Utils.getImageSize(fileLength);
        originalTextView.setText(imageDimensions + " " + imageSize);

    }

    private void preProcessImage() {
        Uri fileUri = Uri.parse("android.resource://"+ Objects.requireNonNull(getActivity()).getPackageName()+"/drawable/coffee_with_a_view");
        String requestId = MediaManager.get().upload(fileUri)
                .unsigned("unsigned-image")
                .preprocess(new ImagePreprocessChain()
                        .loadWith(new BitmapDecoder(1000, 1000))
                        .addStep(new Limit(1000, 1000))
                        .addStep(new DimensionsValidator(10,10,1000,1000))
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
        Glide.with(this)
                .asBitmap()
                .load(URL)
                .into(new SimpleTarget<Bitmap>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                        int bytes = bitmap.getByteCount();
                        double megabytes = bytes / (1024.0 * 1024.0);

                        // Get the length of the bitmap compressed data in bytes
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        int fileLength = byteArray.length;

                        // Now 'fileLength' represents the length of the image file in bytes

                        ImageView imageView = binding.preprocessingOptimizedImageview;
                        imageView.setImageBitmap(bitmap);

                        String imageDimensions = Utils.getImageWidhtAndHeightString(imageView.getDrawable());
                        String imageSize = Utils.getImageSize(fileLength);
                        binding.preprocessingOptimizedTextview.setText(imageDimensions + " " + imageSize);
                    }
                });
    }

}
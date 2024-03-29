package com.cloudinary.academy_course.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cloudinary.academy_course.databinding.UploadWidgetFragmentBinding;
import com.cloudinary.android.UploadRequest;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.uploadwidget.UploadWidget;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UploadWidgetFragment extends Fragment {

    final int UPLOAD_WIDGET_CODE = 123451;

    private UploadWidgetFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = UploadWidgetFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUploadButton();
    }

    private void setUploadButton() {
        Button uploadButton = binding.uploadWidgetButton;
        uploadButton.setOnClickListener(view -> openUploadWidget());
    }

    private void openUploadWidget() {
        UploadWidget.startActivity(requireActivity(), UPLOAD_WIDGET_CODE);
        binding.uploadWidgetProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void handleResultWidgetResult(Intent data) {
        List<UploadWidget.Result> results = data.getParcelableArrayListExtra(UploadWidget.RESULT_EXTRA);
        UploadRequest request = UploadWidget.preprocessResult(getActivity(), Objects.requireNonNull(results).get(0));
        request.unsigned("unsigned-image").callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
            }

            @SuppressLint("UseRequireInsteadOfGet")
            @Override
            public void onSuccess(String requestId, Map resultData) {
                ImageView uploadWidgetImageview = binding.uploadWidgetImageview;
                String secureUrl = (String) resultData.get("secure_url");
                Glide.with(Objects.requireNonNull(getActivity())).load(secureUrl).into(uploadWidgetImageview);
                binding.uploadWidgetProgressbar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {

            }
        }).dispatch(getActivity());
    }
}



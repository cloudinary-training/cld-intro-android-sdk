package com.cloudinary.academy_course.Fragments.VideoFeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.cloudinary.Transformation;
import com.cloudinary.academy_course.databinding.TransformFragmentBinding;
import com.cloudinary.academy_course.databinding.VideoFeedFragmentBinding;
import com.cloudinary.android.MediaManager;

import java.util.ArrayList;
import java.util.List;

public class VideoFeedFragment extends Fragment {

    private VideoFeedFragmentBinding binding;

    public VideoFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = VideoFeedFragmentBinding.inflate(inflater, container, false);
        ViewPager2 recyclerView = binding.viewPager;

        List<String> videoUrls = new ArrayList<>();
        videoUrls.add(MediaManager.get().url().resourceType("video").transformation(new Transformation<>().quality("auto")).generate("backpack"));
        videoUrls.add(MediaManager.get().url().resourceType("video").transformation(new Transformation<>().quality("auto")).generate("test-assets/waterfall"));
        videoUrls.add(MediaManager.get().url().resourceType("video").transformation(new Transformation<>().quality("auto")).generate("cove"));
        videoUrls.add(MediaManager.get().url().resourceType("video").transformation(new Transformation<>().quality("auto")).generate("kids-hiking"));
        VideoFeedAdapter videoFeedAdapter = new VideoFeedAdapter(requireContext(), videoUrls);
        recyclerView.setAdapter(videoFeedAdapter);
        return binding.getRoot();

    }
}

package com.cloudinary.academy_course.Fragments.transform_fragment;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.academy_course.databinding.ItemImageBinding;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ImageViewHolder(@NonNull ItemImageBinding binding) {
        super(binding.getRoot());
        imageView = binding.itemImageview; // Replace with your ImageView ID
    }
}

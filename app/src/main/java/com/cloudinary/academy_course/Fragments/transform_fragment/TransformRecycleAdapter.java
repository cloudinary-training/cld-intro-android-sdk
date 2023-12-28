package com.cloudinary.academy_course.Fragments.transform_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.Transformation;
import com.cloudinary.academy_course.R;
import com.cloudinary.academy_course.databinding.ItemImageBinding;
import com.cloudinary.android.MediaManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TransformRecycleAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private List<String> imagePublicIds; // Replace with your data source

    private ItemImageBinding itemViewBinding;

    private Context context;

    public TransformRecycleAdapter(List<String> imagePublicIds) {
        this.imagePublicIds = imagePublicIds;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        itemViewBinding = ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ImageViewHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imagePublicId = imagePublicIds.get(position);

        // Load the image into the ImageView using a library like Picasso or Glide
        setText(position);
        setImageView(position);
    }

    private void setText(int position) {
        switch(position) {
            case 0:
                itemViewBinding.imageCellTitle.setText("Original");
                break;
            case 1:
                itemViewBinding.imageCellTitle.setText("Greyscale");
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imagePublicIds.size();
    }

        private void setImageView(int position) {
        String url = null;
        switch(position) {
            case 0:
                url = MediaManager.get().url().generate(imagePublicIds.get(position));
                break;
            case 1:
                url = MediaManager.get().url().transformation(new Transformation().effect("grayscale")).generate(imagePublicIds.get(position));
                break;
            default:
                break;
        }
        ImageView transformImageview = itemViewBinding.itemImageview;
        Glide.with(context).load(url).into(transformImageview);
    }
}


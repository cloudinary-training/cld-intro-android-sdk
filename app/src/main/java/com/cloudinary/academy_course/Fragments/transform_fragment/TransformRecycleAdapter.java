package com.cloudinary.academy_course.Fragments.transform_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.academy_course.databinding.ItemImageBinding;

import java.util.List;

public class TransformRecycleAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final List<String> imageUrls; // Replace with your data source

    private ItemImageBinding itemViewBinding;

    private Context context;

    private final TransformRecycleClick delegate;

    public TransformRecycleAdapter(TransformRecycleClick delegate, List<String> imageUrls) {
        this.delegate = delegate;
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        itemViewBinding = ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageViewHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        // Load the image into the ImageView using a library like Picasso or Glide
        setText(holder.getBindingAdapterPosition());
        setImageView(holder.getBindingAdapterPosition());

        // Set click listener
        holder.imageView.setOnClickListener(view -> {
            if (delegate != null) {
                delegate.itemClicked(imageUrls.get(position));
            }
        });
    }

    private void setText(int position) {
        switch (position) {
            case 0:
                itemViewBinding.imageCellTitle.setText("Original");
                break;
            case 1:
                itemViewBinding.imageCellTitle.setText("Grayscale");
                 break;
            case 2:
                itemViewBinding.imageCellTitle.setText("Sharpen");
                break;
            case 3:
                itemViewBinding.imageCellTitle.setText("Vignette");
                break;
            case 4:
                itemViewBinding.imageCellTitle.setText("Tint");
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    private void setImageView(int position) {
        String url = imageUrls.get(position);
        ImageView transformImageview = itemViewBinding.itemImageview;
        Glide.with(context).load(url).into(transformImageview);
    }
}


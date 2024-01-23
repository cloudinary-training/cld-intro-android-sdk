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
    private final List<String> imagePublicIds; // Replace with your data source

    private ItemImageBinding itemViewBinding;

    private Context context;

    private final TransformRecycleClick delegate;

    public TransformRecycleAdapter(TransformRecycleClick delegate, List<String> imagePublicIds) {
        this.delegate = delegate;
        this.imagePublicIds = imagePublicIds;
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
        String imagePublicId = imagePublicIds.get(position);

        // Load the image into the ImageView using a library like Picasso or Glide
        setText(holder.getBindingAdapterPosition());
        setImageView(holder.getBindingAdapterPosition());

        // Set click listener
        holder.imageView.setOnClickListener(view -> {
            if (delegate != null) {
                switch (holder.getBindingAdapterPosition()) {
                    case 0:
                        delegate.itemClicked("https://res.cloudinary.com/adimizrahi2/image/upload/e_sharpen:400/butterfly.jpg");
                        break;
                    case 1:
                        delegate.itemClicked("https://res.cloudinary.com/adimizrahi2/image/upload/e_grayscale/butterfly.jpg");
                        break;
                    case 2:
                        delegate.itemClicked("https://res.cloudinary.com/adimizrahi2/image/upload/e_cartoonify/butterfly.jpg");
                        break;
                    case 3:
                        delegate.itemClicked("https://res.cloudinary.com/adimizrahi2/image/upload/e_sepia/butterfly.jpg");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setText(int position) {
        switch (position) {
            case 0:
                itemViewBinding.imageCellTitle.setText("Sharpen");
                 break;
            case 1:
                itemViewBinding.imageCellTitle.setText("Grayscale");
                break;
            case 2:
                itemViewBinding.imageCellTitle.setText("Cartooinfy");
                break;
            case 3:
                itemViewBinding.imageCellTitle.setText("Sepia");
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
        switch (position) {
            case 0:
                url = "https://res.cloudinary.com/adimizrahi2/image/upload/e_sharpen:400/butterfly.jpg";
                break;
            case 1:
                url = "https://res.cloudinary.com/adimizrahi2/image/upload/e_grayscale/butterfly.jpg";
                break;
            case 2:
                url = "https://res.cloudinary.com/adimizrahi2/image/upload/e_cartoonify/butterfly.jpg";
                break;
            case 3:
                url = "https://res.cloudinary.com/adimizrahi2/image/upload/e_sepia/butterfly.jpg";
                break;
            default:
                break;
        }
        ImageView transformImageview = itemViewBinding.itemImageview;
        Glide.with(context).load(url).into(transformImageview);
    }
}


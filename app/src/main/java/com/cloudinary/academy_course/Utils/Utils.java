package com.cloudinary.academy_course.Utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.cloudinary.academy_course.R;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static void setStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity , R.color.main));
    }

    public static String getImageWidhtAndHeightString(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            // If the drawable is not a BitmapDrawable, you can use other methods to convert it to a Bitmap
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return width + "x" + height;
    }

    public static String getImageSizeString(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            // If the drawable is not a BitmapDrawable, create a temporary Bitmap and draw the drawable onto it
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();

            // Handle zero or negative dimensions
            if (width <= 0 || height <= 0) {
                return "Invalid Size";
            }

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        // Calculate the size of the Bitmap in bytes
        int bytes = bitmap.getByteCount();

        // Convert bytes to megabytes
        double megabytes = bytes / (1024.0 * 1024.0);

        return String.format("%.2f MB", megabytes);
    }
}

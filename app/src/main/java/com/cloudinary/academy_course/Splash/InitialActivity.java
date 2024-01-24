package com.cloudinary.academy_course.Splash;

import android.content.Intent;
import android.os.Bundle;

import com.cloudinary.academy_course.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

import androidx.navigation.ui.AppBarConfiguration;

import com.cloudinary.academy_course.databinding.ActivitySplashBinding;
import com.cloudinary.academy_course.Utils.Utils;

public class InitialActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(this, MainActivity.class);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 4000);
    }



}
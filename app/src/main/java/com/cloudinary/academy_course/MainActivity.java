package com.cloudinary.academy_course;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cloudinary.academy_course.Fragments.UploadWidgetFragment;
import com.cloudinary.academy_course.Fragments.WelcomeFragment;
import com.cloudinary.academy_course.NavigationView.NavigationViewListener;
import com.cloudinary.academy_course.databinding.ActivityMainBinding;
import com.cloudinary.academy_course.utils.Utils;
import com.cloudinary.android.MediaManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.navigation.NavigationView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private NavigationViewListener navigationViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Utils.setStatusBarColor(this);
        initCloudinary();
        setDrawer();
        setNavigationView();
        setFirstFragment();
    }

    private void initCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "jen-brissman");
        config.put("secure", true);
        MediaManager.init(this, config);
    }

    private void setNavigationView() {
        navigationViewListener = new NavigationViewListener(this);
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(navigationViewListener);
    }

    private void setDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setFirstFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment welcomeFragment = new WelcomeFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, welcomeFragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            if (fragments.get(0) instanceof UploadWidgetFragment) {
                ((UploadWidgetFragment) fragments.get(0)).handleResultWidgetResult(data);
            }
        }
    }
}
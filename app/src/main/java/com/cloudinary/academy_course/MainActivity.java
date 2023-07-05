package com.cloudinary.academy_course;

import android.os.Bundle;


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

import com.cloudinary.academy_course.Fragments.WelcomeFragment;
import com.cloudinary.academy_course.NavigationView.NavigationViewListener;
import com.cloudinary.academy_course.databinding.ActivityMainBinding;
import com.cloudinary.android.MediaManager;
import com.google.android.material.navigation.NavigationView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private NavigationViewListener navigationViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCloudinary();
        setDrawer();
        setNavigationView();
        setFirstFragment();
    }

    private void initCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "jenbrissman");
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
}
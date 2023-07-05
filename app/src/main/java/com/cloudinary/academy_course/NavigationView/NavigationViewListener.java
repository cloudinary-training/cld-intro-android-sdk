package com.cloudinary.academy_course.NavigationView;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cloudinary.academy_course.Fragments.TransformComplexFragment;
import com.cloudinary.academy_course.Fragments.UploadFragment;
import com.cloudinary.academy_course.R;
import com.cloudinary.academy_course.Fragments.TransformFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {

    AppCompatActivity activity;

    public NavigationViewListener(AppCompatActivity activity) {
        this.activity = activity;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (id == R.id.nav_transform) {
            fragment = new TransformFragment();
        } else if (id == R.id.nav_complex_transform) {
            fragment = new TransformComplexFragment();
        } else if (id == R.id.nav_upload) {
            fragment = new UploadFragment();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.cloudinary.academy_course.NavigationView;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cloudinary.academy_course.Fragments.TransfromComplexFragment;
import com.cloudinary.academy_course.Fragments.UploadFragment;
import com.cloudinary.academy_course.R;
import com.cloudinary.academy_course.Fragments.TransfromFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {

    Activity activity;

    public NavigationViewListener(Activity activity) {
        this.activity = activity;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (id == R.id.nav_transform) {
            fragment = new TransfromFragment();
        } else if (id == R.id.nav_complex_transform) {
            fragment = new TransfromComplexFragment();
        } else if (id == R.id.nav_upload) {
            fragment = new UploadFragment();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

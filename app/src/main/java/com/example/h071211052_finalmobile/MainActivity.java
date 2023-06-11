package com.example.h071211052_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.h071211052_finalmobile.fragment.FavoriteFragment;
import com.example.h071211052_finalmobile.fragment.MovieFragment;
import com.example.h071211052_finalmobile.fragment.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String language = "en-US";
    private ProgressBar progressBar; // Deklarasikan ProgressBar

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottonnav);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieFragment fragment1 = new MovieFragment();
        TvShowFragment fragment2 = new TvShowFragment();
        FavoriteFragment fragment3 = new FavoriteFragment();

        Fragment fragment =
                fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());
        if (!(fragment instanceof MovieFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.cl_container, fragment1,
                            MovieFragment.class.getSimpleName())
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (item.getItemId() == R.id.item_1) {

                transaction.replace(R.id.cl_container, fragment1);
            } else if (item.getItemId() == R.id.item_2) {

                transaction.replace(R.id.cl_container, fragment2);
            } else if (item.getItemId() == R.id.item_3) {

                transaction.replace(R.id.cl_container, fragment3);
            } else {

                return false;
            }

            transaction.commit();
            return true;
        });
    }

}

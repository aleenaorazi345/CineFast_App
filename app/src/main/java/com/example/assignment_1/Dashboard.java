package com.example.assignment_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Dashboard extends AppCompatActivity {

    Button btnOpenSecondActiivty;
    Fragment homeFragment;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        manager = getSupportFragmentManager();

        // Create HomeFragment instance
        homeFragment = new HomeFragment();

        // Add it once to the container and hide it
        manager.beginTransaction()
                .add(R.id.fragment_container, homeFragment, "HOME")
                .hide(homeFragment)
                .commit();

        btnOpenSecondActiivty = findViewById(R.id.btnOpenSecondActiivty);

        btnOpenSecondActiivty.setOnClickListener(v -> {
            // Show HomeFragment
            manager.beginTransaction()
                    .show(homeFragment)

                    .addToBackStack(null) // optional: allows back press to hide
                    .commit();
                     btnOpenSecondActiivty.setVisibility(View.GONE); // ✅ correct
        });
    }
}
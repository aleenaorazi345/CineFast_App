package com.example.assignment_1;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import android.content.Context;
import androidx.core.view.MenuProvider;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter adapter;
    TabLayoutMediator mediator;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPreferences sPref;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // MODERN WAY TO ADD MENU (Replaces setHasOptionsMenu)
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                // Inflate the menu resource
                menuInflater.inflate(R.menu.home_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                // Handle the menu item click
                if (menuItem.getItemId() == R.id.action_view_last_booking) {
                    showLastBookingDialog();
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_view_last_booking) {
            showLastBookingDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLastBookingDialog() {


            // Initialize SharedPreferences (Must match Tickets_Fragment)
            sPref = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE);

            // Retrieve the data using your specific keys
            String movie = sPref.getString("name", null);
            int seats = sPref.getInt("seats", 0);
            int total = sPref.getInt("totalcost", 0);

            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

            if (movie == null) {
                // Case: No booking found
                builder.setTitle("Last Booking");
                builder.setMessage("No previous booking found.");
            } else {
                // Case: Display stored booking info
                builder.setTitle("Last Booking Information");
                builder.setMessage("Movie: " + movie +
                        "\nSeats: " + seats +
                        "\nTotal Price: $" + total);
            }

            builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
            builder.create().show();

    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_home, container, false);

            // Initialize views using 'view'
            tabLayout = view.findViewById(R.id.tablayout);
            viewPager2 = view.findViewById(R.id.viewpager);

            // Set adapter
            adapter = new ViewPagerAdapter(this);
            viewPager2.setAdapter(adapter);

            // Attach TabLayout with ViewPager2
            mediator = new TabLayoutMediator(
                    tabLayout,
                    viewPager2,
                    (tab, position) -> {
                        switch (position) {
                            case 0:
                                tab.setText("Now Showing");
                                break;
                            case 1:
                                tab.setText("Coming Soon");
                                break;
                        }
                    }
            );

            mediator.attach();

            return view;
        }
    }


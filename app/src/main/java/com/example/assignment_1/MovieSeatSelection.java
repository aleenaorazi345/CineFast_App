package com.example.assignment_1;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import org.jspecify.annotations.Nullable;

public class MovieSeatSelection extends Fragment {

    TextView tvName;
    GridLayout seatsGrid;
    Button btnPrimary, btnSecondary;
    int selectedCount = 0;
    boolean isComingSoon;
    String trailerUrl;

    int rows = 5;
    int cols = 4;

    public MovieSeatSelection() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_seat_selection, container, false);

        tvName = view.findViewById(R.id.tvName);
        seatsGrid = view.findViewById(R.id.seatsGrid);
        btnPrimary = view.findViewById(R.id.action_btn_primary);
        btnSecondary = view.findViewById(R.id.action_btn_secondary);

        if (getArguments() != null) {
            tvName.setText(getArguments().getString("name_key"));
            isComingSoon = getArguments().getBoolean("coming_soon_key", false);
            trailerUrl = getArguments().getString("trailer_url", "");
        }

        setupSeats();

        if (isComingSoon) {
            setupComingSoonMode();
        } else {
            setupNowShowingMode();
        }

        return view;
    }

    private void setupSeats() {
        seatsGrid.removeAllViews();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                AppCompatButton seat = new AppCompatButton(getContext());
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 120;
                params.height = 120;
                params.setMargins(8, 8, 8, 8);
                seat.setLayoutParams(params);

                char rowChar = (char) ('A' + i);
                seat.setText(rowChar + String.valueOf(j + 1));
                seat.setBackgroundColor(Color.LTGRAY);

                if (isComingSoon) {
                    seat.setEnabled(false);
                } else {
                    seat.setOnClickListener(v -> {
                        if (seat.isSelected()) {
                            seat.setSelected(false);
                            seat.setBackgroundColor(Color.LTGRAY);
                            selectedCount--;
                        } else {
                            seat.setSelected(true);
                            seat.setBackgroundColor(Color.GREEN);
                            selectedCount++;
                        }
                        updateUI();
                    });
                }
                seatsGrid.addView(seat);
            }
        }
    }

    private void updateUI() {
        boolean hasSelection = selectedCount > 0;
        btnPrimary.setEnabled(hasSelection);
        btnSecondary.setEnabled(hasSelection);
    }

    private void setupNowShowingMode() {
        btnPrimary.setText("Book Seats (Skip Snacks)");
        btnSecondary.setText("Proceed to Snacks");
        btnPrimary.setEnabled(false);
        btnSecondary.setEnabled(false);

        // Case 1: Book directly (Skip Snacks)
        btnPrimary.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Booking Confirmed!", Toast.LENGTH_SHORT).show();

            // 1. Create the Fragment instance
            Tickets_Fragment ticketFrag = new Tickets_Fragment();

            // 2. Create a Bundle to pass the data (replacing Intent extras)
            Bundle args = new Bundle();
            args.putString("name", tvName.getText().toString());
            args.putInt("seats", selectedCount);

            // Calculate a basic total (e.g., $10 per seat) since snacks are skipped
            int ticketPrice = selectedCount * 10;
            args.putInt("total", ticketPrice);
            args.putInt("quantity", 0); // No snacks selected

            // 3. Set the arguments to the fragment
            ticketFrag.setArguments(args);

            // 4. Perform the Fragment Transaction
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ticketFrag)
                    .addToBackStack(null) // Allows user to navigate back
                    .commit();
        });

        // Case 2: Navigate to SnackFragment
        btnSecondary.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Moving to Snacks...", Toast.LENGTH_SHORT).show();

            // Perform Fragment Transaction
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SnackFragment()) // R.id.fragment_container is the ID in your Activity layout
                    .addToBackStack(null) // Allows user to go back to seat selection
                    .commit();
        });
    }

    private void setupComingSoonMode() {
        btnPrimary.setText("Coming Soon");
        btnPrimary.setEnabled(false);
        btnPrimary.setBackgroundColor(Color.GRAY);

        btnSecondary.setText("Watch Trailer");
        btnSecondary.setEnabled(true);
        btnSecondary.setOnClickListener(v -> {
            if (trailerUrl != null && !trailerUrl.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Trailer not available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
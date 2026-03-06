package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.button.MaterialButton;

public class SeatSelection extends AppCompatActivity {
    TextView tvName;
    ImageView btnBack;
    GridLayout seats_grid;
    Button proceed_snacks_btn,book_seats_btn;
    int selectedCount = 0,total=0;
    ActivityResultLauncher<Intent> getInfoLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_seat_selection);

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            tvName = findViewById(R.id.tvName);
            btnBack = findViewById(R.id.btnBack);
            seats_grid = findViewById(R.id.seats_grid);
            proceed_snacks_btn = findViewById(R.id.proceed_snacks_btn);
            book_seats_btn=findViewById(R.id.book_seats_btn);
        // Inside onCreate() of SeatSelection Activity



        proceed_snacks_btn.setEnabled(false);

            // 🔹 Get movie name from previous activity
            Intent intent = getIntent();
            if (intent != null) {
                String name = intent.getStringExtra("name_key");
                tvName.setText("You booked: " + name);
            }


            for (int i = 0; i < seats_grid.getChildCount(); i++) {

                View view = seats_grid.getChildAt(i);

                if (view instanceof androidx.appcompat.widget.AppCompatButton) {

                    androidx.appcompat.widget.AppCompatButton seat =
                            (androidx.appcompat.widget.AppCompatButton) view;

                    // If seat is red initially, mark it as booked
                    if (seat.getBackground() != null &&
                            seat.getTag() == null &&
                            seat.getBackground().getConstantState() ==
                                    getResources().getDrawable(R.color.red).getConstantState()) {

                        seat.setTag("booked");
                    }

                    seat.setOnClickListener(v -> {

                        // Ignore booked seats
                        if ("booked".equals(seat.getTag())) {
                            return;
                        }

                        // Toggle selection
                        if (seat.isSelected()) {
                            seat.setSelected(false);
                            seat.setBackgroundResource(R.color.black);
                            selectedCount--;
                        } else {
                            seat.setSelected(true);
                            seat.setBackgroundResource(R.drawable.circle_bg); // selected color
                            selectedCount++;
                        }
                        total=selectedCount*10;
                        if (selectedCount > 0) {

                            proceed_snacks_btn.setEnabled(true);
                            proceed_snacks_btn.setBackgroundResource(R.color.red);

                        } else {

                            proceed_snacks_btn.setEnabled(false);
                            proceed_snacks_btn.setBackgroundResource(R.color.white);
                        }

                    });
                }
            }
            proceed_snacks_btn.setOnClickListener((view -> {
                Intent snacksintent = new Intent(SeatSelection.this, Snacks.class);

                String movieName = tvName.getText().toString();
                //int seats_num = Integer.parseInt(seats.getText().toString());
                //int totalCost = Integer.parseInt(totalcost.getText().toString());

                // Pass values using putExtra (one key-value pair at a time)
                snacksintent.putExtra("name", movieName);
                snacksintent.putExtra("seats", selectedCount);
                snacksintent.putExtra("total", total);

                // Start Tickets activity
                startActivity(snacksintent);
            }));


            // 🔹 On Click of BOOK SEAT (Back Button assumed as Book Seat)
        book_seats_btn.setOnClickListener(v -> {
            Intent ticketIntent = new Intent(SeatSelection.this, Tickets.class);

            // Get values from TextViews/EditTexts
            String movieName = tvName.getText().toString();
            //int seats_num = Integer.parseInt(seats.getText().toString());
            //int totalCost = Integer.parseInt(totalcost.getText().toString());

            // Pass values using putExtra (one key-value pair at a time)
            ticketIntent.putExtra("name", movieName);
            ticketIntent.putExtra("seats", selectedCount);
            ticketIntent.putExtra("total", total);

            // Start Tickets activity
            startActivity(ticketIntent);
        });

    }

}
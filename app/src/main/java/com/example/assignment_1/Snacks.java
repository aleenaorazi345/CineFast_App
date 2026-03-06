package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;




import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


    public class Snacks extends AppCompatActivity {
        TextView tvMsg;

        // Quantities for each snack
        int quantity1 = 0;
        int quantity2 = 0;
        int quantity3 = 0;
        int quantity4 = 0;

        // Buttons and TextViews for Card 1
        Button btnPlus, btnMinus;
        TextView tvQuantity;

        // Buttons and TextViews for Card 2
        Button btnPlus2, btnMinus2;
        TextView tvQuantity2;

        // Buttons and TextViews for Card 3
        Button btnPlus3, btnMinus3;
        TextView tvQuantity3;

        // Buttons and TextViews for Card 4
        Button btnPlus4, btnMinus4;
        TextView tvQuantity4;

        Button confirm;


        private static final int SNACK_PRICE_1 = 5;  // Popcorn
        private static final int SNACK_PRICE_2 = 5;  // Nachos
        private static final int SNACK_PRICE_3 = 5;  // Drink
        private static final int SNACK_PRICE_4 = 5;  // Combo

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_snacks);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            init();

            // Get data from previous activity
            Intent intent = getIntent();
            String movieName = intent.getStringExtra("name");
            int seatsNum = intent.getIntExtra("seats", 0);
            int totalCost = intent.getIntExtra("total", 0);

            // ===== CARD 1 BUTTONS =====
            btnPlus.setOnClickListener(v -> {
                quantity1++;
                tvQuantity.setText(String.valueOf(quantity1));
            });

            btnMinus.setOnClickListener(v -> {
                if (quantity1 > 0) {
                    quantity1--;
                    tvQuantity.setText(String.valueOf(quantity1));
                }
            });

            // ===== CARD 2 BUTTONS =====
            btnPlus2.setOnClickListener(v -> {
                quantity2++;
                tvQuantity2.setText(String.valueOf(quantity2));
            });

            btnMinus2.setOnClickListener(v -> {
                if (quantity2 > 0) {
                    quantity2--;
                    tvQuantity2.setText(String.valueOf(quantity2));
                }
            });

            // ===== CARD 3 BUTTONS =====
            btnPlus3.setOnClickListener(v -> {
                quantity3++;
                tvQuantity3.setText(String.valueOf(quantity3));
            });

            btnMinus3.setOnClickListener(v -> {
                if (quantity3 > 0) {
                    quantity3--;
                    tvQuantity3.setText(String.valueOf(quantity3));
                }
            });

            // ===== CARD 4 BUTTONS =====
            btnPlus4.setOnClickListener(v -> {
                quantity4++;
                tvQuantity4.setText(String.valueOf(quantity4));
            });

            btnMinus4.setOnClickListener(v -> {
                if (quantity4 > 0) {
                    quantity4--;
                    tvQuantity4.setText(String.valueOf(quantity4));
                }
            });

            // ===== CONFIRM BUTTON =====
            confirm.setOnClickListener(v -> {
                // Calculate individual snack costs
                int snacksCost1 = quantity1 * SNACK_PRICE_1;
                int snacksCost2 = quantity2 * SNACK_PRICE_2;
                int snacksCost3 = quantity3 * SNACK_PRICE_3;
                int snacksCost4 = quantity4 * SNACK_PRICE_4;

                // Calculate total snacks cost
                int totalSnacksCost = snacksCost1 + snacksCost2 + snacksCost3 + snacksCost4;

                // Calculate total quantity
                int totalQuantity = quantity1 + quantity2 + quantity3 + quantity4;

                // Calculate new total cost (previous total + snacks)
                int newTotalCost = totalCost + totalSnacksCost;

                // Pass all data to Tickets.java
                Intent i = new Intent(Snacks.this, Tickets.class);
                i.putExtra("name", movieName);
                i.putExtra("seats", seatsNum);
                i.putExtra("total", newTotalCost);
                i.putExtra("snacksCost", totalSnacksCost);
                i.putExtra("quantity", totalQuantity);

                // Optional: Pass individual quantities if needed in Tickets.java
                i.putExtra("quantity1", quantity1);
                i.putExtra("quantity2", quantity2);
                i.putExtra("quantity3", quantity3);
                i.putExtra("quantity4", quantity4);

                startActivity(i);
            });
        }

        private void init(){
            tvMsg = findViewById(R.id.tvMsg);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                tvMsg.setText(Html.fromHtml(getString(R.string.cinefast), Html.FROM_HTML_MODE_LEGACY));
            } else {
                tvMsg.setText(Html.fromHtml(getString(R.string.cinefast)));
            }

            // Initialize Card 1
            tvQuantity = findViewById(R.id.tvQuantity);
            btnPlus = findViewById(R.id.btnPlus);
            btnMinus = findViewById(R.id.btnMinus);

            // Initialize Card 2
            tvQuantity2 = findViewById(R.id.tvQuantity2);
            btnPlus2 = findViewById(R.id.btnPlus2);
            btnMinus2 = findViewById(R.id.btnMinus2);

            // Initialize Card 3
            tvQuantity3 = findViewById(R.id.tvQuantity3);
            btnPlus3 = findViewById(R.id.btnPlus3);
            btnMinus3 = findViewById(R.id.btnMinus3);

            // Initialize Card 4
            tvQuantity4 = findViewById(R.id.tvQuantity4);
            btnPlus4 = findViewById(R.id.btnPlus4);
            btnMinus4 = findViewById(R.id.btnMinus4);

            // Confirm button
            confirm = findViewById(R.id.confirm);
        }
    }
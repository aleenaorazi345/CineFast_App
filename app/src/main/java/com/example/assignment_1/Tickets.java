package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Tickets extends AppCompatActivity {
    TextView seats, totalcost, name, snacksInfo;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tickets);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔹 Initialize views
        name = findViewById(R.id.name);
        seats = findViewById(R.id.seats);
        totalcost = findViewById(R.id.totalcost);
        snacksInfo = findViewById(R.id.snacksInfo); // Add this TextView in your XML
        send = findViewById(R.id.send);

        // 🔹 Get data from previous activity
        Intent intent = getIntent();
        String movieName = intent.getStringExtra("name");
        int seatsNum = intent.getIntExtra("seats", 0);
        int totalCost = intent.getIntExtra("total", 0);

        // Get snacks data (will be 0 if coming directly from booking without snacks)
        int snacksCost = intent.getIntExtra("snacksCost", 0);
        int totalQuantity = intent.getIntExtra("quantity", 0);

        // Get individual quantities (optional - for detailed display)
        int quantity1 = intent.getIntExtra("quantity1", 0);
        int quantity2 = intent.getIntExtra("quantity2", 0);
        int quantity3 = intent.getIntExtra("quantity3", 0);
        int quantity4 = intent.getIntExtra("quantity4", 0);

        // Display in TextViews
        name.setText("Movie: " + movieName);
        seats.setText("Seats: " + seatsNum);
        totalcost.setText("Total Cost: $" + totalCost);

        // Display snacks info if snacks were ordered
        if (totalQuantity > 0) {
            snacksInfo.setText("Snacks: " + totalQuantity + " items ($" + snacksCost + ")");
        } else {
            snacksInfo.setText("Snacks: None");
        }

        // 🔹 Prepare ticket text for sharing
        String ticketText = buildTicketText(movieName, seatsNum, totalCost, totalQuantity, snacksCost,
                quantity1, quantity2, quantity3, quantity4);

        // 🔹 Send Ticket Button click
        send.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, ticketText);

            // Open chooser for WhatsApp, Gmail, etc.
            Intent shareIntent = Intent.createChooser(sendIntent, "Share ticket via");
            startActivity(shareIntent);
        });
    }

    // Helper method to build ticket text
    private String buildTicketText(String movieName, int seatsNum, int totalCost, int totalQuantity,
                                   int snacksCost, int qty1, int qty2, int qty3, int qty4) {
        StringBuilder ticketText = new StringBuilder();
        ticketText.append("🎟️ Movie Ticket 🎟️\n");
        ticketText.append("━━━━━━━━━━━━━━━━━━━━\n");
        ticketText.append("Movie: ").append(movieName).append("\n");
        ticketText.append("Seats: ").append(seatsNum).append("\n");

        if (totalQuantity > 0) {
            ticketText.append("\n🍿 Snacks Ordered:\n");

            // Add individual snack details if any were ordered
            if (qty1 > 0) ticketText.append("  • Popcorn: ").append(qty1).append("\n");
            if (qty2 > 0) ticketText.append("  • Nachos: ").append(qty2).append("\n");
            if (qty3 > 0) ticketText.append("  • Drink: ").append(qty3).append("\n");
            if (qty4 > 0) ticketText.append("  • Combo: ").append(qty4).append("\n");

            ticketText.append("Snacks Cost: $").append(snacksCost).append("\n");
            ticketText.append("━━━━━━━━━━━━━━━━━━━━\n");
        }

        ticketText.append("Total Cost: $").append(totalCost).append("\n");
        ticketText.append("━━━━━━━━━━━━━━━━━━━━\n");
        ticketText.append("Enjoy your movie! 🍿");

        return ticketText.toString();
    }
}
package com.example.assignment_1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tickets_Fragment extends Fragment {

    TextView seats, totalcost, name, snacksInfo;
    Button send;
    SharedPreferences sPref;
    public Tickets_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Inflate the layout
        View view = inflater.inflate(R.layout.fragment_tickets_, container, false);

        // 2. Initialize views using the 'view' object
        name = view.findViewById(R.id.name);
        seats = view.findViewById(R.id.seats);
        totalcost = view.findViewById(R.id.totalcost);
        snacksInfo = view.findViewById(R.id.snacksInfo);
        send = view.findViewById(R.id.send);
        sPref= requireContext().getSharedPreferences("user",MODE_PRIVATE);

        // 3. Get data from Bundle (Arguments) instead of Intent
        Bundle bundle = getArguments();
        if (bundle != null) {
            String movieName = bundle.getString("name", "Unknown");
            int seatsNum = bundle.getInt("seats", 0);
            int totalCost = bundle.getInt("total", 0);
            int snacksCost = bundle.getInt("snacksCost", 0);
            int totalQuantity = bundle.getInt("quantity", 0);

            // Optional individual quantities
            int q1 = bundle.getInt("quantity1", 0);
            int q2 = bundle.getInt("quantity2", 0);
            int q3 = bundle.getInt("quantity3", 0);
            int q4 = bundle.getInt("quantity4", 0);

            // 4. Display data
            name.setText("Movie: " + movieName);
            seats.setText("Seats: " + seatsNum);
            totalcost.setText("Total Cost: $" + totalCost);

            if (totalQuantity > 0) {
                snacksInfo.setText("Snacks: " + totalQuantity + " items ($" + snacksCost + ")");
            } else {
                snacksInfo.setText("Snacks: None");
            }

            // 5. Setup Share Button
            String ticketText = buildTicketText(movieName, seatsNum, totalCost, totalQuantity, snacksCost, q1, q2, q3, q4);
            send.setOnClickListener(v -> {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, ticketText);
                startActivity(Intent.createChooser(sendIntent, "Share ticket via"));
            });
            SharedPreferences.Editor editor=sPref.edit();
            editor.putString("name",movieName);
            editor.putInt("seats",seatsNum);
            editor.putInt("totalcost",totalCost);
            editor.commit();
        }

        return view;
    }

    private String buildTicketText(String movieName, int seatsNum, int totalCost, int totalQuantity,
                                   int snacksCost, int qty1, int qty2, int qty3, int qty4) {
        StringBuilder ticketText = new StringBuilder();
        ticketText.append("🎟️ Movie Ticket 🎟️\n");
        ticketText.append("━━━━━━━━━━━━━━━━━━━━\n");
        ticketText.append("Movie: ").append(movieName).append("\n");
        ticketText.append("Seats: ").append(seatsNum).append("\n");

        if (totalQuantity > 0) {
            ticketText.append("\n🍿 Snacks Ordered:\n");
            if (qty1 > 0) ticketText.append("  • Popcorn: ").append(qty1).append("\n");
            if (qty2 > 0) ticketText.append("  • Nachos: ").append(qty2).append("\n");
            if (qty3 > 0) ticketText.append("  • Drink: ").append(qty3).append("\n");
            if (qty4 > 0) ticketText.append("  • Combo: ").append(qty4).append("\n");
            ticketText.append("Snacks Cost: $").append(snacksCost).append("\n");
        }

        ticketText.append("━━━━━━━━━━━━━━━━━━━━\n");
        ticketText.append("Total Cost: $").append(totalCost).append("\n");
        ticketText.append("━━━━━━━━━━━━━━━━━━━━\n");
        ticketText.append("Enjoy your movie! 🍿");
        return ticketText.toString();
    }
}
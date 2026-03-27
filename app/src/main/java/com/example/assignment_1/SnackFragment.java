package com.example.assignment_1;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class SnackFragment extends Fragment {

    private ListView snackListView;
    private ArrayList<Snack> snackList;
    private SnackAdapter adapter;
    private Button btnConfirm;

    public SnackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snack, container, false);

        // 2. Initialize UI Components
        snackListView = view.findViewById(R.id.snackListView);
        btnConfirm = view.findViewById(R.id.btnConfirm); // Ensure this ID exists in your XML

        // 3. Prepare Data (Minimum 4 snacks)
        prepareSnackData();

        // 4. Set up the Custom Adapter
        adapter = new SnackAdapter(getContext(), snackList);
        snackListView.setAdapter(adapter);


        // 5. Confirm Button Logic
        if (btnConfirm != null) {
            btnConfirm.setOnClickListener(v -> {
                int totalQuantity = 0;
                double snacksCost = 0;

                // 1. Calculate snack totals from the list
                for (Snack s : snackList) {
                    totalQuantity += s.getQuantity();
                    snacksCost += (s.getQuantity() * s.getPrice());
                }

                if (totalQuantity == 0) {
                    Toast.makeText(getContext(), "Please select at least one snack!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. Get Movie data passed from Seat Selection Fragment
                String movieName = "Unknown";
                int seatsNum = 0;
                if (getArguments() != null) {
                    movieName = getArguments().getString("name", "Unknown");
                    seatsNum = getArguments().getInt("seats", 0);
                }

                // 3. Calculate Final Total (Seats at $10 each + Snacks)
                int totalCost = (seatsNum * 10) + (int) snacksCost;

                // 4. Prepare Bundle to pass to Tickets_Fragment
                Bundle args = new Bundle();
                args.putString("name", movieName);
                args.putInt("seats", seatsNum);
                args.putInt("quantity", totalQuantity);
                args.putInt("snacksCost", (int) snacksCost);
                args.putInt("total", totalCost);

                // Pass individual quantities for the receipt
                args.putInt("quantity1", snackList.get(0).getQuantity()); // Popcorn
                args.putInt("quantity2", snackList.get(1).getQuantity()); // Nachos
                args.putInt("quantity3", snackList.get(2).getQuantity()); // Coke
                args.putInt("quantity4", snackList.get(3).getQuantity()); // Hotdog

                // 5. Navigate to Tickets_Fragment
                Tickets_Fragment ticketFrag = new Tickets_Fragment();
                ticketFrag.setArguments(args);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ticketFrag)
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(), "Booking Confirmed!", Toast.LENGTH_SHORT).show();
            });
        }

        return view;
    }

    private void prepareSnackData() {
        snackList = new ArrayList<>();
        // Replace drawable names with your actual image file names
        snackList.add(new Snack("Classic Popcorn", 5.00, R.drawable.popcorn));
        snackList.add(new Snack("Nacho Supreme", 7.50, R.drawable.nachos));
        snackList.add(new Snack("Coca Cola", 3.50, R.drawable.drink));
        snackList.add(new Snack("Hot Dog", 6.00, R.drawable.candy));
    }

    // --- INNER MODEL CLASS ---
    public static class Snack {
        private String name;
        private double price;
        private int imageResId;
        private int quantity;

        public Snack(String name, double price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
            this.quantity = 0;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getImageResId() { return imageResId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    // --- CUSTOM ADAPTER CLASS ---
    public class SnackAdapter extends BaseAdapter {
        private Context context;
        private List<Snack> snacks;

        public SnackAdapter(Context context, List<Snack> snacks) {
            this.context = context;
            this.snacks = snacks;
        }

        @Override
        public int getCount() { return snacks.size(); }

        @Override
        public Object getItem(int position) { return snacks.get(position); }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_snack, parent, false);
            }

            Snack currentSnack = snacks.get(position);

            // Link UI from list_item_snack.xml
            ImageView img = convertView.findViewById(R.id.snackImage);
            TextView name = convertView.findViewById(R.id.snackName);
            TextView price = convertView.findViewById(R.id.snackPrice);
            TextView qty = convertView.findViewById(R.id.tvQuantity);
            Button btnPlus = convertView.findViewById(R.id.btnPlus);
            Button btnMinus = convertView.findViewById(R.id.btnMinus);

            // Set Data
            img.setImageResource(currentSnack.getImageResId());
            name.setText(currentSnack.getName());
            price.setText(String.format("$%.2f", currentSnack.getPrice()));
            qty.setText(String.valueOf(currentSnack.getQuantity()));

            // Plus Button Logic
            btnPlus.setOnClickListener(v -> {
                currentSnack.setQuantity(currentSnack.getQuantity() + 1);
                notifyDataSetChanged();
            });

            // Minus Button Logic
            btnMinus.setOnClickListener(v -> {
                if (currentSnack.getQuantity() > 0) {
                    currentSnack.setQuantity(currentSnack.getQuantity() - 1);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }
}
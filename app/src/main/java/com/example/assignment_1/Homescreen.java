package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.widget.Toast;

public class Homescreen extends AppCompatActivity {
    Button btntoday,btntom,btnBook1,btnBook2,btnBook3;
    TextView tvMsg,name,name2,name3;
    ImageButton ibTrailer1,ibTrailer2,ibTrailer3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homescreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        ibTrailer1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri    .parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            startActivity(intent);
        });
        btnBook1.setOnClickListener((view)-> {
            String movieName1 = name.getText().toString();

            Intent intent = new Intent(Homescreen.this, SeatSelection.class);
            intent.putExtra("name_key", movieName1);
            startActivity(intent);
            //finish();

        });
        ibTrailer2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri    .parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            startActivity(intent);
        });
        btnBook2.setOnClickListener((view)-> {
            String movieName2 = name2.getText().toString();

            Intent intent = new Intent(Homescreen.this, SeatSelection.class);
            intent.putExtra("name_key", movieName2);
            startActivity(intent);
            //finish();

        });
        ibTrailer3.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri    .parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            startActivity(intent);
        });
        btnBook3.setOnClickListener((view)-> {
            String movieName3 = name3.getText().toString();

            Intent intent = new Intent(Homescreen.this, SeatSelection.class);
            intent.putExtra("name_key", movieName3);
            startActivity(intent);
           // finish();

        });

        btntoday.setOnClickListener(v -> {
            btntoday.setSelected(true);
            btntom.setSelected(false);
        });

        btntom.setOnClickListener(v -> {
            btntom.setSelected(true);
            btntoday.setSelected(false);
        });
    }
    private void init(){

        name = findViewById(R.id.name);
        name2= findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        btnBook1=findViewById(R.id.btnBook1);
        btnBook2=findViewById(R.id.btnBook2);
        btnBook3=findViewById(R.id.btnBook3);
        tvMsg=findViewById(R.id.tvMsg);
        ibTrailer1=findViewById(R.id.ibTrailer1);
        ibTrailer2=findViewById(R.id.ibTrailer2);
        ibTrailer3=findViewById(R.id.ibTrailer3);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            // For API 24+
            tvMsg.setText(Html.fromHtml(getString(R.string.cinefast), Html.FROM_HTML_MODE_LEGACY));
        } else {
            // For older versions
            tvMsg.setText(Html.fromHtml(getString(R.string.cinefast)));
        }
        btntoday= findViewById(R.id.btntoday);
        btntom = findViewById(R.id.btntom);
    }

}
package com.example.assignment_1;
import android.text.Html;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.animation.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {
    TextView tvMsg;
    ImageView ivLogo;
    Animation loadscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        applyAnimation();
        moveToDashboard();

    }
    private void init(){
    tvMsg=findViewById(R.id.tvMsg);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            // For API 24+
            tvMsg.setText(Html.fromHtml(getString(R.string.cinefast), Html.FROM_HTML_MODE_LEGACY));
        } else {
            // For older versions
            tvMsg.setText(Html.fromHtml(getString(R.string.cinefast)));
        }
    ivLogo=findViewById(R.id.ivLogo);
    loadscreen= AnimationUtils.loadAnimation(this,R.anim.loadscreen);
    }
    private void applyAnimation(){
        ivLogo.setAnimation(loadscreen);
    }
    private void moveToDashboard(){
        new Handler().postDelayed(()->{
            startActivity(new Intent(Splash.this,Dashboard.class));
        },5000);
    }

}
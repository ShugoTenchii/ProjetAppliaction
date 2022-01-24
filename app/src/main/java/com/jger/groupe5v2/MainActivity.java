package com.jger.groupe5v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonCalculer = findViewById(R.id.boutonCalculer);
        Button boutonDernierCalcul = findViewById(R.id.boutonDernierCalcul);
        boutonCalculer.setOnClickListener(view -> lancerActivityCalcul());
        boutonDernierCalcul.setOnClickListener(view -> lancerActivityDernierCalcul());

    }

    private void lancerActivityDernierCalcul() {
        Intent intent = new Intent(this,LastComputeActivity.class);
        startActivity(intent);
    }

    private void lancerActivityCalcul() {
        Intent intent = new Intent(this,CalculActivity.class);
        startActivity(intent);
    }
}
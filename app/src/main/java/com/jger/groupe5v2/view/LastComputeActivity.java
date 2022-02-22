package com.jger.groupe5v2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jger.groupe5v2.R;

public class LastComputeActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_compute);
        Button boutonPrecedent = findViewById(R.id.boutonPrecedent);
        TextView textViewCalcul = findViewById(R.id.lastCompute_textViewCalcul);
        boutonPrecedent.setOnClickListener(view -> finish());
        Intent intent = getIntent();
        if(intent.getStringExtra("symbol")!=null){
            textViewCalcul.setText(intent
                    .getIntExtra("premierElement", 0)
                    + " " +
                    intent.getStringExtra("symbol")
                    + " " +
                    intent.getIntExtra("deuxiemeElement", 0)
                    + " = " +
                    intent.getIntExtra("resultat",0)
            );
        }else{
            textViewCalcul.setText("");
        }


    }
}
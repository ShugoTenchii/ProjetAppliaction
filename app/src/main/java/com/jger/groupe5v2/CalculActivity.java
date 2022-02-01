package com.jger.groupe5v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class CalculActivity extends AppCompatActivity {
    Integer premierElement = 0;
    Integer deuxiemeElement = 0;
    TypeOperationEnum typeOperation = null;
    TextView textViewCalcul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        textViewCalcul = findViewById(R.id.textviewCalcul);
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(view -> ajouterNombre(1));
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(view -> ajouterNombre(2));
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(view -> ajouterNombre(3));
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(view -> ajouterNombre(4));
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(view -> ajouterNombre(5));
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(view -> ajouterNombre(6));
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(view -> ajouterNombre(7));
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(view -> ajouterNombre(8));
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(view -> ajouterNombre(9));
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(view -> ajouterNombre(0));
    }

    public void ajouterNombre(Integer valeur){
        if(typeOperation == null){
            premierElement= 10*premierElement+valeur;
        }else{
            deuxiemeElement= 10*deuxiemeElement+valeur;
        }
        majText();
    }

    private void majText() {
        String textAAfficher="";
        if(typeOperation==null){
            textAAfficher=premierElement.toString();
        }else{
            textAAfficher = premierElement +" "+typeOperation.getSymbol()+" "+deuxiemeElement;
        }
        textViewCalcul.setText(textAAfficher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        MenuItem boutonCalculer = menu.findItem(R.id.toolbarCaculer);
        MenuItem boutonVider = menu.findItem(R.id.toolbarNettoyer);
        boutonVider.setOnMenuItemClickListener(menuItem -> videTextViewCalcul());
        boutonCalculer.setOnMenuItemClickListener(menuItem -> ouvrirDernierCalcul());

        return true;
    }

    private boolean videTextViewCalcul() {
        TextView textViewCalcul = findViewById(R.id.textviewCalcul);
        textViewCalcul.setText("");
        return true;
    }

    private boolean ouvrirDernierCalcul() {
        Intent intent = new Intent(this,LastComputeActivity.class);
        startActivity(intent);
        return true;
    }
}
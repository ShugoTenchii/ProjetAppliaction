package com.jger.groupe5v2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jger.groupe5v2.R;
import com.jger.groupe5v2.controller.CalculService;
import com.jger.groupe5v2.models.TypeOperationEnum;

public class MainActivity extends AppCompatActivity {


    Integer premierElement = 0;
    Integer deuxiemeElement = 0;
    TypeOperationEnum typeOperation = null;
    TextView textViewCalcul;
    Integer BORNE_HAUTE = 9999;
    CalculService calculService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonCalculer = findViewById(R.id.score_view_menu_button);
        Button boutonDernierCalcul = findViewById(R.id.button_last_calcul);
        Button bontonQuiz = findViewById(R.id.ButtonQuiz);
        Button bontonResultatQuizz = findViewById(R.id.buttonResultatQuiz);

        boutonCalculer.setOnClickListener(view -> lancerActivityCalcul());
        boutonDernierCalcul.setOnClickListener(view -> lancerActivityDernierCalcul());
        bontonQuiz.setOnClickListener(view -> lancerQuizActivity());
        bontonResultatQuizz.setOnClickListener(view -> lancerDernierQuiz());


    }


    private void ajouterSymbol(TypeOperationEnum multiply) {
        this.typeOperation = typeOperation;
    }

    public void ajouterNombre() {
        premierElement = (int)(Math.random()*(9999+1));
        deuxiemeElement = (int)(Math.random()*(9999+1));
    }



    private void majText() {
        String textAAfficher = "";
        ajouterNombre();
        ajouterSymbol(TypeOperationEnum.MULTIPLY);
        textViewCalcul = findViewById(R.id.questionCalcul);
        if (typeOperation == null) {
            textAAfficher = premierElement.toString();
        } else {
            textAAfficher = premierElement + " " + typeOperation.getSymbol() + " " + deuxiemeElement;
        }
        textViewCalcul.setText(textAAfficher);
    }

    private void lancerActivityDernierCalcul() {
        Intent intent = new Intent(this, LastComputeActivity.class);
        startActivity(intent);
    }

    private void lancerActivityCalcul() {
        Intent intent = new Intent(this, CalculActivity.class);
        startActivity(intent);
    }

    private void lancerQuizActivity(){
        Intent intent = new Intent(this, MentalActivity.class);
        startActivity(intent);
    }

    private void lancerDernierQuiz(){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

}
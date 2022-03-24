package com.jger.groupe5v2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jger.groupe5v2.R;
import com.jger.groupe5v2.controller.CalculBaseHelper;
import com.jger.groupe5v2.controller.CalculDao;
import com.jger.groupe5v2.controller.CalculService;
import com.jger.groupe5v2.model.Calcul;
import com.jger.groupe5v2.model.TypeOperationEnum;
import com.jger.groupe5v2.model.exception.DivideException;

public class QuizzActivity extends AppCompatActivity {
    Integer premierElement = 0;
    Integer deuxiemeElement = 0;
    TypeOperationEnum typeOperation = null;
    TextView textViewCalcul = findViewById(R.id.questionCalcul);
    Integer BORNE_HAUTE = 9999;
    CalculService calculService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        calculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));
        textViewCalcul = findViewById(R.id.questionCalcul);
        Button button1 = findViewById(R.id.button_1_quiz);
        Button button2 = findViewById(R.id.button_2_quiz);
        Button button3 = findViewById(R.id.button_3_quiz);
        Button button4 = findViewById(R.id.button_4_quiz);
        Button button5 = findViewById(R.id.button_5_quiz);
        Button button6 = findViewById(R.id.button_6_quiz);
        Button button7 = findViewById(R.id.button_7_quiz);
        Button button8 = findViewById(R.id.button_8_quiz);
        Button button9 = findViewById(R.id.button_9_quiz);
        Button button0 = findViewById(R.id.button_0_quiz);
        Button boutonAdd = findViewById(R.id.button_add);
        Button boutonSubstract = findViewById(R.id.button_substract);
        Button boutonDivide = findViewById(R.id.button_divide);
        Button boutonMultiply = findViewById(R.id.button_multiply);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        MenuItem boutonCalculer = menu.findItem(R.id.toolbarCaculer);
        MenuItem boutonVider = menu.findItem(R.id.toolbarNettoyer);
        boutonVider.setOnMenuItemClickListener(menuItem -> videTextViewCalcul());
        boutonCalculer.setOnMenuItemClickListener(menuItem -> ouvrirDernierCalcul());

        return true;
    }

    private boolean videTextViewCalcul() {
        TextView textViewCalcul = findViewById(R.id.questionCalcul);
        textViewCalcul.setText("");
        premierElement = 0;
        deuxiemeElement = 0;
        typeOperation = null;
        return true;
    }

    private Integer faisLeCalcul() throws DivideException {
        int OperationAlea = 1 + (int)(Math.random() * ((3-1)+1));
        if(OperationAlea == 1){
            ajouterSymbol(TypeOperationEnum.MULTIPLY);
            return premierElement + deuxiemeElement;
        }else if(OperationAlea == 2){
            ajouterSymbol(TypeOperationEnum.SUBSTRACT);
            return premierElement - deuxiemeElement;
        }else if(OperationAlea == 3){
            ajouterSymbol(TypeOperationEnum.MULTIPLY);
            return premierElement * deuxiemeElement;
        }
        return 0;
    }

    private void ajouterSymbol(TypeOperationEnum multiply) {
    }

    private boolean ouvrirDernierCalcul() {
        try{
            Intent intent = new Intent(this, LastComputeActivity.class);
            intent.putExtra("premierElement", premierElement);
            intent.putExtra("deuxiemeElement", deuxiemeElement);
            intent.putExtra("symbol", typeOperation.getSymbol());
            intent.putExtra("resultat", faisLeCalcul());
            Calcul calcul = new Calcul();
            calcul.setPremierElement(premierElement);
            calcul.setDeuxiemeElement(deuxiemeElement);
            calcul.setSymbol(typeOperation.getSymbol());
            calcul.setResultat(faisLeCalcul());
            calculService.storeInDB(calcul);
            startActivity(intent);
        }catch (DivideException e){
            Toast.makeText(this,getString(R.string.ERROR_DIVISION_ZERO),Toast.LENGTH_LONG).show();
        }

        return true;
    }

}
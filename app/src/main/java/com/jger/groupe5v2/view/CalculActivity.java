package com.jger.groupe5v2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jger.groupe5v2.controller.CalculBaseHelper;
import com.jger.groupe5v2.controller.CalculDao;
import com.jger.groupe5v2.controller.CalculService;
import com.jger.groupe5v2.models.Calcul;
import com.jger.groupe5v2.models.exception.DivideException;
import com.jger.groupe5v2.R;
import com.jger.groupe5v2.models.TypeOperationEnum;

public class CalculActivity extends AppCompatActivity {
    Integer premierElement = 0;
    Integer deuxiemeElement = 0;
    TypeOperationEnum typeOperation = null;
    TextView textViewCalcul;
    Integer BORNE_HAUTE = 9999;
    CalculService calculService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        calculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));
        textViewCalcul = findViewById(R.id.questionCalcul);
        Button button1 = findViewById(R.id.button_1_quiz);
        button1.setOnClickListener(view -> ajouterNombre(1));
        Button button2 = findViewById(R.id.button_2_quiz);
        button2.setOnClickListener(view -> ajouterNombre(2));
        Button button3 = findViewById(R.id.button_3_quiz);
        button3.setOnClickListener(view -> ajouterNombre(3));
        Button button4 = findViewById(R.id.button_4_quiz);
        button4.setOnClickListener(view -> ajouterNombre(4));
        Button button5 = findViewById(R.id.button_5_quiz);
        button5.setOnClickListener(view -> ajouterNombre(5));
        Button button6 = findViewById(R.id.button_6_quiz);
        button6.setOnClickListener(view -> ajouterNombre(6));
        Button button7 = findViewById(R.id.button_7_quiz);
        button7.setOnClickListener(view -> ajouterNombre(7));
        Button button8 = findViewById(R.id.button_8_quiz);
        button8.setOnClickListener(view -> ajouterNombre(8));
        Button button9 = findViewById(R.id.button_9_quiz);
        button9.setOnClickListener(view -> ajouterNombre(9));
        Button button0 = findViewById(R.id.button_0_quiz);
        button0.setOnClickListener(view -> ajouterNombre(0));
        Button boutonAdd = findViewById(R.id.score_view_menu_button);
        boutonAdd.setOnClickListener(view -> ajouterSymbol(TypeOperationEnum.ADD));
        Button boutonSubstract = findViewById(R.id.button_last_calcul);
        boutonSubstract.setOnClickListener(view -> ajouterSymbol(TypeOperationEnum.SUBSTRACT));
        Button boutonDivide = findViewById(R.id.button_divide);
        boutonDivide.setOnClickListener(view -> ajouterSymbol(TypeOperationEnum.DIVIDE));
        Button boutonMultiply = findViewById(R.id.button_multiply);
        boutonMultiply.setOnClickListener(view -> ajouterSymbol(TypeOperationEnum.MULTIPLY));

    }

    private void ajouterSymbol(TypeOperationEnum typeOperation) {
        this.typeOperation = typeOperation;
        majText();
    }

    public void ajouterNombre(Integer valeur) {
        if (typeOperation == null) {
            if (10 * premierElement + valeur > BORNE_HAUTE) {
                Toast.makeText(this, getString(R.string.ERROR_VALEUR_TROP_GRANDE), Toast.LENGTH_LONG).show();
            } else {
                premierElement = 10 * premierElement + valeur;
            }
        } else {
            if (10 * deuxiemeElement + valeur > BORNE_HAUTE) {
                Toast.makeText(this, getString(R.string.ERROR_VALEUR_TROP_GRANDE), Toast.LENGTH_LONG).show();
            } else {
                deuxiemeElement = 10 * deuxiemeElement + valeur;
            }
        }
        majText();
    }

    private void majText() {
        String textAAfficher = "";
        if (typeOperation == null) {
            textAAfficher = premierElement.toString();
        } else {
            textAAfficher = premierElement + " " + typeOperation.getSymbol() + " " + deuxiemeElement;
        }
        textViewCalcul.setText(textAAfficher);
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
            switch (typeOperation) {
                case ADD:
                    return premierElement + deuxiemeElement;
                case DIVIDE:
                    if (deuxiemeElement != 0) {
                        return premierElement / deuxiemeElement;
                    } else {
                        throw new DivideException();
                    }

                case SUBSTRACT:
                    return premierElement - deuxiemeElement;
                case MULTIPLY:
                    return premierElement * deuxiemeElement;
            }
            return 0;
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
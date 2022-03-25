package com.jger.groupe5v2.view;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.jger.groupe5v2.R;
import com.jger.groupe5v2.controller.CalculService;
import com.jger.groupe5v2.models.Calcul;
import com.jger.groupe5v2.models.TypeOperationEnum;
import com.jger.groupe5v2.models.OperationModel;
import com.jger.groupe5v2.models.exception.DivideException;
import com.jger.groupe5v2.services.OperationsGeneratorService;

import com.jger.groupe5v2.models.exception.NoAnswerException;
import com.jger.groupe5v2.models.exception.WrongAnswerException;
import com.jger.groupe5v2.services.OperationsGeneratorService;
import com.jger.groupe5v2.services.ResolutionService;

public class MentalActivity extends AppCompatActivity {

    private int _answer;
    private TextView operationText;
    private int _number1;
    private int _number2;
    private TypeOperationEnum _typeOperation;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer life = 3;

        operationText = findViewById(R.id.operation_text);
        Button home_image_button = findViewById(R.id.home_image_button);
        EditText editText = findViewById(R.id.answer_zone);
        TextView correctTV = findViewById(R.id.correct_text);
        TextView incorrectTV = findViewById(R.id.incorrect_text);
        TextView skipTV = findViewById(R.id.skip_text);
        TextView answerTV = findViewById(R.id.answer_text);
        TextView Life = findViewById(R.id.LifeId);
        Button validButton =  findViewById(R.id.valid_button);

        OperationsGeneratorService calculator = new OperationsGeneratorService();
        OperationModel operationG = calculator.CallFunctions();

        home_image_button.setOnClickListener(view -> goToPreviousActivity());
        editText.requestFocus();
        validButton.setOnClickListener(view -> Answer(editText, home_image_button, validButton, editText.getText().toString(), operationG, correctTV, incorrectTV, skipTV, answerTV,life, Life));




        setAttributes(operationG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.score_view_menu, menu);
        MenuItem voirScore = menu.findItem(R.id.score_view_menu_button);
        voirScore.setOnMenuItemClickListener( menuItem->ouvrirDernierQuizz());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.score_view_menu_button:
                Intent intent = new Intent(this, ScoreActivity.class);
                intent.putExtra(ScoreActivity.NUMBER1_KEY, this.getNumber1());
                intent.putExtra(ScoreActivity.NUMBER2_KEY, this.getNumber2());
                intent.putExtra(ScoreActivity.TYPE_OPERATOR_KEY, this.getTypeOperator());
                intent.putExtra(ScoreActivity.ANSWER_KEY, this.getAnswer());

                finish();
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToPreviousActivity() {
        finish();
    }

    private void displayOperation() {
        String compute = getString(R.string.operation_template, getNumber1(), getTypeOperator().getSymbol(), getNumber2());
        operationText.setText(compute);
    }
    
    private void setAttributes(OperationModel opModel){
        ResolutionService rS = new ResolutionService();

        setNumber1(opModel.getNumber1());
        setNumber2(opModel.getNumber2());
        setTypeOperator(opModel.getTypeOperator());
        setAnswer(rS.compute(opModel));
        displayOperation();
    }

    private void Answer(EditText answerZone, Button iB, Button b, String proposedAnswer, OperationModel opModel, TextView cTV, TextView iTV, TextView sTV, TextView aTV, Integer life, TextView Life) {
        Intent intent = new Intent(this, MentalActivity.class);
        startActivity(intent);

        answerZone.setVisibility(View.GONE);
        iB.setVisibility(View.GONE);
        b.setVisibility(View.GONE);

        ResolutionService answer = new ResolutionService();
        result = answer.compute(opModel);
        String text2 = R.string.number_of_life + ": " + life.toString();
        Life.setText(text2);
        try {
            answer.CorrectAnswer(proposedAnswer, opModel);
            cTV.setVisibility(View.VISIBLE);
        } catch (NoAnswerException e) {
            String text = getString(R.string.answer, result);
            aTV.setText(text);
            aTV.setVisibility(View.VISIBLE);
            sTV.setVisibility(View.VISIBLE);
        } catch (WrongAnswerException e) {
            life = life - 1;
            if(life<=0){
                goToPreviousActivity();
            }
            String text = getString(R.string.answer, result);
            aTV.setText(text);
            aTV.setVisibility(View.VISIBLE);
            iTV.setVisibility(View.VISIBLE);
        }

        finish();
    }

    public int getNumber1() {
        return _number1;
    }

    private void setNumber1(int number1) {
        this._number1 = number1;
    }

    public int getNumber2() {
        return _number2;
    }

    private void setNumber2(int number2) {
        this._number2 = number2;
    }

    private TypeOperationEnum getTypeOperator() {
        return _typeOperation;
    }

    private void setTypeOperator(TypeOperationEnum typeOperation) {
        this._typeOperation = typeOperation;
    }

    public int getAnswer() {
        return _answer;
    }

    public int getResult() {
        return result;
    }

    private void setAnswer(int result) {
        _answer = result;
    }

    private boolean ouvrirDernierQuizz() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("premierElement", getNumber1());
        intent.putExtra("deuxiemeElement", getNumber2());
        intent.putExtra("symbol", getTypeOperator());
        intent.putExtra("resultat",getResult());
        Calcul calcul = new Calcul();
        calcul.setPremierElement(getNumber1());
        calcul.setDeuxiemeElement(getNumber2());
        calcul.setSymbol(getTypeOperator().toString());
        calcul.setResultat(getAnswer());
        startActivity(intent);

        return true;
    }
}
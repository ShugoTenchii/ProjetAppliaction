package com.jger.groupe5v2.view;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


import com.jger.groupe5v2.R;
import com.jger.groupe5v2.models.TypeOperationEnum;
import com.jger.groupe5v2.models.OperationModel;
import com.jger.groupe5v2.services.OperationsGeneratorService;

import com.jger.groupe5v2.models.exception.NoAnswerException;
import com.jger.groupe5v2.models.exception.WrongAnswerException;
import com.jger.groupe5v2.services.OperationsGeneratorService;
import com.jger.groupe5v2.services.ResolutionService;

public class MentalActivity extends AppCompatActivity {

    private static int _answer;
    private TextView operationText;
    private int _number1;
    private int _number2;
    private TypeOperationEnum _typeOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental);

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        operationText = findViewById(R.id.operation_text);
        Button home_image_button = findViewById(R.id.home_image_button);
        EditText editText = findViewById(R.id.answer_zone);
        TextView correctTV = findViewById(R.id.correct_text);
        TextView incorrectTV = findViewById(R.id.incorrect_text);
        TextView skipTV = findViewById(R.id.skip_text);
        TextView answerTV = findViewById(R.id.answer_text);
        Button validButton =  findViewById(R.id.valid_button);

        OperationsGeneratorService calculator = new OperationsGeneratorService();
        OperationModel operationG = calculator.CallFunctions();

        home_image_button.setOnClickListener(view -> goToPreviousActivity());
        editText.requestFocus();
        validButton.setOnClickListener(view -> Answer(editText, home_image_button, validButton, editText.getText().toString(), operationG, correctTV, incorrectTV, skipTV, answerTV));

        setAttributes(operationG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.score_view_menu, menu);
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

    private void Answer(EditText answerZone, Button iB, Button b, String proposedAnswer, OperationModel opModel, TextView cTV, TextView iTV, TextView sTV, TextView aTV) {
        Intent intent = new Intent(this, MentalActivity.class);
        startActivity(intent);

        answerZone.setVisibility(View.GONE);
        iB.setVisibility(View.GONE);
        b.setVisibility(View.GONE);

        ResolutionService answer = new ResolutionService();
        int result = answer.compute(opModel);

        try {
            answer.CorrectAnswer(proposedAnswer, opModel);
            cTV.setVisibility(View.VISIBLE);
        } catch (NoAnswerException e) {
            String text = getString(R.string.answer, result);
            aTV.setText(text);
            aTV.setVisibility(View.VISIBLE);
            sTV.setVisibility(View.VISIBLE);
        } catch (WrongAnswerException e) {
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

    public static int getAnswer() {
        return _answer;
    }

    private void setAnswer(int result) {
        this._answer = result;
    }
}
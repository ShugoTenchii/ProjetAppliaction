package com.jger.groupe5v2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jger.groupe5v2.R;
import com.jger.groupe5v2.models.TypeOperationEnum;
import com.jger.groupe5v2.view.MentalActivity;

public class ScoreActivity extends AppCompatActivity {
    public final static String NUMBER1_KEY = "NUMBER1";
    public final static String NUMBER2_KEY = "NUMBER2";
    public final static String TYPE_OPERATOR_KEY = "TYPE_OPERATOR";
    public final static String ANSWER_KEY = "ANSWER";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        int number1 = intent.getIntExtra(NUMBER1_KEY, 0);
        int number2 = intent.getIntExtra(NUMBER2_KEY, 0);
        TypeOperationEnum typeOperator = intent.getParcelableExtra(TYPE_OPERATOR_KEY);
        int answer = intent.getIntExtra(ANSWER_KEY, 0);

        Button previous_button = findViewById(R.id.previous_button);
        previous_button.setOnClickListener(view -> goToPreviousActivity());

        TextView nombreOperation = findViewById(R.id.textView_Nb_Op);
        TextView precision = findViewById(R.id.textView_Precision);
        TextView calculEtDernier = findViewById(R.id.last_operation_text);

        System.out.println("test     : " + intent.getIntExtra("resultat",0));
        if( intent.getIntExtra("resultat",0)!= 0){
            nombreOperation.setText(R.string.operations_count_label );
        }else{
            nombreOperation.setText(R.string.operations_count_label);
        }
    }

    private void goToPreviousActivity() {
        finish();
    }
}


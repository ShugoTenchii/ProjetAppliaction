package com.jger.groupe5v2.services;

import androidx.annotation.NonNull;

import com.jger.groupe5v2.models.TypeOperationEnum;
import com.jger.groupe5v2.models.OperationModel;
import com.jger.groupe5v2.models.exception.NoAnswerException;
import com.jger.groupe5v2.models.exception.WrongAnswerException;

public class ResolutionService {

    public int compute(@NonNull OperationModel operation) {
        if(operation == null){
            throw new NullPointerException("operation can not be null");
        }

        int number1 = operation.getNumber1();
        int number2 = operation.getNumber2();
        TypeOperationEnum operator = operation.getTypeOperator();

        int result;
        switch (operator) {
            case ADD:
                result = number1 + number2;
                break;
            case SUBSTRACT:
                result = number1 - number2;
                break;
            case MULTIPLY:
                result = number1 * number2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }

        return result;
    }

    public void CorrectAnswer(String aZ, OperationModel oM) throws NoAnswerException, WrongAnswerException {
        if (aZ.equals("")){
            throw new NoAnswerException("Operation skipped");
        }

        int proposedAnswer = Integer.parseInt(aZ);
        int answer = compute(oM);
        if (proposedAnswer != answer){
            throw new WrongAnswerException("Wrong Answer proposed");
        }
    }
}

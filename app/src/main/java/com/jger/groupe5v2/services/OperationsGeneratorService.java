package com.jger.groupe5v2.services;

import com.jger.groupe5v2.models.TypeOperationEnum;
import com.jger.groupe5v2.models.OperationModel;

public class OperationsGeneratorService {

    private int numbersGenerator() {
        return (int) (11*Math.random());
    }

    private TypeOperationEnum OperationGenerator() {
        TypeOperationEnum typeOp;
        switch ((int) (3*Math.random())) {
            case 0:
                typeOp = TypeOperationEnum.ADD;
                break;
            case 1:
                typeOp = TypeOperationEnum.SUBSTRACT;
                break;
            case 2:
                typeOp = TypeOperationEnum.MULTIPLY;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (int) (3 * Math.random()));
        }

        return typeOp;
    }

    public OperationModel CallFunctions() {
        int n1 = numbersGenerator();
        int n2 = numbersGenerator();
        TypeOperationEnum operator = OperationGenerator();

        if (operator == TypeOperationEnum.DIVIDE){
            if (n1 < n2) {
                int temp = n1;
                n1 = n2;
                n2 = temp;
            }

            if (n2 == 0)
                n2++;

            n1 *= n2; //Guarantee a rest of 0
        }

        return new OperationModel(n1, n2, operator);
    }
}

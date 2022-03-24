package com.jger.groupe5v2.controller;

import com.jger.groupe5v2.models.Calcul;

public class CalculService {
    private CalculDao calculDao;

    public CalculService(CalculDao calculDao) {
        this.calculDao = calculDao;
    }

    public Long getComputeCount(){
        return calculDao.count();
    }

    public void storeInDB(Calcul calcul){
        calculDao.create(calcul);
    }
}

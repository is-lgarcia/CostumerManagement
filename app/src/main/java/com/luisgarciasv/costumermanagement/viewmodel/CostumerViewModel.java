package com.luisgarciasv.costumermanagement.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.luisgarciasv.costumermanagement.data.sqlite.CostumerCrud;
import com.luisgarciasv.costumermanagement.model.Costumer;

import java.util.List;

public class CostumerViewModel extends AndroidViewModel {

    private CostumerCrud crud;
    private LiveData<List<Costumer>> allCostumers;

    public CostumerViewModel(@NonNull Application application) {
        super(application);
        crud = new CostumerCrud(application.getApplicationContext());
        allCostumers = crud.getCostumers();
    }

    public LiveData<List<Costumer>> getCustomers(){
        return allCostumers;
    }

    public void newCostumer(Costumer costumer){
        crud.newCostumer(costumer);
        refreshCostumers();
    }

    public LiveData<Costumer> getCostumer(String dui){
        return crud.getCostumer(dui);
    }

    public void deleteCostumer(String dui){
        crud.deleteCostumer(dui);
        refreshCostumers();
    }

    public void updateCostumer(Costumer costumer){
        crud.updateCostumer(costumer);
        refreshCostumers();
    }

    private void refreshCostumers(){
        allCostumers = crud.getCostumers();
    }
}

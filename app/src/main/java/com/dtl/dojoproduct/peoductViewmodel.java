package com.dtl.dojoproduct;

import androidx.lifecycle.ViewModel;

public class peoductViewmodel extends ViewModel {
    private productModel model;

    public void setModel(productModel model) {
        this.model = model;
    }

    public productModel getModel() {
        return model;
    }
}

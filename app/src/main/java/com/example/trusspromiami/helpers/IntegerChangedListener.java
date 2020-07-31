package com.example.trusspromiami.helpers;

public class IntegerChangedListener {
    private int quantityValue = 0;
    private ChangeListener changeListener;

    public int getQuantityValue() {

        return quantityValue;
    }

    public void setQuantityValue(int quantityValue) {
        if (quantityValue > 0) {
            this.quantityValue = quantityValue;
            if (changeListener != null) changeListener.onChange();
        }

    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }


    public interface ChangeListener {
        void onChange();
    }


}
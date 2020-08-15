package com.example.trusspromiami.helpers;

public class IntegerChangedListener {
    private int quantityValue = 1;
    private ChangeListener changeListener;

    public int getQuantityValue() {

        return quantityValue;
    }

    public void setQuantityValue(int quantityValue) {

            this.quantityValue = quantityValue;
            if (changeListener != null)
                changeListener.onChange();


    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }


    public interface ChangeListener {
        void onChange();
    }


}
package com.example.trusspromiami.adapters;

import android.widget.Filter;
import android.widget.Filterable;

public class SearchAdapter implements Filterable {
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ///Filter Logic
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notify();

            }
        };
    }
}

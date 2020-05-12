package ru.mirea.casehistory;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    ArrayList<Model> filterList;

    Adapter adapter;

    public CustomFilter(ArrayList<Model> filterList, Adapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();

        if(constraint != null && constraint.length() > 0) {

            constraint = constraint.toString().toUpperCase();

            ArrayList<Model> filterModels = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++){
                if(filterList.get(i).getName().toUpperCase().contains(constraint)) {

                    filterModels.add(filterList.get(i));
                }
            }

            results.count = filterModels.size();
            results.values = filterModels;
        }
        else {

            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.arrayList = (ArrayList<Model>) results.values;
        adapter.notifyDataSetChanged();
    }
}

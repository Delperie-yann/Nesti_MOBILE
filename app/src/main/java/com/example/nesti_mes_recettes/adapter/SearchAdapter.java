package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Search;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<Search> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param search
     */

    // public IngredientAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
    //    super(context, textViewResourceId, ingredients);
    // }

    public SearchAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Search> search) {
        super(context, textViewResourceId, search);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_search, parent, false);
        }
        Search one_recipe = getItem(position);

        TextView name = result.findViewById(R.id.list_result_search);
        name.setText(one_recipe.getName());



        return result;

    }
}

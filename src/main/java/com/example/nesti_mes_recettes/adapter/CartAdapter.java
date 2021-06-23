package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;

public class CartAdapter extends ArrayAdapter<Ingredient> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param ingredients
     */


    public CartAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_buy, parent, false);
        }
        Ingredient one_ingredient = getItem(position);



        CheckedTextView ctv = (CheckedTextView)result.findViewById(R.id.buy_txt_view);
        ctv.setText(one_ingredient.getNameProduct());

        ctv.setChecked(one_ingredient.getCheck() == 1);
        Log.i("clik", "Action click  1");

        return result;

    }


}

package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Paragraph;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;

public class ParagraphAdapter extends ArrayAdapter<Paragraph> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param paragraph
     */

    // public IngredientAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
    //    super(context, textViewResourceId, ingredients);
    // }

    public ParagraphAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Paragraph> paragraph) {
        super(context, textViewResourceId, paragraph);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_step, parent, false);
        }
        Paragraph one_paragraph = getItem(position);

        TextView name = result.findViewById(R.id.setp_paragraph);
        name.setText(one_paragraph.getContent());


        return result;

    }
}



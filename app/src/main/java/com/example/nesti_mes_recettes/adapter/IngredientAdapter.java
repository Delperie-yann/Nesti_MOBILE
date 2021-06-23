package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.nesti_mes_recettes.R;

import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;


public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param ingredients
     */

   // public IngredientAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
    //    super(context, textViewResourceId, ingredients);
   // }

    public IngredientAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_ing, parent, false);
        }
        Ingredient one_ingredient = getItem(position);

        TextView name = result.findViewById(R.id.ingredient_name);
        name.setText(one_ingredient.getNameProduct());

        TextView quant = result.findViewById(R.id.ingredient_quant);
        quant.setText(one_ingredient.getQuantity());

        TextView unit = result.findViewById(R.id.ingredient_unit);
        unit.setText(one_ingredient.getUnitName());

        FloatingActionButton btn = result.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                try{
                    addItem(one_ingredient);

                }catch(Exception e) {
                    e.printStackTrace();}}});
        return result;

    }
    /*** Add in database sqlite* @param item*/
    public void addItem(Ingredient item) {
        try {
            TableCart model = new TableCart(getContext());
            model.insertItem(item);
            Toast.makeText(getContext(), item.getNameProduct() + " ajouté au panier",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }}

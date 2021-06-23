package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nesti_mes_recettes.adapter.CartAdapter;
import com.example.nesti_mes_recettes.adapter.IngredientAdapter;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Recipe;

import java.util.ArrayList;

import data.sqlite.TableCart;
import model.IngredientViewModel;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ListView listView = findViewById(R.id.easy_list_buy);

        IngredientViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(IngredientViewModel.class);
        TableCart model = new TableCart(BuyActivity.this);

        AllItemControl(model,listView);

        final Button btnEmpty = (Button) findViewById(R.id.btn_empty_buy);
        btnEmpty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TableCart model = new TableCart(BuyActivity.this);
                model.removeAllItem();
                AllItemControl(model,listView);

            }
        });


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final Ingredient ingredient = (Ingredient) parent.getItemAtPosition(position);

                        ingredient.setChek(1);
                        model.insertItem(ingredient);

                        AllItemControl(model,listView);

                    }
                }
        );
    }

    public void AllItemControl(TableCart model,ListView listView) {
        ArrayList<Ingredient> cart = model.getAllItems();
        CartAdapter cartAdapter = new CartAdapter(this, R.layout.line_buy, (ArrayList<Ingredient>) cart);
         listView.setAdapter(cartAdapter);
    }
}
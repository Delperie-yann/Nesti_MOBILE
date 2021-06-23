package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.nesti_mes_recettes.adapter.RecipeAdapter;
import com.example.nesti_mes_recettes.entity.Recipe;

import java.util.ArrayList;

import model.GlutenViewModel;
import model.TraditionViewModel;

public class TraditionActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trad);

        TraditionViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(TraditionViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            ListView list_listView = (ListView) findViewById(R.id.ingredient_listView);
            RecipeAdapter glutenAdapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);

            list_listView.setAdapter(glutenAdapter);
        });

        final Button btnBack = (Button) findViewById(R.id.btn_trad_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TraditionActivity.this.finish();
            }
        });

        final ListView list_listView = (ListView) findViewById(R.id.ingredient_listView);
        list_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TraditionActivity.this,
                        IngListActivity.class);
                final Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                Bundle extras =new Bundle();
                extras.putString("name", String.valueOf(recipe.getTitle()));
                extras.putInt("idRecipe", (recipe.getIdRecipe()));
                intent.putExtras(extras);
                startActivity(intent);


            }


        });
    }

}
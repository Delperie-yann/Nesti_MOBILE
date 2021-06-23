package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.nesti_mes_recettes.adapter.RecipeAdapter;
import com.example.nesti_mes_recettes.entity.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.GlutenViewModel;
import model.SeasonViewModel;

public class SeasonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);

        SeasonViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(SeasonViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            ListView list_listView = (ListView) findViewById(R.id.season_listView);
            RecipeAdapter glutenAdapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);

            list_listView.setAdapter(glutenAdapter);
        });

        final Button btnBack = (Button) findViewById(R.id.btn_season_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeasonActivity.this.finish();
            }
        });

        final ListView list_listView = (ListView) findViewById(R.id.season_listView);
        list_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SeasonActivity.this,
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
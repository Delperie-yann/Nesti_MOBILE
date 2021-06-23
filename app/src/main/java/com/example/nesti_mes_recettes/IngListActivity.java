package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;

import com.example.nesti_mes_recettes.adapter.IngredientAdapter;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.IngredientViewModel;

public class IngListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ing_list);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int sessionId = extras.getInt("idRecipe");

        Toolbar textView = (Toolbar) findViewById(R.id.toolbar);
        textView.setTitle(name);


        IngredientViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(IngredientViewModel.class);

        viewModel.getIngredient(sessionId).observe(this, ingredients -> {
            ListView list_listView = (ListView) findViewById(R.id.ingredient_listView);
            IngredientAdapter ingredientAdapter  = new IngredientAdapter(this,R.layout.line_recipe, (ArrayList<Ingredient>) ingredients);

            list_listView.setAdapter(ingredientAdapter);
        });
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("IdRecipe", String.valueOf(view.getId()));
                Intent intent = new Intent(IngListActivity.this, BuyActivity.class);

                startActivity(intent);
            }
        });


       Button prep_to_step  = findViewById(R.id.step_to_prep);
        prep_to_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IngListActivity.this, StepListActivity.class);
                extras.putString("name", name);
                extras.putInt("idRecipe", sessionId);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
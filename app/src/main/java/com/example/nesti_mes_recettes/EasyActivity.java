package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nesti_mes_recettes.adapter.RecipeAdapter;
import com.example.nesti_mes_recettes.entity.Recipe;

import java.util.ArrayList;

import model.EasyViewModel;
import model.GlutenViewModel;

public class EasyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        EasyViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(EasyViewModel.class);

        viewModel.getRecipes().observe(this, recipes -> {
            ListView list_listView = (ListView) findViewById(R.id.easy_listview);
            RecipeAdapter easyAdapter = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);

            list_listView.setAdapter(easyAdapter);
        });

        final Button btnBack = (Button) findViewById(R.id.btn_easy_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyActivity.this.finish();
            }
        });

        final ListView list_listView = (ListView) findViewById(R.id.easy_listview);
        list_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(EasyActivity.this,
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
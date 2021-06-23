package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;

import com.example.nesti_mes_recettes.adapter.IngredientAdapter;
import com.example.nesti_mes_recettes.adapter.ParagraphAdapter;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Paragraph;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import model.IngredientViewModel;
import model.ParagraphViewModel;

public class StepListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);
        Toolbar toolbar = findViewById(R.id.toolbar);


        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int sessionId = extras.getInt("idRecipe");

        Toolbar textView = (Toolbar) findViewById(R.id.toolbar);
        textView.setTitle(name);

        ParagraphViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(ParagraphViewModel.class);

        viewModel.getParagraph(sessionId).observe(this, paragraph -> {
            ListView list_listView = (ListView) findViewById(R.id.step_listView);
            ParagraphAdapter paragraphAdapter  = new ParagraphAdapter(this,R.layout.line_step, (ArrayList<Paragraph>) paragraph);

            list_listView.setAdapter(paragraphAdapter);
        });
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StepListActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });

        Button prep_to_step  = findViewById(R.id.step_to_prep);
        prep_to_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StepListActivity.this, IngListActivity.class);

                intent.putExtras(extras);
                startActivity(intent);
                startActivity(intent);
            }
        });
    }
}
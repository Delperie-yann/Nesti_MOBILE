package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EditText edit_text = findViewById(R.id.search_editText);


        final Button btnSearch = (Button) findViewById(R.id.search_btnOk);
        btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Récupération de la valeur saisie

                        String term = edit_text.getText().toString();

                        if (!term.matches("")) {
                            Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                            // Ajout de l’extra “term”
                            intent.putExtra("term", term);

                            startActivity(intent);
                        }
                    }

                }
        );
    }


}
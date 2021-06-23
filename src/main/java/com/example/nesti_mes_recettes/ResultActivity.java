package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nesti_mes_recettes.adapter.SearchAdapter;
import com.example.nesti_mes_recettes.entity.Recipe;
import com.example.nesti_mes_recettes.entity.Search;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.SearchViewModel;

import static com.example.nesti_mes_recettes.R.layout.activity_result;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String term = this.getIntent().getStringExtra("term");
        SearchViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(
                this.getApplication()).create(SearchViewModel.class);

        viewModel.getSearch(term).observe(this, search -> {
            ListView list_search = (ListView) findViewById(R.id.result_listView);
            SearchAdapter searchAdapter = new SearchAdapter(this, R.layout.line_search, (ArrayList<Search>) search);

            list_search.setAdapter(searchAdapter);
        });
            // Modification du textView
            TextView text_view = findViewById(R.id.result_txtview_term);
            text_view.setText((String) term);

        final ListView list_search = (ListView) findViewById(R.id.result_listView);
        list_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ResultActivity.this,
                        IngListActivity.class);
                final Search search = (Search) parent.getItemAtPosition(position);
                String name = text_view.getText().toString();

                Bundle extras =new Bundle();
                extras.putString("name", search.getName());
                extras.putInt("idRecipe", search.getIdRecipe());
                intent.putExtras(extras);
                startActivity(intent);
            }


        });
    }
}
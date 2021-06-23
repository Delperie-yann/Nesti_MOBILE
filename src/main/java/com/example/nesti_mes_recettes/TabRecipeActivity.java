package com.example.nesti_mes_recettes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

public class TabRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_recipe);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String idRecipe = extras.getString("idRecipe");
        TextView textView = findViewById(R.id.title_recipe);

        textView.setText(name);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TabRecipeActivity.this, BuyActivity.class);
                startActivity(intent);
                Snackbar.make(view, "liste de course", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
            }
        });
    }
}
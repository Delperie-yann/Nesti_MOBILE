package com.example.nesti_mes_recettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnEasy = (Button) findViewById(R.id.btn_easy);
      // this.deleteDatabase("NestiList.db");
        Log.i("data :", "NestiList.db");
        btnEasy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Facile à faire");
                alertDialog.setMessage("Voulez vous choisir une recette facile à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), btnEasy.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, EasyActivity.class);
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });

        final Button btnTrad = (Button) findViewById(R.id.btn_trad);
        btnTrad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Traditionnelle");
                alertDialog.setMessage("Voulez vous choisir une recette traditionnelle à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(getApplicationContext(), btnTrad.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, TraditionActivity.class);
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnSeason = (Button) findViewById(R.id.btn_season);
        btnSeason.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Recette de saison");
                alertDialog.setMessage("Voulez vous choisir une recette de saison  à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), btnSeason.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SeasonActivity.class);
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnGluten = (Button) findViewById(R.id.btn_gluten);
        btnGluten.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Sans gluten");
                alertDialog.setMessage("Voulez vous choisir une recette sans gluten à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), btnGluten.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, GlutenActivity.class);
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_general, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem pItem) {
        switch (pItem.getItemId()) {
            case R.id.menu_search:

                Intent intentSearch= new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.menu_list:

                Intent intentlist= new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intentlist);
                break;
            case R.id.menu_contact:

                Intent intent= new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_team:

                Intent intentTeam= new Intent(MainActivity.this, TeamActivity.class);
                startActivity(intentTeam);
                break;
            case R.id.menu_project:

                Intent intentProject= new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(intentProject);
                break;
        }
        return true;
    }
}
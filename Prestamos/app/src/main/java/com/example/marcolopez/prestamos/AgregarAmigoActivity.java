package com.example.marcolopez.prestamos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AgregarAmigoActivity extends AppCompatActivity {

    LinearLayout agregarAmigoLyt;
    EditText usernameEdtTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_amigo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        agregarAmigoLyt = (LinearLayout) findViewById(R.id.agregarAmigoLyt);
        usernameEdtTxt = (EditText) findViewById(R.id.usernameEdtTxt);



    }

}

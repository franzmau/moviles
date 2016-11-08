package com.example.marcolopez.prestamos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout registrarmeLyt, iniciarLyt;
    EditText usuarioEdtTxt, contrasenaEdtTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarLyt = (LinearLayout) findViewById(R.id.iniciarLyt);
        registrarmeLyt = (LinearLayout) findViewById(R.id.registrarmeLyt);

        usuarioEdtTxt = (EditText) findViewById(R.id.usuarioEdtTxt);
        contrasenaEdtTxt = (EditText) findViewById(R.id.contrasenaEdtTxt);

        registrarmeLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });

        iniciarLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Aqui le pones las cosas para iniciar sesión
            }
        });
    }



}

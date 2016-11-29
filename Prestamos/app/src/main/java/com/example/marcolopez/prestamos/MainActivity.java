package com.example.marcolopez.prestamos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Arrays;

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
                //Inicio de sesión
                String usernameString = usuarioEdtTxt.getText().toString();
                String passwordString = contrasenaEdtTxt.getText().toString();
                if (usernameString != "" && passwordString != ""){
                    login(usernameString, passwordString, registrarmeLyt);
                }
            }
        });

        if (ParseUser.getCurrentUser() != null && ParseUser.getCurrentUser().isAuthenticated()) {
            Intent i = new Intent(MainActivity.this, Profile.class);
            startActivity(i);
            finish();
            return;
        }
    }

    private void login(String userName, String password, final View btn) {
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, getString(R.string.cargando),
                "", true, false, null);
        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    if (progressDialog != null) {
                        progressDialog.cancel();
                    }
                    ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                    installation.addAllUnique("channels", Arrays.asList("user_" + user.getObjectId()));
                    installation.saveInBackground();

                    Intent intent = new Intent(MainActivity.this, CrearPrestamoActivity.class);
                    startActivity(intent);
                    finish();
                    Toast toast1 = Toast.makeText(getApplicationContext(), getResources().getString(R.string.acceso_correcto),
                            Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    if (e.getCode() == 100) {
                        if (progressDialog != null) {
                            progressDialog.cancel();
                        }
                        alertDialogGeneral(getResources().getString(R.string.conexion), "", true, btn);
                    } else {
                        if (progressDialog != null) {
                            progressDialog.cancel();
                        }
                        alertDialogGeneral(getResources().getString(R.string.user_pass_incorrect), "", true, btn);
                    }
                }
            }
        });
    }

    public void alertDialogGeneral(String mensaje,String titulo, final boolean select, final View btn){
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setCancelable(false);
        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        dialog.setButton(1 , getResources().getString(R.string.btn_ok_alert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                btn.setEnabled(select);
            }
        });
        dialog.show();
    }


}

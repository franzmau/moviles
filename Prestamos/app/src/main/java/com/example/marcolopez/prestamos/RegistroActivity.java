package com.example.marcolopez.prestamos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegistroActivity extends AppCompatActivity {

    LinearLayout registrarLyt;
    EditText confContrasenaEdtTxt, contrasenaEdtTxt, correoEdtTxt, usuarioEdtTxt, nombreEdtTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrarLyt = (LinearLayout) findViewById(R.id.registrarLyt);

        nombreEdtTxt = (EditText) findViewById(R.id.nombreEdtTxt);
        usuarioEdtTxt = (EditText) findViewById(R.id.usuarioEdtTxt);
        correoEdtTxt = (EditText) findViewById(R.id.correoEdtTxt);
        contrasenaEdtTxt = (EditText) findViewById(R.id.contrasenaEdtTxt);
        confContrasenaEdtTxt = (EditText) findViewById(R.id.confContrasenaEdtTxt);



        registrarLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validacionRegistro();

            }
        });

    }



    public void validacionRegistro(){
        String nombreusuario = nombreEdtTxt.getText().toString();
        String correo = correoEdtTxt.getText().toString();
        String contrasenaedt = contrasenaEdtTxt.getText().toString();
        String confirmarcontrasena = confContrasenaEdtTxt.getText().toString();
        String usuario = usuarioEdtTxt.getText().toString();
        if(nombreusuario.equals("") || correo.equals("") || contrasenaedt.equals("")
                || confirmarcontrasena.equals("") || usuario.equals("")){
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setCancelable(false);
            dialog.setMessage("Llene todos los campos.");
            dialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    return;
                }
            });
            dialog.show();
        }else{
            if(!correo.matches("^\\w+([\\.\\+\\-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$")){
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setCancelable(false);
                dialog.setMessage("Formato de correo invalido.");
                dialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                dialog.show();
            }
            else if(!contrasenaedt.equals(confirmarcontrasena)){
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setCancelable(false);
                dialog.setMessage("Las contraseñas no coinciden.");
                dialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });

                dialog.show();
            }else{
                ParseUser usuarioNuevo = new ParseUser();
                usuarioNuevo.setUsername(usuario);
                usuarioNuevo.setEmail(correo);
                usuarioNuevo.setPassword(contrasenaedt);
                usuarioNuevo.put("nombre", nombreusuario);
                usuarioNuevo.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(RegistroActivity.this, Profile.class);
                            startActivity(i);
                            finish();
                        } else {

                            Log.i("Registro", "" + e.getMessage());
                            Toast.makeText(RegistroActivity.this, "No se pudo registrar", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }


}

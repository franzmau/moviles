package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class AgregarAmigoActivity extends Activity {

    LinearLayout agregarAmigoLyt;
    EditText usernameEdtTxt;
    ProgressDialog progress;
    ImageView btnFloatMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_amigo);

        agregarAmigoLyt = (LinearLayout) findViewById(R.id.agregarAmigoLyt);
        usernameEdtTxt = (EditText) findViewById(R.id.usernameEdtTxt);
        btnFloatMenu = (ImageView) findViewById(R.id.btnMenuFloat);

        buildMenuFloating();

        agregarAmigoLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = ProgressDialog.show(AgregarAmigoActivity.this, "",
                        "Cargando...", true);
                if(!usernameEdtTxt.getText().toString().equals("")){

                    getUser(usernameEdtTxt.getText().toString());
                }else{
                    progress.cancel();
                    AlertDialog dialog = new AlertDialog.Builder(AgregarAmigoActivity.this).create();
                    dialog.setCancelable(false);
                    dialog.setMessage("Agrega un usuario");
                    dialog.setButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            return;
                        }
                    });

                    dialog.show();
                }
            }
        });

    }

    public void getUser(final String username){

        ParseQuery <ParseUser> query =ParseUser.getQuery();
        query.whereEqualTo("username",username);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                        if(!objects.isEmpty()){

                    addFriend(username);
                        }
                    else{
                    Toast.makeText(AgregarAmigoActivity.this, "Amigo no encontrado", Toast.LENGTH_SHORT).show();
                    progress.cancel();
                        }
                } else {
                    // Something went wrong.

                }
            }
        });


    }

    public void addFriend(String username){
        ParseObject friend = new ParseObject("friends");
        ParseUser currentUser = ParseUser.getCurrentUser();
        friend.put("amigo", currentUser.getUsername());
        friend.put("username_f", username);
        friend.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    progress.cancel();
                    Toast.makeText(AgregarAmigoActivity.this, "Amigo Agregado", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(AgregarAmigoActivity.this, Profile.class);
                    startActivity(i);
                }
            }
        });
    }

    public void buildMenuFloating(){
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(AgregarAmigoActivity.this)
                .setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.button_action, null));
        ImageView itemEvent = new ImageView(this);
        itemEvent.setImageResource(R.mipmap.ic_launcher);
        ImageView itemGuest = new ImageView(this);
        itemGuest.setImageResource(R.mipmap.invoice);
        ImageView itemPeople = new ImageView(this);
        itemPeople.setImageResource(R.mipmap.ic_launcher);
        ImageView amigosImage = new ImageView(this);
        itemPeople.setImageResource(R.mipmap.ic_launcher);

        SubActionButton button1 = itemBuilder.setContentView(amigosImage).build();
        SubActionButton button2 = itemBuilder.setContentView(itemEvent).build();
        SubActionButton button3 = itemBuilder.setContentView(itemGuest).build();
        SubActionButton button4 = itemBuilder.setContentView(itemPeople).build();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarAmigoActivity.this, Deudas.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarAmigoActivity.this, CrearPrestamoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarAmigoActivity.this, Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarAmigoActivity.this, AgregarAmigoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(AgregarAmigoActivity.this)
                .setStartAngle(180)
                .setEndAngle(270)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(btnFloatMenu)
                .build();
        actionMenu.getActionViewCenter();
    }
}

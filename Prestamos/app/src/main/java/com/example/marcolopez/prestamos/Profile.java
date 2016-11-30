package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Profile extends Activity {
    ImageView btnFloatMenu;
    ListView listaAmigos;
    TextView logoutTxt;
    Integer [] icons={
            R.drawable.male
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listaAmigos = (ListView) findViewById(R.id.listaAmigos);
        logoutTxt = (TextView) findViewById(R.id.logoutTxt);
        btnFloatMenu = (ImageView) findViewById(R.id.btnMenuFloat);

        cargarAmigos();
        buildMenuFloating();

        logoutTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicio de sesión
                ParseUser.logOut();
                Intent i = new Intent(Profile.this, MainActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });



    }



    public void cargarAmigos(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("friends");
        query.whereEqualTo("amigo", ParseUser.getCurrentUser().getUsername());
        try {
            System.out.println("contador "+query.count()+"usuario"+ParseUser.getCurrentUser().getUsername());
            } catch (ParseException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    final ParseObject[] objects = list.toArray(new ParseObject[list.size()]);

                    ParseObject[] dataList = new ParseObject[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        dataList[i] = (ParseObject) list.get(i);
                    }
                    System.out.println("ayuda "+dataList);
                    AmigosAdapter adapter = new AmigosAdapter(Profile.this,
                            R.layout.item_amigos, objects, icons[0]);
                    listaAmigos.setDivider(new ColorDrawable(0xFFFFFF));
                    listaAmigos.setDividerHeight(1);
                    listaAmigos.setAdapter(adapter);



                }else{
                    System.out.println("vacio");
                }
            }
        });






    }

    public void buildMenuFloating(){
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(Profile.this)
                .setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.menu_copia, null));
        ImageView itemEvent = new ImageView(this);
        itemEvent.setImageResource(R.mipmap.dinero_copia);
        ImageView itemGuest = new ImageView(this);
        itemGuest.setImageResource(R.mipmap.dar_copia);
        ImageView itemPeople = new ImageView(this);
        itemPeople.setImageResource(R.mipmap.gente_copia);
        ImageView amigosImage = new ImageView(this);
        itemPeople.setImageResource(R.mipmap.people_copia);

        SubActionButton button1 = itemBuilder.setContentView(amigosImage).build();
        SubActionButton button2 = itemBuilder.setContentView(itemEvent).build();
        SubActionButton button3 = itemBuilder.setContentView(itemGuest).build();
        SubActionButton button4 = itemBuilder.setContentView(itemPeople).build();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Deudas.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, CrearPrestamoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, AgregarAmigoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(Profile.this)
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





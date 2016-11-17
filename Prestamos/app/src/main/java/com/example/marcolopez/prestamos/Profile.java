package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import java.util.List;

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
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {

                    final ParseObject[] objects = list.toArray(new ParseObject[list.size()]);
                    ParseObject[] dataList = new ParseObject[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        dataList[i] = (ParseObject) list.get(i);
                    }
                    AmigosAdapter adapter = new AmigosAdapter(Profile.this,
                            R.layout.item_amigos, objects, icons[0]);
                    listaAmigos.setDivider(new ColorDrawable(0xFFFFFF));
                    listaAmigos.setDividerHeight(1);
                    listaAmigos.setAdapter(adapter);

                }else{
                }
            }
        });
    }

    public void buildMenuFloating(){
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(Profile.this)
                .setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.button_action, null));
        ImageView itemEvent = new ImageView(this);
        itemEvent.setImageResource(R.mipmap.ic_launcher);
        ImageView itemGuest = new ImageView(this);
        itemGuest.setImageResource(R.mipmap.invoice);
        ImageView itemPeople = new ImageView(this);
        itemPeople.setImageResource(R.mipmap.ic_launcher);


        SubActionButton button2 = itemBuilder.setContentView(itemEvent).build();
        SubActionButton button3 = itemBuilder.setContentView(itemGuest).build();
        SubActionButton button4 = itemBuilder.setContentView(itemPeople).build();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Deudas.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, RegistroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(Profile.this)
                .setStartAngle(200)
                .setEndAngle(100)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(btnFloatMenu)
                .build();
        actionMenu.getActionViewCenter();
    }


}

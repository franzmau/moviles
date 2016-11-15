package com.example.marcolopez.prestamos;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Profile extends AppCompatActivity {

    ListView listaAmigos;
    TextView logoutTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listaAmigos = (ListView) findViewById(R.id.listaAmigos);
        logoutTxt = (TextView) findViewById(R.id.logoutTxt);

        cargarAmigos();

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
                            R.layout.item_amigos, objects);
                    listaAmigos.setDivider(new ColorDrawable(0xFFFFFF));
                    listaAmigos.setDividerHeight(1);
                    listaAmigos.setAdapter(adapter);

                }else{
                }
            }
        });
    }


}

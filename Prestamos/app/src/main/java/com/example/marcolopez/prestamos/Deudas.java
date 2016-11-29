package com.example.marcolopez.prestamos;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.Serializable;
import java.util.List;

public class Deudas extends AppCompatActivity {

    ListView deudas;
    ListView prestamos;
    TextView logoutTxt;
    Integer [] debess={
            R.drawable.debes
    };
    Integer [] prestamo={
            R.drawable.teben
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deudas);

        deudas = (ListView) findViewById(R.id.debes);
        prestamos=(ListView)findViewById(R.id.te_deben);
        logoutTxt = (TextView) findViewById(R.id.logoutTxt);

       cargardeudas();
        cargarprestamos();

        logoutTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicio de sesión
                ParseUser.logOut();
                Intent i = new Intent(Deudas.this, MainActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });








        prestamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String stringID = objects[position].getObjectId();
                Intent intent= new Intent(Deudas.this, AddPrestamo.class);
                intent.putExtra("stringID", stringID);
                startActivity(intent);

                  //Toast.makeText(getBaseContext(),,Toast.LENGTH_SHORT).show();
            }
        });
    }

    ParseObject[] objects;

    public void cargarprestamos(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");
        query.whereEqualTo("lender", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    objects = list.toArray(new ParseObject[list.size()]);
                    ParseObject[] dataList = new ParseObject[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        dataList[i] = (ParseObject) list.get(i);
                    }
                    DeudasAdapter adapter = new DeudasAdapter(Deudas.this,
                            R.layout.list_prestamos, objects, prestamo[0],1);
                    prestamos.setDivider(new ColorDrawable(0xFFFFFF));
                    prestamos.setDividerHeight(1);
                    prestamos.setAdapter(adapter);

                }else{
                }
            }
        });
    }

    public void cargardeudas(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");
        query.whereEqualTo("borrower", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {

                    final ParseObject[] objects = list.toArray(new ParseObject[list.size()]);
                    ParseObject[] dataList = new ParseObject[list.size()];

                    for (int i = 0; i < list.size(); i++) {
                        dataList[i] = (ParseObject) list.get(i);

                    }
                    DeudasAdapter adapter = new DeudasAdapter(Deudas.this,
                            R.layout.list_deudas, objects, debess[0], 0);
                    deudas.setDivider(new ColorDrawable(0xFFFFFF));
                    deudas.setDividerHeight(1);
                    deudas.setAdapter(adapter);

                }else{
                }
            }
        });
    }


}

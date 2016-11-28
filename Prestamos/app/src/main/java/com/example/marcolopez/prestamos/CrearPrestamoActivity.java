package com.example.marcolopez.prestamos;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrearPrestamoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_prestamo);
        Button button = (Button) findViewById(R.id.send);


        final Spinner amigos = (Spinner) findViewById(R.id.amigo);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("friends");
        query.whereEqualTo("amigo", ParseUser.getCurrentUser().getUsername());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<String> nameList = new ArrayList<>();
                    for(ParseObject object : list) {
                        nameList.add(object.getString("username_f"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(
                            getApplicationContext(),android.R.layout.simple_list_item_1 ,nameList);
                    amigos.setAdapter(adapter);
                } else {

                }
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView quantity=(TextView)findViewById(R.id.qua);
                TextView dia=(TextView)findViewById(R.id.day);
                TextView mes=(TextView)findViewById(R.id.month);
                TextView year=(TextView)findViewById(R.id.year);



                int  deuda=Integer.valueOf(quantity.getText().toString());
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date da = null;
                try {
                    da = formatter.parse(dia.getText().toString()+"/"+mes.getText().toString()+"/"+year.getText().toString());
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                ParseObject prestamo = new ParseObject("prestamos");
                prestamo.put("lender", amigos.getSelectedItem().toString());
                prestamo.put("borrower", ParseUser.getCurrentUser().getUsername());
                prestamo.put("deuda", deuda);
                prestamo.put("fechapago", da);
                prestamo.put("parcial", 0);
                prestamo.put("pagado", false);
                System.out.println(da);
                prestamo.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(CrearPrestamoActivity.this, Deudas.class);
                            startActivity(i);
                            finish();
                        } else {

                            Log.i("Registro", "" + e.getMessage());
                            Toast.makeText(CrearPrestamoActivity.this, "No se pudo registrar", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }


}

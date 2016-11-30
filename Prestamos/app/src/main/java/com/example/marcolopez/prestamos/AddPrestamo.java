package com.example.marcolopez.prestamos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mauricio on 16/11/16.
 */


public class AddPrestamo extends AppCompatActivity {
    TextView name_l;
    TextView quantity;
    TextView pagado;
    Double debe;
    Double paga;
    String idstring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {/*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprestamo);
        Button button = (Button) findViewById(R.id.pagar);
        Intent i = getIntent();
         idstring=i.getStringExtra("stringID");
        name_l=(TextView) findViewById(R.id.amigo_pago);
        pagado=(TextView) findViewById(R.id.pagad);
        quantity = (TextView) findViewById(R.id.pago_ca);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");
        query.getInBackground(i.getStringExtra("stringID"), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    name_l.setText((String) object.get("borrower"));
                    name_l.setFocusable(false);
                    debe=object.getDouble("deuda")-object.getDouble("parcial");

                  double pago=debe;
                   pagado.setText(String.valueOf(pago));
                    pagado.setFocusable(false);
                } else {
                    // something went wrong
                }
            }
        });






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paga =Double.parseDouble(quantity.getText().toString());

                    if(paga>debe){
                        Toast.makeText(AddPrestamo.this, "No te deben tanto", Toast.LENGTH_LONG).show();
                    }

                new AlertDialog.Builder(AddPrestamo.this)
                        .setTitle("Registrar pago")
                        .setMessage("Estas seguro que quieres registrar el pago de $"+paga+"?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");
                                query.getInBackground(idstring, new GetCallback<ParseObject>() {
                                    public void done(ParseObject object, ParseException e) {
                                        if (e == null) {
                                            // Now let's update it with some new data. In this case, only cheatMode and score
                                            // will get sent to the Parse Cloud. playerName hasn't changed.
                                            object.put("parcial", paga);
                                            if(paga==debe){
                                            object.put("pagado", true);
                                            }
                                            object.saveInBackground();

                                        }
                                    }
                                });
                                Intent i=new Intent(AddPrestamo.this, Deudas.class);
                                startActivity(i);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

    */


            /*

                ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");

                // Retrieve the object by id
                query.getInBackground("", new GetCallback<ParseObject>() {
                    public void done(ParseObject gameScore, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            gameScore.put("score", 1338);
                            gameScore.put("cheatMode", true);
                            gameScore.saveInBackground();
                        }
                    }
                });






                ParseObject prestamo = new ParseObject("prestamos");
                prestamo.put("lender", name_l.getText().toString());
                prestamo.put("borrower", ParseUser.getCurrentUser().getUsername());



                prestamo.put("deuda", deuda);
                prestamo.put("fechapago", da);
                prestamo.put("parcial", 0);
                prestamo.put("pagado", false);
                prestamo.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(AddPrestamo.this, Deudas.class);
                            startActivity(i);
                            finish();
                        } else {

                            Log.i("Registro", "" + e.getMessage());

                        }
                    }
                });
                    */
        /*
            }
        });*/
    }
}

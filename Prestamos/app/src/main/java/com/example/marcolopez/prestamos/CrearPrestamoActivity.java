package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
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

public class CrearPrestamoActivity extends Activity {

    ImageView btnFloatMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_prestamo);
        Button button = (Button) findViewById(R.id.send);
        btnFloatMenu = (ImageView) findViewById(R.id.btnMenuFloat);
        buildMenuFloating();
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

    public void buildMenuFloating(){
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(CrearPrestamoActivity.this)
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
                Intent intent = new Intent(CrearPrestamoActivity.this, Deudas.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPrestamoActivity.this, CrearPrestamoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPrestamoActivity.this, Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPrestamoActivity.this, AgregarAmigoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(CrearPrestamoActivity.this)
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

package com.example.declaracion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText nombre;
    private EditText direccion;
    private EditText dni;
    private EditText fecha;
    private EditText tel;
    private EditText mail;
    private Button enviar;
    ArrayList<String> modelArrayList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences1 = getSharedPreferences("FLAG", Context.MODE_PRIVATE);
        if (sharedPreferences1.getBoolean("envio", false)){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameId, new PagFragment());
            ft.commit();
        } else {
            nombre = findViewById(R.id.nombre);
            direccion = findViewById(R.id.dir);
            dni = findViewById(R.id.dni);
            fecha = findViewById(R.id.fecha);
            tel = findViewById(R.id.tel);
            mail = findViewById(R.id.mail);
            enviar = findViewById(R.id.enviar);
            enviar.setVisibility(View.GONE);
            sharedPreferences = getSharedPreferences("DATA", Context.MODE_PRIVATE);
            enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modelArrayList.add(0, nombre.getText().toString());
                    modelArrayList.add(1, direccion.getText().toString());
                    modelArrayList.add(2, dni.getText().toString());
                    modelArrayList.add(3, fecha.getText().toString());
                    modelArrayList.add(4, tel.getText().toString());
                    modelArrayList.add(5, mail.getText().toString());
                    Gson gson = new Gson();
                    String json = gson.toJson(modelArrayList);
                    sharedPreferences.edit().putString("cosas", json).apply();
                    sharedPreferences1.edit().putBoolean("envio", true).apply();
                    enviar.setVisibility(View.GONE);
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.frameId, new PagFragment());
                    ft.commit();
                }
            });
        }
    }



}

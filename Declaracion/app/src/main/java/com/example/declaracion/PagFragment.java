package com.example.declaracion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class PagFragment extends Fragment {
    
    View vista;
    SharedPreferences sharedPreferences;
    private String nombre;
    private String direccion;
    private String dni;
    private String fecha;
    private String tel;
    private String mail;
    private WebView myWebView;
    private WebSettings ajustes;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_pag, container, false);
        myWebView = (WebView) vista.findViewById(R.id.pag);
        ajustes = myWebView.getSettings();
        ajustes.setJavaScriptEnabled(true);
        Gson gson = new Gson();
        sharedPreferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String response = sharedPreferences.getString("cosas", "");
        ArrayList<String> arrayList = gson.fromJson(response,
                new TypeToken<List<String>>() {
                }.getType());
        nombre = arrayList.get(0);
        direccion = arrayList.get(1);
        dni = arrayList.get(2);
        fecha = arrayList.get(3);
        tel = arrayList.get(4);
        mail = arrayList.get(5);
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSeKk4lJLDSd6cUA8Dpd7Q4Ta2ZrMEGIGyGZiJ_VrTTGU0heHQ/viewform?entry.88419787=" +
                nombre + "&entry.1095322835="+ direccion + "&entry.1593531468=" + dni + "&entry.2133595658="
                + fecha + "&entry.983891194=" +tel + "&entry.1891238433=" + mail +
                "&entry.1592573084=NIEGO&entry.2099497317=NIEGO&entry.1021325978=NIEGO&entry.1577436562=NIEGO&entry.944125523=NIEGO&entry.2013915094=NIEGO";

        myWebView.loadUrl(url);
        return vista;
    }
}

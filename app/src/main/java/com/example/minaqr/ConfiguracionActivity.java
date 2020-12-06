package com.example.minaqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConfiguracionActivity extends AppCompatActivity {
    private EditText ip;
    private Spinner spApis;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        SetUI();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ConfiguracionActivity.this,
                R.layout.support_simple_spinner_dropdown_item, APIS());
        spApis.setAdapter(adapter);

        // Traer configuración guardada
        SharedPreferences sharedPreferences = getSharedPreferences("",Context.MODE_PRIVATE);
        String direccion_ip = sharedPreferences.getString("ip","");
        String data = sharedPreferences.getString("webservice", "");

        if (data.equals("") || direccion_ip.equals("")) {

        } else {
            String position = data.split("-")[0];
            ip.setText(direccion_ip);
            spApis.setSelection(Integer.parseInt(position));
        }



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ip.getText().equals("") && spApis.getSelectedItem().toString().equals("")) {

                }
                else{
                    SharedPreferences sharedPreferences = getSharedPreferences("",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ip",ip.getText().toString());
                    editor.putString("webservice", spApis.getSelectedItemPosition()+"-"+spApis.getSelectedItem().toString());
                    editor.apply();
                    Toast.makeText(ConfiguracionActivity.this, "Configuración guardada.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void SetUI(){
        ip = findViewById(R.id.txtIp);
        spApis = findViewById(R.id.spApis);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    private ArrayList<String> APIS(){
        ArrayList<String> apis = new ArrayList<>();
        apis.add("webservice_direccion");
        apis.add("webservice_ayuntamiento");

        return apis;
    }
}
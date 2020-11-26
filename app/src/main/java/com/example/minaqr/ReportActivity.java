package com.example.minaqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    private ListView lista;
    private List<String> a = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        textInputLayout = findViewById(R.id.inputlayoutDepartamento);
        textInputEditText = findViewById(R.id.textinputDepartamento);
        lista = findViewById(R.id.lista);

        /*for (String item : Datos()) {
            a.add(item);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, a);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a.remove(i);
                adapter.notifyDataSetChanged();
            }
        });
        */

    }

    private List<String> Datos(){
        return new ArrayList<String>(){
            {
                add("N-1001, Memoria USB 4GB, Finanzas");
                add("N-1002, Laptop HP, Finanzas");
                add("N-1003, Cable HDMI, Finanzas");
                add("N-1004, Memoria USB 32 GB, Finanzas");
                add("N-1005, Laptop Lenovo, Finanzas");
                add("N-1006, Memoria USB 64 GB, Finanzas");
            }
        };

    }
}
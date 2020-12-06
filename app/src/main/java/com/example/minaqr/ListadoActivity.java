package com.example.minaqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.minaqr.API.API;
import com.example.minaqr.POJO.Departamentos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoActivity extends AppCompatActivity {
    private AutoCompleteTextView autoDepartamentos;
    ArrayList<String> data = new ArrayList<String>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        setUI();

        SharedPreferences sharedPreferences = getSharedPreferences("",Context.MODE_PRIVATE);
        String ip = sharedPreferences.getString("ip","");
        String arrayData = sharedPreferences.getString("webservice", "");

        if (ip.equals("") || arrayData.equals("")) {

        }
        else{
            String webservice = arrayData.split("-")[1];

            API api = new API(getApplicationContext(), ip, webservice);
            final Call<ArrayList<Departamentos>> departamentos =api.getDepartamentos();
            departamentos.enqueue(new Callback<ArrayList<Departamentos>>() {
                @Override
                public void onResponse(Call<ArrayList<Departamentos>> call, Response<ArrayList<Departamentos>> response) {
                    Toast.makeText(ListadoActivity.this, response.body().get(0).getDescripcion().toString(), Toast.LENGTH_SHORT).show();

                    for (Departamentos departamento: response.body()
                    ) {
                        data.add(departamento.getDescripcion());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Departamentos>> call, Throwable t) {

                }
            });
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListadoActivity.this, android.R.layout.simple_expandable_list_item_1, data);

            autoDepartamentos.setAdapter(adapter);
        }
    }

    private void setUI(){
        autoDepartamentos = findViewById(R.id.autoDepartamentos);
    }

}
package com.example.minaqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minaqr.API.API;
import com.example.minaqr.POJO.Activos;
import com.example.minaqr.POJO.Departamentos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleActivity extends AppCompatActivity {
    private TextView numInventario;
    private TextView descripcion;
    private TextView responsable;
    private TextView departamento;
    private Button btnProcesar, btnCancelar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        setUI();

        Bundle bundle = getIntent().getExtras();

        numInventario.setText(bundle.getString("numInventario").trim());
        descripcion.setText(bundle.getString("descripcion").trim());
        responsable.setText(bundle.getString("responsable").trim());
        departamento.setText(bundle.getString("departamento").trim());

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetalleActivity.this, numInventario.getText().toString(), Toast.LENGTH_SHORT).show();

                sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                String ip = sharedPreferences.getString("ip","");
                String webservice = sharedPreferences.getString("webservice", "");

                API api = new API(getApplicationContext(), ip, webservice);

                final Call<Activos> actualizarNum = api.actualizarActivo(numInventario.getText().toString());

                actualizarNum.enqueue(new Callback<Activos>() {
                    @Override
                    public void onResponse(Call<Activos> call, Response<Activos> response) {
                        if (response.body().getEstado() == "1") {
                            Toast.makeText(DetalleActivity.this, "Activo procesado", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(DetalleActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Activos> call, Throwable t) {

                    }
                });
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUI(){
        numInventario = findViewById(R.id.txtNumInventario);
        descripcion = findViewById(R.id.txtDescripcion);
        responsable = findViewById(R.id.txtResponsable);
        departamento = findViewById(R.id.txtDepartamento);
        btnProcesar = findViewById(R.id.btnProcesar);
        btnCancelar = findViewById(R.id.btnCancelar);
    }
}
package com.example.minaqr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button buttonScan;
    private Button buttonReport;
    private TextView numero;
    private SwipeRefreshLayout swipe;

    private IntentIntegrator qrscan;

    private int number = 1;

    final static int REQUEST_CODE = 101;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = findViewById(R.id.buttonScan);
        buttonReport = findViewById(R.id.buttonReport);
        numero = findViewById(R.id.numero);
        swipe = findViewById(R.id.swipe);

        qrscan = new IntentIntegrator(this);

        swipe.setColorSchemeColors(android.R.color.background_dark);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                aumentar();
            }
        });

        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        // In an educational UI, explain to the user why your app requires this
                        // permission for a specific feature to behave as expected. In this UI,
                        // include a "cancel" or "no thanks" button that allows the user to
                        // continue using your app without granting the permission.

                        Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                    } else {
                        // You can directly ask for the permission.
                        requestPermissions(MainActivity.this,
                                new String[] { Manifest.permission.CAMERA },
                                REQUEST_CODE);
                        Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                    }
                }

                qrscan.setPrompt("Coloque un código qr en el interior del rectángulo del visor para escanear");
                qrscan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                qrscan.setOrientationLocked(false);
                qrscan.setBeepEnabled(false);
                qrscan.initiateScan();
            }

            private void requestPermissions(MainActivity mainActivity, String[] strings, int requestCode) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                Log.d("Test","Entro :)");
                try {
                    //converting the data to json
                    //JSONObject obj = new JSONObject(result.getContents());
                    String [] datos = result.getContents().split(", ");
                    //setting values to textviews
                    //textViewName.setText(obj.getString("name"));
                    //textViewAddress.setText(obj.getString("address"));
                    String numeroInventario = datos[0];
                    String descripcion = datos[1];
                    String departamento = datos[2];

                    //Toast.makeText(this, datos[1], Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, numeroInventario, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, numeroInventario +"\n"+descripcion+"\n"+departamento, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            Toast.makeText(this, "Estas conectado a internet", Toast.LENGTH_SHORT).show();
            boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            if (isWifi) {
                Toast.makeText(this, "Estas conectado por WIFI", Toast.LENGTH_SHORT).show();
                if (isConnectedToThisServer("https://www.google.com/")) {
                    Toast.makeText(this, "Si, si estas conectado por internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No, no estas conectado por internet", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(this, "Estas conectado por datos móviles", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No estas conectado a internet", Toast.LENGTH_SHORT).show();
            finish();
        }*/
    }

    public boolean isConnectedToThisServer(String host) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 8.8.8.8" + host);
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private class Cargar extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            swipe.setRefreshing(false);
        }
    }

    public void aumentar(){
        numero.setText(String.valueOf(++number));
        new Cargar().execute();
    }

}
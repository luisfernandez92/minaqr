package com.example.minaqr.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import com.example.minaqr.POJO.Activos;
import com.example.minaqr.POJO.Departamentos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private final Context context;
    private final Retrofit retrofit;
    private final String ip;
    private final String webservice;

    public API(Context context, String ip, String webservice) {
        this.context = context;
        this.ip  = ip;
        this.webservice = webservice;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ip+"/"+webservice+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<ArrayList<Departamentos>> getDepartamentos() {
        IActivo service = retrofit.create(IActivo.class);
        return service.getDepartamentos();
    }
    public Call<Activos> actualizarActivo(String num) {
        IActivo service = retrofit.create(IActivo.class);
        return service.actualizarActivo( num);

    }

}

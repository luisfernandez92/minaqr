package com.example.minaqr.API;

import com.example.minaqr.POJO.Activos;
import com.example.minaqr.POJO.Departamentos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IActivo {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("obtener_departamentos.php")
    Call<ArrayList<Departamentos>> getDepartamentos();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("actualizar_por_num_inventario.php?Num_Inventario=")
    Call<Activos> actualizarActivo(@Query("Num_Inventario") String numero_Inventario);
}

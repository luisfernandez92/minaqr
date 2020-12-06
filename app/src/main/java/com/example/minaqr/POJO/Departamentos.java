package com.example.minaqr.POJO;

import com.google.gson.annotations.SerializedName;

public class Departamentos {
    private int Id;
    private String Codigo;
    @SerializedName("Nombre")
    private String Descripcion;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}

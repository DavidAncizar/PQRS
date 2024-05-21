/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pqrs;

import java.util.ArrayList;

/**
 *
 * @author ANCIZAR
 */
public class usuario {
    private int ID_usuario;
    private String nombre_Completo;
    private String numeroTelefono;
    private String correo;
    private String cedula;
    private String contrasenia;
    private ArrayList<tablaPQRS> pqrs;

    public usuario() {
    }

    public usuario(int ID_usuario, String nombre_Completo, String numeroTelefono, String correo, String cedula,String contrasenia) {
        this.ID_usuario = ID_usuario;
        this.nombre_Completo = nombre_Completo;
        this.numeroTelefono = numeroTelefono;
        this.correo = correo;
        this.cedula = cedula;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public String getNombre_Completo() {
        return nombre_Completo;
    }

    public void setNombre_Completo(String nombre_Completo) {
        this.nombre_Completo = nombre_Completo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
      public String getContrasenia() {
        return cedula;
    }

    public void setContrasenia(String contrasenia) {
        this.cedula = cedula;
    }
    
    
    
    
}


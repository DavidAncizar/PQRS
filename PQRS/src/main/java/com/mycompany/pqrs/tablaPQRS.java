/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pqrs;

/**
 *
 * @author ANCIZAR
 */
public class tablaPQRS {
    
    private int ID_pqrs;
    private String descripcion;
    private String archivo_adj;
    private String estado;
    private String tipoPQRS;

    public tablaPQRS() {
    }

    public tablaPQRS(int ID_pqrs, String descripcion, String archivo_adj, String estado, String tipoPQRS) {
        this.ID_pqrs = ID_pqrs;
        this.descripcion = descripcion;
        this.archivo_adj = archivo_adj;
        this.estado = estado;
        this.tipoPQRS = tipoPQRS;
    }

    public int getID_pqrs() {
        return ID_pqrs;
    }

    public void setID_pqrs(int ID_pqrs) {
        this.ID_pqrs = ID_pqrs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo_adj() {
        return archivo_adj;
    }

    public void setArchivo_adj(String archivo_adj) {
        this.archivo_adj = archivo_adj;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPQRS() {
        return tipoPQRS;
    }

    public void setTipoPQRS(String tipoPQRS) {
        this.tipoPQRS = tipoPQRS;
    }
    
    
}

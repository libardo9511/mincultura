/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.mincultura.modelo;

import java.sql.Date;

/**
 *
 * @author YStark
 */
public class Pintura {

    private String codigo;
    private double precio;
    private Date fecha;
    private Artista artista;

    public Pintura(String codigo, double precio, Date fecha, Artista artista) {
        this.codigo = codigo;
        this.precio = precio;
        this.fecha = fecha;
        this.artista = artista;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    
    
}

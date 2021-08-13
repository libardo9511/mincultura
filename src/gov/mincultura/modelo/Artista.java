/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.mincultura.modelo;

/**
 *
 * @author YStark
 */
public class Artista {

    private String codigo;
    private String nombre;
    private String apellido;
    private Museo museo;

    public Artista(String codigo, String nombre, String apellido, Museo museo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.museo = museo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

    
}

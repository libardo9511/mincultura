/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.mincultura.controlador;

import gov.mincultura.conexion.Conexion;
import gov.mincultura.modelo.Artista;
import gov.mincultura.modelo.Museo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YStark
 */
public class ControladorArtista {

    private final String tabla = "artistas";
    private Connection conexion;
    private ControladorMuseo ctrMuseo = new ControladorMuseo();

    public ArrayList<Artista> listarArtistas() throws SQLException, ClassNotFoundException {
        ArrayList<Artista> artistas = new ArrayList<>();
        String consultaSql = "";
        consultaSql += "SELECT codigo, nombre, apellido, codigo_museo ";
        consultaSql += "FROM " + this.tabla;
        conexion = conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta = conexion.prepareStatement(consultaSql);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            Museo museo = ctrMuseo.buscarMuseoPorCodigo(resultado.getString("codigo_museo"));
            Artista artista = new Artista(resultado.getString("codigo"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"), museo);
            artistas.add(artista);
        }

        return artistas;
    }

    public Artista buscarArtistaPorCodigo(String codigo) throws SQLException, ClassNotFoundException {
        conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta;
        String consultaSql = "";
        consultaSql += "SELECT codigo, nombre, apellido, codigo_museo ";
        consultaSql += "FROM " + this.tabla + " ";
        consultaSql += "WHERE codigo = ?";
        consulta = conexion.prepareStatement(consultaSql);
        consulta.setString(1, codigo);
        ResultSet resultado = consulta.executeQuery();
        Artista artistaEncontrado = null;
        while (resultado.next()) {
            Museo museo = ctrMuseo.buscarMuseoPorCodigo(resultado.getString("codigo_museo"));
            artistaEncontrado = new Artista(resultado.getString("codigo"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"), museo);
        }
        return artistaEncontrado;
    }
}

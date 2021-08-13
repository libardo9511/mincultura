/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.mincultura.controlador;

import gov.mincultura.conexion.Conexion;
import gov.mincultura.modelo.Artista;
import gov.mincultura.modelo.Pintura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YStark
 */
public class ControladorPinturas {

    private final String tabla = "pinturas";
    private Connection conexion;
    private ControladorArtista ctrArtista = new ControladorArtista();
    
    public ArrayList<Pintura> listarPinturas() throws SQLException, ClassNotFoundException {
        ArrayList<Pintura> pinturas = new ArrayList<>();
        String consultaSql = "";
        consultaSql += "SELECT codigo, precio, fecha, codigo_artista ";
        consultaSql += "FROM " + this.tabla;
        conexion = conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta = conexion.prepareStatement(consultaSql);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            Artista artista = ctrArtista.buscarArtistaPorCodigo(resultado.getString("codigo_artista"));
            Pintura pintura = new Pintura(resultado.getString("codigo"),
                    resultado.getDouble("precio"),
                    resultado.getDate("fecha"), artista);
            pinturas.add(pintura);
        }

        return pinturas;
    }

    public ArrayList<Pintura> listarPinturasPorArtista(String codigoArtista) throws SQLException, ClassNotFoundException {
        ArrayList<Pintura> pinturas = new ArrayList<>();
        String consultaSql = "";
        consultaSql += "SELECT codigo, precio, fecha, codigo_artista ";
        consultaSql += "FROM " + this.tabla;
        consultaSql += " WHERE codigo_artista = ? ";
        conexion = conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta = conexion.prepareStatement(consultaSql);
        consulta.setString(1, codigoArtista);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            Artista artista = ctrArtista.buscarArtistaPorCodigo(resultado.getString("codigo_artista"));
            Pintura pintura = new Pintura(resultado.getString("codigo"),
                    resultado.getDouble("precio"),
                    resultado.getDate("fecha"), artista);
            pinturas.add(pintura);
        }

        return pinturas;
    }
    
}

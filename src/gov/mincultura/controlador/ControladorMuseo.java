/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.mincultura.controlador;

import gov.mincultura.conexion.Conexion;
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
public class ControladorMuseo {

    private final String tabla = "museos";
    private Connection conexion;

    /*public void guardarMuseo(Museo museo) throws SQLException, ClassNotFoundException {
        conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta;
        consulta = conexion.prepareStatement("INSERT INTO " + this.tabla
                + "(codigo, nombre, direccion) "
                + "VALUES(?, ?, ?)");
        consulta.setString(1, museo.getCodigo());
        consulta.setString(2, museo.getNombre());
        consulta.setString(3, museo.getDireccion());

        consulta.executeUpdate();
    }

    public void editarMuseo(Museo museo) throws SQLException, ClassNotFoundException {
        conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta;
        String consultaSql = "";
        consultaSql += "UPDATE " + this.tabla + " ";
        consultaSql += "SET nombre = ?, direccion = ?";
        consultaSql += "WHERE codigo = ?";
        consulta = conexion.prepareStatement(consultaSql);
        consulta.setString(1, museo.getNombre());
        consulta.setString(2, museo.getDireccion());
        consulta.setString(3, museo.getCodigo());

        consulta.executeUpdate();
    }*/

    public ArrayList<Museo> listarMuseos() throws SQLException, ClassNotFoundException {
        ArrayList<Museo> museos = new ArrayList<>();
        String consultaSql = "";
        consultaSql += "SELECT codigo, nombre, direccion ";
        consultaSql += "FROM " + this.tabla;
        conexion = conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta = conexion.prepareStatement(consultaSql);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {

            Museo museo = new Museo(resultado.getString("codigo"),
                    resultado.getString("nombre"), resultado.getString("direccion"));
            museos.add(museo);
        }

        return museos;
    }

    public double valorDePinturas(String codigoMuseo) throws SQLException, ClassNotFoundException {
        conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta;
        String consultaSql = "";
        consultaSql += "SELECT SUM(precio) AS \"Total\" ";
        consultaSql += "FROM pinturas p, artistas a, museos m ";
        consultaSql += "WHERE p.codigo_artista = a.codigo ";
        consultaSql += "AND a.codigo_museo = m.codigo ";
        consultaSql += "AND m.codigo = ? ";
        consulta = conexion.prepareStatement(consultaSql);
        consulta.setString(1, codigoMuseo);
        ResultSet resultado = consulta.executeQuery();
        double valorObras = 0;
        while (resultado.next()) {
            valorObras = resultado.getDouble("Total");
        }
        return valorObras;
    }
    
    public Museo buscarMuseoPorCodigo(String codigo) throws SQLException, ClassNotFoundException {
        conexion = Conexion.obtenerConexionSQL();
        PreparedStatement consulta;
        String consultaSql = "";
        consultaSql += "SELECT codigo, nombre, direccion ";
        consultaSql += "FROM " + this.tabla + " ";
        consultaSql += "WHERE codigo = ?";
        consulta = conexion.prepareStatement(consultaSql);
        consulta.setString(1, codigo);
        ResultSet resultado = consulta.executeQuery();
        Museo museoEncontrado = null;
        while (resultado.next()) {
            museoEncontrado = new Museo(resultado.getString("codigo"),
                    resultado.getString("nombre"), resultado.getString("direccion"));
        }
        return museoEncontrado;
    }

}

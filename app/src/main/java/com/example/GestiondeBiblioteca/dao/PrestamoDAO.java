/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestiondeBiblioteca.dao;

import com.example.GestiondeBiblioteca.models.Prestamo;
import com.example.GestiondeBiblioteca.models.Usuario;
import static com.example.GestiondeBiblioteca.utils.GlobalData.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author lil-a
 */
public class PrestamoDAO {

    public void registrarPrestamo(int idLibro, String nombreUsuario, Date fechaPrestamo, Date fechaDevolucionEsperada) throws SQLException {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRESTAMO_QUERY)) {

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerUsuarioPorNombreUsuario(nombreUsuario);

            if (usuario == null) {
                throw new SQLException("No se encontró un usuario con el nombre " + nombreUsuario);
            }

            int idUsuario = usuario.getId();

            ps.setInt(1, idLibro);
            ps.setInt(2, idUsuario);
            ps.setDate(3, fechaPrestamo);
            ps.setDate(4, fechaDevolucionEsperada);

            ps.executeUpdate();
        }
    }

    public List<Prestamo> obtenerPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRESTAMOS_QUERY);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getInt("libro_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getString("estado")
                );
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }
}
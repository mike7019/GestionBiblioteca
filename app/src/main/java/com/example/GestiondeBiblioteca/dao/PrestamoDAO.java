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
import javax.swing.JOptionPane;

/**
 *
 * @author lil-a
 */
public class PrestamoDAO {

    public void registrarPrestamo(int idLibro, String nombreUsuario, Date fechaPrestamo, Date fechaDevolucionEsperada) throws SQLException {

        try (
            Connection connection = DatabaseConnection.getConnection();
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
    
    public void devolverLibro(int idLibro) throws SQLException {
        Connection connection = null;
        PreparedStatement psPrestamo = null;
        PreparedStatement psActualizarPrestamo = null;
        PreparedStatement psActualizarLibro = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            
            // 1. Buscar el préstamo activo por ID del libro
            psPrestamo = connection.prepareStatement(QUERY_PRESTAMO_POR_LIBRO_ID);
            psPrestamo.setInt(1, idLibro);
            rs = psPrestamo.executeQuery();

            if (rs.next()) {
                int idPrestamo = rs.getInt("id");

                // 2. Cambiar el estado del préstamo a 'Devuelto'
                psActualizarPrestamo = connection.prepareStatement(ACTUALIZAR_ESTADO_PRESTAMO);
                psActualizarPrestamo.setInt(1, idPrestamo);
                psActualizarPrestamo.executeUpdate();

                // 3. Cambiar el estado del libro a 'Disponible'
                psActualizarLibro = connection.prepareStatement(ACTUALIZAR_ESTADO_LIBRO);
                psActualizarLibro.setInt(1, idLibro);
                psActualizarLibro.executeUpdate();

                JOptionPane.showMessageDialog(null, "Libro devuelto exitosamente.");

            } else {
                throw new SQLException("No se encontró un préstamo activo para este libro.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al devolver el libro: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (psPrestamo != null) psPrestamo.close();
            if (psActualizarPrestamo != null) psActualizarPrestamo.close();
            if (psActualizarLibro != null) psActualizarLibro.close();
            if (connection != null) connection.close();
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
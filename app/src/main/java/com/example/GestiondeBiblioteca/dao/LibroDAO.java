package com.example.GestiondeBiblioteca.dao;

import com.example.GestiondeBiblioteca.models.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.GestiondeBiblioteca.utils.GlobalData.*;

public class LibroDAO {

    

    public LibroDAO() {
    }

    public List<Libro> obtenerLibros() {
        List<Libro> libros = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_ALL_BOOKS)) {

            while (resultSet.next()) {
                Libro libro = new Libro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getString("categoria"),
                        resultSet.getString("estado")
                );
                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }

        return libros;
    }

    public Libro obtenerLibroPorTitulo(String titulo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_BOOK_BY_TITLE)) {

            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getString("estado")
                );
            } else {
                return null;
            }
        }
    }

    public void agregarLibro(Libro libro) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_QUERY)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.setString(4, libro.getEstado());
            ps.executeUpdate();

            System.out.println("Libro agregado exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }

    public void editarLibro(Libro libro) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_QUERY)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.setString(4, libro.getEstado());
            ps.setInt(5, libro.getId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No se encontró el libro con el id: " + libro.getId());
            }
        }
    }

    public void eliminarLibro(String titulo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_QUERY)) {

            ps.setString(1, titulo);

            int filasAfectadas = ps.executeUpdate();  // Ejecuta la consulta SQL

            if (filasAfectadas > 0) {
                System.out.println("El libro con Titulo " + titulo + " fue eliminado.");
            } else {
                System.out.println("No se encontró el libro con Titulo " + titulo);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
            throw e;
        }
    }
}

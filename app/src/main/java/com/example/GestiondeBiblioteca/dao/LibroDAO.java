package com.example.GestiondeBiblioteca.dao;


import com.example.GestiondeBiblioteca.models.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.GestiondeBiblioteca.utils.GlobalData.QUERY_ALL_BOOKS;

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
                        resultSet.getString("estante")
                );
                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }

        return libros;
    }

    public void agregarLibro(Libro libro) {
        String query = "INSERT INTO libros (titulo, autor, categoria, estante) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.setString(4, libro.getEstante());
            ps.executeUpdate();

            System.out.println("Libro agregado exitosamente");

        } catch (SQLException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }

    // Otros métodos como actualizar y eliminar libro
}

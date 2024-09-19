/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestiondeBiblioteca.dao;

import com.example.GestiondeBiblioteca.models.Usuario;
import static com.example.GestiondeBiblioteca.utils.GlobalData.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lil-a
 */
public class UsuarioDAO {

    // Método para agregar un usuario
    public void agregarUsuario(Usuario usuario) throws SQLException {
        try (
            Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getRol());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (
            Connection connection = DatabaseConnection.getConnection(); 
            Statement statement = connection.createStatement(); 
            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                usuarios.add(new Usuario(id, nombre, rol, usuario, contrasena));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            throw e;
        }
        return usuarios;
    }

    // Método para obtener un usuario por su nombre de usuario
    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws SQLException {
        Usuario usuario = null;
        try (
            Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");
                String usuarioStr = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                usuario = new Usuario(id, nombre, rol, usuarioStr, contrasena);
            } else {
                System.out.println("Usuario no encontrado: " + nombreUsuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario por nombre de usuario: " + e.getMessage());
            throw e;
        }
        return usuario;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getRol());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar un usuario por su nombre de usuario
    public void eliminarUsuario(String usuario) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setString(1, usuario);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("El usuario " + usuario + " fue eliminado.");
            } else {
                System.out.println("No se encontró el usuario " + usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
            throw e;
        }
    }

    // Método para autenticar un usuario
    public Usuario autenticarUsuario(String usuario, String contrasena) throws SQLException {
        Usuario usuarioAutenticado = null;
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement ps = connection.prepareStatement(AUTHENTICATE_USER)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");
                usuarioAutenticado = new Usuario(id, nombre, rol, usuario, contrasena);
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
            throw e;
        }
        return usuarioAutenticado;
    }
}

    

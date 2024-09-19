package com.example.GestiondeBiblioteca.utils;

public class GlobalData {


    //Database
    public static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    public static final String USER = "bibliouser";
    public static final String PASSWORD = "password";

    //Query's
    public static final String QUERY_ALL_BOOKS = "SELECT * FROM libros";
    public static final String QUERY_BOOK_BY_TITLE = "SELECT * FROM libros WHERE titulo = ?";
    public static final String INSERT_BOOK_QUERY = "INSERT INTO libros (titulo, autor, categoria, estado) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_BOOK_QUERY = "UPDATE libros SET titulo = ?, autor = ?, categoria = ?, estado = ? WHERE id = ?";
    public static final String DELETE_BOOK_QUERY = "DELETE FROM libros WHERE titulo = ?";
    
    
    //Query's usuario
    public static final String INSERT_USER = "INSERT INTO usuarios(nombre, rol, usuario, contrasena) VALUES(?, ?, ?, ?)";
    public static final String SELECT_ALL_USERS = "SELECT * FROM usuarios";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM usuarios WHERE usuario = ?";
    public static final String UPDATE_USER = "UPDATE usuarios SET nombre = ?, rol = ?, usuario = ?, contrasena = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM usuarios WHERE usuario = ?";
    public static final String AUTHENTICATE_USER = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
    
    //Query's prestamo
    public static final String SELECT_ALL_PRESTAMOS_QUERY = "SELECT * FROM prestamos";
    public static final String INSERT_PRESTAMO_QUERY = 
        "INSERT INTO prestamos (libro_id, usuario_id, fecha_prestamo, fecha_devolucion, estado) VALUES (?, ?, ?, ?, 'Activo')";
    

}

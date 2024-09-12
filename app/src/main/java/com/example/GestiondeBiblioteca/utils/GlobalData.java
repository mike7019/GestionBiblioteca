package com.example.GestiondeBiblioteca.utils;

public class GlobalData {


    //Database
    public static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    public static final String USER = "bibliouser";
    public static final String PASSWORD = "password";

    //Query's
    public static final String QUERY_ALL_BOOKS = "SELECT * FROM libros";

}

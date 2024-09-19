/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestiondeBiblioteca.models;

/**
 *
 * @author lil-a
 */
public class Usuario {

    private int id;
    private String nombre;
    private String rol; // "Prestamista" o "Usuario"
    private String usuario;
    private String contrasena;
    
    public Usuario(){}

    public Usuario(int id, String nombre, String rol, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

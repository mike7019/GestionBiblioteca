package com.example.GestiondeBiblioteca.userInterfaces;


import com.example.GestiondeBiblioteca.dao.LibroDAO;
import com.example.GestiondeBiblioteca.models.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarLibroUI extends JPanel {

    // Campos del formulario
    private JTextField tituloField;
    private JTextField autorField;
    private JTextField categoriaField;
    private JTextField estanteField;
    private JButton agregarButton;
    public LibroDAO libroDAO;

    public AgregarLibroUI(LibroDAO libroDAO) {
        this.libroDAO = new LibroDAO();
        initComponents();
    }

    private void initComponents() {
        // Configuración del panel
        this.setLayout(new GridLayout(5, 2, 10, 10));

        // Crear los componentes
        JLabel tituloLabel = new JLabel("Título:");
        tituloField = new JTextField();

        JLabel autorLabel = new JLabel("Autor:");
        autorField = new JTextField();

        JLabel categoriaLabel = new JLabel("Categoría:");
        categoriaField = new JTextField();

        JLabel estanteLabel = new JLabel("Estante:");
        estanteField = new JTextField();

        agregarButton = new JButton("Agregar Libro");

        // Añadir componentes al panel
        this.add(tituloLabel);
        this.add(tituloField);

        this.add(autorLabel);
        this.add(autorField);

        this.add(categoriaLabel);
        this.add(categoriaField);

        this.add(estanteLabel);
        this.add(estanteField);

        this.add(new JLabel()); // Espacio vacío
        this.add(agregarButton);

        // Acción del botón "Agregar Libro"
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibroActionPerformed(e);
            }
        });
    }

    // Método que se ejecuta cuando se pulsa el botón de agregar
    private void agregarLibroActionPerformed(ActionEvent evt) {
        // Capturar los datos del formulario
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String categoria = categoriaField.getText();
        String estante = estanteField.getText();

        // Validación básica de los campos
        if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty() || estante.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un nuevo objeto Libro
        Libro nuevoLibro = new Libro(0, titulo, autor, categoria, estante);

        // Insertar el libro en la base de datos
        try {
            libroDAO.agregarLibro(nuevoLibro);
            JOptionPane.showMessageDialog(this, "Libro agregado exitosamente");

            // Limpiar los campos después de agregar
            tituloField.setText("");
            autorField.setText("");
            categoriaField.setText("");
            estanteField.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgregarLibroUI(new LibroDAO()).setVisible(true);
            }
        });
    }
}

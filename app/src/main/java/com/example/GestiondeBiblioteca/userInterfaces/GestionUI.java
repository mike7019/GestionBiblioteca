package com.example.GestiondeBiblioteca.userInterfaces;



import com.example.GestiondeBiblioteca.dao.LibroDAO;
import com.example.GestiondeBiblioteca.models.Libro;
import com.example.GestiondeBiblioteca.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionUI extends JFrame {

    private LibroDAO libroDAO;
    private JPanel panel;

    public GestionUI() {
        libroDAO = new LibroDAO();

        setTitle("Gestión de Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuCRUD = new JMenu("Management");
        JMenuItem menuItemAdd = new JMenuItem("Add Book");
        JMenuItem menuItemEdit = new JMenuItem("Edit Book");
        JMenuItem menuItemDelete = new JMenuItem("Delete Book");

        menuItemAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    AnadirLibroUI anadirLibroUI = new AnadirLibroUI(new Dashboard(new Usuario()));
                    anadirLibroUI.setVisible(true);
            }
        });

        menuItemEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Edit Book
            }
        });

        menuItemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Delete Book
            }
        });

        menuCRUD.add(menuItemAdd);
        menuCRUD.add(menuItemEdit);
        menuCRUD.add(menuItemDelete);
        menuBar.add(menuCRUD);
        setJMenuBar(menuBar);

        mostrarLibros();

        setContentPane(panel);
    }

    void mostrarLibros() {
        String[] columnNames = {"Id", "Titulo", "Autor", "Categoria", "Estante" };

        List<Libro> libros = libroDAO.obtenerLibros();
        int columns = Libro.class.getDeclaredFields().length;  // assuming all fields corresponds to columns
        Object[][] data = new Object[libros.size()][columns];
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            data[i][0] = libro.getId();
            data[i][1] = libro.getTitulo();
            data[i][2] = libro.getAutor();
            data[i][3] = libro.getCategoria();
            data[i][4] = libro.getEstado();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionUI().setVisible(true);
            }
        });
    }
}
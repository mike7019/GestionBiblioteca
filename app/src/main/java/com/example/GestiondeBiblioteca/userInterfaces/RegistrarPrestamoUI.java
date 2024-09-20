/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.GestiondeBiblioteca.userInterfaces;

import com.example.GestiondeBiblioteca.dao.LibroDAO;
import com.example.GestiondeBiblioteca.dao.PrestamoDAO;
import com.example.GestiondeBiblioteca.dao.UsuarioDAO;
import com.example.GestiondeBiblioteca.models.Libro;
import com.example.GestiondeBiblioteca.models.Usuario;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lil-a
 */
public class RegistrarPrestamoUI extends javax.swing.JFrame {

    private Dashboard dashboard;
    private SimpleDateFormat dateFormat;
    private UsuarioDAO usuarioDAO;
    private LibroDAO libroDAO;

    /**
     * Creates new form RegistrarPrestamoUI
     */
    public RegistrarPrestamoUI(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponents();
        this.usuarioDAO = new UsuarioDAO();  // DAO para obtener los usuarios
        this.libroDAO = new LibroDAO();
        this.setLocationRelativeTo(null);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mostrarLibros();
        cargarUsuariosComboBox();  // Llenar el JComboBox con los usuarios
        Date[] fechas = generarFechas();
        txt_fecha_prestamo.setText(fechas[0].toString());
        txt_fecha_devolucion.setText(fechas[1].toString());
        txt_titulo.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_titulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_fecha_prestamo = new javax.swing.JTextField();
        txt_fecha_devolucion = new javax.swing.JTextField();
        cbx_usuarios = new javax.swing.JComboBox<>();
        btn_consultar = new javax.swing.JButton();
        btn_prestar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_libros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Seleccione ID Usuario:");

        jLabel4.setText("Fecha de Prestamo");

        jLabel5.setText("Fecha de Devolucion");

        jLabel1.setText("Titulo a prestar");

        txt_titulo.setName("txt_titulo"); // NOI18N

        jLabel2.setText("Registro de Prestamos");

        btn_consultar.setText("Actualizar");
        btn_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultarActionPerformed(evt);
            }
        });

        btn_prestar.setText("Prestar Libro");
        btn_prestar.setName("btn_prestar"); // NOI18N
        btn_prestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prestarActionPerformed(evt);
            }
        });

        btn_cancelar.setText("Cancelar");
        btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_cancelar.setName("btn_cancelar"); // NOI18N
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_fecha_prestamo)
                            .addComponent(cbx_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_fecha_devolucion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_prestar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbx_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fecha_prestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_fecha_devolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_prestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        tbl_libros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_libros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_librosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_libros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_prestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prestarActionPerformed

        int filaSeleccionada = tbl_libros.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Asumiendo que la columna 0 es el título
        String tituloLibro = (String) tbl_libros.getValueAt(filaSeleccionada, 0);

        // Obtener el usuario seleccionado del JComboBox
        String usuarioSeleccionado = (String) cbx_usuarios.getSelectedItem();
        if (usuarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date[] fechas = generarFechas();
        Date FechaPrestamo = fechas[0];
        Date sqlFechaDevolucion = fechas[1];

        // Validar y registrar el préstamo
        try {
            PrestamoDAO prestamoDAO = new PrestamoDAO();
            LibroDAO libroDAO = new LibroDAO();
            Libro libro = libroDAO.obtenerLibroPorTitulo(tituloLibro);

            if (libro == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el libro", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (btn_prestar.getText() == "Devolver") {
                prestamoDAO.devolverLibro(libro.getId());
                mostrarLibros();

            } else {
                if (!libro.getEstado().equals("Disponible")) {
                    JOptionPane.showMessageDialog(this, "El libro no está disponible para préstamo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                prestamoDAO.registrarPrestamo(libro.getId(), usuarioSeleccionado, FechaPrestamo, sqlFechaDevolucion);

                // Actualizar el estado del libro a "Prestado"
                libro.setEstado("Prestado");
                libroDAO.editarLibro(libro);

                JOptionPane.showMessageDialog(this, "Préstamo registrado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);

                // Actualizar la tabla de libros
                mostrarLibros();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el préstamo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_prestarActionPerformed

    private void btn_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultarActionPerformed
        mostrarLibros();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_consultarActionPerformed

    private void tbl_librosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_librosMouseClicked
        int filaSeleccionada = tbl_libros.getSelectedRow();
        String tituloLibro = (String) tbl_libros.getValueAt(filaSeleccionada, 0);
        String estado = (String) tbl_libros.getValueAt(filaSeleccionada, 3);

        if ("Prestado".equals(estado)) {
            btn_prestar.setText("Devolver");
        } else if ("Disponible".equals(estado)) {
            btn_prestar.setText("Prestar");
        }
        txt_titulo.setText(tituloLibro);
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_librosMouseClicked

    private void agregarValidacionFecha(JTextField campoFecha) {
        campoFecha.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarFecha(campoFecha);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarFecha(campoFecha);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarFecha(campoFecha);
            }
        });
    }

    // Validar el formato de fecha (dd/MM/yyyy)
    private void validarFecha(JTextField campoFecha) {
        String texto = campoFecha.getText();

        if (texto.length() == 10) {  // El formato dd/MM/yyyy tiene 10 caracteres
            try {
                dateFormat.parse(texto);  // Intentar hacer el parseo de la fecha
                campoFecha.setForeground(Color.BLACK);  // Fecha válida, texto negro
            } catch (ParseException e) {
                campoFecha.setForeground(Color.RED);  // Fecha inválida, texto rojo
            }
        } else {
            campoFecha.setForeground(Color.RED);  // Si no tiene 10 caracteres, el formato es incorrecto
        }
    }

    private void cargarUsuariosComboBox() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
            for (Usuario usuario : usuarios) {
                cbx_usuarios.addItem(usuario.getUsuario());  // Llenar el ComboBox con el nombre de usuario
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Date[] generarFechas() {
        // Generar las fechas automáticamente
        java.util.Date fechaActual = new java.util.Date();
        Date fechaPrestamo = new Date(fechaActual.getTime());

        // Calcular la fecha de devolución (un mes después)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH, 1);
        java.util.Date fechaDevolucion = calendar.getTime();
        Date sqlFechaDevolucion = new Date(fechaDevolucion.getTime());

        // Retornar las fechas como un arreglo de cadenas
        return new Date[]{fechaPrestamo, sqlFechaDevolucion};
    }

    public void mostrarLibros() {
        String[] columnNames = {"Titulo", "Autor", "Categoria", "Estado"};

        List<Libro> libros = libroDAO.obtenerLibros();
        DefaultTableModel defaultTableModel = (DefaultTableModel) tbl_libros.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(columnNames);

        for (Libro libro : libros) {
            Object[] fila = new Object[4];
            fila[0] = libro.getTitulo();
            fila[1] = libro.getAutor();
            fila[2] = libro.getCategoria();
            fila[3] = libro.getEstado();

            defaultTableModel.addRow(fila);
        }
        System.out.println("libros actualizados...");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarPrestamoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarPrestamoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarPrestamoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarPrestamoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarPrestamoUI(new Dashboard(new Usuario())).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_consultar;
    private javax.swing.JButton btn_prestar;
    private javax.swing.JComboBox<String> cbx_usuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_libros;
    private javax.swing.JTextField txt_fecha_devolucion;
    private javax.swing.JTextField txt_fecha_prestamo;
    private javax.swing.JTextField txt_titulo;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jose Ajcalon
 */
public class Login extends javax.swing.JFrame {

    //intanciamos la clase
    /**
     *  Devuelve la Conecxion a la Base de Datos
     */
    Conectar conectarBase = new Conectar();
    Connection con = (Connection) conectarBase.ConectarBase();
    /**
     * Variable que Nos sirve para Validar si el Usuario Existe en Alguna de
     * Nuestras 2 Tablas en Nuestra Base de Datos
     */
    int validado = 0;

    public Login() {
        initComponents();
        /**
         * Creacion de Fondo y Centrado del Programa en Pantalla
         */
        setLocationRelativeTo(null);
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("/imagen/Azul.jpg"));
        JLabel azul = new JLabel();
        azul.setIcon(uno);
        getLayeredPane().add(azul, JLayeredPane.FRAME_CONTENT_LAYER);
        azul.setBounds(0, 0, uno.getIconWidth(), uno.getIconHeight());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        Ingresar = new javax.swing.JButton();
        CrearUsuarioCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });

        Ingresar.setText("Ingresar");
        Ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IngresarMouseClicked(evt);
            }
        });
        Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarActionPerformed(evt);
            }
        });

        CrearUsuarioCliente.setText("Crear nuevo usuario");
        CrearUsuarioCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearUsuarioClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addComponent(txtusuario))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password)
                            .addComponent(CrearUsuarioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(Ingresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Ingresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CrearUsuarioCliente)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     *Metodo que Valida si EL Usuario Ingresado es Un Administrador
     */
    public void ValidarUsuario() {
        int resultado = 0;
        //Convierte la Informacion Ingresada por el usuario en Cadenas de Texto
        String clave = String.valueOf(password.getPassword());
        String nombre = txtusuario.getText();
        /**
         * Declaracion de sentencia SQL que va a ser enviada en la Consulta SQL
         */
        String SQL = "SELECT * FROM administrador WHERE nombre = '" + nombre + "' AND password = '" + clave + "'";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "bienvenido "+nombre);
                    conectarBase.ConectarBase();
                    conectarBase.setClienteId(Integer.parseInt(rs.getString("id_administrador")));
                    conectarBase.setPassword(rs.getString("password"));
                    conectarBase.setNombreUsuario(rs.getString("nombre"));
                    MenuAdministrador admin = new MenuAdministrador();
                    admin.setVisible(true);
                    this.setVisible(false);
                }

            } else {
                /**
                 *Cambia el Valor de la Variable si el Usuario No ha sido Encontrado
                */
                validado = 1;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " ... " + e);
        }

    }
    /**
     *Metodo que Valida si EL Usuario Ingresado es Un Cliente
     */
    public void ValidarClientes() {
        int resultado = 0;
        //Convierte la Informacion Ingresada por el usuario en Cadenas de Texto
        String clave = String.valueOf(password.getPassword());
        String nombre = txtusuario.getText();
         /**
         * Declaracion de sentencia SQL que va a ser enviada en la Consulta SQL
         */
        String SQL = "SELECT * FROM cliente WHERE nombre = '" + nombre + "' AND  password = '" + clave + "'";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "bienvenido "+nombre);

                    conectarBase.ConectarBase();
                    conectarBase.setClienteId(Integer.parseInt(rs.getString("id_cliente")));
                    conectarBase.setNombreUsuario(rs.getString("nombre"));
                    conectarBase.setPassword(rs.getString("password"));

                    MenuClientes cliente = new MenuClientes();
                    cliente.setVisible(true);
                    this.setVisible(false);

                }

            } else {//Excepcion que Muestra si EL Usuario No ha Sido Encontrado en Ninguna de las 2 Tablas
                JOptionPane.showMessageDialog(null, "Error\nUsuario no registrado");
                validado = 0;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " ... " + e);
        }
    }


    private void IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarActionPerformed
        /**
         * Realiza la Accion concreta de Buscar si el Usuario Exite/No, al Invocar la Conecxion 
         * a la Base de Datos y Llamar los dos metodos
         */
        conectarBase = new Conectar();
        Connection reg = (Connection) conectarBase.ConectarBase();
        ValidarUsuario();
        if (validado == 1) {
            ValidarClientes();
        }

    }//GEN-LAST:event_IngresarActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed


    }//GEN-LAST:event_txtusuarioActionPerformed

    private void IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresarMouseClicked

    }//GEN-LAST:event_IngresarMouseClicked

    private void CrearUsuarioClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearUsuarioClienteActionPerformed
        /**
         * Llama a la Opcion de poder Crear un Nuevo Usuario del Tipo Cliente
         */
        CrearUsuarioCliente cliente = new CrearUsuarioCliente();
        cliente.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CrearUsuarioClienteActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrearUsuarioCliente;
    private javax.swing.JButton Ingresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPasswordField password;
    public static javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}

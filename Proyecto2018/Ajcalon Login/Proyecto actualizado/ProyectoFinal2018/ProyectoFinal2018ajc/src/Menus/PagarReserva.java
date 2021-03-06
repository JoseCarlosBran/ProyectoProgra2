/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author José Carlos Bran
 */
public class PagarReserva extends javax.swing.JFrame {

    /**
     * Creates new form PagarReserva
     */
    public PagarReserva() {
        initComponents();
        Conectar con = new Conectar();
        Calendar c1=new GregorianCalendar();
            String dia=Integer.toString(c1.get(Calendar.DATE));
            String mes=Integer.toString(c1.get(Calendar.MONTH));
            String anio=Integer.toString(c1.get(Calendar.YEAR));
            String date = (anio+"/"+mes+"/"+dia);
            fechaPago.setText(date);
            CodigoReserva.setText(Integer.toString(con.getCodReserva()));
            ClienteId.setText(Integer.toString(con.getClienteId()));
            EstadoReserva.setText(con.getEstadoReserva());
            CodigoReserva.setEditable(false);
            fechaPago.setEditable(false);
            ClienteId.setEditable(false);
            EstadoReserva.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPagar = new javax.swing.JButton();
        txtmonto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CodigoReserva = new javax.swing.JTextField();
        ClienteId = new javax.swing.JTextField();
        fechaPago = new javax.swing.JTextField();
        EstadoReserva = new javax.swing.JTextField();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPagar.setText("Pagar ");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo reserva");

        jLabel2.setText("Id cliente");

        jLabel3.setText("Fecha pago");

        jLabel4.setText("Monto");

        jLabel5.setText("Estado de reserva");

        CodigoReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoReservaActionPerformed(evt);
            }
        });

        Cancelar.setText("Regresar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnPagar)
                        .addGap(35, 35, 35)
                        .addComponent(Cancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmonto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(CodigoReserva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ClienteId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaPago, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EstadoReserva))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(CodigoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(EstadoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagar)
                    .addComponent(Cancelar))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        //metodo para guardar los datos en la base
        //asi nos queda que tenemos una reservacio
        //ya que tenemos registrado en BD
        try {

            Conectar con = new Conectar();
            Connection cn = con.ConectarBase();

            String cd = CodigoReserva.getText();
            String id = ClienteId.getText();
            String mt = txtmonto.getText();
            
            
            Calendar c1=new GregorianCalendar();
            String dia=Integer.toString(c1.get(Calendar.DATE));
            String mes=Integer.toString(c1.get(Calendar.MONTH));
            String anio=Integer.toString(c1.get(Calendar.YEAR));
            String date = (anio+"/"+mes+"/"+dia);
            fechaPago.setText(date);
            String sql = ("INSERT INTO pago(reserva_codigo, cliente_id, fechaPago, monto) VALUES (?,?,?,?)");
            PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);

            ps.setString(1, cd);
            ps.setString(2, id);
            ps.setString(3, date);
            ps.setString(4, mt);

            int r = ps.executeUpdate();
            r=0;
            String estadoReserva="Cancelada";
            sql = ("UPDATE reserva SET estadoReserva= '"+estadoReserva+"' WHERE codigo LIKE '%"+CodigoReserva.getText()+"%'");
            ps = (PreparedStatement) cn.prepareStatement(sql);
            r = ps.executeUpdate();

            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Pago con exito");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " " + e);
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void CodigoReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoReservaActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        // TODO add your handling code here:
        PagoReservas cliente=new PagoReservas();
        cliente.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CancelarActionPerformed

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
            java.util.logging.Logger.getLogger(PagarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagarReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField ClienteId;
    private javax.swing.JTextField CodigoReserva;
    private javax.swing.JTextField EstadoReserva;
    private javax.swing.JButton btnPagar;
    private javax.swing.JTextField fechaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtmonto;
    // End of variables declaration//GEN-END:variables
}

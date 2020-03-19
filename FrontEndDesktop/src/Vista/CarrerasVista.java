/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CarrerasController;
import Main.Main;
import Modelo.CarrerasModel;
import com.mobiles.backend.AccesoADatos.GlobalException;
import com.mobiles.backend.AccesoADatos.NoDataException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario1
 */
public class CarrerasVista extends javax.swing.JInternalFrame implements java.util.Observer {

    CarrerasController controller;
    CarrerasModel model;

    /**
     * Creates new form CarrerasView
     */
    public CarrerasVista() {
        initComponents();
    }

    public void setController(CarrerasController controller) {
        this.controller = controller;
    }

    public void setModel(CarrerasModel model) {
        this.model = model;
        model.addObserver(this);
    }

    public CarrerasModel getModel() {
        return model;
    }

    public int getRow() {
        return this.carrerasTB.getSelectedRow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBusqueda = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        busFld = new javax.swing.JTextField();
        btnListar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        carrerasTB = new javax.swing.JTable();
        RBCodigo = new javax.swing.JRadioButton();
        RBNombre = new javax.swing.JRadioButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Carreras");

        jLabel1.setText("Buscar:");

        busFld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busFldKeyReleased(evt);
            }
        });

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Icons.png"))); // NOI18N
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/add.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/delete.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        carrerasTB.setModel(new javax.swing.table.DefaultTableModel(
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
        carrerasTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carrerasTBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(carrerasTB);

        grupoBusqueda.add(RBCodigo);
        RBCodigo.setText("Código");

        grupoBusqueda.add(RBNombre);
        RBNombre.setText("Nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(busFld, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RBCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RBNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBorrar)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(busFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RBCodigo)
                    .addComponent(RBNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListar)
                    .addComponent(btnAgregar)
                    .addComponent(btnBorrar))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        try {
            controller.Listar();
        } catch (GlobalException ex) {
            Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Main.CARRERA_VIEW.setLocation(this.btnAgregar.getLocationOnScreen());
        controller.preAgregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int row = this.carrerasTB.getSelectedRow();
        if (row != -1) {
            int resp = JOptionPane.showConfirmDialog(this, "¿Desea borrar?", "Eliminar Carrera", 0, 0);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    controller.eliminarCarrera(row);
                } catch (GlobalException ex) {
                    Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoDataException ex) {
                    Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void busFldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busFldKeyReleased
        // TODO add your handling code here:
        if (RBCodigo.isSelected()) {
            try {
                controller.buscarCarreraCodigo();
            } catch (GlobalException ex) {
                Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoDataException ex) {
                Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (RBNombre.isSelected()) {
                try {
                    controller.buscarCarreraNombre();
                } catch (GlobalException ex) {
                    Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoDataException ex) {
                    Logger.getLogger(CarrerasVista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // controller.buscar();  
        if (!model.getMensaje().isEmpty()) {
            busFld.setText("");
        }
    }//GEN-LAST:event_busFldKeyReleased

    private void carrerasTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carrerasTBMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int row = this.carrerasTB.getSelectedRow();
            Main.CARRERA_VIEW.setLocation(evt.getLocationOnScreen());
            controller.editar(row);
        }
    }//GEN-LAST:event_carrerasTBMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RBCodigo;
    private javax.swing.JRadioButton RBNombre;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnListar;
    public javax.swing.JTextField busFld;
    private javax.swing.JTable carrerasTB;
    private javax.swing.ButtonGroup grupoBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        carrerasTB.setModel(model.getCarreras());
        this.revalidate();
        if (!model.getMensaje().equals("")) {
            JOptionPane.showMessageDialog(this, model.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
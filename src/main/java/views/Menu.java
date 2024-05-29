/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import models.Productos;

/**
 *
 * @author khalid
 */
public class Menu extends javax.swing.JDialog {

    /**
     * Creates new form Menu
     */
    private ComprarMenu padre;
    private DefaultListModel<Productos> modeloCarrito;
     
    public Menu(ComprarMenu ventana, boolean modal) {
        super(ventana, modal);
        padre = ventana;
        initComponents();
        this.setTitle("Menu de Productos");
         configurarRenderizadorListaCarrito(); 
         
         // Inicializar el modelo del carrito
        modeloCarrito = new DefaultListModel<>();
        
        // Inicializar la lista con las comidas
         actualizarLista("Comida");
    }
        
    
    
    
    
    private void actualizarLista(String categoria) {
        // Obtén la lista de productos de la categoría especificada
        List<Productos> productos = models.Metodos.RecibirListaProd(categoria);

        // Crea un DefaultListModel y añade los productos
        DefaultListModel<Productos> modeloLista = new DefaultListModel<>();
        for (Productos producto : productos) {
            modeloLista.addElement(producto);
        }

        // Establece el modelo para el JList
        ListaMuestra.setModel(modeloLista);

        // Configura el ListCellRenderer para mostrar el nombre y el precio
        ListaMuestra.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Productos) {
                    Productos producto = (Productos) value;
                    setText(producto.getNomproducto() + ", " + producto.getPrecio());
                }
                return renderer;
            }
        });
    }
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VariableCambiar = new javax.swing.JLabel();
        AñadirProducto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        CantidadCBtn = new javax.swing.JLabel();
        CantidadCTxt = new javax.swing.JTextField();
        Bebidas = new javax.swing.JButton();
        Comidas = new javax.swing.JButton();
        Postres = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaMuestra = new javax.swing.JList<>();
        ActCarritoBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaCarrito = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        LimpiarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        VariableCambiar.setText("Comidas Disponibles:");

        AñadirProducto.setText("Añadir");
        AñadirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirProductoActionPerformed(evt);
            }
        });

        jButton2.setText("Volver atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CantidadCBtn.setText("Cantidad");

        Bebidas.setText("Bebidas");
        Bebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BebidasActionPerformed(evt);
            }
        });

        Comidas.setText("Comidas");
        Comidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComidasActionPerformed(evt);
            }
        });

        Postres.setText("Postres");
        Postres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostresActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(ListaMuestra);

        ActCarritoBtn.setText("Actualizar carrito");
        ActCarritoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActCarritoBtnActionPerformed(evt);
            }
        });

        ListaCarrito.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaCarritoValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ListaCarrito);

        jLabel1.setText("Productos en el carrito:");

        LimpiarBtn.setText("Limpiar carrito");
        LimpiarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CantidadCBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(CantidadCTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(AñadirProducto)
                        .addGap(49, 49, 49)
                        .addComponent(jButton2)
                        .addGap(199, 199, 199))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Comidas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VariableCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Postres, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ActCarritoBtn)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LimpiarBtn))
                .addGap(245, 245, 245))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(VariableCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(Comidas)
                                .addGap(25, 25, 25)
                                .addComponent(Bebidas)
                                .addGap(33, 33, 33)
                                .addComponent(Postres))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CantidadCTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CantidadCBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AñadirProducto)
                            .addComponent(jButton2)
                            .addComponent(ActCarritoBtn)
                            .addComponent(LimpiarBtn))
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ComidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComidasActionPerformed
        // TODO add your handling code here:
        VariableCambiar.setText("Comidas Disponibles:");
         actualizarLista("Comida");
    }//GEN-LAST:event_ComidasActionPerformed

    private void BebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BebidasActionPerformed
        // TODO add your handling code here:
        VariableCambiar.setText("Bebidas Disponibles:");
        actualizarLista("Bebida");
    }//GEN-LAST:event_BebidasActionPerformed

    private void PostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostresActionPerformed
        // TODO add your handling code here:
        VariableCambiar.setText("Postres Disponibles:");
        actualizarLista("Postre");
    }//GEN-LAST:event_PostresActionPerformed

    private void ListaCarritoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaCarritoValueChanged

    }//GEN-LAST:event_ListaCarritoValueChanged

    private void AñadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirProductoActionPerformed
        // TODO add your handling code here:
        // Obtener el producto seleccionado
        Productos productoSeleccionado = ListaMuestra.getSelectedValue();
        // Agregar el producto al carrito
        if (productoSeleccionado != null) {
            modeloCarrito.addElement(productoSeleccionado);
        }
    }//GEN-LAST:event_AñadirProductoActionPerformed

    private void ActCarritoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActCarritoBtnActionPerformed
        // TODO add your handling code here:
        ListaCarrito.setModel(modeloCarrito);
    }//GEN-LAST:event_ActCarritoBtnActionPerformed

    private void LimpiarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarBtnActionPerformed
        // TODO add your handling code here:
        modeloCarrito.clear();
    }//GEN-LAST:event_LimpiarBtnActionPerformed
    

        
        
        

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Menu dialog = new Menu(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActCarritoBtn;
    private javax.swing.JButton AñadirProducto;
    private javax.swing.JButton Bebidas;
    private javax.swing.JLabel CantidadCBtn;
    private javax.swing.JTextField CantidadCTxt;
    private javax.swing.JButton Comidas;
    private javax.swing.JButton LimpiarBtn;
    public javax.swing.JList<Productos> ListaCarrito;
    public javax.swing.JList<Productos> ListaMuestra;
    private javax.swing.JButton Postres;
    private javax.swing.JLabel VariableCambiar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    private void configurarRenderizadorListaCarrito() {
    // Establecer el renderizador personalizado para ListaCarrito
    ListaCarrito.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Productos) {
                Productos producto = (Productos) value;
                setText(producto.getNomproducto() + ", " + producto.getPrecio());
            }
            return renderer;
        }
    });
}
    
    
}

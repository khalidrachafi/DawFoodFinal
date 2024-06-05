/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import models.FuncionesCarrito;
import models.Productos;

/**
 *
 * @author khalid
 */
public class Menu extends javax.swing.JDialog {

    /**
     * Creates new form Menu
     */
    private VentanaPrincipal padre;
    private Map<Productos, Integer> carrito;
    private DefaultListModel<String> modeloCarrito;

    public Menu(VentanaPrincipal ventana, boolean modal) {
        super(ventana, modal);
        padre = ventana;
        initComponents();
        carrito = new HashMap<>();
        this.setTitle("Menu de Productos");
        configurarRenderizadorListaCarrito();

        // Inicializar el modelo del carrito
        modeloCarrito = new DefaultListModel<>();

        // Inicializar la lista con las comidas
        actualizarLista("Comida");

    }

    public Map<Productos, Integer> getCarrito() {
        return carrito;
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

    //actualizar el carrito para que muestre los productos
    private void actualizarCarrito() {
        modeloCarrito.clear();
        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
            Productos producto = entry.getKey();
            int cantidad = entry.getValue();
            modeloCarrito.addElement(producto.getNomproducto() + " x" + cantidad);
        }
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
        Comprar = new javax.swing.JButton();

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

        Comprar.setText("Comprar");
        Comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComprarActionPerformed(evt);
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
                            .addComponent(Bebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Postres, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VariableCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ActCarritoBtn)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LimpiarBtn)
                        .addGap(196, 196, 196)
                        .addComponent(Comprar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CantidadCTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CantidadCBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AñadirProducto)
                    .addComponent(jButton2)
                    .addComponent(ActCarritoBtn)
                    .addComponent(LimpiarBtn)
                    .addComponent(Comprar))
                .addGap(27, 27, 27))
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

        Productos productoSeleccionado = ListaMuestra.getSelectedValue();
        String cantidadStr = CantidadCTxt.getText();

        try {
            int cantidad = Integer.parseInt(cantidadStr);

            if (productoSeleccionado != null && cantidad > 0) {
                carrito.put(productoSeleccionado, carrito.getOrDefault(productoSeleccionado, 0) + cantidad);
                actualizarCarrito();
                JOptionPane.showMessageDialog(this, cantidad + " " + productoSeleccionado.getNomproducto() + "(s) añadido(s) al carrito.");
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto y una cantidad válida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la cantidad.");
        }


    }//GEN-LAST:event_AñadirProductoActionPerformed

    private void ActCarritoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActCarritoBtnActionPerformed
        // TODO add your handling code here:
        ListaCarrito.setModel(modeloCarrito);

        modeloCarrito.clear();
        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
            Productos producto = entry.getKey();
            int cantidad = entry.getValue();
            modeloCarrito.addElement(producto.getNomproducto() + " x" + cantidad);
        }


    }//GEN-LAST:event_ActCarritoBtnActionPerformed

    private void LimpiarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarBtnActionPerformed
        // TODO add your handling code here:
        carrito.clear();
        modeloCarrito.clear();

    }//GEN-LAST:event_LimpiarBtnActionPerformed

    private void ComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComprarActionPerformed
        // TODO add your handling code here:
        if (modeloCarrito.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede comprar, porque no hay nada en el carrito");
        } else {
            new PasarelaPago(this, true).setVisible(true);
        }
    }//GEN-LAST:event_ComprarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActCarritoBtn;
    private javax.swing.JButton AñadirProducto;
    private javax.swing.JButton Bebidas;
    private javax.swing.JLabel CantidadCBtn;
    private javax.swing.JTextField CantidadCTxt;
    private javax.swing.JButton Comidas;
    private javax.swing.JButton Comprar;
    private javax.swing.JButton LimpiarBtn;
    public javax.swing.JList<String> ListaCarrito;
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

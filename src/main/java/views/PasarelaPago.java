/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.AtributosTarjeta;
import models.Productos;

/**
 *
 * @author khalid
 */
public class PasarelaPago extends javax.swing.JDialog {

    /**
     * Creates new form PasarelaPago
     */
//    public PasarelaPago(java.awt.Frame parent, boolean modal) {
//        super(parent, modal);
//        initComponents();
//    }
    private CarritoInterfaz padre;
    private static List<Productos> carrito = new ArrayList<>();
    private static List<AtributosTarjeta> listaDeTarjetas = new ArrayList<>();
     
    public PasarelaPago(CarritoInterfaz ventana, boolean modal) {
        super(ventana, modal);
        padre = ventana;
        initComponents();
        this.setTitle("Pasarela de pago");
        listaDeTarjetas.add(new AtributosTarjeta(1234, LocalDate.of(2025, 12, 1), 123, 100, "Juan Perez"));
        listaDeTarjetas.add(new AtributosTarjeta(5432, LocalDate.of(2025, 10, 4), 456, 500, "Maria Rodriguez"));
        listaDeTarjetas.add(new AtributosTarjeta(1122, LocalDate.of(2025, 8, 23), 789, 200, "Pedro Gomez"));
        listaDeTarjetas.add(new AtributosTarjeta(3322, LocalDate.of(2025, 5, 22), 234, 1500, "Ana Martinez"));
        listaDeTarjetas.add(new AtributosTarjeta(1422, LocalDate.of(2025, 3, 21), 567, 3000, "Carlos Herrera"));
    
    }
    
    
    /*--------------
    */
    

    // Método para agregar productos seleccionados al carrito.
    
//    public static void agregarProductoAlCarrito(Productos producto) {
//        // Verificar si el producto está en stock
//        if (producto.getStock() == 0) {
//            JOptionPane.showMessageDialog(null, "El producto '" + producto.getNomproducto() + "' no está disponible en stock.", "Producto no disponible", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        // Pedir al usuario que ingrese la cantidad utilizando JOptionPane
//        String cantidada = JOptionPane.showInputDialog("Ingrese la cantidad de '" + producto.getNombre() + "' que desea agregar al carrito:");
//
//        try {
//            // Convertir la cantidad ingresada a un número entero
//            int cantidad = Integer.parseInt(cantidada);
//
//            if (cantidad > 0) {
//                for (int i = 0; i < cantidad; i++) {
//                    // Agregar el producto al carrito la cantidad especificada de veces
//                    carrito.add(producto);
//                }
//
//                JOptionPane.showMessageDialog(null, cantidad + " x '" + producto.getNombre() + "' ha sido agregado al carrito.");
//            } else {
//                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0. No se ha agregado nada al carrito.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }



    // Método para limpiar el carrito.
    public static void noComprar() {
        carrito.clear();
    }

    // Método para realizar el proceso de pago
    public static void procesarPago() {
        // Calcular el precio total con IVA
        // lo he puesto a 5 para que no salte error
        double precioTotal = 5; // calcularPrecioTotalConIVA();

        // Verificar si hay productos en el carrito.
        if (precioTotal != 0) {
            boolean tarjetaValida = false;
            int intentos = 0;

            // Permitir hasta 2 intentos de pago.
            while (!tarjetaValida && intentos < 2) {
                try {
                    // Solicitar al usuario el número de tarjeta.
                    String stringNumTarj = JOptionPane.showInputDialog("Ingrese el número de su tarjeta:");

                    // Verificar si la entrada es un número.
                    if (!esNumero(stringNumTarj)) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el número de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
                        intentos++;
                    } else {
                        int numeroTarjeta = Integer.parseInt(stringNumTarj);

                        // Verificar si la tarjeta con ese número existe.
                        AtributosTarjeta tarjeta = obtenerTarjetaPorNumero(numeroTarjeta);

                        if (tarjeta != null) {

                            LocalDate fechaCaducidad = null;

                            // Si la tarjeta existe, solicitar la fecha de caducidad y el CVV.
                            try {
                                String fechaCaducidadA = JOptionPane.showInputDialog("Ingrese la fecha de caducidad (YYYY/MM/DD):");
                                fechaCaducidad = LocalDate.parse(fechaCaducidadA, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                            } catch (DateTimeParseException e) {
                                // Capturar excepción DateTimeParseException e imprimir en la terminal.
                                // System.out.println("Excepción DateTimeParseException: " + e.getMessage());
                            }

                            String cvv = JOptionPane.showInputDialog("Ingrese el CVV:");

                            // Verificar que la fecha de caducidad y el CVV no sean nulos o vacíos.
                            if (fechaCaducidad != null && cvv != null && !cvv.isEmpty()) {
                                // Verificar la fecha de caducidad y el CVV.
                                if (verificarFechaYCVV(tarjeta, fechaCaducidad, cvv)) {
                                    // Calcular el precio total con IVA.
                                    double precioTotalConIVA = 5; //calcularPrecioTotalConIVA();

                                    // Verificar si hay suficiente saldo para la compra.
                                    if (tarjeta.getSaldo() >= precioTotalConIVA) {
                                        // Realizar la compra descontando el saldo.
                                        tarjeta.setSaldo(tarjeta.getSaldo() - precioTotalConIVA);

                                        // Mostrar mensaje de compra realizada.
                                        JOptionPane.showMessageDialog(null, "Compra realizada. Gracias por su compra!");

                                        // Llamar al método ticket.
                                        //ticket();

                                        // Limpiar el carrito después de la compra.
                                        carrito.clear();

                                        // Salir del bucle.
                                        tarjetaValida = true;

                                    } else {
                                        // Mostrar mensaje de saldo insuficiente.
                                        JOptionPane.showMessageDialog(null, "Saldo insuficiente. Ingrese otra tarjeta.");
                                        intentos++;
                                    }
                                } else {
                                    // Mostrar mensaje de fecha de caducidad o CVV incorrecto.
                                    JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
                                    intentos++;
                                }
                            } else {
                                // Mostrar mensaje de fecha de caducidad o CVV nulo o vacío.
                                JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
                                intentos++;
                            }

                        } else {
                            // Mostrar mensaje de tarjeta no encontrada.
                            JOptionPane.showMessageDialog(null, "Tarjeta no encontrada. Ingrese otra tarjeta.");
                            intentos++;
                        }
                    }
                } catch (NumberFormatException e) {
                    // Mostrar mensaje de entrada no válida.
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    intentos++;
                }
            }

        } else {
            // Mostrar mensaje de carrito vacío.
            JOptionPane.showMessageDialog(null, "El carrito está vacío. No se puede realizar la compra.");

            // Limpiar el carrito después de la compra.
            carrito.clear();
        }
    }

    // Método para verificar si una cadena es un número
    private static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena); // Intenta convertir la cadena a un entero.
            return true; // Si funciona, devuelve true.
        } catch (NumberFormatException e) {
            return false;  // Si hay una excepción, devuelve false.
        }
    }

    // Método para buscar una tarjeta por su número en la lista de tarjetas
    public static AtributosTarjeta obtenerTarjetaPorNumero(int numeroTarjeta) {
        for (AtributosTarjeta tarjeta : listaDeTarjetas) {
            if (tarjeta.getNumeroTarjeta() == numeroTarjeta) {
                return tarjeta; // Devuelve la tarjeta si encuentra el número proporcionado.
            }
        }
        return null; // Retorna null si no se encuentra la tarjeta con el número proporcionado.
    }

    // Método para verificar la fecha de caducidad y el CVV de una tarjeta
    private static boolean verificarFechaYCVV(AtributosTarjeta tarjeta, LocalDate fechaCaducidad, String cvv) {

        // Verificar la fecha de caducidad
        if (tarjeta.getFechaVencimiento().equals(fechaCaducidad)) {
            // Verificar el CVV
            return tarjeta.getCvv() == Integer.parseInt(cvv);
        }

        return false; // Devuelve false si la fecha de caducidad no coincide o el CVV no coincide.
    }
    
    
    /*--------------
    */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComprobarBtn = new javax.swing.JButton();
        NumTarjLb = new javax.swing.JLabel();
        FechaVnLb = new javax.swing.JLabel();
        CvvLb = new javax.swing.JLabel();
        NumTarjTxt = new javax.swing.JTextField();
        mesTxt = new javax.swing.JTextField();
        CvvTxt = new javax.swing.JTextField();
        AñoTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ComprobarBtn.setText("Comprobar");

        NumTarjLb.setText("Numero Tarjeta");

        FechaVnLb.setText("Introduce la fecha de vencimiento:");

        CvvLb.setText("Cvv");

        jLabel1.setText("Mes");

        jLabel2.setText("Año");

        jLabel3.setText("Introduce tus datos bancarios:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(CvvLb, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(CvvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(FechaVnLb))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(mesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(NumTarjLb, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AñoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NumTarjTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComprobarBtn))))
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(NumTarjLb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FechaVnLb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumTarjTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AñoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CvvLb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CvvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComprobarBtn))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
//            java.util.logging.Logger.getLogger(PasarelaPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PasarelaPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PasarelaPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PasarelaPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                PasarelaPago dialog = new PasarelaPago(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField AñoTxt;
    private javax.swing.JButton ComprobarBtn;
    private javax.swing.JLabel CvvLb;
    private javax.swing.JTextField CvvTxt;
    private javax.swing.JLabel FechaVnLb;
    private javax.swing.JLabel NumTarjLb;
    private javax.swing.JTextField NumTarjTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField mesTxt;
    // End of variables declaration//GEN-END:variables
}

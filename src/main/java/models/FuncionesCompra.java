//package models;
//
//// Importar las clases necesarias desde otros paquetes.
//
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import javax.swing.JOptionPane;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import models.AtributosTarjeta;
//import models.Productos;
//
///**
// * Clase con las funciones relacionadas con el carrito de compras.
// *
// * @author acebedo
// */
//public class FuncionesCompra {
//
//    private static Map<Productos, Integer> carrito = new HashMap<>();
//    private static List<AtributosTarjeta> listaDeTarjetas = new ArrayList<>();
//    private static List<Tickets> listaDeTickets = new ArrayList<>();
//
//
//    private static int idPedidoContador = 1;
//
//    // Constructor que inicializa la lista de tarjetas.
//    public FuncionesCompra() {
//
//        listaDeTarjetas.add(new AtributosTarjeta(1234, LocalDate.of(2025, 12, 1), 123, 100, "Juan Perez"));
//        listaDeTarjetas.add(new AtributosTarjeta(5432, LocalDate.of(2025, 10, 4), 456, 500, "Maria Rodriguez"));
//        listaDeTarjetas.add(new AtributosTarjeta(1122, LocalDate.of(2025, 8, 23), 789, 200, "Pedro Gomez"));
//        listaDeTarjetas.add(new AtributosTarjeta(3322, LocalDate.of(2025, 5, 22), 234, 1500, "Ana Martinez"));
//        listaDeTarjetas.add(new AtributosTarjeta(1422, LocalDate.of(2025, 3, 21), 567, 3000, "Carlos Herrera"));
//    }
//
//    // Método para agregar productos seleccionados al carrito.
//    public static void agregarProductoAlCarrito(Productos producto) {
//        // Verificar si el producto está en stock
//        if (producto.getStock() == 0) {
//            JOptionPane.showMessageDialog(null, "El producto '" + producto.getNomproducto() + "' no está disponible en stock.", "Producto no disponible", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        // Pedir al usuario que ingrese la cantidad utilizando JOptionPane
//        String cantidada = JOptionPane.showInputDialog("Ingrese la cantidad de '" + producto.getNomproducto() + "' que desea agregar al carrito:");
//
//        try {
//            // Convertir la cantidad ingresada a un número entero
//            int cantidad = Integer.parseInt(cantidada);
//
//            if (cantidad > 0) {
//                for (int i = 0; i < cantidad; i++) {
//                    // Agregar el producto al carrito una vez por cada iteración
//                    carrito.put(producto, carrito.getOrDefault(producto, 0) + 1);
//                }
//
//                JOptionPane.showMessageDialog(null, cantidad + " x '" + producto.getNomproducto() + "' ha sido agregado al carrito.");
//            } else {
//                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0. No se ha agregado nada al carrito.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    // Método para mostrar el menú de productos seleccionados con precios usando JOptionPane
//    public void mostrarMenuCarritoConPrecios() {
//        try {
//            // StringBuilder para construir el mensaje a mostrar.
//            StringBuilder mensaje = new StringBuilder("Productos Seleccionados:\n");
//            // Variables para calcular el precio total sin IVA y con IVA.
//            double precioTotalSinIVA = 0;
//            double precioTotalConIVA = 0;
//
//            // Iterar sobre las entradas (producto y cantidad) en el carrito.
//            for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
//                Productos producto = entry.getKey();
//                int cantidad = entry.getValue();
//
//                // Agregar al mensaje el nombre del producto, su cantidad y precio (sin IVA).
//                mensaje.append(producto.getNomproducto()).append(" (").append(cantidad).append(" unidades): ");
//                mensaje.append(producto.getPrecio() * cantidad).append(" € (sin IVA)\n");
//
//                // Calcular el precio total sin IVA sumando el precio de cada producto multiplicado por su cantidad.
//                precioTotalSinIVA += producto.getPrecio() * cantidad;
//
//                // Calcular el precio total con IVA sumando el precio con IVA de cada producto multiplicado por su cantidad.
//                precioTotalConIVA += producto.getPrecioConIVA() * cantidad;
//            }
//
//            // Agregar al mensaje el precio total sin IVA y el precio total con IVA.
//            mensaje.append("Precio Total sin IVA: ").append(String.format("%.2f", precioTotalSinIVA)).append(" €\n");
//            mensaje.append("Precio Total con IVA: ").append(String.format("%.2f", precioTotalConIVA)).append(" €");
//
//            // Mostrar el mensaje utilizando JOptionPane.
//            JOptionPane.showMessageDialog(null, mensaje.toString(), "Carrito", JOptionPane.INFORMATION_MESSAGE);
//
//
//        } catch (NullPointerException e) {
//            // Capturar excepción NullPointerException e imprimir en la terminal.
//            // System.out.println("Excepción NullPointerException: " + e.getMessage());
//        }
//    }
//
//    // Método para limpiar el carrito.
//    public static void noComprar() {
//        carrito.clear();
//    }
//
//    // Método para realizar el proceso de pago
//    public static void procesarPago() {
//        // Calcular el precio total con IVA
//        double precioTotal = calcularPrecioTotalConIVA();
//
//        // Verificar si hay productos en el carrito.
//        if (precioTotal != 0) {
//            boolean tarjetaValida = false;
//            int intentos = 0;
//
//            // Permitir hasta 2 intentos de pago.
//            while (!tarjetaValida && intentos < 2) {
//                try {
//                    // Solicitar al usuario el número de tarjeta.
//                    String stringNumTarj = JOptionPane.showInputDialog("Ingrese el número de su tarjeta:");
//
//                    // Verificar si la entrada es un número.
//                    if (!esNumero(stringNumTarj)) {
//                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el número de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
//                        intentos++;
//                    } else {
//                        int numeroTarjeta = Integer.parseInt(stringNumTarj);
//
//                        // Verificar si la tarjeta con ese número existe.
//                        AtributosTarjeta tarjeta = obtenerTarjetaPorNumero(numeroTarjeta);
//
//                        if (tarjeta != null) {
//
//                            LocalDate fechaCaducidad = null;
//
//                            // Si la tarjeta existe, solicitar la fecha de caducidad y el CVV.
//                            try {
//                                String fechaCaducidadA = JOptionPane.showInputDialog("Ingrese la fecha de caducidad (YYYY/MM/DD):");
//                                fechaCaducidad = LocalDate.parse(fechaCaducidadA, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//                            } catch (DateTimeParseException e) {
//                                // Capturar excepción DateTimeParseException e imprimir en la terminal.
//                                // System.out.println("Excepción DateTimeParseException: " + e.getMessage());
//                            }
//
//                            String cvv = JOptionPane.showInputDialog("Ingrese el CVV:");
//
//                            // Verificar que la fecha de caducidad y el CVV no sean nulos o vacíos.
//                            if (fechaCaducidad != null && cvv != null && !cvv.isEmpty()) {
//                                // Verificar la fecha de caducidad y el CVV.
//                                if (verificarFechaYCVV(tarjeta, fechaCaducidad, cvv)) {
//                                    // Calcular el precio total con IVA.
//                                    double precioTotalConIVA = calcularPrecioTotalConIVA();
//
//                                    // Verificar si hay suficiente saldo para la compra.
//                                    if (tarjeta.getSaldo() >= precioTotalConIVA) {
//                                        // Realizar la compra descontando el saldo.
//                                        tarjeta.setSaldo(tarjeta.getSaldo() - precioTotalConIVA);
//
//                                        // Mostrar mensaje de compra realizada.
//                                        JOptionPane.showMessageDialog(null, "Compra realizada. Gracias por su compra!");
//
//                                        // Llamar al método ticket.
//                                        ticket();
//
//                                        // Limpiar el carrito después de la compra.
//                                        carrito.clear();
//
//                                        // Salir del bucle.
//                                        tarjetaValida = true;
//
//                                    } else {
//                                        // Mostrar mensaje de saldo insuficiente.
//                                        JOptionPane.showMessageDialog(null, "Saldo insuficiente. Ingrese otra tarjeta.");
//                                        intentos++;
//                                    }
//                                } else {
//                                    // Mostrar mensaje de fecha de caducidad o CVV incorrecto.
//                                    JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
//                                    intentos++;
//                                }
//                            } else {
//                                // Mostrar mensaje de fecha de caducidad o CVV nulo o vacío.
//                                JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
//                                intentos++;
//                            }
//
//                        } else {
//                            // Mostrar mensaje de tarjeta no encontrada.
//                            JOptionPane.showMessageDialog(null, "Tarjeta no encontrada. Ingrese otra tarjeta.");
//                            intentos++;
//                        }
//                    }
//                } catch (NumberFormatException e) {
//                    // Mostrar mensaje de entrada no válida.
//                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
//                    intentos++;
//                }
//            }
//
//            // Si se superan los 2 intentos, limpiar el carrito y encender el TPV.
////            if (intentos >= 2) {
////                carrito.clear();
////                llamarEncenderTPV();
////            }
//
//        } else {
//            // Mostrar mensaje de carrito vacío.
//            JOptionPane.showMessageDialog(null, "El carrito está vacío. No se puede realizar la compra.");
//
//            // Limpiar el carrito después de la compra.
//            carrito.clear();
//
//
//        }
//    }
//
//    // Método para verificar si una cadena es un número
//    private static boolean esNumero(String cadena) {
//        try {
//            Integer.parseInt(cadena); // Intenta convertir la cadena a un entero.
//            return true; // Si funciona, devuelve true.
//        } catch (NumberFormatException e) {
//            return false;  // Si hay una excepción, devuelve false.
//        }
//    }
//
//    // Método para buscar una tarjeta por su número en la lista de tarjetas
//    public static AtributosTarjeta obtenerTarjetaPorNumero(int numeroTarjeta) {
//        for (AtributosTarjeta tarjeta : listaDeTarjetas) {
//            if (tarjeta.getNumeroTarjeta() == numeroTarjeta) {
//                return tarjeta; // Devuelve la tarjeta si encuentra el número proporcionado.
//            }
//        }
//        return null; // Retorna null si no se encuentra la tarjeta con el número proporcionado.
//    }
//
//    // Método para verificar la fecha de caducidad y el CVV de una tarjeta
//    private static boolean verificarFechaYCVV(AtributosTarjeta tarjeta, LocalDate fechaCaducidad, String cvv) {
//
//        // Verificar la fecha de caducidad
//        if (tarjeta.getFechaVencimiento().equals(fechaCaducidad)) {
//            // Verificar el CVV
//            return tarjeta.getCvv() == Integer.parseInt(cvv);
//        }
//
//        return false; // Devuelve false si la fecha de caducidad no coincide o el CVV no coincide.
//    }
//
//    // Método para calcular el precio total con IVA de los productos en el carrito
//    private static double calcularPrecioTotalConIVA() {
//        double precioTotalConIVA = 0;
//
//        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
//            Productos producto = entry.getKey();
//            int cantidad = entry.getValue();
//
//            // Calcular el precio total con IVA sumando el precio con IVA de cada producto multiplicado por su cantidad.
//            precioTotalConIVA += producto.getPrecioConIVA() * cantidad;
//        }
//
//        return precioTotalConIVA; // Devuelve el precio total con IVA
//    }
//
//    // Método para generar y mostrar un ticket de compra.
//    private static void ticket() {
//        // Calcular el precio final con IVA de los productos en el carrito.
//        double precioFinal = Math.round(calcularPrecioTotalConIVA() * 100d) / 100d;
//
//        // Generar un ID único para el pedido
//        int idPedido = generarIdPedido();
//
//        // Obtener la fecha y hora actual
//        LocalDateTime fechaYHoraOperacion = LocalDateTime.now();
//
//        // Crear una cadena para almacenar la información de los productos comprados.
//        StringBuilder productosComprados = new StringBuilder("\nProductos Seleccionados:\n");
//        for (Map.Entry<Productos, Integer> entry : carrito.entrySet()) {
//            Productos producto = entry.getKey();
//            int cantidad = entry.getValue();
//
//            productosComprados.append("ID: ").append(producto.getIdproductos()).append(" -- ");
//            productosComprados.append("Nombre: ").append(producto.getNomproducto()).append(" -- ");
//            productosComprados.append("Descripción: ").append(producto.getDescripcion()).append(" -- ");
//            productosComprados.append("Precio: ").append(producto.getPrecio()).append(" € (sin IVA) -- ");
//            productosComprados.append("Cantidad: ").append(cantidad).append(" unidades -- ");
//            productosComprados.append("IVA: ").append(producto.getIva()).append("\n");
//        }
//
//        // Crear un objeto AtributosTicket con la información recopilada.
//        AtributosTicket atributosTicket = new AtributosTicket(precioFinal, idPedido, fechaYHoraOperacion, productosComprados.toString());
//
//        // Agregar el ticket a la lista de tickets.
//        listaDeTickets.add(atributosTicket);
//
//        // Mostrar el ticket de compra utilizando JOptionPane.
//        JOptionPane.showMessageDialog(null, atributosTicket.toString(), "Ticket de Compra", JOptionPane.INFORMATION_MESSAGE);
//
//    }
//
//    // Método para generar un ID de pedido único
//    private static int generarIdPedido() {
//        return idPedidoContador++; // Incrementa el contador de ID de pedido y devuelve el valor anterior.
//    }
//
//
//
//    // Método para consultar las ventas realizadas.
//    public static void consultarVentas() {
//        // Preguntar al usuario qué tipo de consulta desea realizar.
//        String[] opciones = {"Ver las ventas de un día concreto", "Ver las ventas de hasta una fecha concreta", "Ver todas las ventas"};
//        int seleccion = JOptionPane.showOptionDialog(null, "¿Qué prefieres?", "Consulta de Ventas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
//
//        LocalDate fechaInicio = null;
//        LocalDate fechaFin = null;
//        boolean mostrarTodos = false;
//
//        switch (seleccion) {
//            case 0:
//                // Ver las ventas de un día concreto.
//                String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha en formato YYYY-MM-DD:");
//                try {
//                    fechaInicio = LocalDate.parse(fechaStr);
//                    fechaFin = fechaInicio.plusDays(1);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, "Fecha inválida. Mostrando todas las ventas.");
//                    mostrarTodos = true;
//                }
//                break;
//
//            case 1:
//                // Ver las ventas hasta una fecha concreta
//                String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha hasta la cual desea ver las ventas en formato YYYY-MM-DD:");
//                try {
//                    fechaFin = LocalDate.parse(fechaFinStr).plusDays(1);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, "Fecha inválida. Mostrando todas las ventas.");
//                    mostrarTodos = true;
//                }
//                break;
//
//            case 2:
//                // Ver todas las ventas.
//                mostrarTodos = true;
//                break;
//
//            default:
//                // Opción no válida, mostrar todas las ventas.
//                mostrarTodos = true;
//                break;
//        }
//
//
//
//        // Verificar si hay tickets registrados.
//        if (ticketsFiltrados.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "No hay ventas registradas en el período especificado.");
//        } else {
//            // Mostrar los tickets.
//            StringBuilder mensaje = new StringBuilder("Ventas Realizadas:\n\n");
//
//            for (AtributosTicket ticket : ticketsFiltrados) {
//                mensaje.append("ID Pedido: ").append(ticket.getIdPedido()).append("\n");
//                mensaje.append("Fecha y Hora: ").append(ticket.getFechaYHoraOperacion()).append("\n");
//                mensaje.append("Productos:\n").append(ticket.getProductosComprados()).append("\n");
//                mensaje.append("Precio Final: ").append(ticket.getPrecioFinal()).append(" €\n\n");
//            }
//
//            JOptionPane.showMessageDialog(null, mensaje.toString(), "Ventas Realizadas", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//
//    
//}
//

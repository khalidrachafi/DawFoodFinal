-- Insertando datos en la tabla TipoProducto
INSERT INTO tipoproducto (nombretipoprodcucto, categoria) VALUES
('Pizza', 'Comida'),
('Refresco', 'Bebida'),
('Helado', 'Postre');

-- Insertando datos en la tabla Tpv
INSERT INTO tpv (passwordadmin, ubicacion, fechahoraactual) VALUES
('12345678', 'Local A', NOW()),
('abcdefgh', 'Local B', NOW());

-- Insertando datos en la tabla Productos
INSERT INTO productos (iva, stock, descripcion, precio, nomproducto, codtipoproducto) VALUES
(0.12, 50, 'Pizza de pepperoni', 10.99, 'Pizza Pepperoni', 1),
(0.16, 100, 'Refresco de cola', 1.50, 'Coca-Cola', 2),
(0.10, 30, 'Helado de vainilla', 3.99, 'Helado de Vainilla', 3);

-- Insertando datos en la tabla Tickets
INSERT INTO tickets (codtransaccion, preciofinal, fechahoraticket, codtpv) VALUES
(123456, 15.99, NOW(), 1),
(789012, 5.00, NOW(), 2);

-- Insertando datos en la tabla contienen
INSERT INTO detalleticket (cantidad, idproductos, idtickets) VALUES
(2, 1, 1),
(3, 2, 1),
(1, 3, 2);
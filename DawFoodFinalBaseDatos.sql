
create database if not exists DawFood;

use DawFood;

CREATE TABLE TipoProducto
(
  nombreTipoProdcucto varchar(30),
  codTipoProducto INT not null auto_increment,
  categoria enum('Comida','Bebida','Postre'),
  PRIMARY KEY (codTipoProducto)
);

CREATE TABLE Tpv
(
  codTpv INT not null auto_increment,
  passwordAdmin varchar(8),
  ubicacion varchar(30),
  fechaHoraActual datetime,
  PRIMARY KEY (codTpv)
);

CREATE TABLE Productos
(
  idProductos INT not null auto_increment,
  iva double,
  stock int,
  descripcion varchar(30),
  precio decimal(4,2),
  nomProducto varchar(30),
  codTipoProducto INT NOT NULL,
  PRIMARY KEY (idProductos),
  FOREIGN KEY (codTipoProducto) REFERENCES TipoProducto(codTipoProducto)
);

CREATE TABLE Tickets
(
  idTickets INT not null auto_increment,
  codTransaccion INT NOT NULL,
  precioFinal decimal(5,2),
  fechaHoraTicket datetime,
  codTpv INT NOT NULL,
  PRIMARY KEY (idTickets),
  FOREIGN KEY (codTpv) REFERENCES Tpv(codTpv)
);

CREATE TABLE DetalleTicket
(
  cantidad INT,
  idProductos INT NOT NULL,
  idTickets INT NOT NULL,
  PRIMARY KEY (idProductos,idTickets),
  FOREIGN KEY (idProductos) REFERENCES Productos(idProductos),
  FOREIGN KEY (idTickets) REFERENCES Tickets(idTickets)
);

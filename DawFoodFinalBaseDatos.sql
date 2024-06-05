
create database if not exists dawfood;

use dawfood;

CREATE TABLE tipoproducto
(
  nombretipoprodcucto varchar(30),
  codtipoproducto INT not null auto_increment,
  categoria enum('Comida','Bebida','Postre'),
  PRIMARY KEY (codtipoproducto)
);

CREATE TABLE tpv
(
  codtpv INT not null auto_increment,
  passwordadmin varchar(8),
  ubicacion varchar(30),
  fechahoraactual datetime,
  PRIMARY KEY (codtpv)
);

CREATE TABLE productos
(
  idproductos INT not null auto_increment,
  iva double,
  stock int,
  descripcion varchar(30),
  precio decimal(4,2),
  nomproducto varchar(30),
  codtipoproducto INT NOT NULL,
  PRIMARY KEY (idproductos),
  FOREIGN KEY (codtipoproducto) REFERENCES tipoproducto(codtipoproducto)
);

CREATE TABLE tickets
(
  idtickets INT not null auto_increment,
  codtransaccion INT NOT NULL,
  preciofinal decimal(5,2),
  fechahoraticket datetime,
  codtpv INT NOT NULL,
  PRIMARY KEY (idtickets),
  FOREIGN KEY (codtpv) REFERENCES tpv(codtpv)
);

CREATE TABLE detalleticket
(
  cantidad INT,
  idproductos INT NOT NULL,
  idtickets INT NOT NULL,
  PRIMARY KEY (idproductos,idtickets),
  FOREIGN KEY (idproductos) REFERENCES productos(idproductos),
  FOREIGN KEY (idtickets) REFERENCES tickets(idtickets)
);








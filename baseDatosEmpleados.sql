create dataBase empleadosnominas;
USE empleadosnominas;
CREATE TABLE Empleado (
    Nombre VARCHAR(25) not null,
    Dni VARCHAR(9) PRIMARY KEY,
    Sexo CHAR(1) not null,
    Categoria INT(10) not null default 1,
    Anyos INT(10) not null default 0
);

CREATE TABLE Nomina (
    Dni varCHAR(9) PRIMARY KEY,
    sueldo int(20)
);

INSERT INTO empleado(nombre,dni,sexo,categoria,anyos) VALUES ('James Cosling','32000032G','M',4,7);
INSERT INTO empleado(nombre,dni,sexo) VALUES ('Ada Lovelace','32000031R','F');
CREATE TABLE carrera (
    id integer NOT NULL,
    nombre text
);

CREATE TABLE profesor (
    id integer NOT NULL,
    nombre text,
    titulo text,
    jerarquia text
);

CREATE TABLE director (
    idcarrera integer NOT NULL,
    idprofesor integer NOT NULL,
    anio integer NOT NULL
);

CREATE TABLE curso (
    id integer NOT NULL,
    nombre text,
    idcarrera integer,
    semestre integer,
    creditos integer
);

CREATE TABLE instanciacurso (
    idcurso integer NOT NULL,
    anio integer NOT NULL,
    idprofesor integer,
    nroalumnos integer
);

CREATE TABLE alumno (
    id integer NOT NULL,
    nombre text,
    apellidopat text,
    apellidomat text,
    sexo text,
    fecnac date,
    anioingreso integer
);

CREATE TABLE alumnoinstancia (
    idalumno integer,
    idcurso integer,
    anio integer,
    nota double precision
);





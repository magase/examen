INSERT INTO examen.roles (name)
SELECT 'ROLE_ADMIN'
    WHERE NOT EXISTS(SELECT 1 FROM examen.roles WHERE name = 'ROLE_ADMIN');

INSERT INTO examen.roles (name)
SELECT 'ROLE_USER'
    WHERE NOT EXISTS(SELECT 1 FROM examen.roles WHERE name = 'ROLE_USER');

INSERT INTO examen.asignaturas( familia_profesional, nombre)
SELECT 'Ciencias','Matematicas'
    WHERE NOT EXISTS(SELECT 1 FROM asignaturas WHERE familia_profesional = 'Ciencias' AND nombre = 'Matematicas');

INSERT INTO examen.asignaturas( familia_profesional, nombre)
SELECT 'Literatura','Lengua'
    WHERE NOT EXISTS(SELECT 1 FROM asignaturas WHERE familia_profesional = 'Literatura' AND nombre = 'Lengua');

INSERT INTO examen.grupos( nombre, curso)
SELECT 'Primero', 1
WHERE NOT EXISTS(SELECT 1 FROM grupos WHERE nombre = 'Primero' AND curso = 1);

INSERT INTO examen.grupos( nombre, curso)
SELECT 'Segundo', 2
WHERE NOT EXISTS(SELECT 1 FROM grupos WHERE nombre = 'Segundo' AND curso =2);

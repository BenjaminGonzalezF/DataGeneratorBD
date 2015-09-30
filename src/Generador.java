
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author renzo
 */

/*
 ESQUEMA DE LA BASE DE DATOS
 Carrera(id[PK],nombre)
 Profesor(id,nombre,titulo,jerarquia)
 Director(idCarrera[FK],idProfesor[FK],anio)
 Curso(id,nombre,idCarrera[FK],semestre,creditos)
 Alumno(id[PK],nombre,apellidoPat,apellidoMat,sexo,fecnac,anioIngreso)
 InstanciaCurso(idCurso[FK],anio,idProfesor,nroAlumnos) PK={idCurso,anio} 
 AlumnoInstancia(idAlumno[FK],idCurso[FK],anio,nota) FK={idCurso,año}
 */

/*
 Algunas estadísticas
 las carreras duran 12 semestres (6 años)
 6 cursos por semestre
 un curso puede tener entre 3 y 6 creditos
 */
public class Generador {

    ArrayList<String> dic_nombres_hombres = new ArrayList();
    ArrayList<String> dic_nombres_mujeres = new ArrayList();
    ArrayList<String> dic_apellidos = new ArrayList();
    ArrayList<String> dic_fechas_nac = new ArrayList();
    ArrayList<String> dic_jerarquias = new ArrayList();
    ArrayList<String> dic_carreras = new ArrayList();
    ArrayList<String> dic_cursos = new ArrayList();

    ArrayList<String> profesores = new ArrayList();

    int anio_base = 2000;
    int nro_anios = 0;
    int nro_carreras = 0;
    int nro_profesores = 0;
    int min_nro_profesores = 20;
    int max_nro_profesores = 30;
    int min_alumnos_carrera = 80;
    int max_alumnos_carrera = 100;
    int min_alumnos_curso = 70;
    int max_alumnos_curso = 100;
    int id_carrera = 0;
    int id_profesor_1 = 0;
    int id_profesor_n = 0;
    int id_alumno_1 = 1000000;
    int id_alumno_n = 0;
    int id_curso_1 = 0;
    int id_curso_n = 0;
    int id_instancia_1 = 0;
    int id_instancia_n = 0;

    Random rand = new Random(9999);

    public Generador() {

    }

    public void run(int _nro_anios) {
        this.nro_anios = _nro_anios;
        this.CrearDiccionarios();
        this.generarCarreras();
    }

    public void CrearDiccionarios() {
        System.out.println("---------------------------");
        System.out.println("---CARGA DE DICCIONARIOS---");
        System.out.println("---------------------------");
        this.CargarDatos(dic_nombres_hombres, "nombres_hombres.txt");
        System.out.println("Nombres de hombres = " + dic_nombres_hombres.size());
        this.CargarDatos(dic_nombres_mujeres, "nombres_mujeres.txt");
        System.out.println("Nombres de mujeres = " + dic_nombres_mujeres.size());
        this.CargarDatos(dic_apellidos, "apellidos.txt");
        System.out.println("Apellidos = " + dic_apellidos.size());
        this.CargarDatos(dic_fechas_nac, "fechas_1990-2000.txt");
        System.out.println("Fechas de nacimiento = " + dic_fechas_nac.size());
        this.CargarDatos(dic_jerarquias, "jerarquias.txt");
        System.out.println("Jerarquias = " + dic_jerarquias.size());
        this.CargarDatos(dic_carreras, "carreras.txt");
        System.out.println("Carreras universitarias = " + dic_carreras.size());
        nro_carreras = dic_carreras.size();
        this.CargarDatos(dic_cursos, "cursos.txt");
        System.out.println("Cursos = " + dic_cursos.size());
    }

    public void CargarDatos(ArrayList<String> list, String archivo) {
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            file = new File(archivo);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                list.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //Carrera(id[PK],nombre,idDirector[FK])    
    public void generarCarreras() {
        System.out.println("-------------------------");
        System.out.println("---GENERACION DE DATOS---");
        System.out.println("-------------------------");
        int idDirector = 0;
        String nombre = "";
        while (id_carrera <= nro_carreras) {
            id_carrera++;
            nombre = dic_carreras.get(rand.nextInt(dic_carreras.size()));
            this.writeCarrera(this.id_carrera, nombre);
            this.generarProfesores();
            this.generarDirectores();
            this.generarCursos();
            this.generarAlumnos();
            this.generarInstancias();
        }
    }

    public void generarProfesores() {
        String nombre;
        String apellidoPat;
        String apellidoMat;
        String nombreCompleto;
        String titulo;
        String jerarquia;
        int min = this.min_nro_profesores;
        int max = this.max_nro_profesores;
        this.nro_profesores = rand.nextInt(max - min) + min;
        id_profesor_1 = id_carrera * 1000;
        id_profesor_n = id_profesor_1;
        for (int i = 0; i < this.nro_profesores; i++) {
            if (rand.nextBoolean()) {
                nombre = dic_nombres_hombres.get(rand.nextInt(dic_nombres_hombres.size()));
            } else {
                nombre = dic_nombres_mujeres.get(rand.nextInt(dic_nombres_mujeres.size()));
            }
            apellidoPat = dic_apellidos.get(rand.nextInt(dic_apellidos.size()));
            apellidoMat = dic_apellidos.get(rand.nextInt(dic_apellidos.size()));
            nombreCompleto = nombre + apellidoPat + apellidoMat;
            titulo = dic_carreras.get(rand.nextInt(dic_carreras.size()));
            jerarquia = dic_jerarquias.get(rand.nextInt(dic_jerarquias.size()));
            this.writeProfesor(id_profesor_n, nombreCompleto, titulo, jerarquia);
            id_profesor_n++;
        }
        id_profesor_n--;
    }

    public void generarDirectores() {
        int idDirector = 0;
        int anio = this.anio_base;
        for (int a = 1; a <= this.nro_anios; a++) {
            idDirector = id_profesor_1 + rand.nextInt(id_profesor_n - id_profesor_1 + 1);
            this.writeDirector(this.id_carrera, idDirector, anio);
            anio++;
        }
    }

    // Curso(id,nombre,idCarrera[FK],semestre,creditos)
    public void generarCursos() {
        String nombre;
        int creditos;
        int semestre;
        this.id_curso_1 = this.id_carrera * 10000;
        this.id_curso_n = this.id_curso_1;
        // 12 semestres
        for (int i = 1; i <= 12; i++) {
            // 6 cursos por semestre
            for (int j = 1; j <= 6; j++) {
                nombre = dic_cursos.get(rand.nextInt(dic_cursos.size()));
                semestre = i;
                creditos = 3 + rand.nextInt(4);
                this.writeCurso(id_curso_n, nombre, this.id_carrera, semestre, creditos);
                id_curso_n++;
            }
        }
        id_curso_n--;
    }

    public void generarAlumnos() {
        String nombre = "";
        String apellidoPat = "";
        String apellidoMat = "";
        String sexo = "";
        String fecnac = "";
        int anioIngreso = 0;
        int alumnos_carrera = this.min_alumnos_carrera + rand.nextInt(this.max_alumnos_carrera - this.min_alumnos_carrera + 1);
        int nro_alumnos = nro_anios * 6 * alumnos_carrera; //suponemos 100 alumnos por nivel
        id_alumno_1 = id_carrera * 1000000;
        id_alumno_n = id_alumno_1;
        for (int i = 1; i <= nro_alumnos; i++) {
            if (rand.nextBoolean()) {
                nombre = dic_nombres_hombres.get(rand.nextInt(dic_nombres_hombres.size()));
                sexo = "M";
            } else {
                nombre = dic_nombres_mujeres.get(rand.nextInt(dic_nombres_mujeres.size()));
                sexo = "F";
            }
            apellidoPat = dic_apellidos.get(rand.nextInt(dic_apellidos.size()));
            apellidoMat = dic_apellidos.get(rand.nextInt(dic_apellidos.size()));
            fecnac = dic_fechas_nac.get(rand.nextInt(dic_fechas_nac.size()));
            anioIngreso = 1995 + rand.nextInt(5);
            this.writeAlumno(id_alumno_n, nombre, apellidoPat, apellidoMat, sexo, fecnac, anioIngreso);
            id_alumno_n++;
        }
        id_alumno_n--;
    }

    public void generarInstancias() {
        int idCurso = this.id_curso_1;
        int anio = 0;
        int idProfesor = 0;
        int nroAlumnos = 0;
        int idAlumno = 0;
        double nota = 0;
        while (idCurso <= this.id_curso_n) {
            //generar instancia para cada año
            anio = this.anio_base;
            for (int a = 1; a <= this.nro_anios; a++) {
                idProfesor = this.id_profesor_1 + rand.nextInt(this.id_profesor_n - this.id_profesor_1 + 1);
                nroAlumnos = this.min_alumnos_curso + rand.nextInt(this.max_alumnos_curso - this.min_alumnos_curso + 1);
                this.writeInstanciaCurso(idCurso, anio, idProfesor, nroAlumnos);
                //asignar alumnos a la Instancia
                for (int i = 1; i <= nroAlumnos; i++) {
                    idAlumno = this.id_alumno_1 + rand.nextInt(this.id_alumno_n - this.id_alumno_1 + 1);
                    if (rand.nextBoolean()) {
                        nota = 7;
                    } else {
                        nota = 1 + rand.nextInt(6) + rand.nextDouble();
                        nota = redondear(nota,1);
                    }
                    this.writeAlumnoInstancia(idAlumno, idCurso, anio, nota);
                }
                anio++;
            }
            idCurso++;
        }
    }

    public double redondear(double val, int places) {
        long factor = (long) Math.pow(10, places);
        val = val * factor;
        long tmp = Math.round(val);
        return (double) tmp / factor;
    }

    public void writeCarrera(int id, String nombre) {
        System.out.println("Carrera(" + id + "," + nombre + ")");
    }

    public void writeCurso(int id, String nombre, int idCarrera, int semestre, int creditos) {
        System.out.println("Curso(" + id + "," + nombre + "," + idCarrera + "," + semestre + "," + creditos + ")");
    }

    public void writeProfesor(int id, String nombre, String titulo, String jerarquia) {
        System.out.println("Profesor(" + id + "," + nombre + "," + titulo + "," + jerarquia + ")");
    }

    public void writeDirector(int idCarrera, int idProfesor, int anio) {
        System.out.println("Director(" + idCarrera + "," + idProfesor + "," + anio + ")");
    }

    public void writeAlumno(int id, String nombre, String apellidoPat, String apellidoMat, String sexo, String fecnac, int anioIngreso) {
        System.out.println("Alumno(" + id + "," + nombre + "," + apellidoPat + "," + apellidoMat + "," + sexo + "," + fecnac + "," + anioIngreso + ")");
    }

    public void writeInstanciaCurso(int idCurso, int anio, int idProfesor, int nroAlumnos) {
        System.out.println("InstanciaCurso(" + idCurso + "," + anio + "," + idProfesor + "," + nroAlumnos + ")");
    }

    public void writeAlumnoInstancia(int idAlumno, int idCurso, int anio, double nota) {
        System.out.println("AlumnoInstancia(" + idAlumno + "," + idCurso + "," + anio + "," + nota + ")");
    }

}

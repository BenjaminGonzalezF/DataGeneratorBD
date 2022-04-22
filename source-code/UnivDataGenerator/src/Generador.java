
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    Random rand = new Random(9999);

    ArrayList<String> dic_nombres_hombres = new ArrayList();
    ArrayList<String> dic_nombres_mujeres = new ArrayList();
    ArrayList<String> dic_apellidos = new ArrayList();
    ArrayList<String> dic_actores_doblaje = new ArrayList();
    ArrayList<String> dic_actores_normal = new ArrayList();
    ArrayList<String> dic_fechas_nacimiento = new ArrayList();
    ArrayList<String> dic_direcciones = new ArrayList();





    int nro_anios = 0;
    int nro_actores = 0;
    int min_nro_personas = 20;
    int max_nro_personas = 30;

    int id_persona_1 = 0;
    int id_persona_n = 0;
    int snn = 100000000;

    FileWriter fwPersonas;
    FileWriter fwActoresDoble;
    FileWriter fwActoresExtra;
    FileWriter fwActoresNormal;
    FileWriter fwManagers;
    FileWriter fwDirectores;


    public Generador() {

    }

    public void run(int _nro_anios) {
        try {
            File file;

            file = new File("./personas.csv");
            if (file.exists()) {
                file.delete();
            }
            fwPersonas = new FileWriter(file, true);

            file = new File("./actoresDoble.csv");
            if (file.exists()) {
                file.delete();
            }
            fwActoresDoble = new FileWriter(file, true);

            file = new File("./actoresExtra.csv");
            if (file.exists()) {
                file.delete();
            }
            fwActoresExtra = new FileWriter(file, true);

            file = new File("./actoresNormales.csv");
            if (file.exists()) {
                file.delete();
            }
            fwActoresNormal = new FileWriter(file, true);

            file = new File("./managers.csv");
            if (file.exists()) {
                file.delete();
            }
            fwManagers = new FileWriter(file, true);
            file = new File("./directores.csv");
            if (file.exists()) {
                file.delete();
            }
            fwDirectores = new FileWriter(file, true);




            this.nro_anios = _nro_anios;
            this.CrearDiccionarios();
            this.generarPersona();
            fwPersonas.close();
            fwActoresDoble.close();
            fwActoresExtra.close();
            fwActoresNormal.close();
            fwManagers.close();
            fwDirectores.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("No fue posible crear los archivos de salida.");
        }

    }

    public void CrearDiccionarios() {
        System.out.println("--- Carga de diccionarios ---");
        this.CargarDatos(dic_nombres_hombres, "nombres_hombres.txt");
        System.out.println("Nombres de hombres = " + dic_nombres_hombres.size());
        this.CargarDatos(dic_nombres_mujeres, "nombres_mujeres.txt");
        System.out.println("Nombres de mujeres = " + dic_nombres_mujeres.size());
        this.CargarDatos(dic_apellidos, "apellidos.txt");
        System.out.println("Apellidos = " + dic_apellidos.size());
        this.CargarDatos(dic_actores_doblaje, "especialidadDobles.txt");
        System.out.println("Especialidad = " + dic_actores_doblaje.size());
        this.CargarDatos(dic_actores_normal, "papeles.txt");
        System.out.println("Papeles = " + dic_actores_normal.size());
        this.CargarDatos(dic_fechas_nacimiento, "fechas_1990-2000.txt");
        System.out.println("Fechas de nacimiento = " + dic_fechas_nacimiento.size());
        this.CargarDatos(dic_direcciones, "direcciones.txt");
        System.out.println("Direcciones = " + dic_direcciones.size());



    }

    public void CargarDatos(ArrayList<String> list, String archivo) {
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = "./diccionarios/" + archivo;
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

    public void writeDirector(int id, String nombre, String direccion, String telefono) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "\n";
        try {
            fwDirectores.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeManager(int id, String nombre, String direccion, String telefono, String snn, String fechaNacimiento) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + snn + "," + fechaNacimiento + "\n";
        try {
            fwManagers.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeActoresDoble(int id, String nombre, String direccion, String telefono, String especialidad) {
        //System.out.println(id + "," + nombre + "," + direccion + "," + telefono + "," + especialidad + "\n");
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + especialidad + "\n";
        try {
            fwActoresDoble.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void writeActoresExtra(int id, String nombre, String direccion, String telefono, String tiempo) {
        //System.out.println(id + "," + nombre + "," + direccion + "," + telefono + "," + especialidad + "\n");
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + tiempo + "\n";
        try {
            fwActoresExtra.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeActoresNormal(int id, String nombre, String direccion, String telefono, String papel) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + papel + "\n";
        try {
            fwActoresNormal.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void generarActor(int id, String nombre, String direccion, String telefono) {
        int rnd = ThreadLocalRandom.current().nextInt(1, 4);
        switch(rnd) {
            case 1: // Doble
                String especialidad =dic_actores_doblaje.get(rand.nextInt(dic_actores_doblaje.size()));
                this.writeActoresDoble(id, nombre, direccion, telefono, especialidad);
                break;
            case 2: //Extra
                int tiempo = ThreadLocalRandom.current().nextInt(0, 80);
                this.writeActoresExtra(id, nombre, direccion, telefono, String.valueOf(tiempo));
                break;
            case 3: //Normal
                String papel = dic_actores_normal.get(rand.nextInt(dic_actores_normal.size()));
                this.writeActoresNormal(id, nombre, direccion, telefono,papel);
                break;

            default:
            }
        }

    public void generarPersona() {
        String nombre;
        int min = this.min_nro_personas;
        int max = this.max_nro_personas;
        this.nro_actores = rand.nextInt(max - min) + min;
        id_persona_1 = 1;
        id_persona_n = id_persona_1;
        for (int i = 0; i < this.nro_anios; i++) {
            int telefono = ThreadLocalRandom.current().nextInt(10000000, 99999999);
            String direccion = dic_direcciones.get(rand.nextInt(dic_direcciones.size()));
            if (rand.nextBoolean()) {
                nombre = dic_nombres_hombres.get(rand.nextInt(dic_nombres_hombres.size()));
            } else {
                nombre = dic_nombres_mujeres.get(rand.nextInt(dic_nombres_mujeres.size()));
            }
            telefono = telefono + 1;
            this.writePersona(id_persona_n, nombre, direccion, "9" + Integer.toString(telefono));
            this.elegirPersona(id_persona_n, nombre, direccion, "9" + Integer.toString(telefono));

            id_persona_n++;
        }
        id_persona_n--;
    }

    public void generarManager(int id, String nombre, String direccion, String telefono){
        String SNN = String.valueOf((this.snn + 1));
        String fechaNacimiento = dic_fechas_nacimiento.get(rand.nextInt(dic_fechas_nacimiento.size()));
        this.writeManager(id,  nombre,  direccion,  telefono, SNN, fechaNacimiento);
        this.snn =snn + 1;
    }


    public void elegirPersona(int id, String nombre, String direccion, String telefono){

        int rnd = ThreadLocalRandom.current().nextInt(1, 4);
        switch(rnd) {
            case 1:
                this.generarActor(id,  nombre,  direccion,  telefono);
                break;
            case 2:
                this.generarManager(id,  nombre,  direccion,  telefono);
                break;
            case 3:
                this.writeDirector(id,  nombre,  direccion,  telefono);
                break;

            default:
        }


    }


    public void writePersona(int id, String nombre, String direccion, String telefono) {
        //System.out.println("Profesor(" + id + "," + nombre + "," + titulo + "," + jerarquia + ")");
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "\n";
        try {
            fwPersonas.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void writeActor(int id, String nombre, String direccion, String telefono) {

        //System.out.println("Profesor(" + id + "," + nombre + "," + titulo + "," + jerarquia + ")");
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "\n";
        try {
            fwPersonas.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public double redondear(double val, int places) {
        long factor = (long) Math.pow(10, places);
        val = val * factor;
        long tmp = Math.round(val);
        return (double) tmp / factor;
    }

}

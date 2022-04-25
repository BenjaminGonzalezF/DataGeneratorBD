
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
 * @author Benjamin G
 */

/*
 ESQUEMA DE LA BASE DE DATOS

Persona(id_persona[PK],nombre,direccion,telefono)
Produccion(id_produccion[PK],fecha_estreno,censura,nombre,categoria,ref_director[FK])
Locacion(id_direccion,direccion_locacion)
grabadaEn(ref_produccion[FK],ref_locacion[FK])
Pelicula(id_produccion[PK],ref_pelicula[FK])
Serie(ref_produccion[FK])
Secuela(id_secuela[PK],ref_pelicula[FK])
Temporada(id_temporada[PK],nro_capitulos, ref_serie[FK])
Director(id_persona[PK],nombre,direccion,telefono,ref_produccion[FK])
Manager(id_persona[PK],nombre,direccion,telefono,snn,fecha_nacimiento)
Actor(id_persona[PK],nombre,direccion,telefono,ref_manager[FK])
Doble(id_persona[PK],nombre,direccion,telefono,especialidad)
Extra(id_persona[PK],nombre,direccion,telefono,tiempo)
Normal(id_persona[PK],nombre,direccion,telefono,papel)
aCargo(ref_produccion[FK],ref_director[FK])
trabajan(ref_persona[FK],ref_produccion[FK])
Bodega(id_bodega[PK],direccion_bodega)
abastece(ref_serie[FK],ref_bodega[FK])
Efecto_especial(id_bodega[PK],direccionbodega,elemento)
Ropa(id_bodega[PK],direccionbodega,vestuario)
Maquinaria(id_bodega[PK],direccionbodega,implementacion)
Insumo(id_bodega[PK],direccionbodega,artefacto)
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
    ArrayList<String> dic_producciones = new ArrayList();

    ArrayList<Integer> id_managers = new ArrayList();

    int nro_anios = 0;
    int nro_actores = 0;
    int min_nro_personas = 20;
    int max_nro_personas = 30;
    int id_pelicula = 1;
    int id_serie = 1;
    int id_persona_1 = 0;
    int id_persona_n = 0;
    int snn = 100000000;

    FileWriter fwPersonas;
    FileWriter fwActores;
    FileWriter fwActoresDoble;
    FileWriter fwActoresExtra;
    FileWriter fwActoresNormal;
    FileWriter fwManagers;
    FileWriter fwDirectores;
    FileWriter fwProducciones;
    FileWriter fwPeliculas;
    FileWriter fwSeries;
    FileWriter fwAcargo;
    FileWriter fwTrabajan;




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

            file = new File("./actores.csv");
            if (file.exists()) {
                file.delete();
            }
            fwActores = new FileWriter(file, true);

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

            file = new File("./producciones.csv");
            if (file.exists()) {
                file.delete();
            }
            fwProducciones = new FileWriter(file, true);

            file = new File("./peliculas.csv");
            if (file.exists()) {
                file.delete();
            }
            fwPeliculas = new FileWriter(file, true);

            file = new File("./series.csv");
            if (file.exists()) {
                file.delete();
            }
            fwSeries = new FileWriter(file, true);

            file = new File("./acargo.csv");
            if (file.exists()) {
                file.delete();
            }
            fwAcargo = new FileWriter(file, true);

            file = new File("./trabajan.csv");
            if (file.exists()) {
                file.delete();
            }
            fwTrabajan = new FileWriter(file, true);

            this.nro_anios = _nro_anios;
            this.CrearDiccionarios();
            this.generarPersona();
            this.generarProducciones();

            fwPersonas.close();
            fwActores.close();
            fwActoresDoble.close();
            fwActoresExtra.close();
            fwActoresNormal.close();
            fwManagers.close();
            fwDirectores.close();
            fwProducciones.close();
            fwPeliculas.close();
            fwSeries.close();
            fwAcargo.close();
            fwTrabajan.close();

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
        this.CargarDatos(dic_producciones, "producciones.txt");
        System.out.println("Producciones = " + dic_producciones.size());



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

    public void writeDirector(int id, String nombre, String direccion, String telefono, String ref_produccion) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + ref_produccion + "\n";
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

    public void writeActoresDoble(int id, String nombre, String direccion, String telefono, Integer ref_manager, String especialidad) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + ref_manager + "," + especialidad + "\n";
        try {
            fwActoresDoble.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void writeActoresExtra(int id, String nombre, String direccion, String telefono, Integer ref_manager, String tiempo) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + ref_manager + "," + tiempo + "\n";
        try {
            fwActoresExtra.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeActoresNormal(int id, String nombre, String direccion, String telefono, Integer ref_manager, String papel) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + ref_manager + "," + papel + "\n";
        try {
            fwActoresNormal.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void generarActor(int id, String nombre, String direccion, String telefono) {
        int rnd = ThreadLocalRandom.current().nextInt(1, 4);
        Integer ref_manager = id_managers.get(ThreadLocalRandom.current().nextInt(0, id_managers.size()));
        this.writeActor(id, nombre, direccion, telefono,ref_manager);
        switch(rnd) {
            case 1: // Doble
                String especialidad =dic_actores_doblaje.get(rand.nextInt(dic_actores_doblaje.size()));
                this.writeActoresDoble(id, nombre, direccion, telefono,ref_manager, especialidad);
                break;
            case 2: //Extra
                int tiempo = ThreadLocalRandom.current().nextInt(0, 80);
                this.writeActoresExtra(id, nombre, direccion, telefono,ref_manager, String.valueOf(tiempo));
                break;
            case 3: //Normal
                String papel = dic_actores_normal.get(rand.nextInt(dic_actores_normal.size()));
                this.writeActoresNormal(id, nombre, direccion, telefono,ref_manager,papel);
                break;

            default:
            }
        }

    public void writeProducciones(int id, String fecha, String censura, String nombre, String categoria, int ref_director) {
        String linea = id  + "," + fecha + "," + censura + "," +nombre + "," + categoria + "," + ref_director + "\n";
        try {
            fwProducciones.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writePelicula(int id, String fecha, String censura, String nombre, String categoria, int ref_director , int ref_produccion) {

        String linea = id  + "," + fecha + "," + censura + "," +nombre + "," + categoria+ "," + ref_director + "," + ref_produccion + "\n";
        try {
            fwPeliculas.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeSerie(int id, String fecha, String censura, String nombre, String categoria, int ref_director, int ref_produccion) {

        String linea = id  + "," + fecha + "," + censura + "," +nombre + "," + categoria+ "," + ref_director + "," + ref_produccion +"\n";
        try {
            fwSeries.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void generarProducciones() {
        int id;
        String fecha;
        ArrayList<String> censuras = new ArrayList();

        censuras.add("No");
        censuras.add("Si");
        String censura;

        ArrayList<String> categorias = new ArrayList();
        categorias.add("Drama");
        categorias.add("Accion");
        categorias.add("Menores de edad");
        categorias.add("Aventura");
        String categoria;

        String nombre = "";

        int ref_director = 0;

        for (int i = 1; i < this.dic_producciones.size() + 1; i++) {
            id = i;
            fecha = dic_fechas_nacimiento.get(rand.nextInt(dic_fechas_nacimiento.size()));
            censura = censuras.get(ThreadLocalRandom.current().nextInt(0, censuras.size()));
            categoria = categorias.get(ThreadLocalRandom.current().nextInt(0, categorias.size()));
            nombre = dic_producciones.get(rand.nextInt(dic_producciones.size()));;
            ref_director = i;


            this.writeProducciones( id,  fecha,  censura,  nombre,  categoria,ref_director);

            //Pelicula
            if(rand.nextBoolean()){
                this.writePelicula( id_pelicula,  fecha,  censura,  nombre,  categoria, ref_director,id);
                id_pelicula+=1;

            }
            //Serie
            else{
                this.writeSerie( id_serie,  fecha,  censura,  nombre,  categoria, ref_director,id);
                id_serie+=1;
            }


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
            //Primero se generan si o si 130 directores 1 para cada pelicula
            if(i <130){
                this.writeDirector(id_persona_n,nombre,direccion,"9" + Integer.toString(telefono), String.valueOf(i+1));
                this.writeAcargo( String.valueOf(i+1),  String.valueOf(i+1));
            }
            else {
                this.elegirPersona(id_persona_n, nombre, direccion, "9" + Integer.toString(telefono));
            }

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

        int rnd = ThreadLocalRandom.current().nextInt(1, 3);
        switch(rnd) {
            case 1://Actor
                if (id_managers.size() > 1) {
                    this.generarActor(id, nombre, direccion, telefono);
                }
                else{
                    this.generarManager(id,  nombre,  direccion,  telefono);
                    id_managers.add(id);
                }
                break;
            case 2://Manager
                this.generarManager(id,  nombre,  direccion,  telefono);
                id_managers.add(id);
                break;

            default:

        }
            this.writeTrabaja(id,ThreadLocalRandom.current().nextInt(1, 130));


    }

    public void writeAcargo(String ref_produccion, String ref_director) {
        String linea = ref_produccion + "," + ref_director + "\n";
        try {
            fwAcargo.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeTrabaja(int id_persona_n,int ref_produccion) {
        String linea = id_persona_n + "," + ref_produccion + "\n";
        try {
            fwTrabajan.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writePersona(int id, String nombre, String direccion, String telefono) {
        String linea = id + "," + nombre + "," + direccion + "," + telefono + "\n";
        try {
            fwPersonas.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void writeActor(int id, String nombre, String direccion, String telefono, int ref_manager) {

        String linea = id + "," + nombre + "," + direccion + "," + telefono + "," + ref_manager + "\n";
        try {
            fwActores.write(linea);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

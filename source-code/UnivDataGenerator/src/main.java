/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renzo
 */
public class main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("UnivDataGenerator permite generar archivos CSV con información de una universidad.");
            System.out.println("Uso:");
            System.out.println("java -jar UnivDataGenerator.jar N ");
            System.out.println("Donde N es un número entero que indica el número de años de simulación.");
        } else {
            try {
                int N = Integer.parseInt(args[0]);
                Generador gen = new Generador();
                System.out.println("Iniciando generación con N = " + N);
                gen.run(N);
                System.out.println("");
                System.out.println("El proceso de generación concluyó satisfactoriamente.");
            } catch (Exception ex) {
                System.out.println("Error:" + ex.getMessage());
            }
        }
    }
}

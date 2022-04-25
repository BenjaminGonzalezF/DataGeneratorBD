
/**
 *
 * @author Benjamin G
 */
public class main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("UnivDataGenerator permite generar archivos CSV con información de una productora");
            System.out.println("Uso:");
            System.out.println("java -jar source-code.jar N ");
            System.out.println("Donde N es un número entero que indica la cantidad de personas a generar.");
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

import java.io.*;
import java.util.*;

public class MainF1 {

    static final String FICHERO = "pilotos_f1.dat";
    static List<Piloto> pilotos = new ArrayList<>();

    public static void main(String[] args) {

        cargarPilotos();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--- MENÚ PILOTOS F1 (BINARIO) ---");
            System.out.println("1. Mostrar pilotos");
            System.out.println("2. Añadir piloto");
            System.out.println("3. Buscar piloto");
            System.out.println("4. Guardar pilotos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> mostrarPilotos();
                case 2 -> anadirPiloto();
                case 3 -> buscarPiloto();
                case 4 -> guardarPilotos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    public static void cargarPilotos() {

        File file = new File(FICHERO);

        if (!file.exists()) {
            System.out.println("No existe fichero binario. Se creará al guardar.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                Piloto p = (Piloto) in.readObject();
                pilotos.add(p);
            }

        } catch (EOFException e) {
            System.out.println("Pilotos cargados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer fichero: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Clase Piloto no encontrada.");
        }
    }

    public static void mostrarPilotos() {

        System.out.println("--- LISTA DE PILOTOS ---");

        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados.");
            return;
        }

        for (Piloto p : pilotos) {
            System.out.println(p);
        }
    }

    public static void anadirPiloto() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        for (Piloto p : pilotos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ese piloto ya existe. No se puede añadir.");
                return;
            }
        }

        System.out.print("Escudería: ");
        String escuderia = sc.nextLine();

        System.out.print("Puntos: ");
        int puntos = sc.nextInt();

        pilotos.add(new Piloto(nombre, escuderia, puntos));
        System.out.println("Piloto añadido correctamente.");
    }

    public static void buscarPiloto() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del piloto: ");
        String nombre = sc.nextLine();

        for (Piloto p : pilotos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Piloto encontrado:");
                System.out.println(p);
                return;
            }
        }

        System.out.println("Piloto no encontrado.");
    }

    public static void guardarPilotos() {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO))) {

            for (Piloto p : pilotos) {
                out.writeObject(p);
            }

            System.out.println("Pilotos guardados correctamente en binario.");

        } catch (IOException e) {
            System.out.println("Error al guardar fichero: " + e.getMessage());
        }
    }
}

import java.util.Scanner;
/**
 * Clase Main
 * Interfaz de consola interactiva para la aplicación Pizza-Track.
 */
public class Main {
    public static void main(String[] args) {
        GestionPedidos gestion = new GestionPedidos();
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        System.out.println("=================================================");
        System.out.println("   === BIENVENIDO A PIZZA-TRACK (SISTEMA DE PEDIDOS) ===");
        System.out.println("=================================================");
        while (opcion != 0) {
            imprimirMenu();
            System.out.print("Seleccione una opción: ");
            
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    continue;
                }
                opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1:
                        registrarNuevaPizza(gestion, scanner);
                        break;
                    case 2:
                        ejecutarUndo(gestion);
                        break;
                    case 3:
                        ejecutarRedo(gestion);
                        break;
                    case 4:
                        mostrarPedidoActual(gestion);
                        break;
                    case 5:
                        gestion.mostrarEstadoPilas();
                        break;
                    case 0:
                        System.out.println("\n[!] Saliendo del sistema Pizza-Track. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("\n[X] Opción inválida. Por favor seleccione una opción del 0 al 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[X] Error: Por favor ingrese un número entero válido.");
            } catch (Exception e) {
                System.out.println("\n[X] Ha ocurrido un error: " + e.getMessage());
            }
        }
        scanner.close();
    }
    private static void imprimirMenu() {
        System.out.println("\n----------------- MENÚ PRINCIPAL -----------------");
        System.out.println("  1. Registrar Pizza (Escribir - Push)");
        System.out.println("  2. Deshacer último pedido (Undo)");
        System.out.println("  3. Rehacer pedido deshecho (Redo)");
        System.out.println("  4. Mostrar Pedido Actual (Peek)");
        System.out.println("  5. Ver Estado Completo de las Pilas");
        System.out.println("  0. Salir");
        System.out.println("--------------------------------------------------");
    }
    private static void registrarNuevaPizza(GestionPedidos gestion, Scanner scanner) {
        System.out.println("\n--- REGISTRO DE NUEVA PIZZA ---");
        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.print("Ingrese el nombre de la pizza: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("[X] El nombre no puede estar vacío.");
            }
        }
        String[] ingredientes = new String[3];
        System.out.println("Ingrese exactamente 3 ingredientes:");
        for (int i = 0; i < 3; i++) {
            String ingrediente = "";
            while (ingrediente.isEmpty()) {
                System.out.print("  Ingrediente " + (i + 1) + ": ");
                ingrediente = scanner.nextLine().trim();
                if (ingrediente.isEmpty()) {
                    System.out.println("  [X] El ingrediente no puede estar vacío.");
                }
            }
            ingredientes[i] = ingrediente;
        }
        Pizza registrada = gestion.registrarPedido(nombre, ingredientes);
        System.out.println("\n[✓] ¡Pedido registrado con éxito en la Pila Principal!");
        System.out.println("    --> " + registrada);
    }
    private static void ejecutarUndo(GestionPedidos gestion) {
        System.out.println("\n--- OPERACIÓN: DESHACER (UNDO) ---");
        Pizza deshecha = gestion.deshacer();
        if (deshecha != null) {
            System.out.println("[✓] Se ha deshecho el pedido:");
            System.out.println("    <-- " + deshecha);
            System.out.println("    (Movido a la Pila Secundaria / Redo)");
        } else {
            System.out.println("[!] No hay pedidos registrados en la Pila Principal para deshacer.");
        }
    }
    private static void ejecutarRedo(GestionPedidos gestion) {
        System.out.println("\n--- OPERACIÓN: REHACER (REDO) ---");
        Pizza rehecha = gestion.rehacer();
        if (rehecha != null) {
            System.out.println("[✓] Se ha recuperado el pedido:");
            System.out.println("    --> " + rehecha);
            System.out.println("    (Devuelto a la Pila Principal / Undo)");
        } else {
            System.out.println("[!] No hay pedidos en la Pila Secundaria para rehacer.");
        }
    }
    private static void mostrarPedidoActual(GestionPedidos gestion) {
        System.out.println("\n--- PEDIDO ACTUAL EN PRODUCCIÓN (PEEK) ---");
        Pizza actual = gestion.obtenerPedidoActual();
        if (actual != null) {
            System.out.println("[★] Pedido en el TOPE listo para producción:");
            System.out.println("    " + actual);
        } else {
            System.out.println("[!] No hay pedidos activos en la cola de producción.");
        }
    }
}

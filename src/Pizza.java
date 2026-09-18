import java.util.Arrays;
/**
 * Clase Pizza
 * Representa el modelo de datos para cada pedido en el sistema Pizza-Track.
 * Contiene el nombre de la pizza y un arreglo de tamaño fijo (3) para sus ingredientes.
 */
public class Pizza {
    private String nombre;
    private String[] ingredientes; // Arreglo de tamaño fijo (3 ingredientes)
    /**
     * Constructor de la clase Pizza.
     * @param nombre Nombre de la pizza.
     * @param ingredientes Arreglo de exactamente 3 ingredientes.
     * @throws IllegalArgumentException Si el arreglo no contiene exactamente 3 elementos.
     */
    public Pizza(String nombre, String[] ingredientes) {
        if (ingredientes == null || ingredientes.length != 3) {
            throw new IllegalArgumentException("La pizza debe tener exactamente 3 ingredientes.");
        }
        this.nombre = nombre;
        // Se realiza una copia defensiva del arreglo de 3 ingredientes
        this.ingredientes = Arrays.copyOf(ingredientes, 3);
    }
    public String getNombre() {
        return nombre;
    }
    public String[] getIngredientes() {
        return Arrays.copyOf(ingredientes, 3);
    }
    @Override
    public String toString() {
        return "Pizza: \"" + nombre + "\" | Ingredientes: [" + String.join(", ", ingredientes) + "]";
    }
}

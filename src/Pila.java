/**
 * Clase Pila (Manual - Basada en Lista Ligada)
 * Implementa el comportamiento LIFO (Last In, First Out) utilizando punteros de memoria.
 * NO utiliza la librería java.util.Stack de Java.
 */
public class Pila {
    private Nodo tope;
    private int tamanio;
    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }
    /**
     * Inserta un objeto Pizza en el tope de la pila.
     */
    public void push(Pizza pizza) {
        Nodo nuevoNodo = new Nodo(pizza);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        tamanio++;
    }
    /**
     * Retira y devuelve el objeto Pizza ubicado en el tope de la pila.
     */
    public Pizza pop() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede realizar pop(): La pila se encuentra vacía.");
        }
        Pizza pizzaExtraida = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return pizzaExtraida;
    }
    /**
     * Visualiza la Pizza en el tope de la pila sin retirarla.
     */
    public Pizza peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return tope.getDato();
    }
    /**
     * Comprueba si la pila no contiene elementos.
     */
    public boolean isEmpty() {
        return tope == null;
    }
    public int getTamano() {
        return tamanio;
    }
    /**
     * Imprime en consola la secuencia completa de la pila desde el tope hasta la base.
     */
    public void mostrarPila() {
        if (isEmpty()) {
            System.out.println("   (Pila vacía)");
            return;
        }
        
        Nodo actual = tope;
        int nivel = 1;
        while (actual != null) {
            String etiquetaTope = (actual == tope) ? " [TOPE]" : "";
            System.out.println("   " + nivel + ". " + actual.getDato() + etiquetaTope);
            actual = actual.getSiguiente();
            nivel++;
        }
    }
}

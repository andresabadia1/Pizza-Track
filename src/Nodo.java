/**
 * Clase Nodo
 * Representa la unidad fundamental (nodo) de la lista ligada que compone la Pila Manual.
 * Contiene el dato (un objeto Pizza) y la referencia (puntero) al siguiente nodo en la pila.
 */
public class Nodo {
    private Pizza dato;
    private Nodo siguiente; // Puntero al nodo inferior
    /**
     * Constructor del Nodo.
     * @param dato Objeto Pizza a almacenar.
     */
    public Nodo(Pizza dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    public Pizza getDato() {
        return dato;
    }
    public void setDato(Pizza dato) {
        this.dato = dato;
    }
    public Nodo getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}


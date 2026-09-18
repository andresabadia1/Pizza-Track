/**
 * Clase GestionPedidos
 * Actúa como la clase de control del sistema Pizza-Track.
 * Coordina dos pilas manuales basadas en listas ligadas:
 *  - Pila Principal (Undo): Almacena los pedidos activos para permitir deshacer.
 *  - Pila Secundaria (Redo): Almacena temporalmente los pedidos deshechos para permitir rehacer.
 */
public class GestionPedidos {
    private Pila pilaPrincipal;  // Pila de Pedidos Activos (Undo)
    private Pila pilaSecundaria; // Pila de Pedidos Deshechos (Redo)
    public GestionPedidos() {
        this.pilaPrincipal = new Pila();
        this.pilaSecundaria = new Pila();
    }
    /**
     * Registra un nuevo pedido y limpia la pila de rehacer.
     */
    public Pizza registrarPedido(String nombre, String[] ingredientes) {
        Pizza nuevaPizza = new Pizza(nombre, ingredientes);
        pilaPrincipal.push(nuevaPizza);
        
        // Al registrar una nueva pizza, se reinicia la pila secundaria (Redo)
        if (!pilaSecundaria.isEmpty()) {
            this.pilaSecundaria = new Pila();
        }
        
        return nuevaPizza;
    }
    /**
     * Operación Deshacer (Undo): pop() en principal -> push() en secundaria.
     */
    public Pizza deshacer() {
        if (pilaPrincipal.isEmpty()) {
            return null;
        }
        Pizza pedidoDeshecho = pilaPrincipal.pop();
        pilaSecundaria.push(pedidoDeshecho);
        return pedidoDeshecho;
    }
    /**
     * Operación Rehacer (Redo): pop() en secundaria -> push() en principal.
     */
    public Pizza rehacer() {
        if (pilaSecundaria.isEmpty()) {
            return null;
        }
        Pizza pedidoRecuperado = pilaSecundaria.pop();
        pilaPrincipal.push(pedidoRecuperado);
        return pedidoRecuperado;
    }
    /**
     * Muestra la pizza en el tope mediante peek().
     */
    public Pizza obtenerPedidoActual() {
        if (pilaPrincipal.isEmpty()) {
            return null;
        }
        return pilaPrincipal.peek();
    }
    public boolean tienePedidosActivos() {
        return !pilaPrincipal.isEmpty();
    }
    public boolean tienePedidosParaRehacer() {
        return !pilaSecundaria.isEmpty();
    }
    public Pila getPilaPrincipal() {
        return pilaPrincipal;
    }
    public Pila getPilaSecundaria() {
        return pilaSecundaria;
    }
    public void mostrarEstadoPilas() {
        System.out.println("\n================ ESTADO DE LAS PILAS ================");
        System.out.println("--- PILA PRINCIPAL (PEDIDOS ACTIVOS / UNDO) ---");
        pilaPrincipal.mostrarPila();
        
        System.out.println("\n--- PILA SECUNDARIA (PEDIDOS DESHECHOS / REDO) ---");
        pilaSecundaria.mostrarPila();
        System.out.println("=====================================================\n");
    }
}

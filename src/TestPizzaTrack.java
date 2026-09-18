/**
 * Pruebas de verificación automatizadas para el sistema Pizza-Track.
 */
public class TestPizzaTrack {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS AUTOMATIZADAS DE PIZZA-TRACK ===");
        
        GestionPedidos gestion = new GestionPedidos();
        
        // Test 1: Verificar pila inicialmente vacía
        assert gestion.getPilaPrincipal().isEmpty() : "Error: La pila principal debe iniciar vacía";
        assert gestion.obtenerPedidoActual() == null : "Error: Peek en pila vacía debe retornar null";
        System.out.println("✔ Test 1: Pila vacía verificado correctamente.");
        // Test 2: Registrar 3 pedidos
        String[] ing1 = {"Queso", "Jamón", "Piña"};
        String[] ing2 = {"Queso", "Pepperoni", "Orégano"};
        String[] ing3 = {"Queso", "Champiñones", "Pimientos"};
        gestion.registrarPedido("Hawayana", ing1);
        gestion.registrarPedido("Pepperoni", ing2);
        gestion.registrarPedido("Vegetariana", ing3);
        assert gestion.getPilaPrincipal().getTamano() == 3 : "Error: Tamaño debe ser 3";
        assert gestion.obtenerPedidoActual().getNombre().equals("Vegetariana") : "Error: El tope debe ser Vegetariana";
        System.out.println("✔ Test 2: Inserción (push) de 3 pedidos verificado.");
        // Test 3: Deshacer 2 pedidos (Undo)
        Pizza undo1 = gestion.deshacer();
        assert undo1 != null && undo1.getNombre().equals("Vegetariana") : "Error al deshacer Vegetariana";
        
        Pizza undo2 = gestion.deshacer();
        assert undo2 != null && undo2.getNombre().equals("Pepperoni") : "Error al deshacer Pepperoni";
        assert gestion.obtenerPedidoActual().getNombre().equals("Hawayana") : "Error: El pedido actual debe ser Hawayana";
        assert gestion.getPilaSecundaria().getTamano() == 2 : "Error: Pila secundaria debe tener 2 pizzas deshechas";
        System.out.println("✔ Test 3: Deshacer (Undo) de 2 pedidos verificado.");
        // Test 4: Rehacer 1 pedido (Redo)
        Pizza redo1 = gestion.rehacer();
        assert redo1 != null && redo1.getNombre().equals("Pepperoni") : "Error al rehacer Pepperoni";
        assert gestion.obtenerPedidoActual().getNombre().equals("Pepperoni") : "Error: Tope debe ser Pepperoni";
        assert gestion.getPilaSecundaria().getTamano() == 1 : "Error: Pila secundaria debe tener 1 pedido";
        System.out.println("✔ Test 4: Rehacer (Redo) verificado.");
        // Test 5: Registrar nuevo pedido invalida Pila Secundaria (Redo)
        String[] ing4 = {"Mozzarella", "Gorgonzola", "Parmesano"};
        gestion.registrarPedido("Cuatro Quesos", ing4);
        assert gestion.getPilaSecundaria().isEmpty() : "Error: La pila secundaria debe haberse vaciado tras nuevo registro";
        assert gestion.rehacer() == null : "Error: Rehacer tras nuevo registro debe ser null";
        System.out.println("✔ Test 5: Invalidación de pila secundaria tras nuevo registro verificado.");
        // Test 6: Arreglo de ingredientes de tamaño fijo (3)
        Pizza p = gestion.obtenerPedidoActual();
        assert p.getIngredientes().length == 3 : "Error: El arreglo de ingredientes debe ser de tamaño 3";
        System.out.println("✔ Test 6: Validación de arreglo de 3 ingredientes verificado.");
        System.out.println("\n🎉 ¡TODAS LAS PRUEBAS AUTOMATIZADAS SE EJECUTARON CON ÉXITO!");
    }
}


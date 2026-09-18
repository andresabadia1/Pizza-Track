# 🍕 Pizza-Track

Simulador de gestión de pedidos para una pizzería, desarrollado en Java. El sistema implementa una funcionalidad de **Deshacer/Rehacer (Undo/Redo)** utilizando dos pilas manuales construidas desde cero con listas ligadas.

## 🎯 Objetivo

Aplicar el concepto de **pila (estructura LIFO)** combinando arreglos y listas ligadas, mediante un simulador de gestión de pedidos en consola, sin utilizar la librería `java.util.Stack` de Java.

## 🏗️ Arquitectura del proyecto

El sistema se compone de 6 clases:

| Clase | Responsabilidad |
|---|---|
| `Pizza.java` | Modelo de datos: nombre + arreglo fijo de 3 ingredientes. |
| `Nodo.java` | Unidad básica de la lista ligada (dato + puntero al siguiente nodo). |
| `Pila.java` | Estructura de pila manual (LIFO), implementada con lista ligada. No usa `java.util.Stack`. |
| `GestionPedidos.java` | Clase de control: coordina la Pila Principal (Undo) y la Pila Secundaria (Redo). |
| `Main.java` | Interfaz de consola interactiva con el menú de opciones. |
| `TestPizzaTrack.java` | Suite de pruebas automatizadas (usa `assert`). |

### Flujo Undo / Redo

- **Pila Principal**: almacena los pedidos activos. Cada pizza registrada se apila aquí con `push()`.
- **Pila Secundaria**: almacena temporalmente los pedidos deshechos, para poder recuperarlos con `push()`/`pop()`.
- Al **deshacer**: se hace `pop()` en la Pila Principal y `push()` en la Secundaria.
- Al **rehacer**: se hace `pop()` en la Pila Secundaria y `push()` en la Principal.
- Al **registrar un nuevo pedido**, la Pila Secundaria se reinicia (se pierde el historial de "rehacer" anterior).

### Métodos obligatorios de la pila

- `push(Pizza pizza)`: inserta un pedido en el tope.
- `pop()`: retira y devuelve el pedido del tope.
- `peek()`: consulta el pedido del tope sin retirarlo.
- `isEmpty()`: valida si la pila está vacía.

## 🖥️ Menú de la aplicación

```
----------------- MENÚ PRINCIPAL -----------------
  1. Registrar Pizza (Escribir - Push)
  2. Deshacer último pedido (Undo)
  3. Rehacer pedido deshecho (Redo)
  4. Mostrar Pedido Actual (Peek)
  5. Ver Estado Completo de las Pilas
  0. Salir
--------------------------------------------------
```

## ▶️ Cómo ejecutar el proyecto

### Requisitos
- Tener instalado el JDK (Java 11 o superior).

### Desde terminal

```bash
cd src
javac *.java
java Main
```

### Ejecutar las pruebas automatizadas

Las pruebas usan `assert`, por lo que es necesario habilitar las aserciones con el flag `-ea`:

```bash
cd src
javac *.java
java -ea TestPizzaTrack
```

### Desde Visual Studio Code

1. Instalar el **Extension Pack for Java**.
2. Abrir la carpeta raíz del proyecto (`File > Open Folder...`).
3. Abrir `Main.java` y hacer clic en el enlace **Run** que aparece sobre el método `main`.

## ✅ Pruebas automatizadas

`TestPizzaTrack.java` valida:

1. La pila inicia vacía.
2. Inserción (`push`) de múltiples pedidos.
3. Deshacer (`Undo`) de varios pedidos consecutivos.
4. Rehacer (`Redo`) de un pedido.
5. Invalidación de la pila secundaria tras registrar un nuevo pedido.
6. El arreglo de ingredientes siempre tiene tamaño fijo (3).

## 👤 Autor

Daiver Andrés — Estructura de Datos, Segundo Semestre, IU Digital.

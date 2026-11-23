# AirportFlowManager — Simulador de gestión aeroportuaria

**Proyecto académico de Estructuras de Datos**, desarrollado en Java.

## Descripción

**AirportFlowManager** simula el flujo de pasajeros y maletas dentro de un aeropuerto, conectando dos procesos:

1. **Check-in (LP):** distribuye pasajeros entre mostradores, atiende sus filas y permite cerrar mostradores con redistribución de pasajeros.
2. **Equipaje (LM):** las maletas de los pasajeros atendidos se asignan a sus vuelos y después se procesan.

Es una **aplicación de consola**: no utiliza interfaz gráfica ni base de datos.

## Funcionalidades

- Cargar pasajeros y maletas de ejemplo.
- Consultar el estado de las filas de check-in y del equipaje por vuelo.
- Distribuir un número aleatorio de pasajeros entre mostradores, priorizando la fila más corta.
- Atender al siguiente pasajero de un mostrador y dirigir sus maletas al vuelo correspondiente.
- Cerrar un mostrador y redistribuir sus pasajeros sin perder el orden.
- Procesar la siguiente maleta de un vuelo determinado.
- Ejecutar una simulación automática de atención y equipaje.

## Estructuras de datos implementadas

El código incluye implementaciones propias de estructuras genéricas:

| Clase | Función |
|---|---|
| `ListaArreglo<T>` | Lista de capacidad definida basada en arreglo, con operaciones de inserción, eliminación y comparación. |
| `ListaEnlazada<T>` y `Nodo<T>` | Lista doblemente enlazada con enlaces anterior y siguiente. |
| `Proceso<T>` | Cola de atención con inserción al final y extracción desde el inicio. |
| `ListaProcesosT<T>` | Lista doblemente enlazada de procesos identificados, utilizada para los mostradores y los vuelos. |
| `Pasajero` y `Maleta` | Representación de pasajeros, sus vuelos y sus equipajes. |
| `AirportFlowManager` | Reglas del simulador: distribución, atención y procesamiento. |
| `Pruebas` | Casos de prueba automatizados incluidos en el código. |

Las estructuras son reutilizables y la lógica específica del aeropuerto está separada de su implementación.

## Tecnologías

- **Java** (JDK 8 o superior).
- **Programación orientada a objetos**, genéricos y `Comparator`.
- **Estructuras de datos:** arreglos, nodos, listas doblemente enlazadas y colas.
- **Consola** para interacción con el usuario.

## Organización

```text
AirportFlowManager_Ed/
├── README.md
├── .gitignore
└── src/
    └── MyClass2.java
```

El archivo `MyClass2.java` conserva la organización original de la entrega: contiene las clases del proyecto, el simulador `AirportFlowManager` y el ejecutor de pruebas `Pruebas`.

## Compilar y ejecutar

Con un JDK instalado, desde la carpeta principal del repositorio:

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/MyClass2.java
java -cp out AirportFlowManager
```

### Opciones del menú principal

| Opción | Acción |
|---|---|
| `1` | Cargar datos de ejemplo |
| `2` | Ver estado del aeropuerto |
| `3` | Pasar pasajeros a mostradores |
| `4` | Atender a un pasajero |
| `5` | Cerrar un mostrador |
| `6` | Procesar una maleta |
| `7` | Ejecutar simulación automática |
| `0` | Salir |

## Pruebas automáticas

Para ejecutar los casos de prueba incluidos en el mismo archivo:

```bash
java -cp out Pruebas
```

Las pruebas cubren, entre otras operaciones, la creación y orden de mostradores, el criterio de fila más corta, la atención de pasajeros, el cierre de mostradores, la gestión de maletas y la eliminación segura de vuelos vacíos.

**Verificación efectuada:** el código original se compiló usando **OpenJDK 21** y el ejecutor `Pruebas` finalizó con **42 pruebas correctas y 0 fallidas**. Además, se comprobó que el menú principal inicia y acepta la opción de salida.

> La compilación y estas pruebas no equivalen a una auditoría exhaustiva de todas las entradas y situaciones posibles.

## Autoría y propósito

Proyecto académico de la asignatura **Estructuras de Datos**. El material entregado identifica como integrantes a **Christian Guerrero y Carlos Lema**. Se publica para documentar la implementación de estructuras genéricas aplicadas a la simulación de procesos aeroportuarios.

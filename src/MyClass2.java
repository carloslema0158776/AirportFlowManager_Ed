import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// COMPARADORES

class CompararMaletas implements Comparator<Maleta> {

    public int compare(Maleta m1, Maleta m2) {
        if (m1.getCodigo() < m2.getCodigo()) return -1;
        if (m1.getCodigo() == m2.getCodigo()) return 0;
        return 1;
    }
}

class CompararPasajeros implements Comparator<Pasajero> {

    public int compare(Pasajero p1, Pasajero p2) {
        if (p1.getCantidadMaletas() < p2.getCantidadMaletas()) return -1;
        if (p1.getCantidadMaletas() == p2.getCantidadMaletas()) return 0;
        return 1;
    }
}

// LISTA ARREGLO

class ListaArreglo<T> {

    private T[] A;
    private int n;
    private int max;

    @SuppressWarnings("unchecked")
    public ListaArreglo(int max) {
        A = (T[]) new Object[max];
        n = 0;
        this.max = max;
    }

    public void addLast(T valor) {
        if (n == max) return;

        A[n] = valor;
        n++;
    }

    public void removeFirst() {
        if (n == 0) return;

        for (int i = 0; i < n - 1; i++) {
            A[i] = A[i + 1];
        }

        n--;
        A[n] = null;
    }

    public void addFirst(T valor) {
        if (n == max) return;

        for (int i = n; i > 0; i--) {
            A[i] = A[i - 1];
        }

        A[0] = valor;
        n++;
    }

    public void removeLast() {
        if (n == 0) return;

        n--;
        A[n] = null;
    }

    public T getValor(int indice) {
        return A[indice];
    }

    public int getCantidad() {
        return n;
    }

    public void eliminar(int indice) {
        if (indice < 0 || indice >= n) return;

        for (int i = indice; i < n - 1; i++) {
            A[i] = A[i + 1];
        }

        n--;
        A[n] = null;
    }

    public ListaArreglo<T> buscarMayores(T valor, Comparator<T> c) {

        ListaArreglo<T> N = new ListaArreglo<T>(this.max);

        for (int i = 0; i < this.n; i++) {
            if (c.compare(this.getValor(i), valor) > 0) {
                N.addLast(this.getValor(i));
            }
        }

        return N;
    }

    public String toString() {
        String s = "";

        for (int i = 0; i < n; i++) {
            s = s + A[i];

            if (i < n - 1) {
                s = s + ", ";
            }
        }

        return s;
    }
}

// CLASE PASAJERO

class Pasajero {

    private String nombre;
    private int cantidadMaletas;

    // ID Y VUELO
    private String id;
    private String vuelo;

    public Pasajero(String id, String nombre, String vuelo, int cantidadMaletas) {
        this.id = id;
        this.nombre = nombre;
        this.vuelo = vuelo;
        this.cantidadMaletas = cantidadMaletas;
    }

    public String getId() {
        return id;
    }

    public String getVuelo() {
        return vuelo;
    }

    public Pasajero(String nombre, int cantidadMaletas) {
        this.nombre = nombre;
        this.cantidadMaletas = cantidadMaletas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadMaletas() {
        return cantidadMaletas;
    }

    public String toString() {
        return nombre + " (" + cantidadMaletas + " maletas)";
    }
}

// CLASE MALETA

class Maleta {
    private int codigo;
    private String idPasajero;
    private String vuelo;

    // PESO
    private double peso;

    public Maleta(int codigo, String idPasajero, String vuelo, double peso) {
        this.codigo = codigo;
        this.idPasajero = idPasajero;
        this.vuelo = vuelo;
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public Maleta(int codigo) {
        this.codigo = codigo;
        this.idPasajero = "";
        this.vuelo = "";
    }

    public Maleta(int codigo, String idPasajero, String vuelo) {
        this.codigo = codigo;
        this.idPasajero = idPasajero;
        this.vuelo = vuelo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getIdPasajero() {
        return idPasajero;
    }

    public String getVuelo() {
        return vuelo;
    }

    public String toString() {
        return "Maleta " + codigo;
    }
}

// NODO DOBLEMENTE ENLAZADO

class Nodo<T> {

    private T contenido;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    public Nodo(T contenido) {
        this.contenido = contenido;
        this.siguiente = null;
        this.anterior = null;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }
}

// LISTA DOBLEMENTE ENLAZADA

class ListaEnlazada<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo<T> primero) {
        this.primero = primero;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }

    public void anadir(T contenido) {

        Nodo<T> nuevo = new Nodo<T>(contenido);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
    }

    public void eliminarPrimero() {

        if (primero == null) {
            return;
        }

        Nodo<T> eliminado = primero;
        primero = primero.getSiguiente();

        if (primero == null) {
            ultimo = null;
        } else {
            primero.setAnterior(null);
        }

        eliminado.setSiguiente(null);
        eliminado.setAnterior(null);
    }

    public void eliminarNodo(Nodo<T> nodo) {

        if (nodo == null) return;

        if (nodo.getAnterior() == null) {
            primero = nodo.getSiguiente();
        } else {
            nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        }

        if (nodo.getSiguiente() == null) {
            ultimo = nodo.getAnterior();
        } else {
            nodo.getSiguiente().setAnterior(nodo.getAnterior());
        }

        nodo.setAnterior(null);
        nodo.setSiguiente(null);
    }

    public void imprimir() {

        Nodo<T> actual = primero;

        while (actual != null) {
            System.out.println(actual.getContenido());
            actual = actual.getSiguiente();
        }
    }

    public void imprimirInverso() {

        Nodo<T> actual = ultimo;

        while (actual != null) {
            System.out.println(actual.getContenido());
            actual = actual.getAnterior();
        }
    }

    public String toString() {

        String s = "[";
        Nodo<T> actual = primero;

        while (actual != null) {
            s = s + actual.getContenido();

            if (actual.getSiguiente() != null) {
                s = s + ", ";
            }

            actual = actual.getSiguiente();
        }

        s = s + "]";
        return s;
    }
}

// NODO PROCESO PARA LISTA CIRCULAR

class NodoProceso<E, F> {

    private E contenido;
    private ListaEnlazada<F> lista;
    private NodoProceso<E, F> siguiente;

    public NodoProceso(E contenido) {
        this.contenido = contenido;
        this.lista = new ListaEnlazada<F>();
        this.siguiente = null;
    }

    public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }

    public ListaEnlazada<F> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<F> lista) {
        this.lista = lista;
    }

    public NodoProceso<E, F> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProceso<E, F> siguiente) {
        this.siguiente = siguiente;
    }
}

// LISTA DE PROCESOS CIRCULAR

class ListaProcesos<E, F> {

    private NodoProceso<E, F> primero;
    private NodoProceso<E, F> ultimo;
    private int cantidad;

    public ListaProcesos() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    public NodoProceso<E, F> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoProceso<E, F> primero) {
        this.primero = primero;
    }

    public NodoProceso<E, F> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoProceso<E, F> ultimo) {
        this.ultimo = ultimo;
    }

    public void anadir(E contenido) {

        NodoProceso<E, F> nuevo =
                new NodoProceso<E, F>(contenido);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            nuevo.setSiguiente(primero);
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
            ultimo.setSiguiente(primero);
        }

        cantidad++;
    }

    public NodoProceso<E, F> obtenerMostrador(int indice) {

        if (indice < 0 || indice >= cantidad) {
            return null;
        }

        NodoProceso<E, F> actual = primero;

        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        return actual;
    }

    public void eliminarProceso(NodoProceso<E, F> nodo) {
        if (nodo == null || cantidad == 0 || nodo.getLista().getPrimero() != null) return;
        if (cantidad == 1) {
            primero = null;
            ultimo = null;
        } else {
            NodoProceso<E, F> anterior = ultimo;
            while (anterior.getSiguiente() != nodo) anterior = anterior.getSiguiente();
            anterior.setSiguiente(nodo.getSiguiente());
            if (nodo == primero) primero = nodo.getSiguiente();
            if (nodo == ultimo) ultimo = anterior;
        }
        cantidad--;
    }

    public int contarMostradores() {
        return cantidad;
    }

    public boolean hayPendientes() {

        NodoProceso<E, F> actual = primero;

        for (int i = 0; i < cantidad; i++) {

            if (actual.getLista().getPrimero() != null) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public int contarDisponibles() {

        int disponibles = 0;
        NodoProceso<E, F> actual = primero;

        for (int i = 0; i < cantidad; i++) {

            if (actual.getLista().getPrimero() != null) {
                disponibles++;
            }

            actual = actual.getSiguiente();
        }

        return disponibles;
    }

    public NodoProceso<E, F> seleccionarMostrador() {

        int disponibles = contarDisponibles();

        if (disponibles == 0) {
            return null;
        }

        int nm = (int)(Math.random() * disponibles);
        NodoProceso<E, F> actual = primero;

        for (int i = 0; i < cantidad; i++) {

            if (actual.getLista().getPrimero() != null) {

                if (nm == 0) {
                    return actual;
                }

                nm--;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public NodoProceso<E, F> buscar(E nombre) {

        NodoProceso<E, F> actual = primero;

        for (int i = 0; i < cantidad; i++) {

            if (actual.getContenido().equals(nombre)) {
                return actual;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public NodoProceso<E, F> menorFila() {

        if (primero == null) return null;

        NodoProceso<E, F> menor = primero;
        NodoProceso<E, F> actual = primero.getSiguiente();

        for (int i = 1; i < cantidad; i++) {

            if (contarLista(actual.getLista()) <
                    contarLista(menor.getLista())) {
                menor = actual;
            }

            actual = actual.getSiguiente();
        }

        return menor;
    }

    public int contarLista(ListaEnlazada<F> lista) {

        int n = 0;
        Nodo<F> actual = lista.getPrimero();

        while (actual != null) {
            n++;
            actual = actual.getSiguiente();
        }

        return n;
    }

    public void imprimir() {

        NodoProceso<E, F> actual = primero;

        for (int i = 0; i < cantidad; i++) {
            System.out.println(actual.getContenido());
            System.out.println("Lista: " + actual.getLista());
            actual = actual.getSiguiente();
        }
    }
}

// CLASE PRINCIPAL

public class MyClass2 {

    public static void buscarMaletas(
            ArrayList<Pasajero> pasajeros,
            ArrayList<Maleta> maletas,
            String id) {

        for (int i = 0; i < pasajeros.size(); i++) {

            if (pasajeros.get(i).getNombre().equalsIgnoreCase(id)) {

                System.out.println("Pasajero: " + pasajeros.get(i));

                if (i < maletas.size()) {
                    System.out.println("Maleta: " + maletas.get(i));
                }

                return;
            }
        }

        System.out.println("Pasajero no encontrado");
    }

    public static int contarPasajeros(
            ListaEnlazada<Pasajero> lista) {

        int cantidad = 0;
        Nodo<Pasajero> actual = lista.getPrimero();

        while (actual != null) {
            cantidad++;
            actual = actual.getSiguiente();
        }

        return cantidad;
    }

    // DISTRIBUIR PASAJEROS EN LA FILA MAS CORTA

    public static void distribuirPasajeros(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaProcesos<String, Pasajero> LP) {

        int cantidad = contarPasajeros(LPasajeros);

        if (cantidad == 0 || LP.contarMostradores() == 0) {
            return;
        }

        int mitad = cantidad / 2;

        if (mitad == 0) {
            mitad = 1;
        }

        int np = (int)(Math.random() * mitad) + 1;

        System.out.println("\nPasajeros seleccionados: " + np);

        for (int i = 0; i < np; i++) {

            Pasajero pasajero =
                    LPasajeros.getPrimero().getContenido();

            LPasajeros.eliminarPrimero();

            NodoProceso<String, Pasajero> mostrador =
                    LP.menorFila();

            mostrador.getLista().anadir(pasajero);

            System.out.println(pasajero + " pasa a "
                    + mostrador.getContenido());
        }
    }

    // CERRAR UN MOSTRADOR Y REPARTIR SUS PASAJEROS

    public static void cerrarMostrador(ListaProcesos<String, Pasajero> LP) {
        if (LP.contarMostradores() <= 1) {
            System.out.println("No se puede cerrar el ultimo mostrador.");
            return;
        }
        int numero = (int)(Math.random() * LP.contarMostradores());
        NodoProceso<String, Pasajero> cerrado = LP.obtenerMostrador(numero);
        System.out.println("Se cierra " + cerrado.getContenido());
        while (cerrado.getLista().getPrimero() != null) {
            Pasajero pasajero = cerrado.getLista().getPrimero().getContenido();
            cerrado.getLista().eliminarPrimero();
            NodoProceso<String, Pasajero> menor = null;
            NodoProceso<String, Pasajero> actual = LP.getPrimero();
            for (int i = 0; i < LP.contarMostradores(); i++) {
                if (actual != cerrado && (menor == null ||
                        LP.contarLista(actual.getLista()) < LP.contarLista(menor.getLista()))) {
                    menor = actual;
                }
                actual = actual.getSiguiente();
            }
            menor.getLista().anadir(pasajero);
            System.out.println(pasajero + " pasa a " + menor.getContenido());
        }
        LP.eliminarProceso(cerrado);
    }

    // PROCESAR LAS MALETAS DE LOS VUELOS

    public static void procesarMaletas(ListaProcesos<String, Maleta> LM) {
        while (LM.hayPendientes()) {
            NodoProceso<String, Maleta> vuelo = LM.seleccionarMostrador();
            if (vuelo == null) return;
            Maleta maleta = vuelo.getLista().getPrimero().getContenido();
            vuelo.getLista().eliminarPrimero();
            System.out.println("Procesada " + maleta + " del vuelo " + vuelo.getContenido());
            if (vuelo.getLista().getPrimero() == null) {
                System.out.println("Vuelo terminado: " + vuelo.getContenido());
                LM.eliminarProceso(vuelo);
            }
        }
        System.out.println("Todas las maletas fueron procesadas.");
    }

    // MOVER MALETAS DEL PASAJERO A SU VUELO

    public static void moverMaletas(
            Pasajero pasajero,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesos<String, Maleta> LM) {

        Nodo<Maleta> actual = LMaletas.getPrimero();

        while (actual != null) {

            Nodo<Maleta> siguiente = actual.getSiguiente();
            Maleta maleta = actual.getContenido();

            if (maleta.getIdPasajero().equals(pasajero.getNombre())) {

                LMaletas.eliminarNodo(actual);

                NodoProceso<String, Maleta> vuelo =
                        LM.buscar(maleta.getVuelo());

                if (vuelo == null) {

                    LM.anadir(maleta.getVuelo());

                    vuelo = LM.buscar(maleta.getVuelo());

                    System.out.println("Se creo el vuelo "
                            + maleta.getVuelo());
                }

                vuelo.getLista().anadir(maleta);

                System.out.println(maleta + " de "
                        + pasajero.getNombre()
                        + " enviada al vuelo "
                        + maleta.getVuelo());
            }

            actual = siguiente;
        }
    }

    // ATENDER PASAJERO Y PROCESAR SUS MALETAS

    public static void atenderPasajero(
            ListaProcesos<String, Pasajero> LP,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesos<String, Maleta> LM) {

        NodoProceso<String, Pasajero> mostrador =
                LP.seleccionarMostrador();

        if (mostrador == null) {
            return;
        }

        Pasajero atendido =
                mostrador.getLista().getPrimero().getContenido();

        System.out.println("\n" + atendido
                + " fue atendido en "
                + mostrador.getContenido());

        mostrador.getLista().eliminarPrimero();

        moverMaletas(atendido, LMaletas, LM);
    }

    // MOSTRAR ESTADO

    public static void mostrarEstado(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaProcesos<String, Pasajero> LP) {

        System.out.println("\nPASAJEROS EN ESPERA:");
        LPasajeros.imprimir();

        System.out.println("\nESTADO DE MOSTRADORES:");
        LP.imprimir();
    }

    // SIMULACION

    public static void simularAtencion(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaProcesos<String, Pasajero> LP,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesos<String, Maleta> LM) {

        if (LP.contarMostradores() == 0) {
            System.out.println("No hay mostradores disponibles.");
            return;
        }

        int ronda = 1;

        while (LPasajeros.getPrimero() != null || LP.hayPendientes()) {

            System.out.println("\n========== RONDA "
                    + ronda + " ==========");

            distribuirPasajeros(LPasajeros, LP);

            atenderPasajero(LP, LMaletas, LM);

            if (LP.contarMostradores() > 1 && Math.random() < 0.5) {
                cerrarMostrador(LP);
            }

            mostrarEstado(LPasajeros, LP);

            System.out.println("\nMALETAS EN ESPERA: " + LMaletas);

            System.out.println("\nVUELOS:");
            LM.imprimir();

            ronda++;
        }

        System.out.println(
                "\nTODOS LOS PASAJEROS FUERON ATENDIDOS.");
        procesarMaletas(LM);
    }

    public static void main(String[] args) {

        // PRUEBA DE MALETAS

        ListaArreglo<Maleta> maletas =
                new ListaArreglo<Maleta>(40);

        maletas.addLast(new Maleta(4));
        maletas.addLast(new Maleta(15));
        maletas.addLast(new Maleta(7));
        maletas.addLast(new Maleta(20));
        maletas.addLast(new Maleta(10));

        System.out.println("Lista de maletas:");
        System.out.println(maletas);

        ListaArreglo<Maleta> maletasMayores =
                maletas.buscarMayores(
                        new Maleta(10),
                        new CompararMaletas()
                );

        System.out.println("Maletas con codigo mayor a 10:");
        System.out.println(maletasMayores);

        // PRUEBA DE PASAJEROS

        ListaArreglo<Pasajero> pasajeros =
                new ListaArreglo<Pasajero>(40);

        pasajeros.addLast(new Pasajero("Carlos", 1));
        pasajeros.addLast(new Pasajero("Ana", 3));
        pasajeros.addLast(new Pasajero("Luis", 1));
        pasajeros.addLast(new Pasajero("Maria", 2));
        pasajeros.addLast(new Pasajero("Pedro", 0));

        System.out.println("\nLista de pasajeros:");
        System.out.println(pasajeros);

        ListaArreglo<Pasajero> pasajerosMayores =
                pasajeros.buscarMayores(
                        new Pasajero("", 1),
                        new CompararPasajeros()
                );

        System.out.println("Pasajeros con mas de una maleta:");
        System.out.println(pasajerosMayores);

        // BUSQUEDA DE MALETAS

        ArrayList<Pasajero> listaPasajeros =
                new ArrayList<Pasajero>();

        ArrayList<Maleta> listaMaletas =
                new ArrayList<Maleta>();

        listaPasajeros.add(new Pasajero("Carlos", 1));
        listaPasajeros.add(new Pasajero("Ana", 3));
        listaPasajeros.add(new Pasajero("Luis", 1));
        listaPasajeros.add(new Pasajero("Maria", 2));
        listaPasajeros.add(new Pasajero("Pedro", 0));

        listaMaletas.add(new Maleta(4));
        listaMaletas.add(new Maleta(15));
        listaMaletas.add(new Maleta(7));
        listaMaletas.add(new Maleta(20));
        listaMaletas.add(new Maleta(10));

        System.out.println();
        buscarMaletas(listaPasajeros, listaMaletas, "Ana");

        // LISTA DE PASAJEROS EN ESPERA

        ListaEnlazada<Pasajero> LPasajeros =
                new ListaEnlazada<Pasajero>();

        LPasajeros.anadir(new Pasajero("Carlos", 1));
        LPasajeros.anadir(new Pasajero("Ana", 3));
        LPasajeros.anadir(new Pasajero("Luis", 1));
        LPasajeros.anadir(new Pasajero("Maria", 2));
        LPasajeros.anadir(new Pasajero("Pedro", 0));

        System.out.println("\nPASAJEROS EN ESPERA:");
        LPasajeros.imprimir();

        // CREACION DE MOSTRADORES

        ListaProcesos<String, Pasajero> LP =
                new ListaProcesos<String, Pasajero>();

        LP.anadir("Mostrador 1");
        LP.anadir("Mostrador 2");
        LP.anadir("Mostrador 3");

        System.out.println("\nMOSTRADORES:");
        LP.imprimir();

        // MALETAS DE LOS PASAJEROS

        ListaEnlazada<Maleta> LMaletas =
                new ListaEnlazada<Maleta>();

        LMaletas.anadir(new Maleta(101, "Carlos", "Quito"));
        LMaletas.anadir(new Maleta(102, "Ana", "Quito"));
        LMaletas.anadir(new Maleta(103, "Ana", "Quito"));
        LMaletas.anadir(new Maleta(104, "Ana", "Quito"));
        LMaletas.anadir(new Maleta(105, "Luis", "Cuenca"));
        LMaletas.anadir(new Maleta(106, "Maria", "Guayaquil"));
        LMaletas.anadir(new Maleta(107, "Maria", "Guayaquil"));

        // VUELOS DE SALIDA

        ListaProcesos<String, Maleta> LM =
                new ListaProcesos<String, Maleta>();

        System.out.println("\nMALETAS EN ESPERA: " + LMaletas);

        System.out.println("\nVUELOS DE SALIDA:");
        LM.imprimir();

        // INICIAR SIMULACION

        simularAtencion(LPasajeros, LP, LMaletas, LM);
    }
}

// PROCESO

class Proceso<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamano;

    public Proceso() {
        primero = null;
        ultimo = null;
        tamano = 0;
    }

    public void agregar(T elemento) {

        Nodo<T> nuevo = new Nodo<T>(elemento);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }

        tamano++;
    }

    public T eliminar() {

        if (primero == null) {
            return null;
        }

        T contenido = primero.getContenido();
        primero = primero.getSiguiente();

        if (primero == null) {
            ultimo = null;
        }

        tamano--;
        return contenido;
    }

    public T revisarPrimero() {

        if (primero == null) {
            return null;
        }

        return primero.getContenido();
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int size() {
        return tamano;
    }

    public String toString() {

        if (primero == null) {
            return "[empty]";
        }

        String s = "[";
        Nodo<T> actual = primero;

        while (actual != null) {
            s = s + actual.getContenido();

            if (actual.getSiguiente() != null) {
                s = s + " -> ";
            }

            actual = actual.getSiguiente();
        }

        return s + "]";
    }
}

// NODO DE LA LISTA DE PROCESOS

class NodoDeProceso<T> {

    private String id;
    private Proceso<T> proceso;
    private NodoDeProceso<T> siguiente;
    private NodoDeProceso<T> anterior;

    public NodoDeProceso(String id) {
        this.id = id;
        this.proceso = new Proceso<T>();
        this.siguiente = null;
        this.anterior = null;
    }

    public String getId() {
        return id;
    }

    public Proceso<T> getProceso() {
        return proceso;
    }

    public NodoDeProceso<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDeProceso<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDeProceso<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDeProceso<T> anterior) {
        this.anterior = anterior;
    }
}

// LISTA DE PROCESOS GENERICA

class ListaProcesosT<T> {

    private NodoDeProceso<T> primero;
    private NodoDeProceso<T> ultimo;
    private int cantidad;

    public ListaProcesosT() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    private NodoDeProceso<T> buscarNodo(String id) {

        NodoDeProceso<T> actual = primero;

        while (actual != null) {
            if (actual.getId().equals(id)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    public void agregarProceso(String id) {

        if (buscarNodo(id) != null) {
            return;
        }

        NodoDeProceso<T> nuevo = new NodoDeProceso<T>(id);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }

        cantidad++;
    }

    public boolean removerProceso(String id) {

        NodoDeProceso<T> nodo = buscarNodo(id);

        if (nodo == null || !nodo.getProceso().estaVacia()) {
            return false;
        }

        if (nodo.getAnterior() == null) {
            primero = nodo.getSiguiente();
        } else {
            nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        }

        if (nodo.getSiguiente() == null) {
            ultimo = nodo.getAnterior();
        } else {
            nodo.getSiguiente().setAnterior(nodo.getAnterior());
        }

        nodo.setAnterior(null);
        nodo.setSiguiente(null);
        cantidad--;
        return true;
    }

    public boolean agregar(String id, T elemento) {

        NodoDeProceso<T> nodo = buscarNodo(id);

        if (nodo == null) {
            return false;
        }

        nodo.getProceso().agregar(elemento);
        return true;
    }

    public T eliminar(String id) {

        NodoDeProceso<T> nodo = buscarNodo(id);

        if (nodo == null) {
            return null;
        }

        return nodo.getProceso().eliminar();
    }

    public T revisarPrimero(String id) {

        NodoDeProceso<T> nodo = buscarNodo(id);

        if (nodo == null) {
            return null;
        }

        return nodo.getProceso().revisarPrimero();
    }

    public boolean encontrarProceso(String id) {
        return buscarNodo(id) != null;
    }

    public int tamanoProceso(String id) {

        NodoDeProceso<T> nodo = buscarNodo(id);

        if (nodo == null) {
            return -1;
        }

        return nodo.getProceso().size();
    }

    public String obtenerProcesoMasCorto() {
        return obtenerProcesoMasCortoExcepto(null);
    }

    // IGUAL QUE EL ANTERIOR PERO IGNORA UN PROCESO (SIRVE AL CERRAR)
    public String obtenerProcesoMasCortoExcepto(String idExcluido) {

        NodoDeProceso<T> menor = null;
        NodoDeProceso<T> actual = primero;

        while (actual != null) {

            boolean excluido = idExcluido != null
                    && actual.getId().equals(idExcluido);

            if (!excluido && (menor == null ||
                    actual.getProceso().size() < menor.getProceso().size())) {
                menor = actual;
            }

            actual = actual.getSiguiente();
        }

        if (menor == null) {
            return null;
        }

        return menor.getId();
    }

    public int totalDeProcesos() {
        return cantidad;
    }

    public int totalElementos() {

        int total = 0;
        NodoDeProceso<T> actual = primero;

        while (actual != null) {
            total = total + actual.getProceso().size();
            actual = actual.getSiguiente();
        }

        return total;
    }

    public boolean estaVacia() {
        return totalElementos() == 0;
    }

    // PARA RECORRER Y MOSTRAR SIN TOCAR NODOS

    public String obtenerId(int indice) {

        if (indice < 0 || indice >= cantidad) {
            return null;
        }

        NodoDeProceso<T> actual = primero;

        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getId();
    }

    public String mostrarProceso(String id) {

        NodoDeProceso<T> nodo = buscarNodo(id);

        if (nodo == null) {
            return "[no existe]";
        }

        return nodo.getProceso().toString();
    }
}

// APLICACION CON MENU

class AirportFlowManager {

    static Scanner teclado = new Scanner(System.in);
    static int siguienteTag = 301;

    // ASIGNAR PASAJERO A LA FILA MAS CORTA

    public static void asignarPasajero(
            ListaProcesosT<Pasajero> LP,
            Pasajero pasajero) {

        String id = LP.obtenerProcesoMasCorto();

        if (id == null) {
            return;
        }

        LP.agregar(id, pasajero);
        System.out.println(pasajero + " pasa a " + id);
    }

    // ENVIAR LAS MALETAS DE UN PASAJERO A SU VUELO

    public static void enviarMaletas(
            Pasajero pasajero,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesosT<Maleta> LM) {

        Nodo<Maleta> actual = LMaletas.getPrimero();

        while (actual != null) {

            Nodo<Maleta> siguiente = actual.getSiguiente();
            Maleta maleta = actual.getContenido();

            if (maleta.getIdPasajero().equals(pasajero.getId())) {

                LMaletas.eliminarNodo(actual);

                if (!LM.encontrarProceso(maleta.getVuelo())) {
                    LM.agregarProceso(maleta.getVuelo());
                    System.out.println("Se creo el vuelo " + maleta.getVuelo());
                }

                LM.agregar(maleta.getVuelo(), maleta);
                System.out.println(maleta + " enviada al vuelo " + maleta.getVuelo());
            }

            actual = siguiente;
        }
    }

    // ATENDER AL SIGUIENTE PASAJERO DE UN MOSTRADOR

    public static Pasajero atenderEn(
            ListaProcesosT<Pasajero> LP,
            String idMostrador,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesosT<Maleta> LM) {

        Pasajero atendido = LP.eliminar(idMostrador);

        if (atendido == null) {
            System.out.println(idMostrador + " no tiene pasajeros.");
            return null;
        }

        System.out.println(atendido + " fue atendido en " + idMostrador);
        enviarMaletas(atendido, LMaletas, LM);
        return atendido;
    }

    // CERRAR UN MOSTRADOR Y REPARTIR SUS PASAJEROS

    public static boolean cerrarMostrador(
            ListaProcesosT<Pasajero> LP,
            String idMostrador) {

        if (!LP.encontrarProceso(idMostrador)) {
            System.out.println("El mostrador no existe.");
            return false;
        }

        if (LP.totalDeProcesos() == 1 && LP.tamanoProceso(idMostrador) > 0) {
            System.out.println("No se puede cerrar el unico mostrador con pasajeros.");
            return false;
        }

        System.out.println("Se cierra " + idMostrador);

        while (LP.tamanoProceso(idMostrador) > 0) {
            Pasajero pasajero = LP.eliminar(idMostrador);
            String destino = LP.obtenerProcesoMasCortoExcepto(idMostrador);
            LP.agregar(destino, pasajero);
            System.out.println(pasajero + " pasa a " + destino);
        }

        return LP.removerProceso(idMostrador);
    }

    // PROCESAR LA SIGUIENTE MALETA DE UN VUELO

    public static Maleta procesarMaleta(
            ListaProcesosT<Maleta> LM,
            String idVuelo) {

        Maleta maleta = LM.eliminar(idVuelo);

        if (maleta == null) {
            System.out.println("El vuelo " + idVuelo + " no tiene maletas.");
            return null;
        }

        System.out.println("Procesada " + maleta + " del vuelo " + idVuelo);

        if (LM.tamanoProceso(idVuelo) == 0) {
            LM.removerProceso(idVuelo);
            System.out.println("Vuelo terminado: " + idVuelo);
        }

        return maleta;
    }

    // ELEGIR AL AZAR UN PROCESO QUE TENGA ELEMENTOS

    public static String procesoAlAzar(ListaProcesosT<?> lista) {

        int disponibles = 0;

        for (int i = 0; i < lista.totalDeProcesos(); i++) {
            if (lista.tamanoProceso(lista.obtenerId(i)) > 0) {
                disponibles++;
            }
        }

        if (disponibles == 0) {
            return null;
        }

        int elegido = (int)(Math.random() * disponibles);

        for (int i = 0; i < lista.totalDeProcesos(); i++) {
            String id = lista.obtenerId(i);

            if (lista.tamanoProceso(id) > 0) {
                if (elegido == 0) {
                    return id;
                }
                elegido--;
            }
        }

        return null;
    }

    // CREAR PASAJEROS Y SUS MALETAS

    public static void agregarPasajero(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaEnlazada<Maleta> LMaletas,
            Pasajero pasajero) {

        LPasajeros.anadir(pasajero);

        for (int i = 0; i < pasajero.getCantidadMaletas(); i++) {
            LMaletas.anadir(new Maleta(siguienteTag, pasajero.getId(),
                    pasajero.getVuelo(), 10 + i));
            siguienteTag++;
        }
    }

    public static void cargarDatos(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaEnlazada<Maleta> LMaletas) {

        agregarPasajero(LPasajeros, LMaletas, new Pasajero("P101", "Carlos", "GYE-UIO", 1));
        agregarPasajero(LPasajeros, LMaletas, new Pasajero("P102", "Ana", "GYE-LIM", 3));
        agregarPasajero(LPasajeros, LMaletas, new Pasajero("P103", "Luis", "GYE-MIA", 1));
        agregarPasajero(LPasajeros, LMaletas, new Pasajero("P104", "Maria", "GYE-UIO", 2));
        agregarPasajero(LPasajeros, LMaletas, new Pasajero("P105", "Pedro", "GYE-LIM", 0));

        System.out.println("Datos cargados.");
    }

    // PASAR UN NUMERO ALEATORIO DE PASAJEROS A LOS MOSTRADORES

    public static void distribuir(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaProcesosT<Pasajero> LP) {

        int cantidad = MyClass2.contarPasajeros(LPasajeros);

        if (cantidad == 0 || LP.totalDeProcesos() == 0) {
            return;
        }

        int np = (int)(Math.random() * cantidad) + 1;

        System.out.println("\nPasajeros seleccionados: " + np);

        for (int i = 0; i < np; i++) {
            Pasajero pasajero = LPasajeros.getPrimero().getContenido();
            LPasajeros.eliminarPrimero();
            asignarPasajero(LP, pasajero);
        }
    }

    // MOSTRAR ESTADO

    public static void mostrarEstado(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesosT<Pasajero> LP,
            ListaProcesosT<Maleta> LM) {

        System.out.println("\nCHECK-IN");

        for (int i = 0; i < LP.totalDeProcesos(); i++) {
            String id = LP.obtenerId(i);
            System.out.println(id + ": " + LP.mostrarProceso(id));
        }

        System.out.println("\nBAGGAGE");

        for (int i = 0; i < LM.totalDeProcesos(); i++) {
            String id = LM.obtenerId(i);
            System.out.println(id + ": " + LM.mostrarProceso(id));
        }

        System.out.println("\nTotal passengers waiting: " + LP.totalElementos());
        System.out.println("Total bags waiting: " + LM.totalElementos());
        System.out.println("Pasajeros por atender: " + MyClass2.contarPasajeros(LPasajeros));
        System.out.println("Maletas sin registrar: " + LMaletas);
    }

    // SIMULACION AUTOMATICA

    public static void simular(
            ListaEnlazada<Pasajero> LPasajeros,
            ListaEnlazada<Maleta> LMaletas,
            ListaProcesosT<Pasajero> LP,
            ListaProcesosT<Maleta> LM) {

        if (LP.totalDeProcesos() == 0) {
            System.out.println("No hay mostradores abiertos.");
            return;
        }

        int ronda = 1;

        while (LPasajeros.getPrimero() != null || LP.totalElementos() > 0) {

            System.out.println("\n========== RONDA " + ronda + " ==========");

            distribuir(LPasajeros, LP);

            String mostrador = procesoAlAzar(LP);

            if (mostrador != null) {
                atenderEn(LP, mostrador, LMaletas, LM);
            }

            if (LP.totalDeProcesos() > 1 && Math.random() < 0.5) {
                int numero = (int)(Math.random() * LP.totalDeProcesos());
                cerrarMostrador(LP, LP.obtenerId(numero));
            }

            mostrarEstado(LPasajeros, LMaletas, LP, LM);

            ronda++;
        }

        System.out.println("\nTODOS LOS PASAJEROS FUERON ATENDIDOS.");

        while (LM.totalElementos() > 0) {
            procesarMaleta(LM, procesoAlAzar(LM));
        }

        System.out.println("Todas las maletas fueron procesadas.");
    }

    public static void main(String[] args) {

        ListaEnlazada<Pasajero> LPasajeros = new ListaEnlazada<Pasajero>();
        ListaEnlazada<Maleta> LMaletas = new ListaEnlazada<Maleta>();

        ListaProcesosT<Pasajero> LP = new ListaProcesosT<Pasajero>();
        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();

        LP.agregarProceso("C1");
        LP.agregarProceso("C2");
        LP.agregarProceso("C3");

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n===== AIRPORT FLOW MANAGER =====");
            System.out.println("1. Cargar datos de ejemplo");
            System.out.println("2. Ver estado");
            System.out.println("3. Pasar pasajeros a los mostradores");
            System.out.println("4. Atender pasajero de un mostrador");
            System.out.println("5. Cerrar un mostrador");
            System.out.println("6. Procesar maleta de un vuelo");
            System.out.println("7. Simulacion automatica");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            if (opcion == 1) {
                cargarDatos(LPasajeros, LMaletas);
            } else if (opcion == 2) {
                mostrarEstado(LPasajeros, LMaletas, LP, LM);
            } else if (opcion == 3) {
                distribuir(LPasajeros, LP);
            } else if (opcion == 4) {
                System.out.print("Mostrador (ejemplo C1): ");
                atenderEn(LP, teclado.nextLine().trim(), LMaletas, LM);
            } else if (opcion == 5) {
                System.out.print("Mostrador a cerrar (ejemplo C1): ");
                cerrarMostrador(LP, teclado.nextLine().trim());
            } else if (opcion == 6) {
                System.out.print("Vuelo (ejemplo GYE-UIO): ");
                procesarMaleta(LM, teclado.nextLine().trim());
            } else if (opcion == 7) {
                simular(LPasajeros, LMaletas, LP, LM);
            } else if (opcion != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }
}

// PRUEBAS DE LOS 11 ESCENARIOS

class Pruebas {

    static int buenas = 0;
    static int malas = 0;

    static void verificar(String nombre, boolean condicion) {

        if (condicion) {
            buenas++;
            System.out.println("OK    " + nombre);
        } else {
            malas++;
            System.out.println("FALLO " + nombre);
        }
    }

    static Pasajero pasajero(String id, String vuelo, int maletas) {
        return new Pasajero(id, "Nombre" + id, vuelo, maletas);
    }

    static ListaProcesosT<Pasajero> tresMostradores() {

        ListaProcesosT<Pasajero> LP = new ListaProcesosT<Pasajero>();
        LP.agregarProceso("C1");
        LP.agregarProceso("C2");
        LP.agregarProceso("C3");
        return LP;
    }

    // ESCENARIO 1

    static void escenario1() {

        System.out.println("\n--- Escenario 1: tres mostradores vacios ");

        ListaProcesosT<Pasajero> LP = tresMostradores();

        verificar("hay 3 mostradores", LP.totalDeProcesos() == 3);
        verificar("orden C1", LP.obtenerId(0).equals("C1"));
        verificar("orden C2", LP.obtenerId(1).equals("C2"));
        verificar("orden C3", LP.obtenerId(2).equals("C3"));
        verificar("lista vacia", LP.estaVacia());
    }

    // ESCENARIO 2

    static void escenario2() {

        System.out.println("\n--- Escenario 2: fila mas corta y desempate ---");

        ListaProcesosT<Pasajero> LP = tresMostradores();

        AirportFlowManager.asignarPasajero(LP, pasajero("P1", "GYE-UIO", 0));
        AirportFlowManager.asignarPasajero(LP, pasajero("P2", "GYE-UIO", 0));
        AirportFlowManager.asignarPasajero(LP, pasajero("P3", "GYE-UIO", 0));
        AirportFlowManager.asignarPasajero(LP, pasajero("P4", "GYE-UIO", 0));

        verificar("C1 tiene 2", LP.tamanoProceso("C1") == 2);
        verificar("C2 tiene 1", LP.tamanoProceso("C2") == 1);
        verificar("C3 tiene 1", LP.tamanoProceso("C3") == 1);
        verificar("P4 fue a C1 por desempate", LP.revisarPrimero("C1").getId().equals("P1"));
    }

    // ESCENARIO 3

    static void escenario3() {

        System.out.println("\n--- Escenario 3: atender varios del mismo mostrador ---");

        ListaProcesosT<Pasajero> LP = tresMostradores();
        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();
        ListaEnlazada<Maleta> LMaletas = new ListaEnlazada<Maleta>();

        LP.agregar("C1", pasajero("P1", "GYE-UIO", 0));
        LP.agregar("C1", pasajero("P2", "GYE-UIO", 0));
        LP.agregar("C1", pasajero("P3", "GYE-UIO", 0));

        Pasajero a = AirportFlowManager.atenderEn(LP, "C1", LMaletas, LM);
        Pasajero b = AirportFlowManager.atenderEn(LP, "C1", LMaletas, LM);

        verificar("sale P1 primero", a.getId().equals("P1"));
        verificar("sale P2 segundo", b.getId().equals("P2"));
        verificar("queda P3", LP.tamanoProceso("C1") == 1);
    }

    // ESCENARIO 4

    static void escenario4() {

        System.out.println("\n--- Escenario 4: cerrar mostrador vacio ---");

        ListaProcesosT<Pasajero> LP = tresMostradores();
        verificar("cerrar el primero", AirportFlowManager.cerrarMostrador(LP, "C1"));
        verificar("quedan C2 y C3", LP.obtenerId(0).equals("C2") && LP.obtenerId(1).equals("C3"));

        LP = tresMostradores();
        verificar("cerrar el del medio", AirportFlowManager.cerrarMostrador(LP, "C2"));
        verificar("quedan C1 y C3", LP.obtenerId(0).equals("C1") && LP.obtenerId(1).equals("C3"));

        LP = tresMostradores();
        verificar("cerrar el ultimo", AirportFlowManager.cerrarMostrador(LP, "C3"));
        verificar("quedan C1 y C2", LP.obtenerId(0).equals("C1") && LP.obtenerId(1).equals("C2"));
    }

    // ESCENARIO 5

    static void escenario5() {

        System.out.println("\n--- Escenario 5: cerrar con pasajeros ---");

        ListaProcesosT<Pasajero> LP = tresMostradores();

        LP.agregar("C1", pasajero("A", "GYE-UIO", 0));
        LP.agregar("C1", pasajero("B", "GYE-UIO", 0));
        LP.agregar("C1", pasajero("C", "GYE-UIO", 0));
        LP.agregar("C2", pasajero("D", "GYE-UIO", 0));

        verificar("se cierra C1", AirportFlowManager.cerrarMostrador(LP, "C1"));
        verificar("C1 ya no existe", !LP.encontrarProceso("C1"));
        verificar("no se perdio nadie", LP.totalElementos() == 4);
        verificar("C2 tiene 2", LP.tamanoProceso("C2") == 2);
        verificar("C3 tiene 2", LP.tamanoProceso("C3") == 2);
        verificar("A sale primero en C3", LP.revisarPrimero("C3").getId().equals("A"));
        LP.eliminar("C3");
        verificar("C sale despues de A", LP.revisarPrimero("C3").getId().equals("C"));
    }

    // ESCENARIO 6

    static void escenario6() {

        System.out.println("\n--- Escenario 6: cerrar el unico mostrador con pasajeros ---");

        ListaProcesosT<Pasajero> LP = new ListaProcesosT<Pasajero>();
        LP.agregarProceso("C1");
        LP.agregar("C1", pasajero("A", "GYE-UIO", 0));

        verificar("se rechaza", !AirportFlowManager.cerrarMostrador(LP, "C1"));
        verificar("C1 sigue abierto", LP.totalDeProcesos() == 1);
        verificar("A sigue en la fila", LP.tamanoProceso("C1") == 1);
    }

    // ESCENARIO 7

    static void escenario7() {

        System.out.println("\n--- Escenario 7: pasajero con varias maletas ---");

        ListaProcesosT<Pasajero> LP = tresMostradores();
        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();
        ListaEnlazada<Maleta> LMaletas = new ListaEnlazada<Maleta>();

        Pasajero ana = pasajero("P1", "GYE-LIM", 3);

        LMaletas.anadir(new Maleta(301, "P1", "GYE-LIM", 10));
        LMaletas.anadir(new Maleta(302, "P9", "GYE-MIA", 12));
        LMaletas.anadir(new Maleta(303, "P1", "GYE-UIO", 15));
        LMaletas.anadir(new Maleta(304, "P1", "GYE-LIM", 8));

        LP.agregar("C1", ana);
        AirportFlowManager.atenderEn(LP, "C1", LMaletas, LM);

        verificar("hay 2 vuelos", LM.totalDeProcesos() == 2);
        verificar("GYE-LIM tiene 2", LM.tamanoProceso("GYE-LIM") == 2);
        verificar("GYE-UIO tiene 1", LM.tamanoProceso("GYE-UIO") == 1);
        verificar("la maleta de otro pasajero sigue en la lista",
                LMaletas.getPrimero().getContenido().getCodigo() == 302);
    }

    // ESCENARIO 8

    static void escenario8() {

        System.out.println("\n--- Escenario 8: procesar maletas de un vuelo ---");

        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();
        LM.agregarProceso("GYE-UIO");
        LM.agregar("GYE-UIO", new Maleta(1, "P1", "GYE-UIO", 10));
        LM.agregar("GYE-UIO", new Maleta(2, "P1", "GYE-UIO", 10));

        Maleta m = AirportFlowManager.procesarMaleta(LM, "GYE-UIO");

        verificar("sale la primera maleta", m.getCodigo() == 1);
        verificar("queda 1", LM.tamanoProceso("GYE-UIO") == 1);
    }

    // ESCENARIO 9

    static void escenario9() {

        System.out.println("\n--- Escenario 9: procesar vuelo vacio ---");

        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();
        LM.agregarProceso("GYE-UIO");

        verificar("vuelo vacio devuelve null", AirportFlowManager.procesarMaleta(LM, "GYE-UIO") == null);
        verificar("revisarPrimero vacio es null", LM.revisarPrimero("GYE-UIO") == null);
        verificar("vuelo que no existe devuelve null", AirportFlowManager.procesarMaleta(LM, "XXX") == null);
    }

    // ESCENARIO 10

    static void escenario10() {

        System.out.println("\n--- Escenario 10: borrar vuelo solo si esta vacio ---");

        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();
        LM.agregarProceso("GYE-UIO");
        LM.agregar("GYE-UIO", new Maleta(1, "P1", "GYE-UIO", 10));

        verificar("no se borra con maletas", !LM.removerProceso("GYE-UIO"));
        LM.eliminar("GYE-UIO");
        verificar("se borra vacio", LM.removerProceso("GYE-UIO"));
        verificar("ya no hay vuelos", LM.totalDeProcesos() == 0);
    }

    // ESCENARIO 11

    static void escenario11() {

        System.out.println("\n--- Escenario 11: LP y LM juntos ---");

        ListaProcesosT<Pasajero> LP = tresMostradores();
        ListaProcesosT<Maleta> LM = new ListaProcesosT<Maleta>();
        ListaEnlazada<Pasajero> LPasajeros = new ListaEnlazada<Pasajero>();
        ListaEnlazada<Maleta> LMaletas = new ListaEnlazada<Maleta>();

        LP.agregar("C1", pasajero("P102", "GYE-UIO", 0));
        LP.agregar("C1", pasajero("P108", "GYE-UIO", 0));
        LP.agregar("C2", pasajero("P104", "GYE-UIO", 0));

        LM.agregarProceso("GYE-UIO");
        LM.agregarProceso("GYE-LIM");
        LM.agregar("GYE-UIO", new Maleta(301, "P102", "GYE-UIO", 10));
        LM.agregar("GYE-UIO", new Maleta(304, "P102", "GYE-UIO", 10));
        LM.agregar("GYE-LIM", new Maleta(302, "P104", "GYE-LIM", 10));

        AirportFlowManager.mostrarEstado(LPasajeros, LMaletas, LP, LM);

        verificar("LP tiene 3 pasajeros", LP.totalElementos() == 3);
        verificar("LM tiene 3 maletas", LM.totalElementos() == 3);
    }

    public static void main(String[] args) {

        escenario1();
        escenario2();
        escenario3();
        escenario4();
        escenario5();
        escenario6();
        escenario7();
        escenario8();
        escenario9();
        escenario10();
        escenario11();

        System.out.println("\nRESULTADO: " + buenas + " bien, " + malas + " mal");
    }
}
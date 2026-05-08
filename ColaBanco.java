
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase controladora que gestiona la lógica de la cola del banco.
 * Implementa el principio FIFO (First In, First Out) y maneja la 
 * persistencia de datos en un archivo binario.
 */
public class ColaBanco {
    private Queue<Cliente> colaClientes;
    private final String ARCHIVO_DATOS = "cola_banco.dat";

    /**
     * Constructor de la clase. Inicializa la estructura de datos 
     * e intenta cargar información previa desde el disco.
     */
    public ColaBanco() {
        colaClientes = new LinkedList<>();
        cargarEstado(); 
    }

    /**
     * Agrega un nuevo cliente al final de la cola y actualiza el archivo.
     * @param cliente Objeto Cliente que ingresa a la fila.
     */
    public void encolarCliente(Cliente cliente) {
        colaClientes.add(cliente); 
        guardarEstado();
    }

    /**
     * Extrae y retorna al primer cliente en la fila (el más antiguo).
     * @return El cliente atendido o null si la cola está vacía.
     */
    public Cliente desencolarCliente() {
        if (colaClientes.isEmpty()) {
            return null;
        }
        Cliente atendido = colaClientes.poll(); 
        guardarEstado();
        return atendido;
    }

    /**
     * Permite observar quién es el siguiente en la fila sin retirarlo.
     * @return El primer cliente en espera.
     */
    public Cliente verProximo() {
        return colaClientes.peek(); 
    }

    /**
     * Crea una copia de la cola actual para su visualización.
     * @return Una nueva estructura Queue con todos los clientes.
     */
    public Queue<Cliente> obtenerTodos() {
        return new LinkedList<>(colaClientes); 
    }

    /**
     * Retorna la cantidad actual de personas esperando turno.
     * @return Número entero de clientes.
     */
    public int obtenerTamano() {
        return colaClientes.size();
    }

    /**
     * Elimina a todos los clientes de la fila y limpia el archivo de datos.
     */
    public void vaciarCola() {
        colaClientes.clear();
        guardarEstado();
    }

    /**
     * Verifica si hay personas esperando en la fila.
     * @return true si la cola no tiene elementos.
     */
    public boolean estaVacia() {
        return colaClientes.isEmpty();
    }

    /**
     * Guarda la cola actual en un archivo binario para persistencia.
     * Utiliza ObjectOutputStream para serializar la lista de clientes.
     */
    private void guardarEstado() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(colaClientes);
        } catch (IOException e) {
            System.out.println(">> Advertencia: No se pudo guardar el estado de la cola.");
        }
    }

    /**
     * Recupera la información del archivo 'cola_banco.dat' al iniciar el programa.
     * Si el archivo no existe, inicia una cola nueva y vacía.
     */
    @SuppressWarnings("unchecked")
    private void cargarEstado() {
        File archivo = new File(ARCHIVO_DATOS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                colaClientes = (Queue<Cliente>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(">> Advertencia: No se pudo cargar el archivo anterior. Iniciando cola vacía.");
                colaClientes = new LinkedList<>();
            }
        }
    }
}
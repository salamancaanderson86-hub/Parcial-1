
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;

/**
 * Clase principal que gestiona la interfaz de usuario por consola.
 * Permite la interacción con el sistema de turnos del banco.
 */
public class MenuPrincipal {
    private static Scanner scanner = new Scanner(System.in);
    private static ColaBanco gestorCola = new ColaBanco();

    /**
     * Punto de entrada de la aplicación. Gestiona el bucle del menú principal.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n=== SISTEMA DE COLA DEL BANCO (Mayo 2026) ===");
            System.out.println("1. Agregar cliente (tomar turno)");
            System.out.println("2. Atender siguiente cliente");
            System.out.println("3. Ver próximo cliente en espera");
            System.out.println("4. Mostrar todos los clientes en cola");
            System.out.println("5. Consultar cantidad de clientes en espera");
            System.out.println("6. Vaciar la cola");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEnteroSeguro();

            try {
                switch (opcion) {
                    case 1: agregarCliente(); break;
                    case 2: atenderCliente(); break;
                    case 3: verProximo(); break;
                    case 4: mostrarFila(); break;
                    case 5: 
                        System.out.println(">> Clientes en espera: " + gestorCola.obtenerTamano()); 
                        break;
                    case 6: 
                        gestorCola.vaciarCola(); 
                        System.out.println(">> ATENCIÓN: La cola ha sido vaciada por emergencia."); 
                        break;
                    case 7: 
                        System.out.println("Guardando estado y saliendo del sistema..."); 
                        ejecutando = false; 
                        break;
                    default: 
                        System.out.println(">> Error: Ingrese un número entre 1 y 7.");
                }
            } catch (Exception e) {
                System.out.println(">> ERROR: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Lee un entero de la consola de forma segura, manejando errores de entrada.
     * @return El número ingresado o -1 si la entrada es inválida.
     */
    private static int leerEnteroSeguro() {
        try {
            int num = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            return num;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer tras el error
            return -1;
        }
    }

    /**
     * Solicita los datos al usuario para crear un nuevo cliente y encolarlo.
     */
    private static void agregarCliente() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese identificación: ");
        String id = scanner.nextLine();
        
        System.out.print("Ingrese tipo de transacción (Depósito/Retiro/Consulta/Pago): ");
        String transaccion = scanner.nextLine();
        
        System.out.print("Hora de llegada (HH:MM): ");
        String hora = scanner.nextLine();
        
        System.out.print("¿Es atención prioritaria? (S/N): ");
        boolean prioridad = scanner.nextLine().trim().equalsIgnoreCase("S");

        Cliente nuevo = new Cliente(nombre, id, transaccion, hora, prioridad);
        gestorCola.encolarCliente(nuevo);
        System.out.println(">> Cliente agregado a la cola con éxito.");
    }

    /**
     * Desencola al siguiente cliente y muestra su información por pantalla.
     */
    private static void atenderCliente() {
        if (gestorCola.estaVacia()) {
            System.out.println(">> Error: No hay clientes en espera.");
            return;
        }
        Cliente atendido = gestorCola.desencolarCliente();
        System.out.println(">> Atendiendo a: " + atendido.toString());
    }

    /**
     * Muestra la información del próximo cliente sin retirarlo de la cola.
     */
    private static void verProximo() {
        if (gestorCola.estaVacia()) {
            System.out.println(">> La fila está vacía.");
        } else {
            System.out.println(">> Siguiente en turno: " + gestorCola.verProximo().toString());
        }
    }

    /**
     * Recorre y muestra todos los clientes que se encuentran esperando en la fila.
     */
    private static void mostrarFila() {
        Queue<Cliente> fila = gestorCola.obtenerTodos();
        if (fila.isEmpty()) {
            System.out.println(">> No hay nadie en la fila actual.");
        } else {
            System.out.println("\n--- FILA ACTUAL ---");
            int turno = 1;
            for (Cliente c : fila) {
                System.out.println(turno + ". " + c.toString());
                turno++;
            }
        }
    }
}
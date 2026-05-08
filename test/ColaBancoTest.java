package test;

import controlador.ColaBanco;
import modelo.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para validar el comportamiento de la Cola del Banco.
 * Se enfoca en verificar que se respete el principio FIFO y la integridad de los datos.
 */
public class ColaBancoTest {
    private ColaBanco cola;
    private Cliente c1;
    private Cliente c2;

    /**
     * Configuración inicial antes de cada prueba.
     * Crea una nueva instancia de la cola y la limpia para evitar interferencias.
     */
    @BeforeEach
    public void setUp() {
        cola = new ColaBanco();
        cola.vaciarCola(); 
        c1 = new Cliente("Ana", "1234", "Depósito", "10:30", false);
        c2 = new Cliente("Luis", "5678", "Retiro", "10:35", true);
    }

    /**
     * Prueba que los clientes se agreguen correctamente y el tamaño de la cola aumente.
     */
    @Test
    public void testEncolarYTamano() {
        assertTrue(cola.estaVacia(), "La cola debería iniciar vacía");
        cola.encolarCliente(c1);
        assertEquals(1, cola.obtenerTamano(), "El tamaño debería ser 1 tras agregar un cliente");
        cola.encolarCliente(c2);
        assertEquals(2, cola.obtenerTamano(), "El tamaño debería ser 2 tras agregar dos clientes");
    }

    /**
     * Prueba reina: Verifica que el primero en llegar sea el primero en ser atendido (FIFO).
     */
    @Test
    public void testDesencolarRespetaFIFO() {
        cola.encolarCliente(c1); // Ana llega primero
        cola.encolarCliente(c2); // Luis llega después
        
        Cliente atendido = cola.desencolarCliente();
        assertEquals("Ana", atendido.getNombre(), "Debería atender a Ana primero por orden de llegada"); 
        assertEquals(1, cola.obtenerTamano(), "La cola debería quedar con un solo cliente"); 
    }

    /**
     * Prueba de seguridad: Verifica que el sistema no falle si se intenta atender en una cola vacía.
     */
    @Test
    public void testDesencolarColaVacia() {
        assertNull(cola.desencolarCliente(), "Si la cola está vacía, el resultado debe ser nulo");
    }
}
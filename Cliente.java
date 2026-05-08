
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa a un cliente del banco con sus datos personales y de transacción.
 * Implementa Serializable para permitir la persistencia de datos.
 */
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String identificacion;
    private String tipoTransaccion;
    private LocalTime horaLlegada;
    private boolean prioridad;

    /**
     * Constructor para crear un nuevo cliente con validación inmediata de datos.
     * @param nombre Nombre completo del cliente.
     * @param identificacion Número de documento (solo dígitos).
     * @param tipoTransaccion Tipo de operación (Depósito, Retiro, Consulta, Pago).
     * @param horaLlegadaStr Hora en formato HH:mm.
     * @param prioridad Define si el cliente tiene atención preferencial.
     */
    public Cliente(String nombre, String identificacion, String tipoTransaccion, String horaLlegadaStr, boolean prioridad) {
        setNombre(nombre);
        setIdentificacion(identificacion);
        setTipoTransaccion(tipoTransaccion);
        setHoraLlegada(horaLlegadaStr);
        this.prioridad = prioridad;
    }

    /**
     * Valida y asigna el nombre del cliente.
     * @param nombre El nombre a asignar.
     * @throws IllegalArgumentException si el nombre es nulo o está vacío.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Valida que la identificación contenga únicamente números.
     * @param identificacion Cadena con el número de documento.
     * @throws IllegalArgumentException si contiene caracteres no numéricos.
     */
    public void setIdentificacion(String identificacion) {
        if (identificacion == null || !identificacion.matches("\\d+")) {
            throw new IllegalArgumentException("La identificación debe contener solo números.");
        }
        this.identificacion = identificacion;
    }

    /**
     * Valida que la transacción esté dentro de las opciones permitidas por el banco.
     * @param tipoTransaccion Nombre de la transacción.
     * @throws IllegalArgumentException si la transacción no es reconocida.
     */
    public void setTipoTransaccion(String tipoTransaccion) {
        String tx = tipoTransaccion.toLowerCase();
        if (!tx.equals("depósito") && !tx.equals("retiro") && !tx.equals("consulta") && !tx.equals("pago")) {
            throw new IllegalArgumentException("Transacción inválida (Depósito, Retiro, Consulta, Pago).");
        }
        // Formatea para que siempre empiece con Mayúscula
        this.tipoTransaccion = tx.substring(0, 1).toUpperCase() + tx.substring(1);
    }

    /**
     * Convierte una cadena de texto a un objeto LocalTime.
     * @param horaLlegadaStr Hora en formato 24h (ej. 15:45).
     * @throws IllegalArgumentException si el formato no es correcto.
     */
    public void setHoraLlegada(String horaLlegadaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            this.horaLlegada = LocalTime.parse(horaLlegadaStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de hora inválido. Use HH:mm (ej. 14:30).");
        }
    }

    // Getters comentados para cumplir con el estándar de documentación
    
    /** @return El nombre del cliente */
    public String getNombre() { return nombre; }
    
    /** @return El número de identificación */
    public String getIdentificacion() { return identificacion; }
    
    /** @return El tipo de transacción asignada */
    public String getTipoTransaccion() { return tipoTransaccion; }
    
    /** @return La hora exacta de llegada al banco */
    public LocalTime getHoraLlegada() { return horaLlegada; }
    
    /** @return Verdadero si es cliente prioritario, falso de lo contrario */
    public boolean tienePrioridad() { return prioridad; }

    /**
     * Genera una representación en texto amigable del cliente.
     * @return Cadena formateada con los datos del turno.
     */
    @Override
    public String toString() {
        return String.format("Cliente{nombre='%s', id=%s, transacción=%s, hora=%s, prioridad=%s}", 
                nombre, identificacion, tipoTransaccion, horaLlegada.toString(), (prioridad ? "Sí" : "No"));
    }
}
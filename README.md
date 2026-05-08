# **Primer parcial: Sistema de turnos de un banco**

**Estudiante:** Anderson Arley Salamanca Jarro  
**Materia:** Estructura de Datos  
**Fecha:** Mayo 2026

---

## **De qué se trata este programa**
Este es el código que armé para mi **parcial de Estructura de Datos**. El programa básicamente simula la **fila de un banco** para organizar los turnos de los clientes. Al principio intenté separar todo en varias carpetas para el modelo y la vista, pero me estaba dando muchos **errores con las rutas** de los archivos, así que decidí dejar todo en la **carpeta principal**. De esta forma el código es mucho más **fácil de ejecutar** y no se rompe al moverlo de carpeta o de computador.

---

## **Cómo hacerlo funcionar**
Para ver el programa funcionando solo hay que hacer lo siguiente:

1. Abrir la **carpeta del proyecto** en **VS Code**
git commit -m "Documentacion final terminada"
git push origin main --force
2. Entrar al archivo llamado **MenuPrincipal.java**.
3. Darle al **botón de Play** que sale arriba a la derecha.

Si prefieres usar la **consola**, puedes compilarlo con el comando **`javac *.java`** y luego arrancarlo escribiendo **`java MenuPrincipal`**.

---

## **Lo que aprendí en este proceso**
Hacer este trabajo me sirvió para entender varios detalles que a veces uno pasa por alto:

* **Manejo de archivos:** Me di cuenta de que a veces complicar la estructura con muchas carpetas solo hace que el código sea más difícil de correr en otros equipos. **Dejarlo sencillo** me ahorró muchos problemas.
* **Archivos compilados:** Ya entendí que los **archivos .class** son solo traducciones que hace Java y que no necesito estarlos viendo en mi lista de archivos, por eso configuré el **.gitignore** para que no se suban a mi repositorio.
* **Configuración del editor:** Aprendí que **VS Code** marca muchos errores que no existen si abro los archivos por separado. La solución fue **abrir la carpeta completa** para que el editor reconozca que todos los archivos trabajan juntos.

---

## **Lógica de la fila (FIFO)**
El sistema funciona bajo la lógica de una **cola o Queue**. Apliqué el concepto de **FIFO** (**lo primero que entra es lo primero que sale**). Básicamente es como una **fila de la vida real**: el cliente que llega primero es el primero que debe ser atendido, y los que van llegando se van poniendo al **final de la fila**.

---

## **Cómo está organizado el código**
Dividí el trabajo en **tres partes principales** para que fuera más claro:

1.  **Cliente:** Aquí guardo los **datos básicos** de la persona, como el nombre, el documento, el trámite que va a hacer, la hora de llegada y si tiene prioridad.
2.  **ColaBanco:** Este archivo tiene toda la **lógica de la fila** (encolar, desencolar, ver quién sigue) y es el que se encarga de **guardar y cargar los datos** automáticamente en un archivo para que la información no se borre al cerrar el programa.
3.  **MenuPrincipal:** Es la parte que sale en la **consola** para que el usuario pueda interactuar con el sistema, agregar personas o atender los turnos.

---
## **Capturas**
Menu Principal
<img width="584" height="213" alt="MenuPrincipal" src="https://github.com/user-attachments/assets/432bed07-0fa8-41a5-beae-b9510d1f6e24" />
1. Opción
<img width="851" height="861" alt="Opcion1" src="https://github.com/user-attachments/assets/4ef86f2d-9fb4-459b-9d27-1370b2ffe916" />
2. Opción
<img width="1059" height="244" alt="Opcion2" src="https://github.com/user-attachments/assets/354d0738-38e9-4689-9093-8f96f4acf1be" />
3. Opción
<img width="1043" height="245" alt="Opcion3" src="https://github.com/user-attachments/assets/69ac2d1f-f74f-4cfb-8eaa-597476a54a54" />
4. Opción
<img width="875" height="304" alt="Opcion4" src="https://github.com/user-attachments/assets/bc289c6e-dc74-446d-addb-d1a8c5935da2" />
5. Opción 
<img width="464" height="246" alt="Opcion5" src="https://github.com/user-attachments/assets/857fc460-24a5-4498-9b0c-9a4e37c7fbb8" />
6. Opción
<img width="579" height="237" alt="Opcion6" src="https://github.com/user-attachments/assets/53b060ec-c101-4036-99ea-2e7224bac389" />






## **Diagrama de clases**
Este es el mapa técnico de cómo se conectan los archivos que programé:

```mermaid
classDiagram
    Main ..> ColaBanco : usa
    ColaBanco "1" *-- "*" Cliente : contiene
    ColaBanco ..> Queue : usa
    Queue <|.. LinkedList : implementa

    class Main {
        -colaBanco: ColaBanco
        -scanner: Scanner
        +main(args: String[])$ void
        -mostrarMenu() void
        -procesarOpcion(opcion: int) void
        -agregarCliente() void
        -atenderCliente() void
        -verProximoCliente() void
        -consultarCantidad() void
        -vaciarCola() void
    }

    class ColaBanco {
        -cola: Queue~Cliente~
        -ARCHIVO_BACKUP: String
        +ColaBanco()
        +encolar(cliente: Cliente) void
        +desencolar() Cliente
        +verProximo() Cliente
        +estaVacia() boolean
        +tamaño() int
        +vaciar() void
        +mostrarTodos() void
        -guardarEstado() void
        -cargarEstado() void
    }

    class Cliente {
        -nombre: String
        -identificacion: String
        -tipoTransaccion: String
        -horaLlegada: LocalTime
        -prioridad: boolean
        +Cliente(nombre, id, tipo, hora, prioridad)
        +toString() String
    }

    class Queue {
        <<interface>>
        +offer(e)
        +poll()
        +peek()
    }

    class LinkedList {
    }

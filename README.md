# 🏦 Mi Proyecto: Fila del Banco (FIFO)

**Estudiante:** Anderson Arley Salamanca Jarro  
**Fecha:** Mayo 2026  

---

## 📝 ¿De qué trata este programa?
Este es el trabajo para el parcial de Estructura de Datos. Hice un sistema que maneja los turnos de un banco de forma automática. Aunque al principio probé con muchas carpetas, decidí dejar los archivos principales en la carpeta raíz para que el programa sea más sencillo de ejecutar y no dé errores de rutas. El código sigue respetando la lógica de separar los datos, la fila y el menú.

---

## 🚀 Cómo ponerlo a andar

¡Ahora es mucho más fácil! Como el proyecto ya está bien configurado, tienes dos opciones:

1. **La más fácil:** Abre la carpeta en VS Code, ve al archivo `MenuPrincipal.java` y dale al botón de **"Run"** o al triángulo de **"Play"** arriba a la derecha. ¡Y listo!
2. **Desde la terminal (por si falla lo anterior):**
   - Asegúrate de estar en la carpeta del proyecto.
   - Compila con: `javac *.java`
   - Ejecuta con: `java MenuPrincipal`

---

## 🛠️ Lo que aprendí en el proceso

Este proyecto me sirvió para entender varios detalles técnicos que suelen pasar en Java:

* **Organización de archivos:** Aprendí que a veces, para proyectos escolares, es mejor tener los archivos a la mano para evitar líos con los "packages" y las rutas de la terminal.
* **Archivos .class:** Entendí que estos archivos son la "traducción" que hace Java para poder correr el programa. No hace falta tocarlos ni subirlos a GitHub, para eso creé un archivo `.gitignore`.
* **Configuración del Editor:** Logré que el VS Code dejara de marcar errores amarillos simplemente abriendo la carpeta completa del proyecto en lugar de los archivos sueltos.

---

## 🧠 Lógica de la Fila (FIFO)
El corazón del programa es una estructura llamada **Queue** (Cola). Funciona con el sistema **FIFO** (First In, First Out), que es el mismo de la vida real:
- El cliente que llega primero es el primero que se atiende.
- Los nuevos siempre van para el final de la fila.
- Así el sistema es justo y nadie se cuela.

---

## 📊 Estructura del Código
El programa se divide en tres partes que trabajan juntas:
1. **Cliente:** Guarda la info (nombre, ID, trámite).
2. **ColaBanco:** Maneja la fila y guarda los datos para que no se pierdan al cerrar el programa.
3. **MenuPrincipal:** Es lo que tú ves en pantalla para interactuar.

---


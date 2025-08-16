📱 MyAppMovil - ToDo List

Aplicación móvil desarrollada en Android para la gestión de tareas pendientes (ToDo List).
Permite crear, visualizar y eliminar tareas de manera sencilla con una interfaz amigable.

🚀 Tecnologías utilizadas

Lenguajes: Java y Kotlin

Framework: Android SDK

Interfaz de usuario: XML con RecyclerView y Adapters

Gestión de dependencias: Gradle (Kotlin DSL)

📂 Estructura del proyecto
MyAppMovil-main/
│── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/sena/todolist/
│   │   │   │   ├── MainActivity.java      # Actividad principal
│   │   │   │   ├── Task.java              # Modelo de datos de una tarea
│   │   │   │   ├── TaskAdapter.java       # Adaptador para RecyclerView
│   │   │   │   ├── TaskManager.java       # Gestión de lista de tareas
│   │   │   ├── res/layout/
│   │   │   │   ├── activity_main.xml      # Layout principal
│   │   │   │   ├── item_task.xml          # Layout para cada tarea
│   │   │   ├── res/drawable/              # Fondos y estilos personalizados
│   │   │   ├── AndroidManifest.xml        # Configuración de la app
│── build.gradle.kts
│── settings.gradle.kts
│── gradle.properties

⚙️ Funcionalidades principales

✔️ Agregar nuevas tareas
✔️ Visualizar lista de tareas en un RecyclerView
✔️ Eliminar tareas
✔️ Estilos personalizados para botones y campos de texto
✔️ Interfaz adaptada a Material Design

📲 Instalación y ejecución

Clonar este repositorio:

git clone https://github.com/usuario/MyAppMovil.git


Abrir el proyecto en Android Studio.

Sincronizar dependencias con Gradle.

Ejecutar en un emulador o dispositivo Android con versión mínima API 24 (Android 7.0 Nougat).

👨‍💻 Autores

Proyecto desarrollado por [Tu Nombre / Equipo] en el marco de formación académica en desarrollo de software.

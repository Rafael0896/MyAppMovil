# 📱 MyAppMovil - ToDo List

Aplicación móvil desarrollada en **Android** para la gestión de tareas pendientes (ToDo List). Permite crear, visualizar y eliminar tareas de manera sencilla con una interfaz amigable.

<p>
  <img src="https://github.com/user-attachments/assets/a27fc075-c4b2-4d8f-8305-4801ac1fb904" 
       alt="IMG_20250818_111944" 
       width="300">
</p>


## 🚀 Tecnologías utilizadas

- **Lenguajes:** Java y Kotlin
- **Framework:** Android SDK
- **Interfaz de usuario:** XML con `RecyclerView` y `Adapters`
- **Gestión de dependencias:** Gradle (Kotlin DSL)

## 📂 Estructura del proyecto

```
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
```

## ⚙️ Funcionalidades principales

- ✅ Agregar nuevas tareas
- ✅ Visualizar lista de tareas en un `RecyclerView`
- ✅ Eliminar tareas
- ✅ Estilos personalizados para botones y campos de texto
- ✅ Interfaz adaptada a Material Design

## 📲 Instalación y ejecución

1. **Clonar este repositorio:**
   ```bash
   git clone https://github.com/usuario/MyAppMovil.git
   ```

2. **Abrir el proyecto** en Android Studio.

3. **Sincronizar dependencias** con Gradle.

4. **Ejecutar** en un emulador o dispositivo Android con versión mínima **API 24 (Android 7.0 Nougat)**.

## 👨‍💻 Autores

Proyecto desarrollado por Rafael Alvarez en el marco de formación académica en desarrollo de software.

## 📋 Requisitos del sistema

- **Android Studio:** 4.0 o superior
- **JDK:** 8 o superior
- **Android SDK:** API 24+
- **Gradle:** 7.0+

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para detalles.

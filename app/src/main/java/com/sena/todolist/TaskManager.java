// TaskManager.java
package com.sena.todolist;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la lista de tareas.
 * Proporciona métodos para añadir, eliminar y modificar tareas.
 */
public class TaskManager {

    private List<Task> tasks;

    // Constructor
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Añadir una nueva tarea.
     * @param text Texto de la tarea
     */
    public void addTask(String text) {
        if (text != null && !text.trim().isEmpty()) {
            Task newTask = new Task(text.trim());
            tasks.add(newTask);
        }
    }

    /**
     * Eliminar una tarea por ID.
     * @param taskId ID de la tarea a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean removeTask(long taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Alternar el estado de completado de una tarea.
     * @param taskId ID de la tarea
     * @return true si se cambió el estado, false si no se encontró
     */
    public boolean toggleTask(long taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.toggleCompleted();
                return true;
            }
        }
        return false;
    }

    /**
     * Obtener todas las tareas.
     * @return Lista con todas las tareas (copia)
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Obtener número total de tareas.
     * @return Cantidad de tareas
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Obtener número de tareas completadas.
     * @return Cantidad de tareas completadas
     */
    public int getCompletedTaskCount() {
        int count = 0;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                count++;
            }
        }
        return count;
    }
}

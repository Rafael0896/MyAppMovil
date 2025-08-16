// Task.java
package com.sena.todolist;

/**
 * Modelo de datos para representar una tarea.
 * Esta clase encapsula toda la información de una tarea individual.
 */
public class Task {

    // =========================
    // Propiedades privadas
    // =========================
    private long id;          // ID único de la tarea
    private String text;      // Texto de la tarea
    private boolean completed; // Estado: completada o no

    // =========================
    // Constructores
    // =========================

    /**
     * Constructor vacío (necesario para algunas operaciones).
     * Asigna un ID basado en el timestamp actual y estado no completado.
     */
    public Task() {
        this.id = System.currentTimeMillis();
        this.completed = false;
    }

    /**
     * Constructor con texto.
     * @param text Texto de la tarea
     */
    public Task(String text) {
        this();
        this.text = text;
    }

    /**
     * Constructor completo.
     * @param id ID de la tarea
     * @param text Texto de la tarea
     * @param completed Estado de la tarea
     */
    public Task(long id, String text, boolean completed) {
        this.id = id;
        this.text = text;
        this.completed = completed;
    }

    // =========================
    // Métodos Getter y Setter
    // =========================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // =========================
    // Métodos adicionales
    // =========================

    /**
     * Alterna el estado de completado.
     */
    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    /**
     * Representación en texto para debugging y logging.
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                '}';
    }

    /**
     * Compara dos tareas por su ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id;
    }

    /**
     * Genera el hashCode basado en el ID.
     */
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

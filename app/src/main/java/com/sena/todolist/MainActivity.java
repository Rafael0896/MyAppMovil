// MainActivity.java
package com.sena.todolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Actividad principal de la aplicación
 * Maneja la interfaz de usuario y la lógica de la aplicación
 */
public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    // Constantes para SharedPreferences
    private static final String PREFS_NAME = "TodoAppPrefs";
    private static final String TASKS_KEY = "tasks";

    // Componentes de la interfaz
    private EditText editTextTask;
    private Button buttonAddTask;
    private RecyclerView recyclerViewTasks;

    // Datos y adaptador
    private TaskManager taskManager;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        initializeViews();
        initializeData();
        setupRecyclerView();
        setupListeners();

        // Cargar tareas guardadas
        loadTasks();
    }

    /** Inicializar las vistas de la interfaz */
    private void initializeViews() {
        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
    }

    /** Inicializar los datos y el gestor de tareas */
    private void initializeData() {
        taskManager = new TaskManager();
        taskList = new ArrayList<>();
    }

    /** Configurar el RecyclerView */
    private void setupRecyclerView() {
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);
    }

    /** Configurar los listeners de los botones */
    private void setupListeners() {
        buttonAddTask.setOnClickListener(v -> addNewTask());

        // También permitir añadir tarea presionando Enter
        editTextTask.setOnEditorActionListener((v, actionId, event) -> {
            addNewTask();
            return true;
        });
    }

    /** Añadir una nueva tarea */
    private void addNewTask() {
        String taskText = editTextTask.getText().toString().trim();

        if (taskText.isEmpty()) {
            Toast.makeText(this, "Por favor, escribe una tarea", Toast.LENGTH_SHORT).show();
            return;
        }

        if (taskText.length() < 3) {
            Toast.makeText(this, "La tarea debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        taskManager.addTask(taskText);
        editTextTask.setText("");
        updateTaskList();

        Toast.makeText(this, "Tarea añadida: " + taskText, Toast.LENGTH_SHORT).show();
        saveTasks();
    }

    /** Actualizar la lista de tareas en la interfaz */
    private void updateTaskList() {
        taskList.clear();
        taskList.addAll(taskManager.getAllTasks());
        taskAdapter.notifyDataSetChanged();
    }

    // Implementación de OnTaskClickListener
    @Override
    public void onTaskToggle(Task task) {
        taskManager.toggleTask(task.getId());
        taskAdapter.notifyDataSetChanged();

        String message = task.isCompleted()
                ? "Tarea completada"
                : "Tarea marcada como pendiente";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        saveTasks();
    }

    @Override
    public void onTaskDelete(Task task) {
        boolean removed = taskManager.removeTask(task.getId());
        if (removed) {
            updateTaskList();
            Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
            saveTasks();
        }
    }

    /** Guardar las tareas en SharedPreferences */
    private void saveTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(taskManager.getAllTasks());

        editor.putString(TASKS_KEY, json);
        editor.apply();
    }

    /** Cargar las tareas desde SharedPreferences */
    private void loadTasks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(TASKS_KEY, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Task>>() {}.getType();
            List<Task> savedTasks = gson.fromJson(json, type);

            for (Task task : savedTasks) {
                taskManager.addTask(task.getText());
                if (task.isCompleted()) {
                    taskManager.toggleTask(task.getId());
                }
            }
            updateTaskList();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTasks();
    }

    private void showTaskStatistics() {
        int totalTasks = taskManager.getTaskCount();
        int completedTasks = taskManager.getCompletedTaskCount();
        int pendingTasks = totalTasks - completedTasks;

        String stats = String.format(
                "Total: %d | Completadas: %d | Pendientes: %d",
                totalTasks, completedTasks, pendingTasks
        );

        Toast.makeText(this, stats, Toast.LENGTH_LONG).show();
    }

    /**
     * Limpiar todas las tareas completadas
     */
    private void clearCompletedTasks() {
        List<Task> allTasks = taskManager.getAllTasks();
        List<Task> tasksToRemove = new ArrayList<>();

        // Encontrar tareas completadas
        for (Task task : allTasks) {
            if (task.isCompleted()) {
                tasksToRemove.add(task);
            }
        }

        // Eliminar tareas completadas
        for (Task task : tasksToRemove) {
            taskManager.removeTask(task.getId());
        }

        // Actualizar interfaz
        updateTaskList();
        saveTasks();

        Toast.makeText(this, "Tareas completadas eliminadas", Toast.LENGTH_SHORT).show();
    }

    /**
     * Validar entrada de texto mejorada
     */
    private boolean isValidTaskText(String text) {
        if (text == null || text.trim().isEmpty()) {
            Toast.makeText(this, "Por favor, escribe una tarea", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (text.trim().length() < 3) {
            Toast.makeText(this, "La tarea debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (text.trim().length() > 100) {
            Toast.makeText(this, "La tarea no puede tener más de 100 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Método mejorado para añadir tareas
     */
    private void addNewTaskImproved() {
        String taskText = editTextTask.getText().toString().trim();

        // Usar validación mejorada
        if (!isValidTaskText(taskText)) {
            return;
        }

        // Verificar si la tarea ya existe
        for (Task existingTask : taskManager.getAllTasks()) {
            if (existingTask.getText().equalsIgnoreCase(taskText)) {
                Toast.makeText(this, "Esta tarea ya existe", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Añadir la tarea
        taskManager.addTask(taskText);
        editTextTask.setText("");
        updateTaskList();

        Toast.makeText(this, "✅ Tarea añadida: " + taskText, Toast.LENGTH_SHORT).show();
        saveTasks();
    }
}

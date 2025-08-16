// TaskAdapter.java
package com.sena.todolist;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para conectar la lista de tareas con el RecyclerView.
 * Maneja la creación y actualización de elementos visuales.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private OnTaskClickListener listener;

    // Interface para manejar clicks en las tareas
    public interface OnTaskClickListener {
        void onTaskToggle(Task task);
        void onTaskDelete(Task task);
    }

    // Constructor del adapter
    public TaskAdapter(List<Task> tasks, OnTaskClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    // Crear nuevos ViewHolders (se llama cuando se necesita un nuevo elemento)
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del elemento individual
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    // Vincular datos con ViewHolder (se llama para cada elemento visible)
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    // Retornar el número total de elementos
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Actualizar la lista de tareas
    public void updateTasks(List<Task> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged(); // Notifica que los datos cambiaron
    }

    /**
     * ViewHolder: Contiene las referencias a las vistas de cada elemento.
     * Patrón ViewHolder para mejorar el rendimiento.
     */
    class TaskViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBoxCompleted;
        private TextView textViewTask;
        private ImageButton buttonDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            // Encontrar las vistas por su ID
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        // Vincular una tarea específica con las vistas
        public void bind(Task task) {
            // Establecer el texto de la tarea
            textViewTask.setText(task.getText());

            // Establecer el estado del checkbox
            checkBoxCompleted.setChecked(task.isCompleted());

            // Aplicar estilo de tachado si está completada
            if (task.isCompleted()) {
                textViewTask.setPaintFlags(textViewTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                textViewTask.setAlpha(0.6f);
            } else {
                textViewTask.setPaintFlags(textViewTask.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                textViewTask.setAlpha(1.0f);
            }

            // Configurar listener para el checkbox
            checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (listener != null) {
                    listener.onTaskToggle(task);
                }
            });

            // Configurar listener para el botón de eliminar
            buttonDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTaskDelete(task);
                }
            });
        }
    }
}

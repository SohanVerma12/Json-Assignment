package com.example.api_call;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Todo> todos;

    public ListAdapter() {
        todos = new ArrayList<>();
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.userIdTextView.setText("User ID: " + todo.getUserId());
        holder.idTextView.setText("ID: " + todo.getId());
        holder.titleTextView.setText("Title: " + todo.getTitle());
        holder.completedTextView.setText("Completed: " + (todo.isCompleted() ? "Yes" : "No"));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userIdTextView;
        TextView idTextView;
        TextView titleTextView;
        TextView completedTextView;

        ViewHolder(View itemView) {
            super(itemView);
            userIdTextView = itemView.findViewById(R.id.userIdTextView);
            idTextView = itemView.findViewById(R.id.idTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            completedTextView = itemView.findViewById(R.id.completedTextView);
        }
    }
}

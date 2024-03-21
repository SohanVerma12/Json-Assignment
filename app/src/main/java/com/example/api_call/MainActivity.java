package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements FetchDataTask.OnDataFetchedListener {
    private RecyclerView recyclerView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        // Execute the AsyncTask
        new FetchDataTask(this).execute();
    }

    @Override
    public void onDataFetched(String result) {
        // json response
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int userId = jsonObject.getInt("userId");
                int id = jsonObject.getInt("id");
                String title = jsonObject.getString("title");
                boolean completed = jsonObject.getBoolean("completed");

                Todo todo = new Todo(userId, id, title, completed);
                adapter.addTodo(todo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


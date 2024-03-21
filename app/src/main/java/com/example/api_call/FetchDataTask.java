package com.example.api_call;

import android.os.AsyncTask;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class FetchDataTask extends AsyncTask<Void, Void, String> {
    private OnDataFetchedListener listener;

    public interface OnDataFetchedListener {
        void onDataFetched(String result);
    }

    public FetchDataTask(OnDataFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/todos/")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            listener.onDataFetched(s);
        }
    }
}

package com.example.jokeappbajud.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jokeappbajud.Adapter.JokeAdapter;
import com.example.jokeappbajud.R;
import com.example.jokeappbajud.model.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Jokes extends Fragment {
    public static final String TAG = "TAG";
    String jokesUrl;
    RecyclerView jokesList;
    JokeAdapter adapter;
    List<Joke> jokes;

    public Jokes(String Url) {
        this.jokesUrl = Url;
        jokes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jokes, container, false);
        jokesList = v.findViewById(R.id.jokesList);
        adapter = new JokeAdapter(jokes);
        jokesList.setLayoutManager(new LinearLayoutManager(getContext()));
        jokesList.setAdapter(adapter);
        getJokes(jokesUrl);
        return v;
    }

    public void getJokes(String url) {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jokesArray = response.getJSONArray("jokes");
                        int amount = Integer.parseInt(response.getString("amount"));

                        for (int i = 0; i < amount; i++) {
                            JSONObject jokeData = jokesArray.getJSONObject(i);
                            Joke j = new Joke();
                            j.setType(jokeData.getString("type"));

                            if (jokeData.getString("type").equals("single")) {
                                j.setJoke(jokeData.getString("joke"));
                            } else {
                                j.setSetup(jokeData.getString("setup"));
                                j.setDelivery(jokeData.getString("delivery"));
                            }

                            jokes.add(j);
                        }

                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage())
        );

        queue.add(objectRequest);
    }
}

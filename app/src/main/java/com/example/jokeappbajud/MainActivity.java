package com.example.jokeappbajud;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jokeappbajud.Adapter.JokeCatAdapter;
import com.example.jokeappbajud.fragment.Jokes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView jokeCatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> cats = Arrays.asList("Any", "Programming", "Dark", "Spooky", "Misc", "Pun", "Christmas");

        jokeCatList = findViewById(R.id.jokeCatList);
        jokeCatList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        jokeCatList.setAdapter(new JokeCatAdapter(cats));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, new Jokes("https://v2.jokeapi.dev/joke/Any?amount=10"))
                .commit();
    }
}

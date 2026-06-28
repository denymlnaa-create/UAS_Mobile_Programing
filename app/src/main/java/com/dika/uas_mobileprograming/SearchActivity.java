package com.dika.uas_mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rvSearch;
    private TextView tvEmpty;
    private EndemikAdapter adapter;
    private List<Endemik> list = new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cari");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvSearch = findViewById(R.id.rv_search);
        tvEmpty = findViewById(R.id.tv_empty_search);
        SearchView searchView = findViewById(R.id.search_view);
        db = AppDatabase.getInstance(this);

        setupRecyclerView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                    tvEmpty.setVisibility(View.GONE);
                } else {
                    searchData(newText);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new EndemikAdapter(list);
        rvSearch.setAdapter(adapter);
        adapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra("DATA", data);
            startActivity(intent);
        });
    }

    private void searchData(String query) {
        new Thread(() -> {
            List<Endemik> searchResult = db.endemikDao().searchEndemik(query);
            runOnUiThread(() -> {
                list.clear();
                list.addAll(searchResult);
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
            });
        }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

package com.dika.uas_mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView rvFavorite;
    private TextView tvEmpty;
    private EndemikAdapter adapter;
    private List<Endemik> list = new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Favorit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvFavorite = findViewById(R.id.rv_favorite);
        tvEmpty = findViewById(R.id.tv_empty_favorite);
        db = AppDatabase.getInstance(this);

        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void setupRecyclerView() {
        adapter = new EndemikAdapter(list);
        rvFavorite.setAdapter(adapter);
        adapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
            intent.putExtra("DATA", data);
            startActivity(intent);
        });
    }

    private void loadData() {
        new Thread(() -> {
            List<Favorit> favoriteList = db.endemikDao().getAllFavorit();
            List<Endemik> endemikList = new ArrayList<>();
            for (Favorit f : favoriteList) {
                Endemik e = new Endemik();
                e.setId(f.getId());
                e.setNama(f.getNama());
                e.setNamaLatin(f.getNamaLatin());
                e.setDeskripsi(f.getDeskripsi());
                e.setFoto(f.getFoto());
                e.setAsal(f.getAsal());
                e.setTipe(f.getTipe());
                // Set other fields if needed
                endemikList.add(e);
            }
            
            runOnUiThread(() -> {
                list.clear();
                list.addAll(endemikList);
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

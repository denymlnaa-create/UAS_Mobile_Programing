package com.dika.uas_mobileprograming;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    private boolean isFavorite = false;
    private AppDatabase db;
    private Endemik endemik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = AppDatabase.getInstance(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imgDetail = findViewById(R.id.img_detail_photo);
        TextView tvName = findViewById(R.id.tv_detail_name);
        TextView tvLatin = findViewById(R.id.tv_detail_latin);
        TextView tvStatus = findViewById(R.id.tv_detail_status);
        TextView tvDescription = findViewById(R.id.tv_detail_description);
        TextView tvOrigin = findViewById(R.id.tv_detail_origin);
        FloatingActionButton fabFavorite = findViewById(R.id.fab_favorite);

        endemik = (Endemik) getIntent().getSerializableExtra("DATA");

        if (endemik != null) {
            tvName.setText(endemik.getNama());
            tvLatin.setText(endemik.getNamaLatin());
            tvStatus.setText(endemik.getStatus());
            tvDescription.setText(endemik.getDeskripsi());
            tvOrigin.setText(endemik.getAsal());

            Glide.with(this)
                    .load(endemik.getFoto())
                    .placeholder(android.R.color.darker_gray)
                    .into(imgDetail);
            
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Detail " + endemik.getNama());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            if (endemik.getStatus() != null && endemik.getStatus().toLowerCase().contains("punah")) {
                tvStatus.setBackgroundResource(R.drawable.bg_status_badge_red);
            }

            checkFavoriteStatus(fabFavorite);

            fabFavorite.setOnClickListener(v -> {
                if (isFavorite) {
                    removeFromFavorite(fabFavorite);
                } else {
                    addToFavorite(fabFavorite);
                }
            });
        }
    }

    private void checkFavoriteStatus(FloatingActionButton fab) {
        new Thread(() -> {
            Favorit f = db.endemikDao().getFavoritById(endemik.getId());
            isFavorite = (f != null);
            runOnUiThread(() -> {
                if (isFavorite) {
                    fab.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    fab.setImageResource(android.R.drawable.btn_star_big_off);
                }
            });
        }).start();
    }

    private void addToFavorite(FloatingActionButton fab) {
        new Thread(() -> {
            db.endemikDao().insertFavorit(new Favorit(endemik));
            isFavorite = true;
            runOnUiThread(() -> {
                fab.setImageResource(android.R.drawable.btn_star_big_on);
                Toast.makeText(this, "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void removeFromFavorite(FloatingActionButton fab) {
        new Thread(() -> {
            db.endemikDao().deleteFavorit(new Favorit(endemik));
            isFavorite = false;
            runOnUiThread(() -> {
                fab.setImageResource(android.R.drawable.btn_star_big_off);
                Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

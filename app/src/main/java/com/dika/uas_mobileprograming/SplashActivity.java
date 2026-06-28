package com.dika.uas_mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db = AppDatabase.getInstance(this);
        tvStatus = findViewById(R.id.tv_status);

        tvStatus.setVisibility(View.INVISIBLE);

        checkDataAndFetch();

        tvStatus.setOnClickListener(v -> {
            moveToMain();
        });
    }

    private void checkDataAndFetch() {
        new Thread(() -> {
            int count = db.endemikDao().getEndemikCount();
            if (count == 0) {
                fetchDataFromApi();
            } else {
                runOnUiThread(() -> {
                    tvStatus.setText("Data siap, klik untuk lanjut");
                    tvStatus.setVisibility(View.VISIBLE);
                });
            }
        }).start();
    }

    private void fetchDataFromApi() {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.getEndemikList().enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveToDatabase(response.body());
                } else {
                    handleError("Gagal mengambil data");
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                handleError("Koneksi gagal: " + t.getMessage());
            }
        });
    }

    private void saveToDatabase(List<Endemik> list) {
        new Thread(() -> {
            db.endemikDao().insertAllEndemik(list);
            runOnUiThread(() -> {
                tvStatus.setText("Data tersimpan, lanjut");
                tvStatus.setVisibility(View.VISIBLE);
            });
        }).start();
    }

    private void handleError(String message) {
        runOnUiThread(() -> {
            Toast.makeText(SplashActivity.this, message, Toast.LENGTH_LONG).show();
            tvStatus.setText("Gagal, coba klik untuk lanjut");
            tvStatus.setVisibility(View.VISIBLE);
        });
    }

    private void moveToMain() {
        if (!isFinishing()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
}

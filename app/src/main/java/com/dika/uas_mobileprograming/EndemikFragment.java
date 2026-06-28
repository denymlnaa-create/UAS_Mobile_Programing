package com.dika.uas_mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EndemikFragment extends Fragment {

    private static final String ARG_TIPE = "tipe";
    private String tipe;
    private RecyclerView rvEndemik;
    private TextView tvEmpty;
    private EndemikAdapter adapter;
    private List<Endemik> list = new ArrayList<>();
    private AppDatabase db;

    public static EndemikFragment newInstance(String tipe) {
        EndemikFragment fragment = new EndemikFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TIPE, tipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipe = getArguments().getString(ARG_TIPE);
        }
        db = AppDatabase.getInstance(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_endemik, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvEndemik = view.findViewById(R.id.rv_endemik);
        tvEmpty = view.findViewById(R.id.tv_empty);

        setupRecyclerView();
        loadData();
    }

    private void setupRecyclerView() {
        adapter = new EndemikAdapter(list);
        rvEndemik.setAdapter(adapter);
        adapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("DATA", data);
            startActivity(intent);
        });
    }

    public void loadData() {
        new Thread(() -> {
            List<Endemik> dataFromDb = db.endemikDao().getEndemikByType(tipe);
            if (isAdded()) {
                requireActivity().runOnUiThread(() -> {
                    list.clear();
                    list.addAll(dataFromDb);
                    adapter.notifyDataSetChanged();
                    tvEmpty.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
                });
            }
        }).start();
    }

    public void searchData(String query) {
        new Thread(() -> {
            List<Endemik> searchResult;
            if (query == null || query.isEmpty()) {
                searchResult = db.endemikDao().getEndemikByType(tipe);
            } else {
                List<Endemik> allMatch = db.endemikDao().searchEndemik(query);
                searchResult = new ArrayList<>();
                for (Endemik e : allMatch) {
                    if (e.getTipe().equalsIgnoreCase(tipe)) {
                        searchResult.add(e);
                    }
                }
            }
            if (isAdded()) {
                requireActivity().runOnUiThread(() -> {
                    list.clear();
                    list.addAll(searchResult);
                    adapter.notifyDataSetChanged();
                    tvEmpty.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
                });
            }
        }).start();
    }
}

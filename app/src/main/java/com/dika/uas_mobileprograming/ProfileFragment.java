package com.dika.uas_mobileprograming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgProfile = view.findViewById(R.id.img_profile);
        TextView tvName = view.findViewById(R.id.tv_profile_name);
        TextView tvProdi = view.findViewById(R.id.tv_profile_prodi);
        TextView tvRegion = view.findViewById(R.id.tv_profile_region);

        if (tvName != null) {
            tvName.setText(getString(R.string.profile_name));
        }
        if (tvProdi != null) {
            tvProdi.setText(getString(R.string.profile_prodi));
        }
        if (tvRegion != null) {
            tvRegion.setText(getString(R.string.profile_Kampus));
        }

        Glide.with(this)
                .load(R.drawable.profile)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image)
                .apply(RequestOptions.circleCropTransform())
                .into(imgProfile);
    }
}

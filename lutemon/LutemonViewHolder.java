package com.olio.lutemon;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    ImageView lutemonImage, imgInfo;
    TextView lutemonName, lutemonType, lutemonHP, lutemonLvl;


    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);

        lutemonImage = itemView.findViewById(R.id.imageLutemon);
        lutemonName = itemView.findViewById(R.id.txtName);
        lutemonType = itemView.findViewById(R.id.txtType);
        lutemonHP = itemView.findViewById(R.id.txtHP);
        lutemonLvl = itemView.findViewById(R.id.txtLvl);
        imgInfo = itemView.findViewById(R.id.imgInfo);

    }
}
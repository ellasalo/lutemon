package com.olio.lutemon.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olio.lutemon.FightActivity;
import com.olio.lutemon.LutemonClasses.Black;
import com.olio.lutemon.LutemonClasses.Green;
import com.olio.lutemon.LutemonClasses.Lutemon;
import com.olio.lutemon.LutemonClasses.Opponent;
import com.olio.lutemon.LutemonClasses.Orange;
import com.olio.lutemon.LutemonClasses.Pink;
import com.olio.lutemon.LutemonClasses.Storage;
import com.olio.lutemon.LutemonClasses.White;
import com.olio.lutemon.LutemonListAdapter;
import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

public class TrainingCenterFragment extends Fragment {

    private ArrayList<Opponent> opponents = new ArrayList<>();
    private Opponent opponent;
    private View view;
    private ImageView imgTrash;
    private ImageView imgTricky;
    private ImageView imgShock;
    private ImageView imgIdle;
    private ImageView imgTrombi;
    private ImageView imgMaster;
    private TextView tViewSelected;

    private Button btnStartBattle;
    private EditText inputNickName;
    private String opponentName;
    private int level;
    private int XP;

    public TrainingCenterFragment() {
        // Required empty public constructor
    }


    public static TrainingCenterFragment newInstance(String param1, String param2) {
        TrainingCenterFragment fragment = new TrainingCenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_training_center, container, false);
        return view;

    }

    private Opponent createOpponent(String name, int level, int XP){
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        lutemons.add(new Black("lutemon"));
        lutemons.add(new Orange("lutemon"));
        lutemons.add(new Pink("lutemon"));
        lutemons.add(new Green("lutemon"));
        lutemons.add(new White("lutemon"));

        for (Lutemon lutemon: lutemons) {
            for (int i = 1; i < level; i++){
                lutemon.levelUP();
            }
        }
        Opponent opponent = new Opponent(name, 0, XP);
        opponent.setLutemons(lutemons);
        return opponent;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tViewSelected = view.findViewById(R.id.tViewSelected);
        tViewSelected.setText(" ");

        inputNickName = view.findViewById(R.id.editTxtPickLutemon);



        imgTrash = view.findViewById(R.id.imgTrash);
        imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentName = "Trash";
                level = 0;
                XP = 10;
                tViewSelected.setText(opponentName);
            }
        });
        imgTricky = view.findViewById(R.id.imgTricky);
        imgTricky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentName = "Tricky";
                level = 12;
                XP = 40;
                tViewSelected.setText(opponentName);
            }
        });
        imgShock = view.findViewById(R.id.imgShock);
        imgShock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentName = "Shock";
                level = 24;
                XP = 80;
                tViewSelected.setText(opponentName);
            }
        });
        imgIdle = view.findViewById(R.id.imgIdle);
        imgIdle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentName = "Idle";
                level = 40;
                XP = 200;
                tViewSelected.setText(opponentName);
            }
        });
        imgTrombi = view.findViewById(R.id.imgTrombi);
        imgTrombi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentName = "Trombi";
                level = 57;
                XP = 300;
                tViewSelected.setText(opponentName);
            }
        });
        imgMaster = view.findViewById(R.id.imgMaster);
        imgMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentName = "Master";
                level = 80;
                XP = 400;
                tViewSelected.setText(opponentName);
            }
        });

        btnStartBattle = view.findViewById(R.id.btnToBattle);
        btnStartBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tViewSelected.getText().toString().equals(" ")) {
                    Toast.makeText(getContext(), "Valitse ensin vastustaja", Toast.LENGTH_LONG).show();
                    return;
                }
                if (inputNickName.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Valitse ensin lutemon", Toast.LENGTH_LONG).show();
                    return;
                } else if (Storage.getInstance().getLutemonByNickName(inputNickName.getText().toString().toLowerCase()) == null) {
                    Toast.makeText(getContext(), "Antamallasi nimellä ei löytynyt lutemonia.", Toast.LENGTH_LONG).show();
                    return;
                } else if (Storage.getInstance().getLutemonByNickName(inputNickName.getText().toString().toLowerCase()).getStats().getLeftHP() == 0) {
                    Toast.makeText(getContext(), "Lutemonillasi ei ole enää yhtään elämää.\nLepuuta se ensin.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getContext(), FightActivity.class);
                Random rand = new Random();
                intent.putExtra("ownLutemon", Storage.getInstance().getLutemonByNickName(inputNickName.getText().toString().toLowerCase()));
                opponent = createOpponent(opponentName, level, XP);
                Lutemon opponentLutemon = opponent.getLutemons().get(rand.nextInt(5));
                opponentLutemon.getStats().setLeftHP(opponentLutemon.getStats().getMaxHP());
                intent.putExtra("opponentLutemon", opponentLutemon);
                Bundle bundle = new Bundle();
                bundle.putInt("XP", opponent.getXP());
                bundle.putString("fightType", "Harjoittelu");
                bundle.putInt("tournamentRound", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }


}
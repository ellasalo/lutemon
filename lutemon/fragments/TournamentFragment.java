package com.olio.lutemon.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TournamentFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TournamentFragment extends Fragment {
    private Button btnToTournament;
    private EditText eTxtLutemonName;
    private Opponent opponent;
    private Lutemon opponentLutemon;

    public TournamentFragment() {
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
        return inflater.inflate(R.layout.fragment_tournament, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eTxtLutemonName = view.findViewById(R.id.eTxtLutemonName);

        btnToTournament = view.findViewById(R.id.btnToTournament);
        btnToTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eTxtLutemonName.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Valitse ensin lutemon", Toast.LENGTH_LONG).show();
                    return;
                } else if (Storage.getInstance().getLutemonByNickName(eTxtLutemonName.getText().toString().toLowerCase()) == null) {
                    Toast.makeText(getContext(), "Antamallasi nimellä ei löytynyt lutemonia.", Toast.LENGTH_LONG).show();
                    return;
                } else if (Storage.getInstance().getLutemonByNickName(eTxtLutemonName.getText().toString().toLowerCase()).getStats().getLeftHP() == 0) {
                    Toast.makeText(getContext(), "Lutemonillasi ei ole enää yhtään elämää.\nLepuuta se ensin.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getContext(), FightActivity.class);
                intent.putExtra("ownLutemon", Storage.getInstance().getLutemonByNickName(eTxtLutemonName.getText().toString().toLowerCase()));

                opponent = createOpponent();
                Random randi = new Random();
                opponentLutemon = opponent.getLutemons().get(randi.nextInt(5));
                opponentLutemon.getStats().setLeftHP(opponentLutemon.getStats().getMaxHP());
                intent.putExtra("opponentLutemon", opponentLutemon);
                Bundle bundle = new Bundle();
                bundle.putString("fightType", "Turnaus");
                bundle.putInt("tournamentRound", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private Opponent createOpponent(){
        Random rand = new Random();
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        lutemons.add(new Black("lutemon"));
        lutemons.add(new Orange("lutemon"));
        lutemons.add(new Pink("lutemon"));
        lutemons.add(new Green("lutemon"));
        lutemons.add(new White("lutemon"));

        for (Lutemon lutemon: lutemons) {
            for (int i = 1; i < 97 + (rand.nextInt(5)-2); i++){
                lutemon.levelUP();
            }
        }
        Opponent opponent = new Opponent("name", 0, 0);
        opponent.setLutemons(lutemons);
        return opponent;
    }
}
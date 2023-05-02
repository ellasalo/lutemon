package com.olio.lutemon;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.olio.lutemon.LutemonClasses.AttackMove;
import com.olio.lutemon.LutemonClasses.Lutemon;
import com.olio.lutemon.LutemonClasses.Move;
import com.olio.lutemon.LutemonClasses.Stats;
import com.olio.lutemon.LutemonClasses.StatsImprover;
import com.olio.lutemon.LutemonClasses.Storage;

import java.util.ArrayList;

public class LutexActivity extends AppCompatActivity implements View.OnClickListener {

    private View nameBar;
    private View boxBox;
    private TextView infoBox;
    private TextView moveBox;
    private Button btnReturn;
    private TextView name;
    private TextView type;
    private TextView level;
    private TextView stats;
    private ImageView imageView;
    private TextView description;
    private Lutemon lutemon;

    private View delLutemon;
    private TextView txDel;
    private Button delLutemonYes;
    private Button delLutemonNo;
    private ImageView delView;

    private TextView txWinStats;
    private TextView txtMove1;
    private TextView txtMove2;
    private TextView txtMove3;
    private TextView txtMove4;
    private ArrayList<TextView> txtMoveList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutex);
        nameBar = findViewById(R.id.nameBox);
        boxBox = findViewById(R.id.boxBox);
        infoBox = findViewById(R.id.txKuvaus);
        moveBox = findViewById(R.id.txLiikkeet);
        btnReturn = findViewById(R.id.btnReturnHome);
        imageView = findViewById(R.id.imageView10);
        stats = findViewById(R.id.txStats);
        level = findViewById(R.id.txLv);
        name = findViewById(R.id.txName);
        type = findViewById(R.id.txType);
        description = findViewById(R.id.tViewDescription);
        delLutemon = findViewById(R.id.vDelLutemon);
        txDel = findViewById(R.id.txDelLutemon);
        delLutemonYes = findViewById(R.id.btnDelYes);
        delLutemonNo = findViewById(R.id.btnDelNo);
        delView = findViewById(R.id.delView);
        txWinStats = findViewById(R.id.txWinStats);
        txtMove1 = findViewById(R.id.txtMove1);
        txtMove2 = findViewById(R.id.txtMove2);
        txtMove3 = findViewById(R.id.txtMove3);
        txtMove4 = findViewById(R.id.txtMove4);
        txtMoveList = new ArrayList<>();
        txtMoveList.add(txtMove1);
        txtMoveList.add(txtMove2);
        txtMoveList.add(txtMove3);
        txtMoveList.add(txtMove4);

        setTitle("Lutex");

        lutemon = (Lutemon) getIntent().getSerializableExtra("lutemon");
        changeColor(lutemon.getType());
        imageView.setImageResource(lutemon.getImage());
        name.setText(lutemon.getLutemon()+" "+'"'+lutemon.getNickname()+'"');
        type.setText(lutemon.getType());
        level.setText(lutemon.getStats().getLevel()+"");
        stats.setText("Heikkous: "+lutemon.getWeakness()+"\nHP: "+lutemon.getStats().getMaxHP()+"\nHyökkäys: "+lutemon.getStats().getAttack()+"\nPuolustus: "+lutemon.getStats().getDefence()+"\nNopeus: "+lutemon.getStats().getSpeed()+"\nXP: "+lutemon.getStats().getXP()+"/"+lutemon.getStats().getNextLevelXP());
        txWinStats.setText("Harjoitusottelut: " + lutemon.getStatistics().getPractiseMatches() + "\nAreenaottelut: " + lutemon.getStatistics().getArenaMatches() + "\nTurnausvoitot: " + lutemon.getStatistics().getTournamentVictories());
        description.setText(lutemon.getDescription());
        writeMoves();

        delLutemon.setVisibility(INVISIBLE);
        txDel.setVisibility(INVISIBLE);
        delLutemonYes.setVisibility(INVISIBLE);
        delLutemonNo.setVisibility(INVISIBLE);
    }

    private void changeColor(String type) {
        String mainColor = "#FFAB40";;
        String helpColor = "#FFE57F";
        if(type.equals("Musta")) {
            mainColor = "#FF68686A";
            helpColor = "#B3B3B3";
        } else if(type.equals("Oranssi")) {
            mainColor = "#FFAB40";
            helpColor = "#FFE57F";
        } else if(type.equals("Pinkki")) {
            mainColor = "#9E4AFF";
            helpColor = "#EA80FC";
        } else if(type.equals("Vihreä")) {
            mainColor = "#1FA310";
            helpColor = "#8FF688";
        } else if(type.equals("Valkoinen")) {
            mainColor = "#98F8F8";
            helpColor = "#FBFBFB";
        }
        infoBox.setBackgroundColor(Color.parseColor(helpColor));
        moveBox.setBackgroundColor(Color.parseColor(helpColor));
        nameBar.setBackgroundColor(Color.parseColor(mainColor));
        boxBox.setBackgroundColor(Color.parseColor(mainColor));
        btnReturn.setBackgroundColor(Color.parseColor(mainColor));
    }

    public void writeMoves(){
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            Move move;
            move = lutemon.getMoves().get(i);
            sb.append(move.getName());
            if (move instanceof AttackMove) {
                sb.append("\nTeho: " + ((AttackMove) move).getPower());
            } else {
                String defencechange = "+" + Math.round((((StatsImprover) move).getDefenceImproveNumber() - 1)*100);
                String attackchange = "+" + Math.round((((StatsImprover) move).getAttackImproveNumber() - 1)*100);
                if (((StatsImprover) move).getDefenceImproveNumber() < 1) {
                    defencechange = "" + (Math.round((((StatsImprover) move).getDefenceImproveNumber() - 1)*100));
                }
                if (((StatsImprover) move).getAttackImproveNumber() < 1) {
                    attackchange = "" + (Math.round((((StatsImprover) move).getAttackImproveNumber() - 1)*100));
                }

                sb.append("\nPuolustus: " + defencechange + " %");
                sb.append("\nHyökkäys: " + attackchange + " %");
            }
            txtMoveList.get(i).setText(sb.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delView:
                delLutemon.setVisibility(View.VISIBLE);
                txDel.setVisibility(View.VISIBLE);
                delLutemonYes.setVisibility(View.VISIBLE);
                delLutemonNo.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void deleteLutemon(View view) {
        Storage.getInstance().deleteLutemon(Storage.getInstance().returnIndex(lutemon.getNickname()));
        returnToGame(view);
    }

    public void hideDeleteView(View view) {
        delLutemon.setVisibility(INVISIBLE);
        txDel.setVisibility(INVISIBLE);
        delLutemonYes.setVisibility(INVISIBLE);
        delLutemonNo.setVisibility(INVISIBLE);
    }

    public void returnToGame(View view) {
        Intent intent = new Intent(this, TabGameMode.class);
        startActivity(intent);
    }

}
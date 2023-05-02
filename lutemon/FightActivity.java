package com.olio.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olio.lutemon.LutemonClasses.AttackMove;
import com.olio.lutemon.LutemonClasses.Black;
import com.olio.lutemon.LutemonClasses.Green;
import com.olio.lutemon.LutemonClasses.Lutemon;
import com.olio.lutemon.LutemonClasses.Move;
import com.olio.lutemon.LutemonClasses.Opponent;
import com.olio.lutemon.LutemonClasses.Orange;
import com.olio.lutemon.LutemonClasses.Pink;
import com.olio.lutemon.LutemonClasses.StatsImprover;
import com.olio.lutemon.LutemonClasses.Storage;
import com.olio.lutemon.LutemonClasses.White;

import java.util.ArrayList;
import java.util.Random;

public class FightActivity extends AppCompatActivity implements View.OnClickListener {
    private Lutemon ownLutemon;
    private Lutemon opponentLutemon;
    private ImageView imgBckGround;
    private ConstraintLayout layout;
    private LinearLayout llOwn;
    private LinearLayout llOpponent;

    private View instructionView;


    private ImageView imgOwn;
    private ImageView imgOpponent;

    private TextView tViewOwnName;
    private TextView tViewOpponentName;
    private TextView tViewOwnLvl;
    private TextView tViewOpponentLvl;
    private TextView tViewOwnHP;
    private TextView tViewOpponentHP;

    private Button btnMove1;
    private Button btnMove2;
    private Button btnMove3;
    private Button btnMove4;

    private Button btnStart;

    private ArrayList<Move> ownMoves;
    private ArrayList<Move> opponentMoves;

    private ImageView imgEffectOwn;
    private ImageView imgEffectOpponent;

    private TextView tViewInstructions;
    private ImageView imgContinue;

    private View viewEndGame;
    private TextView tViewWin;
    private TextView tViewEarning;
    private Button btnReturnToGame;


    private double ownAttackImprovement = 1;
    private double opponentAttackImprovement = 1;
    private double ownDefenceImprovement = 1;
    private double opponentDefenceImprovement = 1;
    private double ownSupremacy = 1;
    private double opponentSupremacy = 1;

    private Move ownMove;
    private Move opponentMove;

    private boolean buttonClicked;

    private int fightRound;

    private int XP;

    private String fightType;

    private int tournamentRound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getIntent().getExtras();

        fightType = bundle.getString("fightType");
        if (fightType.equals("Harjoittelu")) {
            XP = bundle.getInt("XP");
            setTitle("Harjoitusottelu");
        } else if (fightType.equals("Turnaus")) {
            XP = 0;
            tournamentRound = bundle.getInt("tournamentRound");
            setTitle(tournamentRound + ". turnausottelu");
        }

        ownLutemon = (Lutemon) getIntent().getSerializableExtra("ownLutemon");
        opponentLutemon = (Lutemon) getIntent().getSerializableExtra("opponentLutemon");
        setContentView(R.layout.activity_fight);

        layout = findViewById(R.id.layout);
        llOwn = findViewById(R.id.LLown);
        llOpponent = findViewById(R.id.LLopponent);
        instructionView = findViewById(R.id.instructionView);
        imgBckGround = findViewById(R.id.imgBckround);
        imgOwn = findViewById(R.id.imgOwn);
        imgOpponent = findViewById(R.id.imgOpponent);
        tViewOwnName = findViewById(R.id.tViewOwnName);
        tViewOpponentName = findViewById(R.id.tViewOpponentName);
        tViewOwnLvl = findViewById(R.id.tViewOwnLvl);
        tViewOpponentLvl = findViewById(R.id.tViewOpponentLvl);
        tViewOwnHP = findViewById(R.id.tViewOwnHP);
        tViewOpponentHP = findViewById(R.id.tViewOpponentHP);
        btnMove1 = findViewById(R.id.btnMove1);
        btnMove2 = findViewById(R.id.btnMove2);
        btnMove3 = findViewById(R.id.btnMove3);
        btnMove4 = findViewById(R.id.btnMove4);
        btnStart = findViewById(R.id.btnStart);
        imgEffectOwn = findViewById(R.id.imgOwnEffect);
        imgEffectOpponent = findViewById(R.id.imgOpponentEffect);
        tViewInstructions = findViewById(R.id.tViewInstructions);
        imgContinue = findViewById(R.id.imgContinue);
        viewEndGame = findViewById(R.id.viewGameEnd);
        tViewWin = findViewById(R.id.tViewWin);
        tViewEarning = findViewById(R.id.tViewEarning);
        btnReturnToGame = findViewById(R.id.btnReturn);

        viewEndGame.setVisibility(View.INVISIBLE);
        tViewWin.setVisibility(View.INVISIBLE);
        tViewEarning.setVisibility(View.INVISIBLE);
        btnReturnToGame.setVisibility(View.INVISIBLE);


        imgOwn.setImageResource(ownLutemon.getImage());
        imgOpponent.setImageResource(opponentLutemon.getImage());
        tViewOwnName.setText(ownLutemon.getNickname());
        tViewOpponentName.setText(opponentLutemon.getLutemon());
        tViewOwnLvl.setText("Taso: " + ownLutemon.getStats().getLevel());
        tViewOpponentLvl.setText("Taso: " + opponentLutemon.getStats().getLevel());
        tViewOwnHP.setText("HP: " + ownLutemon.getStats().getLeftHP() + "/" + ownLutemon.getStats().getMaxHP());
        tViewOpponentHP.setText("HP: " + opponentLutemon.getStats().getLeftHP() + "/" + opponentLutemon.getStats().getMaxHP());

        tViewInstructions.setText("Aloita taistelu.");

        ownMoves = ownLutemon.getMoves();
        opponentMoves = opponentLutemon.getMoves();

        btnMove1.setText(ownMoves.get(0).getName());
        btnMove2.setText(ownMoves.get(1).getName());
        btnMove3.setText(ownMoves.get(2).getName());
        btnMove4.setText(ownMoves.get(3).getName());

        btnMove1.setClickable(false);
        btnMove2.setClickable(false);
        btnMove3.setClickable(false);
        btnMove4.setClickable(false);

        if (opponentLutemon.getType().equals(ownLutemon.getWeakness())) {
            opponentSupremacy = 2;
        } else if (ownLutemon.getType().equals(opponentLutemon.getWeakness())){
            ownSupremacy = 2;
        }

        setColorScheme();

        fightRound = 0;
    }

    public void setColorScheme() {
        if (fightType.equals("Harjoittelu")) {
            imgBckGround.setImageResource(R.drawable.tausta);
            btnMove1.setBackgroundColor(Color.parseColor("#006064"));
            btnMove2.setBackgroundColor(Color.parseColor("#006064"));
            btnMove3.setBackgroundColor(Color.parseColor("#006064"));
            btnMove4.setBackgroundColor(Color.parseColor("#006064"));
            layout.setBackgroundColor(Color.parseColor("#84FFFF"));
            llOwn.setBackgroundColor(Color.parseColor("#84FFFF"));
            llOpponent.setBackgroundColor(Color.parseColor("#84FFFF"));
            instructionView.setBackgroundColor(Color.parseColor("#083226"));

        } else if (fightType.equals("Turnaus")) {
            imgBckGround.setImageResource(R.drawable.taustatournament);
            btnMove1.setBackgroundColor(Color.parseColor("#1A237E"));
            btnMove2.setBackgroundColor(Color.parseColor("#1A237E"));
            btnMove3.setBackgroundColor(Color.parseColor("#1A237E"));
            btnMove4.setBackgroundColor(Color.parseColor("#1A237E"));
            layout.setBackgroundColor(Color.parseColor("#141843"));
            llOwn.setBackgroundColor(Color.parseColor("#EA4F61"));
            llOpponent.setBackgroundColor(Color.parseColor("#EA4F61"));
            instructionView.setBackgroundColor(Color.parseColor("#020B22"));
        }
    }

    public void setImgExplosion(View view, String who) {
        Handler handler = new Handler();
        if (who.equals("own")) {
            imgEffectOwn.setImageResource(R.drawable.starburst_explosion_2_1);
            imgEffectOwn.setVisibility(View.VISIBLE);
        } else {
            imgEffectOpponent.setImageResource(R.drawable.starburst_explosion_2_1);
            imgEffectOpponent.setVisibility(View.VISIBLE);
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                if (who.equals("own")) {
                    imgEffectOwn.setVisibility(View.INVISIBLE);
                } else {
                    imgEffectOpponent.setVisibility(View.INVISIBLE);
                }
            }
        }, 3000);   //3 seconds
    }

    public void setImgStatsUp(View view, String who) {
        Handler handler = new Handler();
        if (who.equals("own")) {
            imgEffectOwn.setImageResource(R.drawable.arrowsup);
            imgEffectOwn.setVisibility(View.VISIBLE);
        } else {
            imgEffectOpponent.setImageResource(R.drawable.arrowsup);
            imgEffectOpponent.setVisibility(View.VISIBLE);
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                if (who.equals("own")) {
                    imgEffectOwn.setVisibility(View.INVISIBLE);
                } else {
                    imgEffectOpponent.setVisibility(View.INVISIBLE);
                }
            }
        }, 3000);   //3 seconds
    }

    private void winBattle(View view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                btnMove1.setVisibility(View.INVISIBLE);
                btnMove2.setVisibility(View.INVISIBLE);
                btnMove3.setVisibility(View.INVISIBLE);
                btnMove4.setVisibility(View.INVISIBLE);
                viewEndGame.setVisibility(View.VISIBLE);
                tViewWin.setVisibility(View.VISIBLE);
                tViewEarning.setVisibility(View.VISIBLE);
                btnReturnToGame.setVisibility(View.VISIBLE);

                viewEndGame.setBackgroundColor(Color.parseColor("#57E65A"));

                btnReturnToGame.setVisibility(View.VISIBLE);

                StringBuilder sb = new StringBuilder();

                if (fightType.equals("Harjoittelu")) {
                    tViewWin.setText("Voitit ottelun!");

                    int previousLvl = ownLutemon.getStats().getLevel();
                    ownLutemon.getStats().addXP(XP);
                    sb.append("Sait " + XP + " XP:tä!\n");
                    while (ownLutemon.getStats().getNextLevelXP() - ownLutemon.getStats().getXP() <= 0) {
                        ownLutemon.levelUP();
                        sb.append(ownLutemon.getNickname() + " kehittyi tasolle " + ownLutemon.getStats().getLevel() + "\n");
                    }

                    if (previousLvl < 35 && ownLutemon.getStats().getLevel() >= 35) {
                        sb.append("Lutemonisi kehittyi!\n");
                        imgOwn.setImageResource(ownLutemon.getImage());
                    }

                    if (!ownLutemon.getMoves().get(0).getName().equals(btnMove1.getText())) {
                        sb.append("Lutemonisi oppi uuden liikkeen!\n");
                    } else if (!ownLutemon.getMoves().get(1).getName().equals(btnMove2.getText())){
                        sb.append("Lutemonisi oppi uuden liikkeen!\n");
                    } else if (!ownLutemon.getMoves().get(2).getName().equals(btnMove3.getText())){
                        sb.append("Lutemonisi oppi uuden liikkeen!\n");
                    } else if (!ownLutemon.getMoves().get(3).getName().equals(btnMove4.getText())){
                        sb.append("Lutemonisi oppi uuden liikkeen!\n");
                    }

                    tViewEarning.setText(sb.toString());
                    ownLutemon.getStatistics().addPractiseMatches();
                }

                else {
                    tViewWin.setText("Voitit " + tournamentRound + ". turnausottelun!");
                    if (tournamentRound == 3) {
                        sb.append("Onneksi olkoon!\nOlet koko turnauksen voittaja!");
                        btnReturnToGame.setText("Palaa kotiin voittajana");
                        ownLutemon.getStatistics().addTournamentVictories();
                    } else {

                        btnReturnToGame.setText("Siirry seuraavaan otteluun");
                    }
                    tViewEarning.setText(sb.toString());
                    ownLutemon.getStatistics().addArenaMatches();
                }


            }
        }, 3000);
    }

    private void loseBattle(View view) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                btnMove1.setVisibility(View.INVISIBLE);
                btnMove2.setVisibility(View.INVISIBLE);
                btnMove3.setVisibility(View.INVISIBLE);
                btnMove4.setVisibility(View.INVISIBLE);
                viewEndGame.setVisibility(View.VISIBLE);
                tViewWin.setVisibility(View.VISIBLE);
                btnReturnToGame.setVisibility(View.VISIBLE);

                viewEndGame.setBackgroundColor(Color.parseColor("#FFF14242"));

                if (fightType.equals("Harjoittelu")) {
                    tViewWin.setText("Hävisit ottelun!");
                    ownLutemon.getStatistics().addPractiseMatches();
                } else if (fightType.equals("Turnaus")) {
                    tViewWin.setText("Hävisit ottelun!");
                    tViewEarning.setText("Hävisit turnauksen " + fightRound + ". kierroksella.");
                    btnReturnToGame.setText("Palaa kotiin häviäjänä");
                    btnReturnToGame.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            returnToGame();
                        }
                    });
                    ownLutemon.getStatistics().addArenaMatches();
                }

            }
        }, 3000);

    }

    public void returnToGame() {
        Storage.getInstance().getLutemons().set(Storage.getInstance().returnIndex(ownLutemon.getNickname()), ownLutemon);
        Intent intent = new Intent(this, TabGameMode.class);
        startActivity(intent);
    }

    public void fight(View view) {
        if (fightRound == 0) {
            btnStart.setVisibility(View.INVISIBLE);
            imgContinue.setVisibility(View.VISIBLE);
            imgContinue.setOnClickListener(this);
        }
        Random rand = new Random();

        tViewInstructions.setText("Valitse liike:");

        final int[] damage = new int[1];
        if (ownLutemon.getStats().getLeftHP() > 0 && opponentLutemon.getStats().getLeftHP() > 0) {

            if (fightType.equals("Harjoittelu")) {
                btnMove1.setBackgroundColor(Color.parseColor("#004D40"));
                btnMove2.setBackgroundColor(Color.parseColor("#004D40"));
                btnMove3.setBackgroundColor(Color.parseColor("#004D40"));
                btnMove4.setBackgroundColor(Color.parseColor("#004D40"));
            } else if (fightType.equals("Turnaus")) {
                btnMove1.setBackgroundColor(Color.parseColor("#283593"));
                btnMove2.setBackgroundColor(Color.parseColor("#283593"));
                btnMove3.setBackgroundColor(Color.parseColor("#283593"));
                btnMove4.setBackgroundColor(Color.parseColor("#283593"));
            }


            btnMove1.setOnClickListener(this);
            btnMove2.setOnClickListener(this);
            btnMove3.setOnClickListener(this);
            btnMove4.setOnClickListener(this);

            opponentMove = opponentLutemon.getMoves().get(rand.nextInt(4));

            if (buttonClicked) {
                if (opponentLutemon.getStats().getSpeed() > ownLutemon.getStats().getSpeed()) {

                    tViewInstructions.setText(opponentLutemon.getLutemon() + " teki liikkeen " + opponentMove.getName());
                    imgContinue.setVisibility(View.INVISIBLE);

                    if (opponentMove instanceof AttackMove) {
                        int L = opponentLutemon.getStats().getLevel();
                        int A = opponentLutemon.getStats().getAttack();
                        int P = ((AttackMove) opponentMove).getPower();
                        int D = ownLutemon.getStats().getDefence();
                        double iA = opponentAttackImprovement;
                        double iD = ownDefenceImprovement;
                        double S = opponentSupremacy;
                        Random rA = new Random();
                        Random rD = new Random();
                        damage[0] = (int) (S*((((2*L/5+2))*(0.8 + 2*rA.nextFloat()/5)*iA*A*P/((0.8 + 2*rD.nextFloat()/5)*iD*D))/50)+1);
                        ownLutemon.takeDamage(damage[0]);
                        setImgExplosion(view, "own");
                        tViewOwnHP.setText("HP: " + ownLutemon.getStats().getLeftHP() + "/" + ownLutemon.getStats().getMaxHP());
                    } else if (opponentMove instanceof StatsImprover) {
                        opponentDefenceImprovement = opponentDefenceImprovement*((StatsImprover) opponentMove).getDefenceImproveNumber();
                        opponentAttackImprovement = opponentAttackImprovement*((StatsImprover) opponentMove).getAttackImproveNumber();
                        setImgStatsUp(view, "opponent");
                    }

                    if (ownLutemon.getStats().getLeftHP() == 0) {
                        loseBattle(view);
                        return;
                    }


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            btnStart.setVisibility(View.INVISIBLE);
                            tViewInstructions.setText(ownLutemon.getNickname() + " teki liikkeen " + ownMove.getName());

                            if (ownMove instanceof AttackMove) {
                                int L = ownLutemon.getStats().getLevel();
                                int A = ownLutemon.getStats().getAttack();
                                int P = ((AttackMove) ownMove).getPower();
                                int D = opponentLutemon.getStats().getDefence();
                                double iA = ownAttackImprovement;
                                double iD = opponentDefenceImprovement;
                                double S = ownSupremacy;
                                Random rA = new Random();
                                Random rD = new Random();
                                int damage = (int) (S*((((2*L/5+2))*(0.8 + 2*rA.nextFloat()/5)*iA*A*P/((0.8 + 2*rD.nextFloat()/5)*iD*D))/50)+1);
                                // Math.floor(Math.floor(Math.floor(2*L/5+2))*A*P/D)+1
                                opponentLutemon.takeDamage(damage);
                                setImgExplosion(view, "opponent");
                                tViewOpponentHP.setText("HP: " + opponentLutemon.getStats().getLeftHP() + "/" + opponentLutemon.getStats().getMaxHP());
                            } else if (ownMove instanceof StatsImprover) {
                                ownDefenceImprovement = ownDefenceImprovement*((StatsImprover) ownMove).getDefenceImproveNumber();
                                ownAttackImprovement = ownAttackImprovement*((StatsImprover) ownMove).getAttackImproveNumber();
                                setImgStatsUp(view, "own");
                            }

                            if (opponentLutemon.getStats().getLeftHP() == 0) {
                                winBattle(view);
                                return;
                            }

                            Handler handler2 = new Handler();
                            handler2.postDelayed(new Runnable() {
                                public void run() {
                                    imgContinue.setVisibility(View.VISIBLE);
                                    tViewInstructions.setText("Valitse liike:");
                                }
                            }, 3000);
                        }
                    }, 3000);




                } else {
                    tViewInstructions.setText(ownLutemon.getNickname() + " teki liikkeen " + ownMove.getName());
                    imgContinue.setVisibility(View.INVISIBLE);
                    if (ownMove instanceof AttackMove) {
                        int L = ownLutemon.getStats().getLevel();
                        int A = ownLutemon.getStats().getAttack();
                        int P = ((AttackMove) ownMove).getPower();
                        int D = opponentLutemon.getStats().getDefence();
                        double iA = ownAttackImprovement;
                        double iD = opponentDefenceImprovement;
                        double S = ownSupremacy;
                        Random rA = new Random();
                        Random rD = new Random();
                        damage[0] = (int) (S*((((2*L/5+2))*(0.8 + 2*rA.nextFloat()/5)*iA*A*P/((0.8 + 2*rD.nextFloat()/5)*iD*D))/50)+1);
                        opponentLutemon.takeDamage(damage[0]);
                        tViewOpponentHP.setText("HP: " + opponentLutemon.getStats().getLeftHP() + "/" + opponentLutemon.getStats().getMaxHP());
                        setImgExplosion(view, "opponent");
                    } else if (ownMove instanceof StatsImprover) {
                        ownDefenceImprovement = ownDefenceImprovement*((StatsImprover) ownMove).getDefenceImproveNumber();
                        ownAttackImprovement = ownAttackImprovement*((StatsImprover) ownMove).getAttackImproveNumber();
                        setImgStatsUp(view, "own");
                    }

                    if (opponentLutemon.getStats().getLeftHP() == 0) {
                        winBattle(view);
                        return;
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            btnStart.setVisibility(View.INVISIBLE);
                            tViewInstructions.setText(opponentLutemon.getLutemon() + " teki liikkeen " + opponentMove.getName());

                            if (opponentMove instanceof AttackMove) {
                                int L = opponentLutemon.getStats().getLevel();
                                int A = opponentLutemon.getStats().getAttack();
                                int P = ((AttackMove) opponentMove).getPower();
                                int D = ownLutemon.getStats().getDefence();
                                double iA = opponentAttackImprovement;
                                double iD = ownDefenceImprovement;
                                double S = opponentSupremacy;
                                Random rA = new Random();
                                Random rD = new Random();
                                damage[0] = (int) (S*((((2*L/5+2))*(0.8 + 2*rA.nextFloat()/5)*iA*A*P/((0.8 + 2*rD.nextFloat()/5)*iD*D))/50)+1);
                                ownLutemon.takeDamage(damage[0]);
                                setImgExplosion(view, "own");
                                tViewOwnHP.setText("HP: " + ownLutemon.getStats().getLeftHP() + "/" + ownLutemon.getStats().getMaxHP());
                            } else if (opponentMove instanceof StatsImprover) {
                                opponentDefenceImprovement = opponentDefenceImprovement*((StatsImprover) opponentMove).getDefenceImproveNumber();
                                opponentAttackImprovement = opponentAttackImprovement*((StatsImprover) opponentMove).getAttackImproveNumber();
                                setImgStatsUp(view, "opponent");
                            }

                            if (ownLutemon.getStats().getLeftHP() == 0) {
                                loseBattle(view);
                                return;
                            }

                            Handler handler2 = new Handler();
                            handler2.postDelayed(new Runnable() {
                                public void run() {
                                    imgContinue.setVisibility(View.VISIBLE);
                                    tViewInstructions.setText("Valitse liike:");
                                }
                            }, 3000);
                        }
                    }, 3000);

                }
            }
            buttonClicked = false;
        }
    }

    @Override
    public void onClick(View view) {
        String colorNotSelected;
        String colorSelected;

        if (fightType.equals("Harjoittelu")) {
            colorNotSelected = "#004D40";
            colorSelected = "#1AB821";
        } else {
            colorNotSelected = "#283593";
            colorSelected = "#00ACC1";
        }
        switch (view.getId()) {
            case R.id.btnMove1:
                ownMove = ownLutemon.getMoves().get(0);
                btnMove1.setBackgroundColor(Color.parseColor(colorSelected));
                btnMove2.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove3.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove4.setBackgroundColor(Color.parseColor(colorNotSelected));
                break;
            case R.id.btnMove2:
                ownMove = ownLutemon.getMoves().get(1);
                btnMove1.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove2.setBackgroundColor(Color.parseColor(colorSelected));
                btnMove3.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove4.setBackgroundColor(Color.parseColor(colorNotSelected));
                break;
            case R.id.btnMove3:
                ownMove = ownLutemon.getMoves().get(2);
                btnMove1.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove2.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove3.setBackgroundColor(Color.parseColor(colorSelected));
                btnMove4.setBackgroundColor(Color.parseColor(colorNotSelected));
                break;
            case R.id.btnMove4:
                ownMove = ownLutemon.getMoves().get(3);
                btnMove1.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove2.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove3.setBackgroundColor(Color.parseColor(colorNotSelected));
                btnMove4.setBackgroundColor(Color.parseColor(colorSelected));
                break;
            case R.id.imgContinue:
                fight(view);
                return;
            case R.id.btnReturn:
                if (fightType.equals("Harjoittelu")){
                    returnToGame();
                } else if (fightType.equals("Turnaus") && tournamentRound == 3){
                    returnToGame();
                } else {
                    goToNextTournamentRound();
                }
        }

        buttonClicked = true;
    }


    private Lutemon createNextRoundLutemon(){
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        lutemons.add(new Black("lutemon"));
        lutemons.add(new Orange("lutemon"));
        lutemons.add(new Pink("lutemon"));
        lutemons.add(new Green("lutemon"));
        lutemons.add(new White("lutemon"));
        int level;
        Random rand = new Random();
        if (tournamentRound == 2) {
            level = 100;
        } else {
            level = 98 + rand.nextInt(5) - 2;
        }
        for (Lutemon lutemon: lutemons) {
            for (int i = 1; i < level; i++){
                lutemon.levelUP();

            }
            if (tournamentRound == 2) {
                lutemon.getStats().setUpgradedStats();
            }
        }
        return lutemons.get(rand.nextInt(5));
    }

    private void goToNextTournamentRound(){
        Storage.getInstance().getLutemons().set(Storage.getInstance().returnIndex(ownLutemon.getNickname()), ownLutemon);
        ownLutemon = Storage.getInstance().getLutemonByNickName(ownLutemon.getNickname());
        opponentLutemon = createNextRoundLutemon();
        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra("ownLutemon", ownLutemon);
        opponentLutemon.getStats().setLeftHP(opponentLutemon.getStats().getMaxHP());
        intent.putExtra("opponentLutemon", opponentLutemon);
        Bundle bundle = new Bundle();
        bundle.putString("fightType", "Turnaus");
        bundle.putInt("tournamentRound", tournamentRound + 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}


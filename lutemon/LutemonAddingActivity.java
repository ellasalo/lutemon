package com.olio.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olio.lutemon.LutemonClasses.Black;
import com.olio.lutemon.LutemonClasses.Green;
import com.olio.lutemon.LutemonClasses.Lutemon;
import com.olio.lutemon.LutemonClasses.Orange;
import com.olio.lutemon.LutemonClasses.Pink;
import com.olio.lutemon.LutemonClasses.Storage;
import com.olio.lutemon.LutemonClasses.White;

public class LutemonAddingActivity extends AppCompatActivity {

    private ImageView image;

    private Lutemon lutemon;

    private TextView tViewStats;
    private TextView tViewSelected;
    private EditText eTextLutemonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutemon_adding);

        setTitle("Nappaa uusia lutemoneja");

        lutemon = new Black("nick");

        this.tViewStats = findViewById(R.id.tviewStats);
        this.tViewSelected = findViewById(R.id.tviewLutemonType);
        this.image = findViewById(R.id.lutemonImage);
        this.eTextLutemonName = findViewById(R.id.editTextLutemonName);

    }

    public void selectBlack(View view){
        lutemon = new Black("nick");
        tViewStats.setText(lutemon.getStats().getStatText() + "\nHeikkous: " + lutemon.getWeakness());
        tViewSelected.setText("Haluatko, että uusi lutemonisi on " + lutemon.getLutemon() + "?");
        image.setImageResource(lutemon.getImage());
    }

    public void selectOrange(View view){
        lutemon = new Orange("nick");
        tViewStats.setText(lutemon.getStats().getStatText() + "\nHeikkous: " + lutemon.getWeakness());
        tViewSelected.setText("Haluatko, että uusi lutemonisi on " + lutemon.getLutemon() + "?");
        image.setImageResource(lutemon.getImage());
    }

    public void selectPink(View view){
        lutemon = new Pink("nick");
        tViewStats.setText(lutemon.getStats().getStatText() + "\nHeikkous: " + lutemon.getWeakness());
        tViewSelected.setText("Haluatko, että uusi lutemonisi on " + lutemon.getLutemon() + "?");
        image.setImageResource(lutemon.getImage());
    }

    public void selectGreen(View view){
        lutemon = new Green("nick");
        tViewStats.setText(lutemon.getStats().getStatText() + "\nHeikkous: " + lutemon.getWeakness());
        tViewSelected.setText("Haluatko, että uusi lutemonisi on " + lutemon.getLutemon() + "?");
        image.setImageResource(lutemon.getImage());
    }

    public void selectWhite(View view){
        lutemon = new White("nick");
        tViewStats.setText(lutemon.getStats().getStatText() + "\nHeikkous: " + lutemon.getWeakness());
        tViewSelected.setText("Haluatko, että uusi lutemonisi on " + lutemon.getLutemon() + "?");
        image.setImageResource(lutemon.getImage());
    }

    public void addLutemon(View view) {
        String type = lutemon.getType();
        String name;
        if (eTextLutemonName.getText().toString().isEmpty()) {
            name = lutemon.getLutemon();
        } else {
            name = eTextLutemonName.getText().toString();
        }

        if (Storage.getInstance().getLutemonByNickName(name) != null) {
            Toast.makeText(this, "Nimen tulee olla uniikki.", Toast.LENGTH_LONG).show();
            return;
        }

        // CheatCode, näillä saa tehtyä tason 101 lutemoneja suoraan.

        if (name.equals("ES") && type == "Valkoinen") {
            Lutemon es = new White(name);
            for (int i = 0; i < 100; i++) {
                es.levelUP();
            }
            Storage.getInstance().addLutemon(es);
            Toast.makeText(this, "Nappasit lutemonin!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TabGameMode.class);
            startActivity(intent);
            return;
        }

        if (name.equals("Ellis") && type == "Pinkki") {
            Lutemon es = new Pink(name);
            for (int i = 0; i < 100; i++) {
                es.levelUP();
            }
            Storage.getInstance().addLutemon(es);
            Toast.makeText(this, "Nappasit lutemonin!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TabGameMode.class);
            startActivity(intent);
            return;
        }

        if (name.equals("Kärmes") && type == "Musta") {
            Lutemon es = new Black(name);
            for (int i = 0; i < 100; i++) {
                es.levelUP();
            }
            Storage.getInstance().addLutemon(es);
            Toast.makeText(this, "Nappasit lutemonin!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TabGameMode.class);
            startActivity(intent);
            return;
        }

        if (name.equals("Luttis") && type == "Vihreä") {
            Lutemon es = new Green(name);
            for (int i = 0; i < 100; i++) {
                es.levelUP();
            }
            Storage.getInstance().addLutemon(es);
            Toast.makeText(this, "Nappasit lutemonin!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TabGameMode.class);
            startActivity(intent);
            return;
        }

        if (name.equals("UN") && type == "Oranssi") {
            Lutemon un = new Orange(name);
            for(int i = 0; i <1000; i++) {
                un.levelUP();
            }
            Storage.getInstance().addLutemon(un);
            Toast.makeText(this, "Nappasit lutemonin!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TabGameMode.class);
            startActivity(intent);
            return;
        }
        if (type == "Musta") {
            Storage.getInstance().addLutemon(new Black(name));
        } if (type == "Oranssi") {
            Storage.getInstance().addLutemon(new Orange(name));
        } if (type == "Vihreä") {
            Storage.getInstance().addLutemon(new Green(name));
        } if (type == "Pinkki") {
            Storage.getInstance().addLutemon(new Pink(name));
        } if (type == "Valkoinen") {
            Storage.getInstance().addLutemon(new White(name));
        }

        Toast.makeText(this, "Nappasit lutemonin!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TabGameMode.class);
        startActivity(intent);
    }

}
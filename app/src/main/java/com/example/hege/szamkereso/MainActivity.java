package com.example.hege.szamkereso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Random szám generálása

        Random rand = new Random();
        final int szam = 1 + rand.nextInt(100);

        //Komponensek példányosítása
        final EditText tippNumber = (EditText) findViewById(R.id.tippNumber);
        final TextView tippView = findViewById(R.id.tippView);
        final Button tippButton = (Button) findViewById(R.id.tippButton);

        //Gomb figyelés
        tippButton.setOnClickListener(new OnClickListener() {
            //próba számláló inicializálása
            int prb = 10;
            //Gomb lenyomásának eseménye
            @Override
            public void onClick(View v) throws NumberFormatException {
                //Próba számláló csökkenése és tipp nullázás
                prb--;
                int tipp = 0;
                //tipp beolvasása és kovertálása integerré. Különböző esetek vizsgálása.
                try {
                    tipp = Integer.parseInt(tippNumber.getText().toString());
                        if (tipp == szam) {
                            tippView.setText("Kitaláltad! Nyertél!!");
                            tippButton.setEnabled(Boolean.parseBoolean("False"));
                        } else if (tipp > 100 || tipp <= 0) {
                            tippView.setText("1 és 100 között legyen!");
                            tippButton.setText("Már csak " + prb);
                            tippNumber.setText("");
                        } else if (tipp > szam) {
                            tippView.setText("Kisebb");
                            tippButton.setText("Már csak " + prb);
                            tippNumber.setText("");
                        } else if (tipp < szam) {
                            tippView.setText("Nagyobb");
                            tippButton.setText("Már csak " + prb);
                            tippNumber.setText("");
                        }
                        //Ha elfogytak a próbák a gomb inaktív lesz és vereség a vége.
                        if (prb == 0){
                            tippButton.setEnabled(Boolean.parseBoolean("False"));
                            tippView.setText("Nincs több próbálkozás. Vesztettél!");
                        }
                //Ha nincs szám akkor figyelmeztetés. Nem számolja a próbát.
                } catch (java.lang.NumberFormatException exception) {
                    tippView.setText("Nem adtál meg számot!");
                }
            }
        });
    }
}
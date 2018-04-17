package com.droidgenesis.os_i.tictactoe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class CreditsActivity extends AppCompatActivity {

    public CardView cardViewAppIcon, cardViewHomeBg, cardViewBg2, cardViewSoundEffects;

    /**Activities that gives credit for resources used in the app and links then to their page online*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        cardViewAppIcon = findViewById(R.id.credit_appicon);
        cardViewHomeBg = findViewById(R.id.credit_background_graysquare);
        cardViewBg2 = findViewById(R.id.credit_background_bright_with_dots_grey);
        cardViewSoundEffects = findViewById(R.id.credit_sound);

        //App Icon credit web link
    cardViewAppIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flaticon.com/free-icon/tic-tac-toe_745804")));
        }
    });
        //Home background credit web link
        cardViewHomeBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freepik.com/free-vector/grey-square-pattern-background-vector-illustration_1265271.htm")));
            }
        });

        //Second grey background credit web link
        cardViewBg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freepik.com/free-vector/bright-background-with-dots_1252906.htm")));
            }
        });

        //Sound credit web link
        cardViewSoundEffects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zapsplat.com/")));
            }
        });
    }
}

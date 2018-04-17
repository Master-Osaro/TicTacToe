package com.droidgenesis.os_i.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.droidgenesis.os_i.tictactoe.Player_Vs_AI.Player_Vs_AI_3x3.PlayerVsAI_3x3_Activity;
import com.droidgenesis.os_i.tictactoe.Player_Vs_AI.Player_Vs_AI_4x4.PlayerVsAI_4x4_Activity;
import com.droidgenesis.os_i.tictactoe.Player_Vs_AI.Player_Vs_AI_5x5.PlayerVsAI_5x5_Activity;
import com.droidgenesis.os_i.tictactoe.Player_Vs_Player.Player_Vs_Player_3x3;
import com.droidgenesis.os_i.tictactoe.Player_Vs_Player.Player_Vs_Player_4x4;
import com.droidgenesis.os_i.tictactoe.Player_Vs_Player.Player_Vs_Player_5x5;
import com.droidgenesis.os_i.tictactoe.Player_Vs_Player.Player_Vs_Player_5x5board_3x3rules;

/**Home Screen Activity of this app*/

public class MainActivity extends AppCompatActivity {

    //Alert dialog that will enable player choose board size
    AlertDialog alertDialog1;

    CardView cardView;

    //An array of values holding the names of the the board selections for the Alert dialog
    CharSequence[] values0 = {" 3 x 3 Board "," 4 x 4 Board "," 5 x 5 Board "," 5 x 5 Board (3 x 3 rules)"};
    CharSequence[] values = {" 3 x 3 Board "," 4 x 4 Board "," 5 x 5 Board "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hook the player vs player button to a variable
        Button button = findViewById(R.id.btn_player_vs_player);
        //Map the player vs CPU button to its Id
        Button button0 = findViewById(R.id.btn_player_vs_cpu);

        //Map the carrdView in main activity to a variable. We will use it for animation later
        cardView = findViewById(R.id.myView);
        cardView.setVisibility(View.INVISIBLE);
        fadeIn();
        //Set an Onclick listener so the Player vs Player button can show "Select Board" Dialog when clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Build the alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //Name speaks for itself. Sets the title and Icon for the alert dialog
                builder.setTitle("  Select Board Size");
                builder.setIcon(R.drawable.ttt);

                /**An onclick listener for the radio buttons. So when they are clicked on, The game
                 * moves the user to the selected board activity
                 */
                builder.setSingleChoiceItems(values0, -1, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        switch(item)
                        {
                            case 0:
                                startActivity( new Intent(MainActivity.this, Player_Vs_Player_3x3.class));
                                break;
                            case 1:
                                startActivity( new Intent(MainActivity.this, Player_Vs_Player_4x4.class));
                                break;
                            case 2:
                                startActivity( new Intent(MainActivity.this, Player_Vs_Player_5x5.class));
                                break;
                            case 3:
                                startActivity( new Intent(MainActivity.this, Player_Vs_Player_5x5board_3x3rules.class));
                                break;
                        }
                        alertDialog1.dismiss();
                    }
                });

                //Creates and displays the alert dialog when the "Player vs Player button is clicked"
                alertDialog1 = builder.create();
                alertDialog1.show();
            }
        });

        //Set an Onclick listener so the button can show "Select Board" Dialog when clicked
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This line below builds the alert dialog fot the player vs CPU button
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //Set the title and Icon for the alert dialog
                builder.setTitle("  Select Board Size");
                builder.setIcon(R.drawable.ttt);

                /**An onclick listener for the radio buttons. So when they are clicked on, The game
                 * moves the user to the selected board activity
                 */
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {

                        switch(item)
                        {
                            case 0:
                                startActivity( new Intent(MainActivity.this, PlayerVsAI_3x3_Activity.class));

                                break;
                            case 1:
                                startActivity( new Intent(MainActivity.this, PlayerVsAI_4x4_Activity.class));
                                break;

                            case 2:
                                startActivity( new Intent(MainActivity.this, PlayerVsAI_5x5_Activity.class));
                                break;
                        }
                        alertDialog1.dismiss();
                    }
                });

                //Creates and displays the Alert Dialog
                alertDialog1 = builder.create();
                alertDialog1.show();

            }
        });
    }

    //Fade in animation for the cardView
    public void fadeIn(){

        AlphaAnimation anim = new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(2000);
        cardView.startAnimation(anim);
        cardView.setVisibility(View.VISIBLE);
    }

    //And options or 3 dot menu on the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Handles Action to be taken when a selected option is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }else if (id == R.id.action_help){
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }else if(id == R.id.action_credit){
            startActivity(new Intent(MainActivity.this, CreditsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}

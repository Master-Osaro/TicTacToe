package com.droidgenesis.os_i.tictactoe.Player_Vs_AI.Player_Vs_AI_3x3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.droidgenesis.os_i.tictactoe.R;
/**
 * This activity handles everything about the Player Vs CPU 3x3 board
 * It contains
 * 1. Variable Declarations
 * 2. View Initializations
 * 3. Start , Reset and Clear Board Onclick Subroutine
 * 4. Board Enabling and Disabling Subroutines
 * 5. Shared Preferences Subroutines
 * 6. Winning Move, Winning Check, Placing Object and Board State Subroutines
 * 7. Sound Subroutines
 * 8. Options Menu Subroutines
 *
 **/
public class PlayerVsAI_3x3_Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 1. Variable declarations for  Buttons, Radio buttons, AI Logic and Mediaplayer etc some view initializations
     * too when the activity is created
     **/
    public Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, start, reset, reset_score;
    public RadioButton naught, cross;
    public TextView text;
    public WinnableAI logic; // Easier to when because the minimax algorithms is not used
    public UnbeatableAI perfectLogic; // Much more difficult because minimax algorithm is used


    public boolean playerTurn = false;
    public boolean playerWin = false;
    public boolean aiWin = false;
    public boolean draw = false;

    public int player_points ;
    public int ai_points ;

    public TextView textViewPlayer1, chooseLetter, textViewAI;

    public CheckBox diffuicultyCheckbox, soundCheckbox;

    private MediaPlayer mediaPlayer, mediaPlayer2, mediaPlayerWin, mediaPlayerDraw, mediaPlayerLose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_3x3);

        textViewPlayer1 =  findViewById(R.id.txt_player);
        textViewAI =  findViewById(R.id.txt_ai);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound00);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.sound01);
        mediaPlayerWin = MediaPlayer.create(this, R.raw.win);
        mediaPlayerDraw = MediaPlayer.create(this, R.raw.draw);
        mediaPlayerLose = MediaPlayer.create(this, R.raw.lose);

        initViews();
        //set the below buttons as invisible when the view is created
        reset_score.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);
        logic = new WinnableAI();
        perfectLogic = new UnbeatableAI();
        loadPrefs();
    }
    /***End of Variable Declarations***/



    /***2. Initialization of Views to prevent referencing a null Object**/
    //Method that maps views from xml to java variable
    private void initViews() {
        button1 =  findViewById(R.id.button1);
        button2 =  findViewById(R.id.button2);
        button3 =  findViewById(R.id.button3);
        button4 =  findViewById(R.id.button4);
        button5 =  findViewById(R.id.button5);
        button6 =  findViewById(R.id.button6);
        button7 =  findViewById(R.id.button7);
        button8 =  findViewById(R.id.button8);
        button9 =  findViewById(R.id.button9);
        start =    findViewById(R.id.start);
        reset =    findViewById(R.id.reset);
        reset_score =  findViewById(R.id.reset_score);

        naught =  findViewById(R.id.naught);
        cross =  findViewById(R.id.cross);

        //used to switch difficulty
        diffuicultyCheckbox =  findViewById(R.id.ai_switcher);

        //used to toggle sound
        soundCheckbox =  findViewById(R.id.sound);

        text =  findViewById(R.id.text);

        chooseLetter = findViewById(R.id.playerchoice);
        //enable onclick listener for all the buttons
        enableTiles();

        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        reset_score.setOnClickListener(this);
    }
    /********End of View Initializations****************/


    /****
     * 3. Start , Reset and Clear Board Onclick Listener
     *    This section handles the START, RESET and CLEAR BOARD buttons in the game
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                /** Enables the views required to start the game to be invisible when the game begins
                 * */
                start.setVisibility(View.INVISIBLE);
                naught.setVisibility(View.INVISIBLE);
                cross.setVisibility(View.INVISIBLE);
                chooseLetter.setVisibility(View.INVISIBLE);
                diffuicultyCheckbox.setVisibility(View.INVISIBLE);
                reset_score.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);
                if(cross.isChecked()) {
                    playerTurn = true;
                    text.setText("Player's turn");
                } else if(naught.isChecked()) {
                    playerTurn = false;
                    disableTiles();
                    text.setText("AI's turn");
                    logic.nextMove(this); // Place the first move in the center
                }

                break;
            case R.id.reset:
                // Make all the invisible views visible again
                enableTiles();
                start.setVisibility(View.VISIBLE);
                naught.setVisibility(View.VISIBLE);
                cross.setVisibility(View.VISIBLE);
                diffuicultyCheckbox.setVisibility(View.VISIBLE);
                chooseLetter.setVisibility(View.VISIBLE);
                reset_score.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.INVISIBLE);
                text.setText("");
                playerTurn = false;
                playerWin = false;
                aiWin = false;
                draw = false;

                resetBoard();
                break;

            //reset the scoreboard
            case R.id.reset_score:
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Clear Scores?");
                    alertDialog.setMessage("Are you sure? Score counter will be set to 0 for both players");
                    alertDialog.setIcon(R.drawable.ttt);
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "RESET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            start.setVisibility(View.VISIBLE);
                            naught.setVisibility(View.VISIBLE);
                            cross.setVisibility(View.VISIBLE);
                            diffuicultyCheckbox.setVisibility(View.VISIBLE);
                            chooseLetter.setVisibility(View.VISIBLE);
                            reset_score.setVisibility(View.INVISIBLE);
                            reset.setVisibility(View.INVISIBLE);
                            text.setText("");
                            playerTurn = false;
                            playerWin = false;
                            aiWin = false;
                            draw = false;
                            textViewPlayer1.setText("Player: 0");
                            textViewAI.setText("AI: 0");
                            player_points = 0;
                            ai_points = 0;
                            savePref();
                            resetBoard();
                        }
                    });
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Do nothing
                        }
                    });

                    alertDialog.show();
                }
                catch(Exception e)
                {
                    //ToDo
                }

                break;
            default:
                if(start.getVisibility() == View.VISIBLE)
                    break; // If a button is pressed without starting the game
                placeObject(v.getId());
                boolean shouldBeHardtoWin = diffuicultyCheckbox.isChecked();

                //If play with difficult ai is unchecked
                if(!shouldBeHardtoWin)
                    logic.nextMove(this); // Don't use minimax
                else
                    perfectLogic.nextMove(this); // Use minimax
        }
    }
    /***End of Start, Reset and Clear Board Onclick Subroutine***/


    /*** 4. Board Enabling and Disabling Subroutines
     *   Created these methods so they can be called easily to prevent user from being able to click when the AI is playing
     *   And to enable the board when the player is about to play
     **/
    //Disables tiles from being clicked
    public void disableTiles(){
        button1.setOnClickListener(null);
        button2.setOnClickListener(null);
        button3.setOnClickListener(null);
        button4.setOnClickListener(null);
        button5.setOnClickListener(null);
        button6.setOnClickListener(null);
        button7.setOnClickListener(null);
        button8.setOnClickListener(null);
        button9.setOnClickListener(null);
    }

    //Makes tiles clickable
    public void enableTiles(){
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }
    /***End of Board Enabling and Disabling Subroutines***/



    /****
     * 5. Shared Preferences Subroutine
     *      Subroutines to save score count of Player and AI, so scores can be saved even when
     *      app is closed and reopened
     */
    public void savePref(){
        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("playerscore", player_points);
        editor.putInt("aiscore", ai_points);
        editor.commit();
    }

    public void loadPrefs(){
        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        player_points = preferences.getInt("playerscore",0);
        ai_points = preferences.getInt("aiscore", 0);
        textViewPlayer1.setText("Player: "+ player_points);
        textViewAI.setText("AI: "+ ai_points);

    }
    /**End of Shared Preferences Subroutines***/



    /**
     * 6. Winning Move, Winning Check, Place Object and Board State Subroutines
     * These methods below defines winnable moves, places text on the board , checks board state
     * and confirms win
     * */

    /*******Method places text on the button tiles on click************/
    public void placeObject(int id) {
        Button button =  findViewById(id);
        if(!button.getText().equals(""))
            return;

        if(cross.isChecked() && playerTurn) {
            button.setText("X");
            if(soundCheckbox.isChecked()) {
                playSound();
            }
        }
        else if(naught.isChecked() && playerTurn) {
            button.setText("O");
            if(soundCheckbox.isChecked()) {
                playSound();
            }
        }

        check(); // check if the game is over
        if(playerWin && !draw) {
            disableTiles();
            player_points++;
            String playerscore = "Player: " + player_points;
            textViewPlayer1.setText(playerscore);
            playWinSound();
            text.setText("Player Won!");
            savePref();
        }
        else if(aiWin && !draw){
            ai_points++;
            String pcscore = "AI: " + ai_points;
            textViewAI.setText(pcscore);
            playLoseSound();
            text.setText("AI Won!");
            savePref();
        }
        else if(draw){
            playDrawSound();
            savePref();
            text.setText("It's a draw!");

        }
        else
            changeTurn();
    }


    /**This change turn subroutine makes the text display whose turn it is, player or CPU
     * and disables the board as appropriate**/
    public void changeTurn() {
        playerTurn = !playerTurn;
        if(playerTurn){
            enableTiles();
            text.setText("Player's Turn");}
        else {
            disableTiles();
            text.setText("AI is thinking. Please wait.......");
        }
    }



    //Method to check if game is won after move
    public void check() {
        if (crossHorizontal() || crossVertical() || crossDiagonal()) {
            if(cross.isChecked())
                playerWin = true;
            else
                aiWin = true;
        } else if (naughtsHorizontal() || naughtsVertical()
                || naughtsDiagonal()) {
            if(naught.isChecked())
                playerWin = true;
            else
                aiWin = true;
        } else if(full() && !playerWin && !aiWin)
            draw = true;
    }

    //Resets the text on the board
    public void resetBoard(){
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
    }

    //Check if board is full and no more move to make
    public boolean full(){
        return !button1.getText().equals("")
                && !button2.getText().equals("")
                && !button3.getText().equals("")
                && !button4.getText().equals("")
                && !button5.getText().equals("")
                && !button6.getText().equals("")
                && !button7.getText().equals("")
                && !button8.getText().equals("")
                && !button9.getText().equals("");
    }

    //Check if board ids empty
    public boolean isEmpty() {
        return button1.getText().equals("")
                && button2.getText().equals("")
                && button3.getText().equals("")
                && button4.getText().equals("")
                && button5.getText().equals("")
                && button6.getText().equals("")
                && button7.getText().equals("")
                && button8.getText().equals("")
                && button9.getText().equals("");
    }

    /**Winning moves Horizontally, Vertically and Diagonally*/
    public boolean crossHorizontal() {
        return (button1.getText().equals("X") && button2.getText().equals("X") && button3.getText().equals("X")) ||
                (button4.getText().equals("X") && button5.getText().equals("X") && button6.getText().equals("X")) ||
                (button7.getText().equals("X") && button8.getText().equals("X") && button9.getText().equals("X"));
    }

    public boolean naughtsHorizontal() {
        return (button1.getText().equals("O") && button2.getText().equals("O") && button3.getText().equals("O")) ||
                (button4.getText().equals("O") && button5.getText().equals("O") && button6.getText().equals("O")) ||
                (button7.getText().equals("O") && button8.getText().equals("O") && button9.getText().equals("O"));
    }

    public boolean crossVertical() {
        return (button1.getText().equals("X") && button4.getText().equals("X") && button7.getText().equals("X")) ||
                (button2.getText().equals("X") && button5.getText().equals("X") && button8.getText().equals("X")) ||
                (button3.getText().equals("X") && button6.getText().equals("X") && button9.getText().equals("X"));
    }

    public boolean naughtsVertical() {
        return (button1.getText().equals("O") && button4.getText().equals("O") && button7.getText().equals("O")) ||
                (button2.getText().equals("O") && button5.getText().equals("O") && button8.getText().equals("O")) ||
                (button3.getText().equals("O") && button6.getText().equals("O") && button9.getText().equals("O"));
    }

    public boolean crossDiagonal() {
        return (button1.getText().equals("X") && button5.getText().equals("X") && button9.getText().equals("X")) ||
                (button3.getText().equals("X") && button5.getText().equals("X") && button7.getText().equals("X"));
    }

    public boolean naughtsDiagonal() {
        return (button1.getText().equals("O") && button5.getText().equals("O") && button9.getText().equals("O")) ||
                (button3.getText().equals("O") && button5.getText().equals("O") && button7.getText().equals("O"));
    }
    /*****************************************End of Winning Move subroutines***********************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ingame_menu_main, menu);
        return true;
    }


    /**
     * 7. Sound Subroutines
     * The Subroutines in this section are for toggling sounds in the game
     **/
    public void playSound(){
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSound();
            }
        });
    }

    public void playSecondSound(){
        mediaPlayer2.start();
        mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSecondSound();
            }
        });
    }public void playWinSound(){
        if(soundCheckbox.isChecked()) {
            mediaPlayerWin.start();
        }
        mediaPlayerWin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopWinSound();
            }
        });
    }

    public void playDrawSound(){
        if(soundCheckbox.isChecked()) {
            mediaPlayerDraw.start();
        }
        mediaPlayerDraw.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopDrawSound();
            }
        });
    }public void playLoseSound(){
        if(soundCheckbox.isChecked()) {
            mediaPlayerLose.start();
        }
        mediaPlayerLose.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopLoseSound();
            }
        });
    }


    public void stopSecondSound(){
        mediaPlayer2.stop();
        mediaPlayer2.reset();
        mediaPlayer2 = MediaPlayer.create(this, R.raw.sound01);
    }

    public void stopSound(){
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound00);
    }
    public void stopWinSound(){
        mediaPlayerWin.stop();
        mediaPlayerWin.reset();
        mediaPlayerWin = MediaPlayer.create(this, R.raw.win);
    }

    public void stopDrawSound(){
        mediaPlayerDraw.stop();
        mediaPlayerDraw.reset();
        mediaPlayerDraw = MediaPlayer.create(this, R.raw.draw);
    }
    public void stopLoseSound(){
        mediaPlayerLose.stop();
        mediaPlayerLose.reset();
        mediaPlayerLose = MediaPlayer.create(this, R.raw.lose);
    }

    /*******************************Button Sound Logic End*****************************/


    /**8. Options Menu Subroutines*/
    //Handles Action to be taken when a selected option is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_start_game) {
            /** Enables the views required to start the game to be invisible when the game begins
             * */
            start.setVisibility(View.INVISIBLE);
            naught.setVisibility(View.INVISIBLE);
            cross.setVisibility(View.INVISIBLE);
            chooseLetter.setVisibility(View.INVISIBLE);
            diffuicultyCheckbox.setVisibility(View.INVISIBLE);
            reset_score.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);
            if(cross.isChecked()) {
                playerTurn = true;
                text.setText("Player's turn");
            } else if(naught.isChecked()) {
                playerTurn = false;
                disableTiles();
                text.setText("AI's turn");
                logic.nextMove(this); // Place the first move in the center
            }
        }else if (id == R.id.action_reset_scores){
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Clear Scores?");
                alertDialog.setMessage("Are you sure? Score counter will be set to 0 for both players");
                alertDialog.setIcon(R.drawable.ttt);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "RESET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        start.setVisibility(View.VISIBLE);
                        naught.setVisibility(View.VISIBLE);
                        cross.setVisibility(View.VISIBLE);
                        diffuicultyCheckbox.setVisibility(View.VISIBLE);
                        chooseLetter.setVisibility(View.VISIBLE);
                        reset_score.setVisibility(View.INVISIBLE);
                        reset.setVisibility(View.INVISIBLE);
                        text.setText("");
                        playerTurn = false;
                        playerWin = false;
                        aiWin = false;
                        draw = false;
                        textViewPlayer1.setText("Player: 0");
                        textViewAI.setText("AI: 0");
                        player_points = 0;
                        ai_points = 0;
                        resetBoard();
                        enableTiles();
                        savePref();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Do nothing
                    }
                });

                alertDialog.show();
            }
            catch(Exception e)
            {
                //ToDo
            }
        }
        else if(id == R.id.action_clearboard){
            // Make all the invisible views visible again
            enableTiles();
            start.setVisibility(View.VISIBLE);
            naught.setVisibility(View.VISIBLE);
            cross.setVisibility(View.VISIBLE);
            diffuicultyCheckbox.setVisibility(View.VISIBLE);
            chooseLetter.setVisibility(View.VISIBLE);
            reset_score.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);
            text.setText("");
            playerTurn = false;
            playerWin = false;
            aiWin = false;
            draw = false;

            resetBoard();
        }

        return super.onOptionsItemSelected(item);
    }
}

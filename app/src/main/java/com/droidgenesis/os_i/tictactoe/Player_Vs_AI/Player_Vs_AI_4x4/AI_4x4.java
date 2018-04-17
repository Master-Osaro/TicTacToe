package com.droidgenesis.os_i.tictactoe.Player_Vs_AI.Player_Vs_AI_4x4;

import android.os.Handler;

import java.util.Random;

/**Helper class for the Player_Vs_AI_4x4 Activity that defines the behavior of the AI*/
public class AI_4x4 {

    private PlayerVsAI_4x4_Activity board;
    private boolean computerFirst = false;
    private boolean movePlayed = false; // Check to see if the computer played it's move

    public void nextMove(final PlayerVsAI_4x4_Activity board ) {
        computerFirst = !board.cross.isChecked() && board.isEmpty(); // is Computer going first
        this.board = board;

        if(board.playerTurn)
            return;

        Handler handler = new Handler(); // Give a delay of 1 second to make it look realistic
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                movePlayed = false;
                if (computerFirst && board.button5.getText().equals("")) {
                    board.button5.setText("X"); // Place the move in the center
                    movePlayed = true;
                } else if(!computerFirst) {
                    if(!movePlayed) win(); // First priority goes to winning
                    if(!movePlayed) defend(); // Second priority goes to preventing player from winning
                    if(!movePlayed) randomMove(); // Make a random move
                }

                if(movePlayed) {
                    board.check();
                    if (board.playerWin && !board.draw) {
                        board.player_points++;
                        String player_score = "Player: " + board.player_points;
                        board.textViewPlayer1.setText(player_score);
                        board.text.setText("Player Wins!");
                        board.playWinSound();
                        board.disableTiles();
                        board.savePref();

                    }
                    else if (board.aiWin && !board.draw) {
                        board.ai_points++;
                        String ai_score = "AI: " + board.ai_points;
                        board.textViewAI.setText(ai_score);
                        board.text.setText("Ai Wins!");
                        board.playLoseSound();
                        board.disableTiles();
                        board.savePref();

                    }
                    else if (board.draw) {
                        board.text.setText("It's a draw!");
                        board.playDrawSound();
                        board.savePref();
                        board.disableTiles();

                    }
                    else
                        board.changeTurn();
                }
                if(board.soundCheckbox.isChecked()) {
                    board.playSecondSound();
                }

            }
        }, 1000);

    }
    
    private void defend() {
        if(board.cross.isChecked()) {
            /**Defend Horizontally against X*/
            if(board.button1.getText().equals("X") && board.button2.getText().equals("X") && board.button3.getText().equals("X") && board.button4.getText().equals("")  && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button2.getText().equals("X") && board.button3.getText().equals("") && board.button4.getText().equals("X")  && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button2.getText().equals("") && board.button3.getText().equals("X") && board.button4.getText().equals("X")  && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button2.getText().equals("X") && board.button3.getText().equals("X") && board.button4.getText().equals("X")  && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("X") && board.button6.getText().equals("X") && board.button7.getText().equals("X") && board.button8.getText().equals("")  && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("X") && board.button6.getText().equals("X") && board.button7.getText().equals("") && board.button8.getText().equals("X")  && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("X") && board.button6.getText().equals("") && board.button7.getText().equals("X") && board.button8.getText().equals("X")  && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("") && board.button6.getText().equals("X") && board.button7.getText().equals("X") && board.button8.getText().equals("X")  && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("X") && board.button10.getText().equals("X") && board.button11.getText().equals("X") && board.button12.getText().equals("")  && !board.playerTurn) {
                board.button12.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("X") && board.button10.getText().equals("X") && board.button11.getText().equals("") && board.button12.getText().equals("X")  && !board.playerTurn) {
                board.button11.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("X") && board.button10.getText().equals("") && board.button11.getText().equals("X") && board.button12.getText().equals("X")  && !board.playerTurn) {
                board.button10.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("") && board.button10.getText().equals("X") && board.button11.getText().equals("X") && board.button12.getText().equals("X")  && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("X") && board.button14.getText().equals("X") && board.button15.getText().equals("X") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("X") && board.button14.getText().equals("X") && board.button15.getText().equals("") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button15.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("X") && board.button14.getText().equals("") && board.button15.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button14.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("") && board.button14.getText().equals("X") && board.button15.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button13.setText("O");
                movePlayed = true;
            }

            /*****Defend against X vertically***/
            else if(board.button1.getText().equals("X") && board.button5.getText().equals("X") && board.button9.getText().equals("X") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button5.getText().equals("X") && board.button9.getText().equals("") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button5.getText().equals("") && board.button9.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button5.getText().equals("X") && board.button9.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("X") && board.button6.getText().equals("X") && board.button10.getText().equals("X") && board.button14.getText().equals("")  && !board.playerTurn) {
                board.button14.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("X") && board.button6.getText().equals("X") && board.button10.getText().equals("") && board.button14.getText().equals("X")  && !board.playerTurn) {
                board.button10.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("X") && board.button6.getText().equals("") && board.button10.getText().equals("X") && board.button14.getText().equals("X")  && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("") && board.button6.getText().equals("X") && board.button10.getText().equals("X") && board.button14.getText().equals("X")  && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("X") && board.button7.getText().equals("X") && board.button11.getText().equals("X") && board.button15.getText().equals("")  && !board.playerTurn) {
                board.button15.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("X") && board.button7.getText().equals("X") && board.button11.getText().equals("") && board.button15.getText().equals("X")  && !board.playerTurn) {
                board.button11.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("X") && board.button7.getText().equals("") && board.button11.getText().equals("X") && board.button15.getText().equals("X")  && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("") && board.button7.getText().equals("X") && board.button11.getText().equals("X") && board.button15.getText().equals("X")  && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button8.getText().equals("X") && board.button12.getText().equals("X") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button8.getText().equals("X") && board.button12.getText().equals("") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button12.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button8.getText().equals("") && board.button12.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button8.getText().equals("X") && board.button12.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }
            /**Defend Against X diagonally from Left**/
            else if(board.button1.getText().equals("X") && board.button6.getText().equals("X") && board.button11.getText().equals("X") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button6.getText().equals("X") && board.button11.getText().equals("") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button11.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button6.getText().equals("") && board.button11.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button6.getText().equals("X") && board.button11.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            }


            /**Defend Against X diagonally from Right**/
            else if(board.button4.getText().equals("X") && board.button7.getText().equals("X") && board.button10.getText().equals("X") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button7.getText().equals("X") && board.button10.getText().equals("") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button10.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button7.getText().equals("") && board.button10.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button7.getText().equals("X") && board.button10.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }
        }
        else if(board.naught.isChecked()) {
            /**Defence Against O winning**/
            /**Defend Horizontally against O*/
            if(board.button1.getText().equals("O") && board.button2.getText().equals("O") && board.button3.getText().equals("O") && board.button4.getText().equals("")  && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button2.getText().equals("O") && board.button3.getText().equals("") && board.button4.getText().equals("O")  && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button2.getText().equals("") && board.button3.getText().equals("O") && board.button4.getText().equals("O")  && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button2.getText().equals("O") && board.button3.getText().equals("O") && board.button4.getText().equals("O")  && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("O") && board.button6.getText().equals("O") && board.button7.getText().equals("O") && board.button8.getText().equals("")  && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("O") && board.button6.getText().equals("O") && board.button7.getText().equals("") && board.button8.getText().equals("O")  && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("O") && board.button6.getText().equals("") && board.button7.getText().equals("O") && board.button8.getText().equals("O")  && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("") && board.button6.getText().equals("O") && board.button7.getText().equals("O") && board.button8.getText().equals("O")  && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("O") && board.button10.getText().equals("O") && board.button11.getText().equals("O") && board.button12.getText().equals("")  && !board.playerTurn) {
                board.button12.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("O") && board.button10.getText().equals("O") && board.button11.getText().equals("") && board.button12.getText().equals("O")  && !board.playerTurn) {
                board.button11.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("O") && board.button10.getText().equals("") && board.button11.getText().equals("O") && board.button12.getText().equals("O")  && !board.playerTurn) {
                board.button10.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("") && board.button10.getText().equals("O") && board.button11.getText().equals("O") && board.button12.getText().equals("O")  && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("O") && board.button14.getText().equals("O") && board.button15.getText().equals("O") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("O") && board.button14.getText().equals("O") && board.button15.getText().equals("") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button15.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("O") && board.button14.getText().equals("") && board.button15.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button14.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("") && board.button14.getText().equals("O") && board.button15.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button13.setText("X");
                movePlayed = true;
            }

            /*****Defend against O vertically***/
            else if(board.button1.getText().equals("O") && board.button5.getText().equals("O") && board.button9.getText().equals("O") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button5.getText().equals("O") && board.button9.getText().equals("") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button5.getText().equals("") && board.button9.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button5.getText().equals("O") && board.button9.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("O") && board.button6.getText().equals("O") && board.button10.getText().equals("O") && board.button14.getText().equals("")  && !board.playerTurn) {
                board.button14.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("O") && board.button6.getText().equals("O") && board.button10.getText().equals("") && board.button14.getText().equals("O")  && !board.playerTurn) {
                board.button10.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("O") && board.button6.getText().equals("") && board.button10.getText().equals("O") && board.button14.getText().equals("O")  && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("") && board.button6.getText().equals("O") && board.button10.getText().equals("O") && board.button14.getText().equals("O")  && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("O") && board.button7.getText().equals("O") && board.button11.getText().equals("O") && board.button15.getText().equals("")  && !board.playerTurn) {
                board.button15.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("O") && board.button7.getText().equals("O") && board.button11.getText().equals("") && board.button15.getText().equals("O")  && !board.playerTurn) {
                board.button11.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("O") && board.button7.getText().equals("") && board.button11.getText().equals("O") && board.button15.getText().equals("O")  && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("") && board.button7.getText().equals("O") && board.button11.getText().equals("O") && board.button15.getText().equals("O")  && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button8.getText().equals("O") && board.button12.getText().equals("O") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button8.getText().equals("O") && board.button12.getText().equals("") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button12.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button8.getText().equals("") && board.button12.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button8.getText().equals("O") && board.button12.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }
            /**Defend Against O diagonally from Left**/
            else if(board.button1.getText().equals("O") && board.button6.getText().equals("O") && board.button11.getText().equals("O") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button6.getText().equals("O") && board.button11.getText().equals("") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button11.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button6.getText().equals("") && board.button11.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button6.getText().equals("O") && board.button11.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            }


            /**Defend Against O diagonally from Right**/
            else if(board.button4.getText().equals("O") && board.button7.getText().equals("O") && board.button10.getText().equals("O") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button7.getText().equals("O") && board.button10.getText().equals("") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button10.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button7.getText().equals("") && board.button10.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button7.getText().equals("O") && board.button10.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }
        }
    }
    
    private void win() {
        if(board.naught.isChecked()) {
            /**Win Horizontally with X*/
            if(board.button1.getText().equals("X") && board.button2.getText().equals("X") && board.button3.getText().equals("X") && board.button4.getText().equals("")  && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button2.getText().equals("X") && board.button3.getText().equals("") && board.button4.getText().equals("X")  && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button2.getText().equals("") && board.button3.getText().equals("X") && board.button4.getText().equals("X")  && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button2.getText().equals("X") && board.button3.getText().equals("X") && board.button4.getText().equals("X")  && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("X") && board.button6.getText().equals("X") && board.button7.getText().equals("X") && board.button8.getText().equals("")  && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("X") && board.button6.getText().equals("X") && board.button7.getText().equals("") && board.button8.getText().equals("X")  && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("X") && board.button6.getText().equals("") && board.button7.getText().equals("X") && board.button8.getText().equals("X")  && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }else if(board.button5.getText().equals("") && board.button6.getText().equals("X") && board.button7.getText().equals("X") && board.button8.getText().equals("X")  && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("X") && board.button10.getText().equals("X") && board.button11.getText().equals("X") && board.button12.getText().equals("")  && !board.playerTurn) {
                board.button12.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("X") && board.button10.getText().equals("X") && board.button11.getText().equals("") && board.button12.getText().equals("X")  && !board.playerTurn) {
                board.button11.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("X") && board.button10.getText().equals("") && board.button11.getText().equals("X") && board.button12.getText().equals("X")  && !board.playerTurn) {
                board.button10.setText("X");
                movePlayed = true;
            }else if(board.button9.getText().equals("") && board.button10.getText().equals("X") && board.button11.getText().equals("X") && board.button12.getText().equals("X")  && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("X") && board.button14.getText().equals("X") && board.button15.getText().equals("X") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("X") && board.button14.getText().equals("X") && board.button15.getText().equals("") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button15.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("X") && board.button14.getText().equals("") && board.button15.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button14.setText("X");
                movePlayed = true;
            }else if(board.button13.getText().equals("") && board.button14.getText().equals("X") && board.button15.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button13.setText("X");
                movePlayed = true;
            }

            /*****Win with X vertically***/
            else if(board.button1.getText().equals("X") && board.button5.getText().equals("X") && board.button9.getText().equals("X") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button5.getText().equals("X") && board.button9.getText().equals("") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button5.getText().equals("") && board.button9.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button5.getText().equals("X") && board.button9.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("X") && board.button6.getText().equals("X") && board.button10.getText().equals("X") && board.button14.getText().equals("")  && !board.playerTurn) {
                board.button14.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("X") && board.button6.getText().equals("X") && board.button10.getText().equals("") && board.button14.getText().equals("X")  && !board.playerTurn) {
                board.button10.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("X") && board.button6.getText().equals("") && board.button10.getText().equals("X") && board.button14.getText().equals("X")  && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }else if(board.button2.getText().equals("") && board.button6.getText().equals("X") && board.button10.getText().equals("X") && board.button14.getText().equals("X")  && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("X") && board.button7.getText().equals("X") && board.button11.getText().equals("X") && board.button15.getText().equals("")  && !board.playerTurn) {
                board.button15.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("X") && board.button7.getText().equals("X") && board.button11.getText().equals("") && board.button15.getText().equals("X")  && !board.playerTurn) {
                board.button11.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("X") && board.button7.getText().equals("") && board.button11.getText().equals("X") && board.button15.getText().equals("X")  && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            }else if(board.button3.getText().equals("") && board.button7.getText().equals("X") && board.button11.getText().equals("X") && board.button15.getText().equals("X")  && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button8.getText().equals("X") && board.button12.getText().equals("X") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button8.getText().equals("X") && board.button12.getText().equals("") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button12.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button8.getText().equals("") && board.button12.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button8.getText().equals("X") && board.button12.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }
            /**Win with X diagonally from Left**/
            else if(board.button1.getText().equals("X") && board.button6.getText().equals("X") && board.button11.getText().equals("X") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button6.getText().equals("X") && board.button11.getText().equals("") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button11.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("X") && board.button6.getText().equals("") && board.button11.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button6.getText().equals("X") && board.button11.getText().equals("X") && board.button16.getText().equals("X")  && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            }


            /**Win with X diagonally from Right**/
            else if(board.button4.getText().equals("X") && board.button7.getText().equals("X") && board.button10.getText().equals("X") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button7.getText().equals("X") && board.button10.getText().equals("") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button10.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("X") && board.button7.getText().equals("") && board.button10.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button7.getText().equals("X") && board.button10.getText().equals("X") && board.button13.getText().equals("X")  && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }

        } else if(board.cross.isChecked()) {
            /**Win with O**/
            /**Win Horizontally with O*/
            if(board.button1.getText().equals("O") && board.button2.getText().equals("O") && board.button3.getText().equals("O") && board.button4.getText().equals("")  && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button2.getText().equals("O") && board.button3.getText().equals("") && board.button4.getText().equals("O")  && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button2.getText().equals("") && board.button3.getText().equals("O") && board.button4.getText().equals("O")  && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button2.getText().equals("O") && board.button3.getText().equals("O") && board.button4.getText().equals("O")  && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("O") && board.button6.getText().equals("O") && board.button7.getText().equals("O") && board.button8.getText().equals("")  && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("O") && board.button6.getText().equals("O") && board.button7.getText().equals("") && board.button8.getText().equals("O")  && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("O") && board.button6.getText().equals("") && board.button7.getText().equals("O") && board.button8.getText().equals("O")  && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }else if(board.button5.getText().equals("") && board.button6.getText().equals("O") && board.button7.getText().equals("O") && board.button8.getText().equals("O")  && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("O") && board.button10.getText().equals("O") && board.button11.getText().equals("O") && board.button12.getText().equals("")  && !board.playerTurn) {
                board.button12.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("O") && board.button10.getText().equals("O") && board.button11.getText().equals("") && board.button12.getText().equals("O")  && !board.playerTurn) {
                board.button11.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("O") && board.button10.getText().equals("") && board.button11.getText().equals("O") && board.button12.getText().equals("O")  && !board.playerTurn) {
                board.button10.setText("O");
                movePlayed = true;
            }else if(board.button9.getText().equals("") && board.button10.getText().equals("O") && board.button11.getText().equals("O") && board.button12.getText().equals("O")  && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("O") && board.button14.getText().equals("O") && board.button15.getText().equals("O") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("O") && board.button14.getText().equals("O") && board.button15.getText().equals("") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button15.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("O") && board.button14.getText().equals("") && board.button15.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button14.setText("O");
                movePlayed = true;
            }else if(board.button13.getText().equals("") && board.button14.getText().equals("O") && board.button15.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button13.setText("O");
                movePlayed = true;
            }

            /*****Win with O vertically***/
            else if(board.button1.getText().equals("O") && board.button5.getText().equals("O") && board.button9.getText().equals("O") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button5.getText().equals("O") && board.button9.getText().equals("") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button5.getText().equals("") && board.button9.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button5.getText().equals("O") && board.button9.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("O") && board.button6.getText().equals("O") && board.button10.getText().equals("O") && board.button14.getText().equals("")  && !board.playerTurn) {
                board.button14.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("O") && board.button6.getText().equals("O") && board.button10.getText().equals("") && board.button14.getText().equals("O")  && !board.playerTurn) {
                board.button10.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("O") && board.button6.getText().equals("") && board.button10.getText().equals("O") && board.button14.getText().equals("O")  && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }else if(board.button2.getText().equals("") && board.button6.getText().equals("O") && board.button10.getText().equals("O") && board.button14.getText().equals("O")  && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("O") && board.button7.getText().equals("O") && board.button11.getText().equals("O") && board.button15.getText().equals("")  && !board.playerTurn) {
                board.button15.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("O") && board.button7.getText().equals("O") && board.button11.getText().equals("") && board.button15.getText().equals("O")  && !board.playerTurn) {
                board.button11.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("O") && board.button7.getText().equals("") && board.button11.getText().equals("O") && board.button15.getText().equals("O")  && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            }else if(board.button3.getText().equals("") && board.button7.getText().equals("O") && board.button11.getText().equals("O") && board.button15.getText().equals("O")  && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button8.getText().equals("O") && board.button12.getText().equals("O") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button8.getText().equals("O") && board.button12.getText().equals("") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button12.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button8.getText().equals("") && board.button12.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button8.getText().equals("O") && board.button12.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }
            /**Win with O diagonally from Left**/
            else if(board.button1.getText().equals("O") && board.button6.getText().equals("O") && board.button11.getText().equals("O") && board.button16.getText().equals("")  && !board.playerTurn) {
                board.button16.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button6.getText().equals("O") && board.button11.getText().equals("") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button11.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("O") && board.button6.getText().equals("") && board.button11.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }else if(board.button1.getText().equals("") && board.button6.getText().equals("O") && board.button11.getText().equals("O") && board.button16.getText().equals("O")  && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            }


            /**Win with O diagonally from Right**/
            else if(board.button4.getText().equals("O") && board.button7.getText().equals("O") && board.button10.getText().equals("O") && board.button13.getText().equals("")  && !board.playerTurn) {
                board.button13.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button7.getText().equals("O") && board.button10.getText().equals("") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button10.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("O") && board.button7.getText().equals("") && board.button10.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            }else if(board.button4.getText().equals("") && board.button7.getText().equals("O") && board.button10.getText().equals("O") && board.button13.getText().equals("O")  && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }
        }
    }
    
    private void randomMove() {
        Random r = new Random();

        while (!movePlayed) {
            switch (r.nextInt(16)) {
                case 0:
                    if(board.button1.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button1.setText("O");
                        else
                            board.button1.setText("X");
                        
                        movePlayed = true;
                    }
                    break;
                case 1:
                    if(board.button2.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button2.setText("O");
                        else
                            board.button2.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 2:
                    if(board.button3.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button3.setText("O");
                        else
                            board.button3.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 3:
                    if(board.button4.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button4.setText("O");
                        else
                            board.button4.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 4:
                    if(board.button5.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button5.setText("O");
                        else
                            board.button5.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 5:
                    if(board.button6.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button6.setText("O");
                        else
                            board.button6.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 6:
                    if(board.button7.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button7.setText("O");
                        else
                            board.button7.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 7:
                    if(board.button8.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button8.setText("O");
                        else
                            board.button8.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 8:
                    if(board.button9.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button9.setText("O");
                        else
                            board.button9.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 9:
                    if(board.button10.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button10.setText("O");
                        else
                            board.button10.setText("X");

                        movePlayed = true;
                    }
                    break;

                case 10:
                    if(board.button11.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button11.setText("O");
                        else
                            board.button11.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 11:
                    if(board.button12.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button12.setText("O");
                        else
                            board.button12.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 12:
                    if(board.button13.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button13.setText("O");
                        else
                            board.button13.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 13:
                    if(board.button14.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button14.setText("O");
                        else
                            board.button14.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 14:
                    if(board.button15.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button15.setText("O");
                        else
                            board.button15.setText("X");

                        movePlayed = true;
                    }
                    break;
                case 15:
                    if(board.button16.getText().equals("") && !board.playerTurn) {
                        if(board.cross.isChecked())
                            board.button16.setText("O");
                        else
                            board.button16.setText("X");

                        movePlayed = true;
                    }
                    break;
            }
        }
    }
}

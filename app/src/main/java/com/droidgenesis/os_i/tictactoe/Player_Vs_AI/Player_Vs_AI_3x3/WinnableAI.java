package com.droidgenesis.os_i.tictactoe.Player_Vs_AI.Player_Vs_AI_3x3;

import android.os.Handler;

import java.util.Random;

/**Helper class for the Player_Vs_AI_3x3 Activity that defines the behavior of the winnable AI*/

public class WinnableAI {
    //Variable Declarations

    //A variable to reference to All the declared functions/methods on the Player_Vs_AI_3x3 Activity
    private PlayerVsAI_3x3_Activity board;

    private boolean aiFirst = false;
    private boolean movePlayed = false; // Check to see if the computer played it's move

    public void nextMove(final PlayerVsAI_3x3_Activity board) {
        // If Player does'nt Choose X and the board is empty, it means CPU plays first
        aiFirst = !board.cross.isChecked() && board.isEmpty();
        this.board = board;

        //If its the player turn, AI waits for player to play
        if(board.playerTurn)
            return;

        Handler handler = new Handler(); // Give a delay of 1 second to make it look realistic
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                movePlayed = false;
                if (aiFirst && board.button5.getText().equals("")) {
                    board.button5.setText("X"); // Place the move in the center
                    movePlayed = true;
                } else if(!aiFirst) {
                    if(!movePlayed) win(); // If AI is about to play its turn, first priority goes to winning
                    if(!movePlayed) defend(); // Second priority goes to preventing player from winning
                    if(!movePlayed) randomMove(); // Else make a random move
                }

                if(movePlayed) {//If AI plays, then..
                    board.check();
                    if (board.playerWin && !board.draw) {
                        board.player_points++;
                        String player_score = "Player: " + board.player_points;
                        board.textViewPlayer1.setText(player_score);
                        board.text.setText("Player Won!");
                        board.playWinSound();
                        board.savePref();
                        board.disableTiles();

                    }
                    else if (board.aiWin && !board.draw) {
                        board.ai_points++;
                        String ai_score = "AI: " + board.ai_points;
                        board.textViewAI.setText(ai_score);
                        board.text.setText("AI Won!");
                        board.savePref();
                        board.disableTiles();
                        board.playLoseSound();
                    }
                    else if (board.draw) {
                        board.text.setText("It's a draw!");
                        board.playDrawSound();
                        board.disableTiles();
                        board.savePref();
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

    /**A defend method to prevent player from winning*/
    private void defend() {
        if(board.cross.isChecked()) {
            if(board.button1.getText().equals("X") && board.button2.getText().equals("X") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            } else if(board.button3.getText().equals("X") && board.button2.getText().equals("X") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            } else if(board.button1.getText().equals("X") && board.button3.getText().equals("X") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            }
            else if(board.button4.getText().equals("X") && board.button5.getText().equals("X") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            } else if(board.button6.getText().equals("X") && board.button5.getText().equals("X") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            } else if(board.button4.getText().equals("X") && board.button6.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
            else if(board.button7.getText().equals("X") && board.button8.getText().equals("X") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            } else if(board.button9.getText().equals("X") && board.button8.getText().equals("X") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            } else if(board.button7.getText().equals("X") && board.button9.getText().equals("X") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("X") && board.button4.getText().equals("X") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            } else if(board.button7.getText().equals("X") && board.button4.getText().equals("X") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            } else if(board.button1.getText().equals("X") && board.button7.getText().equals("X") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }
            else if(board.button2.getText().equals("X") && board.button5.getText().equals("X") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            } else if(board.button8.getText().equals("X") && board.button5.getText().equals("X") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            } else if(board.button2.getText().equals("X") && board.button8.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("X") && board.button6.getText().equals("X") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            } else if(board.button9.getText().equals("X") && board.button6.getText().equals("X") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            } else if(board.button3.getText().equals("X") && board.button9.getText().equals("X") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("X") && board.button5.getText().equals("X") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            } else if(board.button9.getText().equals("X") && board.button5.getText().equals("X") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            } else if(board.button1.getText().equals("X") && board.button9.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("X") && board.button5.getText().equals("X") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            } else if(board.button7.getText().equals("X") && board.button5.getText().equals("X") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            } else if(board.button3.getText().equals("X") && board.button7.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
        } else if(board.naught.isChecked()) {
            if(board.button1.getText().equals("O") && board.button2.getText().equals("O") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            } else if(board.button3.getText().equals("O") && board.button2.getText().equals("O") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            } else if(board.button1.getText().equals("O") && board.button3.getText().equals("O") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            }
            else if(board.button4.getText().equals("O") && board.button5.getText().equals("O") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            } else if(board.button6.getText().equals("O") && board.button5.getText().equals("O") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            } else if(board.button4.getText().equals("O") && board.button6.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
            else if(board.button7.getText().equals("O") && board.button8.getText().equals("O") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            } else if(board.button9.getText().equals("O") && board.button8.getText().equals("O") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            } else if(board.button7.getText().equals("O") && board.button9.getText().equals("O") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("O") && board.button4.getText().equals("O") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            } else if(board.button7.getText().equals("O") && board.button4.getText().equals("O") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            } else if(board.button1.getText().equals("O") && board.button7.getText().equals("O") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }
            else if(board.button2.getText().equals("O") && board.button5.getText().equals("O") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            } else if(board.button8.getText().equals("O") && board.button5.getText().equals("O") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            } else if(board.button2.getText().equals("O") && board.button8.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("O") && board.button6.getText().equals("O") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            } else if(board.button9.getText().equals("O") && board.button6.getText().equals("O") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            } else if(board.button3.getText().equals("O") && board.button9.getText().equals("O") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("O") && board.button5.getText().equals("O") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            } else if(board.button9.getText().equals("O") && board.button5.getText().equals("O") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            } else if(board.button1.getText().equals("O") && board.button9.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("O") && board.button5.getText().equals("O") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            } else if(board.button7.getText().equals("O") && board.button5.getText().equals("O") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            } else if(board.button3.getText().equals("O") && board.button7.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
        }
    }

    /**A win method to from win the game if player fails to block*/
    private void win() {
        if(board.cross.isChecked()) {
            if(board.button1.getText().equals("O") && board.button2.getText().equals("O") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            } else if(board.button3.getText().equals("O") && board.button2.getText().equals("O") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            } else if(board.button1.getText().equals("O") && board.button3.getText().equals("O") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            }
            else if(board.button4.getText().equals("O") && board.button5.getText().equals("O") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            } else if(board.button6.getText().equals("O") && board.button5.getText().equals("O") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            } else if(board.button4.getText().equals("O") && board.button6.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
            else if(board.button7.getText().equals("O") && board.button8.getText().equals("O") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            } else if(board.button9.getText().equals("O") && board.button8.getText().equals("O") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            } else if(board.button7.getText().equals("O") && board.button9.getText().equals("O") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("O") && board.button4.getText().equals("O") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            } else if(board.button7.getText().equals("O") && board.button4.getText().equals("O") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            } else if(board.button1.getText().equals("O") && board.button7.getText().equals("O") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("O");
                movePlayed = true;
            }
            else if(board.button2.getText().equals("O") && board.button5.getText().equals("O") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("O");
                movePlayed = true;
            } else if(board.button8.getText().equals("O") && board.button5.getText().equals("O") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("O");
                movePlayed = true;
            } else if(board.button2.getText().equals("O") && board.button8.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("O") && board.button6.getText().equals("O") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            } else if(board.button9.getText().equals("O") && board.button6.getText().equals("O") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            } else if(board.button3.getText().equals("O") && board.button9.getText().equals("O") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("O");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("O") && board.button5.getText().equals("O") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("O");
                movePlayed = true;
            } else if(board.button9.getText().equals("O") && board.button5.getText().equals("O") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("O");
                movePlayed = true;
            } else if(board.button1.getText().equals("O") && board.button9.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("O") && board.button5.getText().equals("O") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("O");
                movePlayed = true;
            } else if(board.button7.getText().equals("O") && board.button5.getText().equals("O") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("O");
                movePlayed = true;
            } else if(board.button3.getText().equals("O") && board.button7.getText().equals("O") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("O");
                movePlayed = true;
            }

        } else if(board.naught.isChecked()) {
            if(board.button1.getText().equals("X") && board.button2.getText().equals("X") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            } else if(board.button3.getText().equals("X") && board.button2.getText().equals("X") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            } else if(board.button1.getText().equals("X") && board.button3.getText().equals("X") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            }
            else if(board.button4.getText().equals("X") && board.button5.getText().equals("X") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            } else if(board.button6.getText().equals("X") && board.button5.getText().equals("X") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            } else if(board.button4.getText().equals("X") && board.button6.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
            else if(board.button7.getText().equals("X") && board.button8.getText().equals("X") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            } else if(board.button9.getText().equals("X") && board.button8.getText().equals("X") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            } else if(board.button7.getText().equals("X") && board.button9.getText().equals("X") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("X") && board.button4.getText().equals("X") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            } else if(board.button7.getText().equals("X") && board.button4.getText().equals("X") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            } else if(board.button1.getText().equals("X") && board.button7.getText().equals("X") && board.button4.getText().equals("") && !board.playerTurn) {
                board.button4.setText("X");
                movePlayed = true;
            }
            else if(board.button2.getText().equals("X") && board.button5.getText().equals("X") && board.button8.getText().equals("") && !board.playerTurn) {
                board.button8.setText("X");
                movePlayed = true;
            } else if(board.button8.getText().equals("X") && board.button5.getText().equals("X") && board.button2.getText().equals("") && !board.playerTurn) {
                board.button2.setText("X");
                movePlayed = true;
            } else if(board.button2.getText().equals("X") && board.button8.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("X") && board.button6.getText().equals("X") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            } else if(board.button9.getText().equals("X") && board.button6.getText().equals("X") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            } else if(board.button3.getText().equals("X") && board.button9.getText().equals("X") && board.button6.getText().equals("") && !board.playerTurn) {
                board.button6.setText("X");
                movePlayed = true;
            }
            else if(board.button1.getText().equals("X") && board.button5.getText().equals("X") && board.button9.getText().equals("") && !board.playerTurn) {
                board.button9.setText("X");
                movePlayed = true;
            } else if(board.button9.getText().equals("X") && board.button5.getText().equals("X") && board.button1.getText().equals("") && !board.playerTurn) {
                board.button1.setText("X");
                movePlayed = true;
            } else if(board.button1.getText().equals("X") && board.button9.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
            else if(board.button3.getText().equals("X") && board.button5.getText().equals("X") && board.button7.getText().equals("") && !board.playerTurn) {
                board.button7.setText("X");
                movePlayed = true;
            } else if(board.button7.getText().equals("X") && board.button5.getText().equals("X") && board.button3.getText().equals("") && !board.playerTurn) {
                board.button3.setText("X");
                movePlayed = true;
            } else if(board.button3.getText().equals("X") && board.button7.getText().equals("X") && board.button5.getText().equals("") && !board.playerTurn) {
                board.button5.setText("X");
                movePlayed = true;
            }
        }
    }

    //A method for AI to play a random move
    private void randomMove() {
        Random r = new Random();

        while (!movePlayed) {
            switch (r.nextInt(9)) {
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
            }
        }
    }
}

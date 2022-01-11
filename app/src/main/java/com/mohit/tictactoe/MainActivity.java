package com.mohit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    char activePlayer = 'X';
    char[] gameState = new char[9];
    boolean gameActive = true;
    boolean isFull = false;


    int[][] winningPos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    ImageView[] images = new ImageView[9];

    @SuppressLint("ResourceAsColor")
    public void onTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 0 && gameActive) {
            gameState[tappedImage] = activePlayer;
            if (activePlayer == 'O') {
                img.setImageResource(R.drawable.o);
                activePlayer = 'X';
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.x);
                activePlayer = 'O';
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - Tap to play");
            }
        }

        images[0] = findViewById(R.id.imageView1);
        images[1] = findViewById(R.id.imageView2);
        images[2] = findViewById(R.id.imageView3);
        images[3] = findViewById(R.id.imageView4);
        images[4] = findViewById(R.id.imageView5);
        images[5] = findViewById(R.id.imageView6);
        images[6] = findViewById(R.id.imageView7);
        images[7] = findViewById(R.id.imageView8);
        images[8] = findViewById(R.id.imageView9);

        // Check if any player has won
        for (int i = 0 ; i < 8 ; i++) {
            if (gameState[winningPos[i][0]] != 0 && gameState[winningPos[i][0]] == gameState[winningPos[i][1]] && gameState[winningPos[i][0]] == gameState[winningPos[i][2]]) {
                TextView status = findViewById(R.id.status);
                status.setText(gameState[winningPos[i][0]] + " HAS WON THE GAME");
                gameActive = false;

                for (int j = 0 ; j < 9 ; j++) {
                    int tag = Integer.parseInt(images[j].getTag().toString());
                    if (tag == winningPos[i][0] || tag == winningPos[i][1] || tag == winningPos[i][2]) {
                        images[j].setBackgroundColor(Color.RED);
                    }
                }
            }
        }

        for (int j = 0 ; j < 9 ; j++) {
            if (gameState[j] != 0) {
                isFull = true;
            }
            else {
                isFull = false;
                break;
            }
        }
        if (isFull && gameActive) {
            TextView status = findViewById(R.id.status);
            status.setText("GAME DRAWN");
        }

    }

    
    public void gameRestart(View view) {
        gameActive = true;
        activePlayer = 'X';
        gameState = new char[9];
        images[0] = findViewById(R.id.imageView1);
        images[1] = findViewById(R.id.imageView2);
        images[2] = findViewById(R.id.imageView3);
        images[3] = findViewById(R.id.imageView4);
        images[4] = findViewById(R.id.imageView5);
        images[5] = findViewById(R.id.imageView6);
        images[6] = findViewById(R.id.imageView7);
        images[7] = findViewById(R.id.imageView8);
        images[8] = findViewById(R.id.imageView9);

        for (int j = 0 ; j < 9 ; j++) {
            images[j].setImageResource(0);
            images[j].setBackgroundColor(0);
        }

        TextView status = findViewById(R.id.status);
        status.setText("X's turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
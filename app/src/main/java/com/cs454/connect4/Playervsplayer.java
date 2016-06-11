package com.cs454.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import android.view.View.OnClickListener;

public class Playervsplayer extends AppCompatActivity {
    Connect4 connect4;
    String[][] pattern;
    ArrayList<Point> emptySpaces = new ArrayList<>();
    boolean gameOver = false;
    int playerTurn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playervsplayer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<ImageView> images = new ArrayList<>();
        images.add((ImageView) findViewById(R.id.disk1));
        images.add((ImageView) findViewById(R.id.disk2));
        images.add((ImageView) findViewById(R.id.disk3));
        images.add((ImageView) findViewById(R.id.disk4));
        images.add((ImageView) findViewById(R.id.disk5));
        images.add((ImageView) findViewById(R.id.disk6));
        images.add((ImageView) findViewById(R.id.disk7));

        images.add((ImageView) findViewById(R.id.disk8));
        images.add((ImageView) findViewById(R.id.disk9));
        images.add((ImageView) findViewById(R.id.disk10));
        images.add((ImageView) findViewById(R.id.disk11));
        images.add((ImageView) findViewById(R.id.disk12));
        images.add((ImageView) findViewById(R.id.disk13));
        images.add((ImageView) findViewById(R.id.disk14));

        images.add((ImageView) findViewById(R.id.disk15));
        images.add((ImageView) findViewById(R.id.disk16));
        images.add((ImageView) findViewById(R.id.disk17));
        images.add((ImageView) findViewById(R.id.disk18));
        images.add((ImageView) findViewById(R.id.disk19));
        images.add((ImageView) findViewById(R.id.disk20));
        images.add((ImageView) findViewById(R.id.disk21));

        images.add((ImageView) findViewById(R.id.disk22));
        images.add((ImageView) findViewById(R.id.disk23));
        images.add((ImageView) findViewById(R.id.disk24));
        images.add((ImageView) findViewById(R.id.disk25));
        images.add((ImageView) findViewById(R.id.disk26));
        images.add((ImageView) findViewById(R.id.disk27));
        images.add((ImageView) findViewById(R.id.disk28));

        images.add((ImageView) findViewById(R.id.disk29));
        images.add((ImageView) findViewById(R.id.disk30));
        images.add((ImageView) findViewById(R.id.disk31));
        images.add((ImageView) findViewById(R.id.disk32));
        images.add((ImageView) findViewById(R.id.disk33));
        images.add((ImageView) findViewById(R.id.disk34));
        images.add((ImageView) findViewById(R.id.disk35));

        images.add((ImageView) findViewById(R.id.disk36));
        images.add((ImageView) findViewById(R.id.disk37));
        images.add((ImageView) findViewById(R.id.disk38));
        images.add((ImageView) findViewById(R.id.disk39));
        images.add((ImageView) findViewById(R.id.disk40));
        images.add((ImageView) findViewById(R.id.disk41));
        images.add((ImageView) findViewById(R.id.disk42));

        connect4 = new Connect4(images);
        pattern = connect4.createPattern();
        emptySpaces = connect4.checkAmountOfAvailableSpaces(pattern);

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        ImageButton button0 = (ImageButton) findViewById(R.id.button0);
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button4 = (ImageButton) findViewById(R.id.button4);
        ImageButton button5 = (ImageButton) findViewById(R.id.button5);
        ImageButton button6 = (ImageButton) findViewById(R.id.button6);
        ImageButton reset = (ImageButton) findViewById(R.id.reset);

        button0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if(playerTurn % 2 == 0){
                    if(!connect4.dropRedPattern(pattern, 0))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                }else{
                    if(!connect4.dropYellowPattern(pattern, 0))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if(connect4.checkWinner(pattern) != null){
                    if(connect4.checkWinner(pattern) == "R"){
                        status.setText("Red Player Wins");
                        disableButtons();
                    }else if(connect4.checkWinner(pattern) == "Y"){
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if(playerTurn % 2 == 0){
                    if(!connect4.dropRedPattern(pattern, 1))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                }else{
                    if(!connect4.dropYellowPattern(pattern, 1))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if(connect4.checkWinner(pattern) != null){
                    if(connect4.checkWinner(pattern) == "R"){
                        status.setText("Red Player Wins");
                        disableButtons();
                    }else if(connect4.checkWinner(pattern) == "Y"){
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if(playerTurn % 2 == 0){
                    if(!connect4.dropRedPattern(pattern, 2))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                }else{
                    if(!connect4.dropYellowPattern(pattern, 2))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if(connect4.checkWinner(pattern) != null){
                    if(connect4.checkWinner(pattern) == "R"){
                        status.setText("Red Player Wins");
                        disableButtons();
                    }else if(connect4.checkWinner(pattern) == "Y"){
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if(playerTurn % 2 == 0){
                    if(!connect4.dropRedPattern(pattern, 3))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                }else{
                    if(!connect4.dropYellowPattern(pattern, 3))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if(connect4.checkWinner(pattern) != null){
                    if(connect4.checkWinner(pattern) == "R"){
                        status.setText("Red Player Wins");
                        disableButtons();
                    }else if(connect4.checkWinner(pattern) == "Y"){
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if(playerTurn % 2 == 0){
                    if(!connect4.dropRedPattern(pattern, 4))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                }else{
                    if(!connect4.dropYellowPattern(pattern, 4))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if(connect4.checkWinner(pattern) != null){
                    if(connect4.checkWinner(pattern) == "R"){
                        status.setText("Red Player Wins");
                        disableButtons();
                    }else if(connect4.checkWinner(pattern) == "Y"){
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if(playerTurn % 2 == 0){
                    if(!connect4.dropRedPattern(pattern, 5))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                }else{
                    if(!connect4.dropYellowPattern(pattern, 5))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if(connect4.checkWinner(pattern) != null){
                    if(connect4.checkWinner(pattern) == "R"){
                        status.setText("Red Player Wins");
                        disableButtons();
                    }else if(connect4.checkWinner(pattern) == "Y"){
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        button6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView status = (TextView) findViewById(R.id.status);

                if (playerTurn % 2 == 0) {
                    if (!connect4.dropRedPattern(pattern, 6))
                        playerTurn--;
                    status.setText("Drop Yellow Disk at Column (1-7)");
                } else {
                    if (!connect4.dropYellowPattern(pattern, 6))
                        playerTurn--;
                    status.setText("Drop Red Disk at Column (1-7)");
                }
                playerTurn++;
                connect4.drawGrid(pattern);

                if (connect4.checkWinner(pattern) != null) {
                    if (connect4.checkWinner(pattern) == "R") {
                        status.setText("Red Player Wins");
                        disableButtons();
                    } else if (connect4.checkWinner(pattern) == "Y") {
                        status.setText("Yellow Player Wins");
                        disableButtons();
                    }
                }
            }
        });

        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerTurn = 0;
                connect4.resetGame(pattern);
                pattern = connect4.createPattern();
                TextView status = (TextView) findViewById(R.id.status);
                status.setText("Drop Red Disk at Column (1-7)");
                enableButtons();
            }
        });
    }

    public void playConnectFourAgainstComputer(){
        Random random = new Random();

        playerTurn = random.nextInt(3);
        int gameColumn;

        if(playerTurn == 2)//Computer goes first
        {
            Point point = new Point(random.nextInt(connect4.getRowAmount()), random.nextInt(connect4.getColumnAmount()));
            gameColumn = point.y;
        }

        while(!gameOver)
        {

        }
    }

    public void disableButtons(){
        ImageButton button0 = (ImageButton) findViewById(R.id.button0);
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button4 = (ImageButton) findViewById(R.id.button4);
        ImageButton button5 = (ImageButton) findViewById(R.id.button5);
        ImageButton button6 = (ImageButton) findViewById(R.id.button6);

        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);

    }

    public void enableButtons(){
        ImageButton button0 = (ImageButton) findViewById(R.id.button0);
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button4 = (ImageButton) findViewById(R.id.button4);
        ImageButton button5 = (ImageButton) findViewById(R.id.button5);
        ImageButton button6 = (ImageButton) findViewById(R.id.button6);

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);

    }

}

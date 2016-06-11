package com.cs454.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Connect4 connect4;
    String[][] pattern;
    ArrayList<Point> emptySpaces = new ArrayList<>();
    boolean gameOver = false;
    int playerTurn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public void button0(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 0))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 0))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void button1(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 1))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 1))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void button2(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 2))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 2))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void button3(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 3))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 3))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void button4(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 4))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 4))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void button5(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 5))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 5))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void button6(View view){
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            if(!connect4.dropRedPattern(pattern, 6))
                playerTurn--;
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            if(!connect4.dropYellowPattern(pattern, 6))
                playerTurn--;
            status.setText("Drop Red Disk at Column (0-6)");
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

    public void reset(View view){
        playerTurn = 0;
        pattern = connect4.createPattern();
        connect4.resetGame(pattern);
        TextView status = (TextView) findViewById(R.id.status);
        status.setText("Drop Red Disk at Column (0-6)");
        enableButtons();
    }

    public void disableButtons(){
        Button button0 = (Button) findViewById(R.id.col0);
        Button button1 = (Button) findViewById(R.id.col1);
        Button button2 = (Button) findViewById(R.id.col2);
        Button button3 = (Button) findViewById(R.id.col3);
        Button button4 = (Button) findViewById(R.id.col4);
        Button button5 = (Button) findViewById(R.id.col5);
        Button button6 = (Button) findViewById(R.id.col6);

        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);

    }

    public void enableButtons(){
        Button button0 = (Button) findViewById(R.id.col0);
        Button button1 = (Button) findViewById(R.id.col1);
        Button button2 = (Button) findViewById(R.id.col2);
        Button button3 = (Button) findViewById(R.id.col3);
        Button button4 = (Button) findViewById(R.id.col4);
        Button button5 = (Button) findViewById(R.id.col5);
        Button button6 = (Button) findViewById(R.id.col6);

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);

    }
}

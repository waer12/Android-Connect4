package com.cs454.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Connect4 connect4;
    String[][] pattern;
    int playerTurn = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        connect4 = new Connect4();
        pattern = connect4.createPattern();
        String drawnPattern = connect4.printPattern(pattern);
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        grid.setText(drawnPattern);
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

    public void button0(View view){
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 0);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 0);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 1);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 1);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 2);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 2);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 3);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 3);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 4);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 4);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 5);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 5);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        TextView status = (TextView) findViewById(R.id.status);

        if(playerTurn % 2 == 0){
            connect4.dropRedPattern(pattern, 6);
            status.setText("Drop Yellow Disk at Column (0-6)");
        }else{
            connect4.dropYellowPattern(pattern, 6);
            status.setText("Drop Red Disk at Column (0-6)");
        }
        playerTurn++;
        String newPattern = connect4.printPattern(pattern);
        grid.setText(newPattern);

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
        String drawnPattern = connect4.printPattern(pattern);
        TextView grid = (TextView) findViewById(R.id.gameGrid);
        grid.setText(drawnPattern);
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

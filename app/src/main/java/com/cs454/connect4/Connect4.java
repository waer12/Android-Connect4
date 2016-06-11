package com.cs454.connect4;

import android.widget.ImageView;
import java.util.ArrayList;

public class Connect4{
    ArrayList<ImageView> images;
   public Connect4(ArrayList<ImageView> images){
        this.images = images;
    }

    public String[][] createPattern(){
        String[][] f = new String[6][7];
        for (int i = 0; i < f.length; i++){
            for (int j = 0; j < f[i].length; j++){
                f[i][j] = " ";
            }
        }
        return f;
    }

    public void drawGrid(String[][] f){
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                if(f[i][j] != " "){
                    if(f[i][j] == "R"){
                        int index = j + (i * 7);
                        images.get(index).setImageResource(R.drawable.red);
                    }else if(f[i][j] == "Y"){
                        int index = j + (i * 7);
                        images.get(index).setImageResource(R.drawable.yellow);
                    }
                }
            }
        }
    }

    public void resetGame(String[][] f){
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                int index = j + (i * 7);
                images.get(index).setImageResource(R.drawable.white);
            }
        }
    }

    public void dropRedPattern(String[][] f, int column){
        for (int i = 5; i >= 0; i--){
            if (f[i][column] == " ") {
                f[i][column] = "R";
                break;
            }
        }
    }

    public void dropYellowPattern(String[][] f, int column){
        for (int i = 5;i >= 0; i--){
            if (f[i][column] == " ") {
                f[i][column] = "Y";
                break;
            }
        }
    }

    public String checkWinner(String[][] f){
        //Horizontal Check
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 4; j++){
                if ((f[i][j] != " ")
                        && (f[i][j+1] != " ")
                        && (f[i][j+2] != " ")
                        && (f[i][j+3] != " ")
                        && ((f[i][j] == f[i][j+1])
                        && (f[i][j+1] == f[i][j+2])
                        && (f[i][j+2] == f[i][j+3])))

                    return f[i][j];
            }
        }

        //Vertical Check
        for (int i = 0; i < 7; i ++){
            for (int j = 0; j < 3; j++){
                if((f[j][i] != " ")
                        && (f[j+1][i] != " ")
                        && (f[j+2][i] != " ")
                        && (f[j+3][i] != " ")
                        && ((f[j][i] == f[j+1][i])
                        && (f[j+1][i] == f[j+2][i])
                        && (f[j+2][i] == f[j+3][i])))
                    return f[j][i];
            }
        }

        //Diagonal Right
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++){
                if((f[i][j] != " ")
                        && (f[i+1][j+1] != " ")
                        && (f[i+2][j+2] != " ")
                        && (f[i+3][j+3] != " ")
                        && ((f[i][j] == f[i+1][j+1])
                        && (f[i+1][j+1] == f[i+2][j+2])
                        && (f[i+2][j+2] == f[i+3][j+3])))
                    return f[i][j];
            }
        }

        //Diagonal Left
        for (int i = 0; i < 3; i++){
            for (int j = 3; j < 7; j++){
                if((f[i][j] != " ")
                        && (f[i+1][j-1] != " ")
                        && (f[i+2][j-2] != " ")
                        && (f[i+3][j-3] != " ")
                        && ((f[i][j] == f[i+1][j-1])
                        && (f[i+1][j-1] == f[i+2][j-2])
                        && (f[i+2][j-2] == f[i+3][j-3])))
                    return f[i][j];
            }
        }
        return null;
    }
}

package com.cs454.connect4;

public class Connect4{

    public String[][] createPattern(){
        String[][] f = new String[7][15];
        for (int i = 0; i < f.length; i++){
            for (int j = 0; j < f[i].length; j++){
                if (j % 2 == 0){
                    f[i][j] ="|";
                }
                else {
                    f[i][j] = "  ";
                }
                if (i == 6) {
                    if(f[i][j] == "  "){
                        f[i][j] = "--";
                    }
                    else {
                        f[i][j] = "-";
                    }
                }
            }
        }
        return f;
    }

    public String printPattern(String[][] f){
        String pattern = "";
        for (int i = 0; i < f.length; i++){
            for (int j = 0;j < f[i].length; j++){
                pattern = pattern  + f[i][j];
            }
            pattern = pattern + "\n";
        }
        return pattern;
    }

    public void dropRedPattern(String[][] f, int column){
        int c = 2*column+1;
        for (int i =5;i>=0;i--) {
            if (f[i][c] == "  ") {
                f[i][c] = "R";
                break;
            }
        }
    }

    public void dropYellowPattern(String[][] f, int column){
        int c = 2*column+1;
        for (int i = 5;i >= 0; i--){
            if (f[i][c] == "  ")
            {
                f[i][c] = "Y";
                break;
            }
        }
    }

    public String checkWinner(String[][] f){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j += 2){
                if ((f[i][j+1] != "  ")
                        && (f[i][j+3] != "  ")
                        && (f[i][j+5] != "  ")
                        && (f[i][j+7] != "  ")
                        && ((f[i][j+1] == f[i][j+3])
                        && (f[i][j+3] == f[i][j+5])
                        && (f[i][j+5] == f[i][j+7])))

                    return f[i][j+1];
            }
        }

        for (int i=1;i<15;i+=2){
            for (int j =0;j<3;j++)
            {
                if((f[j][i] != "  ")
                        && (f[j+1][i] != "  ")
                        && (f[j+2][i] != "  ")
                        && (f[j+3][i] != "  ")
                        && ((f[j][i] == f[j+1][i])
                        && (f[j+1][i] == f[j+2][i])
                        && (f[j+2][i] == f[j+3][i])))
                    return f[j][i];
            }
        }

        for (int i=0;i<3;i++){
            for (int j=1;j<9;j+=2){
                if((f[i][j] != "  ")
                        && (f[i+1][j+2] != "  ")
                        && (f[i+2][j+4] != "  ")
                        && (f[i+3][j+6] != "  ")
                        && ((f[i][j] == f[i+1][j+2])
                        && (f[i+1][j+2] == f[i+2][j+4])
                        && (f[i+2][j+4] == f[i+3][j+6])))
                    return f[i][j];
            }
        }

        for (int i=0;i<3;i++){
            for (int j=7;j<15;j+=2){
                if((f[i][j] != "  ")
                        && (f[i+1][j-2] != "  ")
                        && (f[i+2][j-4] != "  ")
                        && (f[i+3][j-6] != "  ")
                        && ((f[i][j] == f[i+1][j-2])
                        && (f[i+1][j-2] == f[i+2][j-4])
                        && (f[i+2][j-4] == f[i+3][j-6])))
                    return f[i][j];
            }
        }
        return null;
    }
}

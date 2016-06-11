package com.cs454.connect4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Connect4{

    public static final int CONNECT_FOUR_ROWS = 7;
    public static final int CONNECT_FOUR_COLUMNS = 15;
    public static final String USER_GAME_PIECE = "R";
    public static final String COMPUTER_GAME_PIECE = "Y";
    boolean gameOver = false;

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

    class PointsAndMinimaxScores
    {
        int minimaxScore;
        Point boardPoint;

        PointsAndMinimaxScores(int score, Point point)
        {
            minimaxScore = score;
            boardPoint = point;
        }
    }

    ArrayList<PointsAndMinimaxScores> rootChildrenScores;

    public Point returnBestMove()
    {
        int MAX = Integer.MIN_VALUE;
        int bestScore = -1;

        for(int index = 0; index < rootChildrenScores.size(); index++)
        {
            if(MAX < rootChildrenScores.get(index).minimaxScore)
            {
                MAX = rootChildrenScores.get(index).minimaxScore;
                bestScore = index; //Stores index of the point with the best minimax score
            }
        }
        return rootChildrenScores.get(bestScore).boardPoint;
    }

    public int getRowAmount()
    {
        return CONNECT_FOUR_ROWS;
    }

    public int getColumnAmount()
    {
        return CONNECT_FOUR_COLUMNS;
    }

    public ArrayList<Point> getAvailableSpaces(String[][] board)
    {
        ArrayList<Point> emptySpace = new ArrayList<>();
        for(int row = 0; row < this.getRowAmount(); row++)
        {
            for(int column = 0; column < this.getColumnAmount(); column++)
            {
                if(board[row][column] == "  ")
                {
                    Point point = new Point(row, column);
                    emptySpace.add(point);
                }
            }
        }
        if(emptySpace.isEmpty())
        {
            Log.d("Connect4","No more available spaces! A DRAW!");
        }
        return emptySpace;
    }

    /**
     * Check if the computer has won either using rows or columns
     * @param board
     * @return
     */
    public boolean computerWin(String[][] board)
    {
        if(checkRowWin(board, COMPUTER_GAME_PIECE))
        {
            return true;
        }else if (checkColumnWin(board, COMPUTER_GAME_PIECE))
        {
            return true;
        }
        return false;
    }

    /**
     * Check if the user has won either using rows or columns
     * @param board
     * @return
     */
    public boolean userWin(String[][] board)
    {
        if(checkRowWin(board, USER_GAME_PIECE))
        {
            return true;
        }else if (checkColumnWin(board, USER_GAME_PIECE))
        {
            return true;
        }
        return false;
    }

    public boolean checkRowWin(String[][] board, String gamePiece)
    {
        int connectFourCount = 0;

        for(int row = 0; row < this.getRowAmount(); row++)
        {
            for(int column = 0; column < this.getColumnAmount(); column++)
            {
                if(board[row][column].equalsIgnoreCase(gamePiece))//See if this row contains game pieces
                {//TODO Fix this to account for a different piece found in between counts
                    connectFourCount++;
                }
                if(connectFourCount == 4) //If there are 4 pieces connected
                {
                    if(gamePiece.equals(COMPUTER_GAME_PIECE))
                    {
                        Log.d("Connect4", "Computer won a row win");
                    }else if(gamePiece.equals(USER_GAME_PIECE))
                    {
                        Log.d("Connect4", "User won a row win");
                    }
                    return true;
                }
            }
            connectFourCount = 0; //Reset to zero after finished searching row
        }

        return false;
    }

    public boolean checkColumnWin(String[][] board, String gamePiece)
    {
        int connectFourCount = 0;

        for(int column = 0; column < this.getColumnAmount(); column++)
        {
            for(int row = 0; row < this.getRowAmount(); row++)
            {
                if(board[row][column].equalsIgnoreCase(gamePiece))//See if this column contains game pieces
                {//TODO Fix this to account for a different piece found in between counts
                    connectFourCount++;
                }
                if(connectFourCount == 4) //If there are 4 pieces connected
                {
                    if(gamePiece.equals(COMPUTER_GAME_PIECE))
                    {
                        Log.d("Connect4", "Computer won a column win");
                    }else if(gamePiece.equals(USER_GAME_PIECE))
                    {
                        Log.d("Connect4", "User won a column win");
                    }
                    return true;
                }
            }
            connectFourCount = 0; //Reset to zero after finished searching column
        }

        return false;
    }

    public boolean checkIfGameOver(String[][] board)
    {
        ArrayList<Point> availableSpaces = getAvailableSpaces(board);
        if(computerWin(board) || userWin(board) || availableSpaces.isEmpty())
        {
            return true;
        }
        return false;
    }

    public void playConnectFourAgainstComputer(String[][] board, int playerTurn)
    {
        ArrayList<Point> availableSpaces = getAvailableSpaces(board);
        Random random = new Random();
        playerTurn = random.nextInt(3);//If 1 (USER turn), if 2 (COMPUTER turn)

        //Start game
        while(!gameOver)
        {
            if(playerTurn == 1) //USER turn
            {

            }else if(playerTurn == 2) //COMPUTER turn
            {
                rootChildrenScores = new ArrayList<>();
                minimax(0, 2, board);
                playerTurn = 1;
            }
            gameOver = checkIfGameOver(board);
        }
    }

    public int returnMax(ArrayList<Integer> minimaxScores)
    {
        int MAX_SCORE = Integer.MIN_VALUE;
        for(int index = 0; index < minimaxScores.size(); index++)
        {
            if(MAX_SCORE < minimaxScores.get(index))
            {
                MAX_SCORE = minimaxScores.get(index);
            }
        }
        return MAX_SCORE;
    }

    public int returnMin(ArrayList<Integer> minimaxScores)
    {
        int MIN_SCORE = Integer.MAX_VALUE;
        for(int index = 0; index < minimaxScores.size(); index++)
        {
            if(MIN_SCORE > minimaxScores.get(index))
            {
                MIN_SCORE = minimaxScores.get(index);
            }
        }
        return MIN_SCORE;
    }

    public int minimax(int depth, int turn, String[][] board)
    {
        Log.d("Connect4","Current depth -> " + depth);
        if(computerWin(board))//Check if computer wins
        {
            Log.d("Connect4","Computer won");
            return +1; //Good move for computer
        }
        if(userWin(board))//Check if user wins
        {
            Log.d("Connect4","User won");
            return -1; //Bad move for computer
        }

        ArrayList<Point> availablePoints = getAvailableSpaces(board);
        Log.d("Connect4","Available points -> " + availablePoints.size());
        if(availablePoints.isEmpty())
        {
            return 0; //Draw or no way to win from picking this point/cell
        }

        ArrayList<Integer> minimaxScores = new ArrayList<>();
        for(int index = 0; index < availablePoints.size(); index++)
        {
            Point currentPoint = availablePoints.get(index);
            Log.d("Connect4","Current point -> (" + currentPoint.x + ", " + currentPoint.y + ")");
            int column = currentPoint.y;

            if(turn == 2) //The main computer boss the user plays against, selects highest
            {
                dropYellowPattern(board, column);

                int currentPointScore = minimax(depth + 1, 3, board);
                Log.d("Connect4","Current point score -> " + currentPointScore);
                minimaxScores.add(currentPointScore);

                if(depth == 0)
                {
                    rootChildrenScores.add(new PointsAndMinimaxScores(currentPointScore, currentPoint));
                }
            } else if(turn == 3) //Used to presume what the user would choose, selects lowest
            {
                dropRedPattern(board, column);
                minimaxScores.add(minimax(depth + 1, 2, board));
            }
            board[currentPoint.x][currentPoint.y] = "  "; //Reset the point
        }
        //Return the max or the min based on whose turn it is
        if(turn == 2) //Main computer boss
        {
            Log.d("Connect4","Returning the MAX score.");
            return returnMax(minimaxScores);
        } else if(turn == 3) //Secondary computer, used if computer vs. computer
        {
            Log.d("Connect4","Returning the MIN score.");
            return returnMin(minimaxScores);
        }
        Log.d("Connect4","Returning a WRONG score.");
        return turn == 1 ? returnMax(minimaxScores) : returnMin(minimaxScores); //If this has happened, an error has occured
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

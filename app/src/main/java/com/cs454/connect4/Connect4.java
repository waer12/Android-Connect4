package com.cs454.connect4;
/**
 * CREDIT for minimax  goes to: http://www.codebytes.in/2014/08/minimax-algorithm-tic-tac-toe-ai-in.html
 */

import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Connect4{
    ArrayList<ImageView> images;
    public static final int CONNECT_FOUR_ROWS = 6;
    public static final int CONNECT_FOUR_COLUMNS = 7;
    public static final String USER_GAME_PIECE = "R";
    public static final String COMPUTER_GAME_PIECE = "Y";
    ArrayList<PointsAndMinimaxScores> rootChildrenScores;
    boolean gameOver = false;
    Graph board;

    public Connect4(ArrayList<ImageView> images){
        board = new Graph();
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

    public void resetGame(String[][] f) {
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                int index = j + (i * 7);
                images.get(index).setImageResource(R.drawable.white);
            }
        }
        board = new Graph();
    }

    public boolean dropRedPattern(String[][] f, int column){
        for (int i = 5; i >= 0; i--){
            if (f[i][column] == " ") {
                f[i][column] = "R";
                break;
            }
        }
        return board.dropRed(column);
    }

    public boolean dropYellowPattern(String[][] f, int column){
        for (int i = 5;i >= 0; i--){
            if (f[i][column] == " ") {
                f[i][column] = "Y";
                break;
            }
        }
        return board.dropYellow(column);
    }

    public String checkWinner(String[][] f){
        String tag = "check winner";
        Log.d(tag, "test");
        return board.checkWinner();
//        //Horizontal Check
//        for (int i = 0; i < 6; i++){
//            for (int j = 0; j < 4; j++){
//                if ((f[i][j] != " ")
//                        && (f[i][j+1] != " ")
//                        && (f[i][j+2] != " ")
//                        && (f[i][j+3] != " ")
//                        && ((f[i][j] == f[i][j+1])
//                        && (f[i][j+1] == f[i][j+2])
//                        && (f[i][j+2] == f[i][j+3])))
//
//                    return f[i][j];
//            }
//        }
//
//        //Vertical Check
//        for (int i = 0; i < 7; i ++){
//            for (int j = 0; j < 3; j++){
//                if((f[j][i] != " ")
//                        && (f[j+1][i] != " ")
//                        && (f[j+2][i] != " ")
//                        && (f[j+3][i] != " ")
//                        && ((f[j][i] == f[j+1][i])
//                        && (f[j+1][i] == f[j+2][i])
//                        && (f[j+2][i] == f[j+3][i])))
//                    return f[j][i];
//            }
//        }
//
//        //Diagonal Right
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 4; j++){
//                if((f[i][j] != " ")
//                        && (f[i+1][j+1] != " ")
//                        && (f[i+2][j+2] != " ")
//                        && (f[i+3][j+3] != " ")
//                        && ((f[i][j] == f[i+1][j+1])
//                        && (f[i+1][j+1] == f[i+2][j+2])
//                        && (f[i+2][j+2] == f[i+3][j+3])))
//                    return f[i][j];
//            }
//        }
//
//        //Diagonal Left
//        for (int i = 0; i < 3; i++){
//            for (int j = 3; j < 7; j++){
//                if((f[i][j] != " ")
//                        && (f[i+1][j-1] != " ")
//                        && (f[i+2][j-2] != " ")
//                        && (f[i+3][j-3] != " ")
//                        && ((f[i][j] == f[i+1][j-1])
//                        && (f[i+1][j-1] == f[i+2][j-2])
//                        && (f[i+2][j-2] == f[i+3][j-3])))
//                    return f[i][j];
//            }
//        }
//        return null;
    }

    public int getRowAmount(){
        return CONNECT_FOUR_ROWS;
    }

    public int getColumnAmount(){
        return CONNECT_FOUR_COLUMNS;
    }

    public ArrayList<Point> checkAmountOfAvailableSpaces(String[][] board) {
        ArrayList<Point> emptySpace = new ArrayList<>();
        for(int row = 0; row < board.length; row++)
        {
            for(int column = 0; column < board.length; column++)
            {
                if(board[row][column] == " ")
                {
                    Point point = new Point(row, column);
                    emptySpace.add(point);
                }
            }
        }
        return emptySpace;
    }

    class Node{
        public int[] position;
        public String color;
        Node(int[] pos, String color){
            this.position = pos;
            this.color = color;
        }
    }

    class Graph{
        /**
         * |0.3 | | | |
         * |0.2 | | | |
         * |0.1 | | | |
         * |0.0 |1.0 |2.0 |3.0 |
         */
        private int NUM_COLUMNS = 7;
        private int NUM_ROWS = 6;
        //fast access to existing nodes
        private Node[][] nodes = null;
        private HashMap<Node, ArrayList<Node>> redNodes = null;
        private HashMap<Node, ArrayList<Node>> yellowNodes = null;
        //keeps track of the 'fullness' of each column
        private int[] emptySpaces = null;

        public Graph(){
            nodes = new Node[NUM_COLUMNS][NUM_ROWS];
            emptySpaces = new int[NUM_COLUMNS];
            redNodes = new HashMap<>();
            yellowNodes = new HashMap<>();
        }

        //Returns true of piece successfully dropped
        //False if that column is full
        public boolean dropYellow(int columnIndex){
            //if that column is full
            if(emptySpaces[columnIndex] >= NUM_ROWS || columnIndex < 0)
                return false;
            int[] position = new int[]{columnIndex, emptySpaces[columnIndex]};
            Node newNode = new Node(position, "yellow");
            nodes[columnIndex][emptySpaces[columnIndex]] = newNode;
            yellowNodes.put(newNode, new ArrayList<Node>());
            //attach the node to adjacent nodes of same color
            //up
            //There cannot be a peice above the most recently dropped piece
            //down
            if(emptySpaces[columnIndex] - 1 >= 0 && nodes[columnIndex][emptySpaces[columnIndex] - 1] != null &&
                    nodes[columnIndex][emptySpaces[columnIndex] - 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex][emptySpaces[columnIndex] - 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //left
            if(columnIndex - 1 >= 0 && nodes[columnIndex - 1][emptySpaces[columnIndex]] != null &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex]].color == "yellow"){
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex]];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //right
            if(columnIndex + 1 <  NUM_COLUMNS && nodes[columnIndex + 1][emptySpaces[columnIndex]] != null &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex]].color == "yellow"){
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex]];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //upleft
            if(columnIndex - 1 >= 0 && emptySpaces[columnIndex] + 1 < NUM_ROWS &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] + 1] != null &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] + 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex] + 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //upright
            if(columnIndex + 1 < NUM_COLUMNS && emptySpaces[columnIndex] + 1 < NUM_ROWS &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] + 1] != null &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] + 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex] + 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //downleft
            if(columnIndex - 1 >= 0 && emptySpaces[columnIndex] - 1 >= 0 &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] - 1] != null &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] - 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex] - 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //downright
            if(columnIndex + 1 < NUM_COLUMNS && emptySpaces[columnIndex] - 1 >= 0 &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] - 1] != null &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] - 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex] - 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            emptySpaces[columnIndex]++;
            return true;
        }

        //Returns true of piece successfully dropped
        //False if that column is full
        public boolean dropRed(int columnIndex) {
            //if that column is full
            if (emptySpaces[columnIndex] >= NUM_ROWS || columnIndex < 0)
                return false;
            int[] position = new int[]{columnIndex, emptySpaces[columnIndex]};
            Node newNode = new Node(position, "red");
            nodes[columnIndex][emptySpaces[columnIndex]] = newNode;
            redNodes.put(newNode, new ArrayList<Node>());
            //attach the node to adjacent nodes of same color
            //up
            //There cannot be a peice above the most recently dropped piece
            //down
            if (emptySpaces[columnIndex] - 1 >= 0 &&
                    nodes[columnIndex][emptySpaces[columnIndex] - 1] != null &&
                    nodes[columnIndex][emptySpaces[columnIndex] - 1].color == "red") {
                Node connectedNode = nodes[columnIndex][emptySpaces[columnIndex] - 1];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            //left
            if (columnIndex - 1 >= 0 &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex]] != null &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex]].color == "red") {
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex]];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            //right
            if (columnIndex + 1 < NUM_COLUMNS &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex]] != null &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex]].color == "red") {
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex]];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            //upleft
            if (columnIndex - 1 >= 0 && emptySpaces[columnIndex] + 1 < NUM_ROWS &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] + 1] != null &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] + 1].color == "red") {
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex] + 1];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            //upright
            if (columnIndex + 1 < NUM_COLUMNS && emptySpaces[columnIndex] + 1 < NUM_ROWS &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] + 1] != null &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] + 1].color == "red") {
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex] + 1];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            //downleft
            if (columnIndex - 1 >= 0 && emptySpaces[columnIndex] - 1 >= 0 &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] - 1] != null &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] - 1].color == "red") {
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex] - 1];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            //downright
            if (columnIndex + 1 < NUM_COLUMNS && emptySpaces[columnIndex] - 1 >= 0 &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] - 1] != null &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] - 1].color == "red") {
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex] - 1];
                redNodes.get(connectedNode).add(newNode);
                redNodes.get(newNode).add(connectedNode);
            }
            emptySpaces[columnIndex]++;
            return true;
        }


        //Checks to see if red/yellow has won
        //returns "R" if red won
        //returns "Y" if yellow won
        //returns "" if no winners
        public String checkWinner(){
            //check reds
            for(Node n : redNodes.keySet()){
                if(count(n, "up", redNodes) >= 4 || count(n, "down", redNodes) >= 4 ||
                        count(n, "left", redNodes) >= 4 || count(n, "right", redNodes) >= 4 ||
                        count(n, "upleft", redNodes) >= 4 || count(n, "upright", redNodes) >= 4 ||
                        count(n, "downleft", redNodes) >= 4 || count(n, "downright", redNodes) >= 4)
                    return "R";
            }

            //check yellow
            for(Node n : yellowNodes.keySet()){
                if(count(n, "up", yellowNodes) >= 4 || count(n, "down", yellowNodes) >= 4 ||
                        count(n, "left", yellowNodes) >= 4 || count(n, "right", yellowNodes) >= 4 ||
                        count(n, "upleft", yellowNodes) >= 4 || count(n, "upright", yellowNodes) >= 4 ||
                        count(n, "downleft", yellowNodes) >= 4 || count(n, "downright", yellowNodes) >= 4)
                    return "Y";
            }
            return "";
        }

        //Checks position of other relative to reference
        //e.g. returns up if other is above reference
        //possible returns: up, down, left, right upleft, upright, downleft, downright
        private String checkPosition(Node reference, Node other){
            String position = "";
            //check up
            if(reference.position[1] + 1 == other.position[1])
                position += "up";
            //check down
            if(reference.position[1] - 1 == other.position[1])
                position += "down";
            //check left
            if(reference.position[0] - 1 == other.position[0])
                position += "left";
            //check right
            if(reference.position[0] + 1 == other.position[0])
                position += "right";
            return position;
        }

        //counts the number of consecutive direction nodes from start
        private int count(Node start, String direction, HashMap<Node, ArrayList<Node>> nodes){
            for(Node other : nodes.get(start)){
                if(checkPosition(start, other).equals(direction))
                    return count(other, direction, nodes) + 1;
            }
            return 1;
        }

        //Returns an ArrayList of possible board positions from current board
        private ArrayList<Graph> getPossibleGraphs(){
            return null;
        }

        //Returns the heuristic value of the board from the perspective of player("red" or "yellow")
        //i.e. if the player("red") has 4, returns Integer.MAX_VALUE
        //but if player("yellow") and red has connect 4, returns Integer.MIN_VALUE
        private int heuristic(String player)
        {
            return Integer.MIN_VALUE;
        }
    }

    ///////////////////// Minimax /////////////////////////
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

    public int returnMax(ArrayList<Integer> minimaxScores)
    {
        int MAX_SCORE = Integer.MIN_VALUE;
        int scoreIndex = -1;
        for(int index = 0; index < minimaxScores.size(); index++)
        {
            if(MAX_SCORE < minimaxScores.get(index))
            {
                MAX_SCORE = minimaxScores.get(index);
                scoreIndex = index;
            }
        }
        return minimaxScores.get(scoreIndex);
    }

    public int returnMin(ArrayList<Integer> minimaxScores)
    {
        int MIN_SCORE = Integer.MAX_VALUE;
        int scoreIndex = -1;
        for(int index = 0; index < minimaxScores.size(); index++)
        {
            if(MIN_SCORE > minimaxScores.get(index))
            {
                MIN_SCORE = minimaxScores.get(index);
                scoreIndex = index;
            }
        }
        return minimaxScores.get(scoreIndex);
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
                if(board[row][column].equalsIgnoreCase(gamePiece) && connectFourCount == 0)//See if this row contains game pieces
                {
                    connectFourCount++;
                }else if(board[row][column].equalsIgnoreCase(gamePiece) && board[row][column - 1].equalsIgnoreCase(gamePiece))
                {
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
            connectFourCount = 0; //reset to zero after finished searching row
        }

        return false;
    }

    public boolean checkColumnWin(String[][] board, String gamePiece)
    {
        int connectFourCount = 0;

        for (int column = 0; column < this.getColumnAmount(); column++)
        {
            for(int row = 0; row < this.getRowAmount(); row++)
            {
                if(board[row][column].equalsIgnoreCase(gamePiece) && connectFourCount == 0)//See if this column contains game pieces
                {
                    connectFourCount++;
                }else if(board[row][column].equalsIgnoreCase(gamePiece) && board[row - 1][column].equalsIgnoreCase(gamePiece))
                {
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
            connectFourCount = 0; //reset to zero after finished searching column
        }

        return false;
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

        //int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        ArrayList<Integer> minimaxScores = new ArrayList<>();
        for(int index = 0; index < availablePoints.size(); index++)
        {
            Point currentPoint = availablePoints.get(index);
            Log.d("Connect4","Current point -> (" + currentPoint.x + ", " + currentPoint.y + ")");
            int column = currentPoint.y;

            if(turn == 2) //The main computer boss the user plays against, selects highest
            {
                dropYellowPattern(board, column);
                int currentPointScore = minimax(depth + 1, 1, board);
                //max = Math.max(currentPointScore, max);
                Log.d("Connect4", "Current point score -> " + currentPointScore);
                minimaxScores.add(currentPointScore);

                if(depth == 0)
                {
                    rootChildrenScores.add(new PointsAndMinimaxScores(currentPointScore, currentPoint));
                }
//                if(currentPointScore >= 0)
//                {
//                    if(depth == 0)
//                    {
//                        dropYellowPattern(board, column);
//                    }
//                }
//                if()
//                {
//
//                }
            } else if(turn == 1) //Used to presume what the user would choose, selects lowest
            {
                dropRedPattern(board, column);
                minimaxScores.add(minimax(depth + 1, 2, board));
            }
            board[currentPoint.x][currentPoint.y] = "  "; //reset the point
        }
        //Return the max or the min based on whose turn it is
        return turn == 1 ? returnMax(minimaxScores) : returnMin(minimaxScores);
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

    public boolean checkIfGameOver(String[][] board)
    {
        ArrayList<Point> availableSpaces = getAvailableSpaces(board);
        if(computerWin(board) || userWin(board) || availableSpaces.isEmpty())
        {
            return true;
        }
        return false;
    }
}

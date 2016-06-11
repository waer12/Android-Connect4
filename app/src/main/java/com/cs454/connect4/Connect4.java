package com.cs454.connect4;


import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class Connect4{
    ArrayList<ImageView> images;
    public static final int CONNECT_FOUR_ROWS = 7;
    public static final int CONNECT_FOUR_COLUMNS = 15;
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
}

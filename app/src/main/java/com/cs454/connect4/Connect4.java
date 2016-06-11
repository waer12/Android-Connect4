package com.cs454.connect4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Connect4{

    //drawing is taken care of by the view
    @Deprecated
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
            if(emptySpaces[columnIndex] - 1 > 0 && nodes[columnIndex][emptySpaces[columnIndex] - 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex][emptySpaces[columnIndex] - 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //left
            if(columnIndex - 1 >= 0 && nodes[columnIndex - 1][emptySpaces[columnIndex]].color == "yellow"){
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex]];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //right
            if(columnIndex + 1 <  NUM_COLUMNS && nodes[columnIndex + 1][emptySpaces[columnIndex]].color == "yellow"){
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex]];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //upleft
            if(columnIndex - 1 >= 0 && emptySpaces[columnIndex] + 1 < NUM_ROWS &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] + 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex] + 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //upright
            if(columnIndex + 1 < NUM_COLUMNS && emptySpaces[columnIndex] + 1 < NUM_ROWS &&
                    nodes[columnIndex + 1][emptySpaces[columnIndex] + 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex + 1][emptySpaces[columnIndex] + 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //downleft
            if(columnIndex - 1 >= 0 && emptySpaces[columnIndex] - 1 >= 0 &&
                    nodes[columnIndex - 1][emptySpaces[columnIndex] - 1].color == "yellow"){
                Node connectedNode = nodes[columnIndex - 1][emptySpaces[columnIndex] - 1];
                yellowNodes.get(connectedNode).add(newNode);
                yellowNodes.get(newNode).add(connectedNode);
            }
            //downright
            if(columnIndex + 1 < NUM_COLUMNS && emptySpaces[columnIndex] - 1 >= 0 &&
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
        public boolean dropRed(int columnIndex){
            return false;
        }

        //Checks to see if red/yellow has won
        //returns "red" if red won
        //returns "yellow" if yellow won
        //returns "" if no winners
        public String checkWinner(){
            return "";
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

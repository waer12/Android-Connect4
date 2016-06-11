package com.cs454.connect4;

/**
 * Created by Kiara on 6/10/2016.
 */
public class Point {
    int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "[" + x + ", " + y + "]" ;
    }
}

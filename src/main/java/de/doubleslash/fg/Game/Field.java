package de.doubleslash.fg.Game;

import java.util.List;

public class Field {
    private boolean isMine, isOpen, isMarked;
    private int x, y, surroundingMines;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        surroundingMines = 0;
        isOpen = isMine = false;
    }

    boolean open() {
        if (!isOpen && !isMarked) {
            isOpen = true;
            return true;
        }
        else {
            return false;
        }
    }

    boolean mark() {
        if (!isOpen && !isMarked) {
            isMarked = true;
            return true;
        }
        else {
            return false;
        }
    }

    boolean getIsMine() {
        return isMine;
    }

    void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    void setSurroundingMines(int surroundingMines) {
        this.surroundingMines = surroundingMines;
    }

    @Override
    public String toString() {
        if (isMarked && !isOpen){ return ">"; }
        else if(!isOpen) { return "*"; }
        else if(isMine) { return "X"; }
        else if (surroundingMines == 0) { return " "; }
        else { return String.valueOf(surroundingMines); }
    }

    public int getSurroundingMines() {
        return surroundingMines;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean unmark() {
        if(isMarked && !isOpen) {
            isMarked = false;
            return true;
        }
        return false;
    }
}

package de.doubleslash.fg.Game;

import java.util.Random;

/**
 * TODO<br>
 * <br>
 * <small><i>
 * <b>Copyright:</b> ©Deutsche Post AG, 2017, „All rights reserved“<br>
 * <b>Firma:</b> doubleSlash GmbH (im Auftrag Deutsche Post AG)</br>
 * </i></small>
 */

public class MineDistributor {
        private int firstFieldX, firstFieldY;
    private boolean[][] mines;

    public MineDistributor(int fieldLength, int fieldHeight, int numberOfMines, int firstFieldX, int firstFieldY) {
        this.firstFieldX = firstFieldX;
        this.firstFieldY = firstFieldY;
        mines = new boolean[fieldLength][fieldHeight];
        for (int h = 0; h < fieldHeight; ++h) {
            for (int l = 0; l < fieldLength; ++l) {
                mines[l][h] = false;
            }
        }
        //TODO better distribution algorithm
        Random r = new Random();
        int x, y;
        for (int i = 0; i < numberOfMines; ++i) {
            do {
                x = r.nextInt(fieldLength);
                y = r.nextInt(fieldHeight);
            } while(!tryToSetMine(x, y));
        }
    }

    private boolean tryToSetMine(int x, int y) {
        if (!mines[x][y] && !(x == firstFieldX && y ==firstFieldY)) {
            mines[x][y] = true;
            return true;
        }
        else { return false; }
    }

    public boolean getIsMine(int x, int y) {
        return mines[x][y];
    }
}

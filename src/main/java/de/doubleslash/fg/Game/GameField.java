package de.doubleslash.fg.Game;

import de.doubleslash.fg.CLI.FieldDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO<br>
 * <br>
 * <small><i>
 * <b>Copyright:</b> ©Deutsche Post AG, 2017, „All rights reserved“<br>
 * <b>Firma:</b> doubleSlash GmbH (im Auftrag Deutsche Post AG)</br>
 * </i></small>
 */

public class GameField {
    private int numberOfMines, length, height;
    private boolean gameOver, firstFieldOpened;
    private Field[][] fields;

    public GameField(int length, int height, int numberOfMines) {
        this.length = length;
        this.height = height;
        this.numberOfMines = numberOfMines;
        initializeFields();
    }

    private void initializeFields() {
        fields = new Field[length][height];
        for (int h = 0; h < height; ++h) {
            for(int l = 0; l < length; ++l) {
                fields[l][h] = new Field(l, h);
            }
        }
    }

    private void setSurroundingMinesOfField(int x, int y) {
        List<Field> neighbours = getNeigbours(x, y);
        int surroundingMines = 0;
        for (Field field: neighbours) {
            if (field.getIsMine()) {
                ++surroundingMines;
            }
        }
        fields[x][y].setSurroundingMines(surroundingMines);
    }

    public boolean openField(int x, int y) {
        if (x >= length || y >= height || x < 0 || y < 0) {
            return false;
        }
        boolean opened;
        if (!firstFieldOpened) {
            distributeMines(x, y);
            firstFieldOpened = true;
        }
        opened = fields[x][y].open();
        if (fields[x][y].getIsMine()) {
            gameOver = true;
        }
        if(opened && fields[x][y].getSurroundingMines() == 0) {
            openSurroundingFields(x, y);
        }
        return opened;
    }

    private void openSurroundingFields(int x, int y) {
        List<Field> neigbours = getNeigbours(x, y);

        for (Field field: neigbours) {
            if (!field.isOpen()) {
                field.open();
                if (field.getSurroundingMines() == 0) {
                    openSurroundingFields(field.getX(), field.getY());
                }
            }
        }
    }

    private List<Field> getNeigbours(int x, int y) {
        List<Field> neighbours = new ArrayList<>();
        for (int h = (y - 1); h <= y+1; ++h) {
            for (int l = (x - 1); l <= x + 1; ++l) {
                if ((l >= 0) && (l < length)
                        && (h >= 0) && (h < height)
                        && !((h == y) && (l == x))) {
                    neighbours.add(fields[l][h]);
                }
            }
        }
        return neighbours;
    }

    void distributeMines(int firstFieldX, int firstFieldY) {
        MineDistributor distributor = new MineDistributor(length, height, numberOfMines, firstFieldX, firstFieldY);
        for (int h = 0; h < height; ++h) {
            for (int l = 0; l < length; ++l) {
                fields[l][h].setIsMine(distributor.getIsMine(l, h));
            }
        }
        for (int h = 0; h < height; ++h) {
            for (int l = 0; l < length; ++l) {
                if (!fields[l][h].getIsMine()) {
                    setSurroundingMinesOfField(l, h);
                }
            }
        }
    }

    public boolean markField(int x, int y) {
        if (x >= length || y >= height || x < 0 || y < 0) {
            return false;
        }
        return fields[x][y].mark();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean unmark(int x, int y) {
        if (x >= length || y >= height || x < 0 || y < 0) {
            return false;
        }
        return fields[x][y].unmark();
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Field[][] getFields() {
        return fields;
    }
}

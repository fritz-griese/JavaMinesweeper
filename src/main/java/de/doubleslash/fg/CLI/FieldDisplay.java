package de.doubleslash.fg.CLI;

import de.doubleslash.fg.Game.Field;
import de.doubleslash.fg.Game.GameField;

public class FieldDisplay {
    private int displaySize, displayLength, displayHeight, fieldLength, fieldHeight;
    private GameField gameField;
    private Field[][] fields;
    private StringBuffer buffer;

    public FieldDisplay(GameField gameField) {
        this.gameField = gameField;
        this.fields = gameField.getFields();
        this.fieldLength = gameField.getLength();
        this.fieldHeight = gameField.getHeight();
        displayLength = fieldLength * 2;
        displayHeight = fieldHeight + 2;
        displaySize = displayLength * displaySize;
        buffer = new StringBuffer(displaySize); //TODO recalculate buffer size
    }

    public void print() {
        buffer.delete(0, buffer.length());
        appendLine();
        buffer.append('\n');

        for(int h = 0; h < fieldHeight; ++h) {
            buffer.append(fields[0][h]);
            for (int l = 1; l < fieldLength; ++l) {
                buffer.append(" " + fields[l][h]);
            }
            buffer.append('\n');
        }
        appendLine();
        System.out.println(buffer.toString());
    }

    private void appendLine() {
        if (gameField.isGameOver()) {
            appendLineWithMessage("GAME_OVER");
        }
        else {
            for (int l = 0; l < (displayLength-1); ++l) {
                buffer.append("-");
            }
        }
    }

    private void appendLineWithMessage(String msg) {
        for (int l = 0; l < (displayLength-1); ++l) {
            buffer.append("-");
            if (l == (((displayLength) - (msg.length())) / 2) - 1) {
                buffer.append(msg);
                l += msg.length();
            }
        }
    }
}

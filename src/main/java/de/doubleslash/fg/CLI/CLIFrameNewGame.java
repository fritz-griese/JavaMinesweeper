package de.doubleslash.fg.CLI;

import de.doubleslash.fg.Game.GameField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO<br>
 * <br>
 * <small><i>
 * <b>Copyright:</b> ©Deutsche Post AG, 2017, „All rights reserved“<br>
 * <b>Firma:</b> doubleSlash GmbH (im Auftrag Deutsche Post AG)</br>
 * </i></small>
 */

public class CLIFrameNewGame extends CLIFrameWithIntInput {

    public boolean startCLIAndReturnTrueWhenFinished() throws IOException {
        int fieldLength, fieldHeight, numberOfMines;
        fieldLength = waitForIntInput("Please enter field length.");
        fieldHeight = waitForIntInput("Please enter field height.");
        numberOfMines = waitForIntInput("Please enter number of mines.");

        new CLIFrameGame(fieldHeight, fieldHeight, numberOfMines).start();

        return true;
    }
}

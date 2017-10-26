package de.doubleslash.fg.CLI;

import de.doubleslash.fg.Game.GameField;

import java.io.IOException;

/**
 * TODO<br>
 * <br>
 * <small><i>
 * <b>Copyright:</b> ©Deutsche Post AG, 2017, „All rights reserved“<br>
 * <b>Firma:</b> doubleSlash GmbH (im Auftrag Deutsche Post AG)</br>
 * </i></small>
 */

public class CLIFrameGame extends CLIFrameWithIntInput{
    private GameField gameField;
    private FieldDisplay fieldDisplay;
    private boolean quit = false;

    public CLIFrameGame(int fieldLength, int fieldHeight, int numberOfMines) {
        gameField = new GameField(fieldLength, fieldHeight, numberOfMines);
        fieldDisplay = new FieldDisplay(gameField);
        fieldDisplay.print();
    }

    @Override
    boolean startCLIAndReturnTrueWhenFinished() throws IOException {
        String input;
        boolean success;

        do {
            success = false;
            System.out.println("Open, mark or unmark a field.");
            input = reader.readLine();
            if (input.equals("open") || input.equals("mark") || input.equals("quit") || input.equals("unmark")) {
                success = true;
            } else {
                System.out.println("Invalid input.");
            }
        } while (!success);
        executeInput(input);
        fieldDisplay.print();

        return quit || gameField.isGameOver();
    }

    private void executeInput(String input) throws IOException {
        switch (input) {
            case "open": openField();
            break;
            case "mark": markField();
            break;
            case "quit": quit = true;
            break;
            case "unmark": unmarkField();
            break;
            default: System.out.println("Unknown error.");
        }
    }

    private boolean unmarkField() throws IOException {
        int x, y;
        x = waitForIntInput("x:");
        y = waitForIntInput("y:");

        if (gameField.unmark(x, y)) {
            return true;
        }
        else {
            System.out.println("Error: Field[" + x +"][" + y + "] is opened or not marked.");
//            openField();
            return false;
        }
    }

    private boolean markField() throws IOException {
        int x, y;
        x = waitForIntInput("x:");
        y = waitForIntInput("y:");

        if (gameField.markField(x, y)){
            return true;
        }
        else {
            System.out.println("Error: Field[" + x +"][" + y + "] is marked or already opened.");
            return false;
        }
    }

    private boolean openField() throws IOException {
        int x, y;
        x = waitForIntInput("x:");
        y = waitForIntInput("y:");

        if (gameField.openField(x, y)) {
            return true;
        }
        else {
            System.out.println("Error: Field[" + x +"][" + y + "] is opened or already marked.");
            return false;
        }
    }



}

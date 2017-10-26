package de.doubleslash.fg.CLI;

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

public class CLIFrameMinesweeperMain extends CLIFrame {
    private boolean exit = false;

    @Override
    void start() {
        boolean finished;

        try {
            do {
                finished = startCLIAndReturnTrueWhenFinished();
            } while (!finished);

        } catch (IOException e) {
            System.out.println("An unexpected I/O error occurred.");
            e.printStackTrace();

        } finally {
            try {
                closeReaders();
            } catch (IOException e) {
                System.out.println("Error while closing readers.");
                e.printStackTrace();
            }
        }
    }

    @Override
    boolean startCLIAndReturnTrueWhenFinished() throws IOException {
        String input;
        boolean success = false;

        do {
            System.out.println("Welcome to Minesweeper! Type start to start a new game.");
            input = reader.readLine();
            if (input.equals("start") || input.equals("exit")) {
                success = true;
            } else {
                System.out.println("Invalid input.");
            }
        } while (!success);

        executeInput(input);
        return exit;
    }

    void executeInput(String input) throws IOException {
        switch (input) {
            case "start":
                startNewGame();
                break;
            case "exit":
                exit = true;
        }
    }

    private void startNewGame() throws IOException {
        new CLIFrameNewGame().start();
    }

    private void closeReaders() throws IOException {
        if (reader != null) {
            reader.close();
        }
        if (streamReader != null) {
            streamReader.close();
        }
    }
}

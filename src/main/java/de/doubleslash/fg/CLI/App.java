package de.doubleslash.fg.CLI;

import de.doubleslash.fg.Game.GameField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App
{
    private static GameField gameField;
    private static boolean first = true;

    public static void main( String[] args ) throws IOException {
        new CLIFrameMinesweeperMain().start();
    }

}

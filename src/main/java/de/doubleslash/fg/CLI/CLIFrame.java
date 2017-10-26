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

public abstract class CLIFrame {
    static InputStreamReader streamReader = new InputStreamReader(System.in);
    static BufferedReader reader = new BufferedReader(streamReader);

    void start() throws IOException {
        boolean finished;
        do {
            finished = startCLIAndReturnTrueWhenFinished();
        } while (!finished);
    }



    abstract boolean startCLIAndReturnTrueWhenFinished() throws IOException;

}

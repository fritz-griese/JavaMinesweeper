package de.doubleslash.fg.CLI;

import java.io.IOException;

/**
 * TODO<br>
 * <br>
 * <small><i>
 * <b>Copyright:</b> ©Deutsche Post AG, 2017, „All rights reserved“<br>
 * <b>Firma:</b> doubleSlash GmbH (im Auftrag Deutsche Post AG)</br>
 * </i></small>
 */

public abstract class CLIFrameWithIntInput extends CLIFrame {
    protected int waitForIntInput(String msg) throws IOException {
        String input;
        boolean success = false;
        do {
            System.out.println(msg);
            input = reader.readLine();
            if (isValidInput(input)) {
                success = true;
            }
            else {
                System.out.println("Not a valid input.");
            }
        } while (!success);

        return Integer.valueOf(input);
    }

    private boolean isValidInput(String input) {
        if (input.length() < 1) {
            return false;
        }
        for (Character c: input.toCharArray()) {
            if (c >= '0' && c <= '9') {}
            else return false;
        }
        return true;
    }
}

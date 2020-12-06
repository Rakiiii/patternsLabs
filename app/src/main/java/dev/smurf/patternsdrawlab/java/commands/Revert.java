package dev.smurf.patternsdrawlab.java.commands;

/**
 * Команда для отмены команд
 */
public class Revert implements Command {

    /** Команда для отмены */
    Command prevCommand = null;

    /** Метод установки команд */
    public void setPrevCommand(Command prev) {
        prevCommand = prev;
    }

    @Override
    public void implement(State state) {
        if (prevCommand != null) {
            prevCommand.reverse(state);
        }
    }

    @Override
    public void reverse(State state) {
        if (prevCommand != null) {
            prevCommand.implement(state);
        }
    }
}

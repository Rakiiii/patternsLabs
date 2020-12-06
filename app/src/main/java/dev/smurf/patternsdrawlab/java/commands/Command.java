package dev.smurf.patternsdrawlab.java.commands;

/**
 * Базовый интерфейс для команды, Intent из MVI
 */
public interface Command {
    /**
     * Метод применения команды
     *
     * @param state Состояние для описания ui
     */
    void implement(State state);

    /**
     * Метод для отмены команды
     *
     * @param state Состояние для описания ui
     */
    void reverse(State state);
}

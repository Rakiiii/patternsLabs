package dev.smurf.patternsdrawlab.java;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Stack;

import dev.smurf.patternsdrawlab.java.commands.Command;
import dev.smurf.patternsdrawlab.java.commands.Revert;
import dev.smurf.patternsdrawlab.java.commands.State;

/**
 * Model в Mvi архитектуре
 */
public class Model {
    /**
     * Стэк команд
     */
    private Stack<Command> commandStack = new Stack<>();

    /**
     * Конструктор для подписи на поток команд от ui слоя
     */
    public Model() {
        commandStream.observeForever(new Observer<Command>() {
            @Override
            public void onChanged(Command command) {
                processCommand(command);
            }
        });
    }

    /**
     * Состояние ui
     */
    public State state = new State();

    /**
     * Поток команд
     */
    public MutableLiveData<Command> commandStream = new MutableLiveData<>();

    /**
     * Метод обработки команд
     */
    private void processCommand(Command command) {
        if (command instanceof Revert && !commandStack.isEmpty()) {
            ((Revert) command).setPrevCommand(commandStack.pop());
        } else {
            commandStack.push(command);
        }
        command.implement(state);
    }
}

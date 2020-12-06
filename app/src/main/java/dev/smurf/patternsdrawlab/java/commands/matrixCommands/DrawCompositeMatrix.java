package dev.smurf.patternsdrawlab.java.commands.matrixCommands;

import dev.smurf.patternsdrawlab.java.commands.Command;
import dev.smurf.patternsdrawlab.java.commands.State;
import dev.smurf.patternsdrawlab.java.matrix.MatrixConstructor;

public class DrawCompositeMatrix implements Command {
    State prev = null;

    @Override
    public void implement(State state) {
        prev = state.makeCopy();
        state.compositeMatrix.setValue(MatrixConstructor.Companion.constructCompositeMatrix());
        state.type.setValue(MatrixTypes.CompositeMatrix);
    }

    @Override
    public void reverse(State state) {
        if (prev != null) {
            state.compositeMatrix.setValue(prev.compositeMatrix.getValue());
            state.type.setValue(prev.type.getValue());
        }
    }
}

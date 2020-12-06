package dev.smurf.patternsdrawlab.java.commands.matrixCommands;

import dev.smurf.patternsdrawlab.java.commands.Command;
import dev.smurf.patternsdrawlab.java.commands.State;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.matrix.MatrixConstructor;

public class RenumBasicMatrix implements Command {
    State prev = null;

    @Override
    public void implement(State state) {
        prev = state.makeCopy();
        IMatrix<Integer> mat = state.basicMatrix.getValue();
        if (mat != null) {
            state.basicMatrix.setValue(MatrixConstructor.Companion.restoreMatrix(mat));
            state.type.setValue(MatrixTypes.BasicMatrix);
        }
    }

    @Override
    public void reverse(State state) {
        if (prev != null) {
            state.basicMatrix.setValue(prev.basicMatrix.getValue());
            state.type.setValue(prev.type.getValue());
        }
    }
}
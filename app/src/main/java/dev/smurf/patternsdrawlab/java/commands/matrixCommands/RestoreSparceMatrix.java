package dev.smurf.patternsdrawlab.java.commands.matrixCommands;

import dev.smurf.patternsdrawlab.java.commands.Command;
import dev.smurf.patternsdrawlab.java.commands.State;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.matrix.MatrixConstructor;

public class RestoreSparceMatrix implements Command {
    State prev = null;

    @Override
    public void implement(State state) {
        prev = state.makeCopy();
        IMatrix<Integer> mat = state.sparceMatrix.getValue();
        if (mat != null) {
            state.sparceMatrix.setValue(MatrixConstructor.Companion.renumMatrix(mat));
            state.type.setValue(MatrixTypes.SparceMatrix);
        }
    }

    @Override
    public void reverse(State state) {
        if (prev != null) {
            state.sparceMatrix.setValue(prev.sparceMatrix.getValue());
            state.type.setValue(prev.type.getValue());
        }
    }
}
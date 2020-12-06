package dev.smurf.patternsdrawlab.java.commands.matrixCommands;

import dev.smurf.patternsdrawlab.java.commands.Command;
import dev.smurf.patternsdrawlab.java.commands.State;

public class BorderCommand implements Command {
    State prev = null;

    @Override
    public void implement(State state) {
        prev = state.makeCopy();
        Boolean isBorder = state.isBorders.getValue();
        if (isBorder == null) isBorder = false;
        state.isBorders.setValue(!isBorder);
        state.type.setValue(MatrixTypes.NoRedraw);
    }

    @Override
    public void reverse(State state) {
        if (prev != null) {
            state.isBorders.setValue(prev.isBorders.getValue());
            state.type.setValue(prev.type.getValue());
        }
    }
}

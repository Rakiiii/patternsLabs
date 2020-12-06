package dev.smurf.patternsdrawlab.java.commands;

import androidx.lifecycle.MutableLiveData;

import dev.smurf.patternsdrawlab.java.commands.matrixCommands.MatrixTypes;
import dev.smurf.patternsdrawlab.java.drawers.DrawableInteger;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;

/**
 * Класс описания состояния ui , фактически является ViewState из Mvi архитектуры или Redux
 */
public class State {
    /**
     * Состояни матрицы
     * MutableLiveData является умным observable не допускающим обновления ui в недопустимый момент
     */
    public MutableLiveData<IMatrix<DrawableInteger>> compositeMatrix = new MutableLiveData<>();
    public MutableLiveData<IMatrix<Integer>> basicMatrix = new MutableLiveData<>();
    public MutableLiveData<IMatrix<Integer>> sparceMatrix = new MutableLiveData<>();
    public MutableLiveData<Boolean> isBorders = new MutableLiveData<>(false);
    public MutableLiveData<MatrixTypes> type = new MutableLiveData<>(null);

    /**
     * Копировать состояние
     *
     * @return Возвращет копию состояния
     */
    public State makeCopy() {
        State copy = new State();
        copy.basicMatrix.setValue(basicMatrix.getValue());
        copy.compositeMatrix.setValue(compositeMatrix.getValue());
        copy.sparceMatrix.setValue(sparceMatrix.getValue());
        copy.isBorders.setValue(isBorders.getValue());
        copy.type.setValue(type.getValue());
        return copy;
    }
}

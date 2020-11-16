package dev.smurf.patternsdrawlab.java.matrix;


import dev.smurf.patternsdrawlab.java.fabrics.BasicVectorFactory;

public class BasicMatrix<T> extends BaseMatrix<T>{
    public BasicMatrix(Integer row, Integer col) {
        reInitMatrix(new BasicVectorFactory<T>(), row, col);
    }
}

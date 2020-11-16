package dev.smurf.patternsdrawlab.java.fabrics;


import dev.smurf.patternsdrawlab.java.interfaces.IDrawable;
import dev.smurf.patternsdrawlab.java.interfaces.IVector;

public interface VectorDrawableFactory<T> {
    IDrawable createVectorDrawable(IVector<T> vector);
}

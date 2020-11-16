package dev.smurf.patternsdrawlab.java.vectors;

import java.util.ArrayList;

public final class BasicVector<T> extends BaseVector<T> {
    public BasicVector(Integer size) {
        this.vector = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            vector.add(null);
        }
    }
}

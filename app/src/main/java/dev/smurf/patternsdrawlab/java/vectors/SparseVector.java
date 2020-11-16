package dev.smurf.patternsdrawlab.java.vectors;

import android.os.Build;

import androidx.annotation.RequiresApi;

import dev.smurf.patternsdrawlab.java.interfaces.IVector;

import java.util.HashMap;
import java.util.Map;

public class SparseVector<T> implements IVector<T> {
    protected Map<Integer, T> vector;
    protected Integer size;
    protected T zeroValue;

    public SparseVector(Integer size, T zeroValue) {
        this.size = size;
        this.vector = new HashMap<Integer, T>();
        this.zeroValue = zeroValue;
    }

    @Override
    public void setElement(Integer i, T element) {
        if (i > size) throw new IllegalArgumentException("Index out of bound");
        if (element.equals(zeroValue)) return;
        vector.put(i, element);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public T getElement(Integer i) {
        return vector.getOrDefault(i, zeroValue);
    }

    @Override
    public Integer size() {
        return size;
    }
}

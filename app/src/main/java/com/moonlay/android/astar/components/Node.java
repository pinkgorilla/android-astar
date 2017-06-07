package com.moonlay.android.astar.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tris Setiawan on 5/30/2017.
 */

public class Node {
    public int index;
    public int row;
    public int col;
    public String value;
    public Node prev;

    public int totalCost = Integer.MAX_VALUE;
    public int cost;
    public int heuristic;
    boolean isPath = false;

    List<ValueChangedListener> listeners;
    List<CycleValueListener> cycleValueListeners;
    List<IsPathChangedListener> isPathListeners;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = "";
        this.listeners = new ArrayList<>();
        this.cycleValueListeners = new ArrayList<>();
        this.isPathListeners = new ArrayList<>();
    }

    public void setValue(String value) {
        this.value = value;
        for (ValueChangedListener listener : this.listeners)
            listener.valueChanged(this.value);
    }

    public void setIsPath(boolean isPath) {
        this.isPath = isPath;
        for (IsPathChangedListener listener : this.isPathListeners)
            listener.isPathChanged(this.isPath);
    }

    public void cycleValue() {
        for (CycleValueListener listener : this.cycleValueListeners)
            listener.set(this);
    }

    public void addValueChangedListener(ValueChangedListener listener) {
        this.listeners.add(listener);
    }

    public void addCycleValueListener(CycleValueListener listener) {
        this.cycleValueListeners.add(listener);
    }

    public void addIsPathChangedListener(IsPathChangedListener listener) {
        this.isPathListeners.add(listener);
    }

    public void reset() {
        this.totalCost = Integer.MAX_VALUE;
        this.cost = 0;
        this.heuristic = 0;
        this.prev = null;
        this.setIsPath(false);
    }

    public interface ValueChangedListener {
        void valueChanged(String value);
    }

    public interface IsPathChangedListener {
        void isPathChanged(boolean isPath);
    }

    public interface CycleValueListener {
        void set(Node node);
    }
}


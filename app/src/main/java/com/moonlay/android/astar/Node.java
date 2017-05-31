package com.moonlay.android.astar;

import android.net.sip.SipAudioCall;

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

    public int f = Integer.MAX_VALUE;
    public int g;
    public int h;
    boolean isPath = false;

    List<ValueChangedListener> listeners;
    List<SetListener> setListeners;
    List<PathChangedListener> isPathListeners;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = "";
        this.listeners = new ArrayList<>();
        this.setListeners = new ArrayList<>();
        this.isPathListeners = new ArrayList<>();
    }

    public void setValue(String value) {
        this.value = value;
        for (ValueChangedListener listener : this.listeners)
            listener.valueChanged(this.value);
    }

    public void setIsPath(boolean isPath) {
        this.isPath = isPath;
        for (PathChangedListener listener : this.isPathListeners)
            listener.pathChanged(this.isPath);
    }

    public void set() {
        for (SetListener listener : this.setListeners)
            listener.set(this);
    }

    public void addValueChangedListener(ValueChangedListener listener) {
        this.listeners.add(listener);
    }

    public void addSetListener(SetListener listener) {
        this.setListeners.add(listener);
    }

    public void addIsPathChangedListener(PathChangedListener listener) {
        this.isPathListeners.add(listener);
    }

    public void reset() {
        this.f = Integer.MAX_VALUE;
        this.g = 0;
        this.h = 0;
        this.prev = null;
        this.setIsPath(false);
    }

    public interface ValueChangedListener {
        void valueChanged(String value);
    }

    public interface PathChangedListener {
        void pathChanged(boolean isPath);
    }

    public interface SetListener {
        void set(Node node);
    }
}


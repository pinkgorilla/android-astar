package com.moonlay.android.astar.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.moonlay.android.astar.components.AStar;
import com.moonlay.android.astar.views.Cell;

public class AStarAdapter extends BaseAdapter {

    private final Context context;
    private final int size;
    private Cell[] cells;
    private AStar AStar;

    public AStarAdapter(Context context, int size) {
        super();
        this.context = context;
        this.size = size;
        this.AStar = new AStar(this.size);
        this.initializeCells();
    }

    private void initializeCells() {
        this.cells = new Cell[this.size * this.size];
        int index = 0;
        for (Cell cell : this.cells) {
            this.cells[index] = new Cell(this.context, this.AStar.nodes[index]);
            index++;
        }
    }

    @Override
    public int getCount() {
        return cells.length;
    }

    @Override
    public Object getItem(int position) {
        return cells[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return this.cells[position];
    }
}

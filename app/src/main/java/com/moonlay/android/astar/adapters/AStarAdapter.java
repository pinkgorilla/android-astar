package com.moonlay.android.astar.adapters;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.moonlay.android.astar.Calculator;
import com.moonlay.android.astar.Node;

/**
 * Created by Tris Setiawan on 5/30/2017.
 */

class Cell extends android.support.v7.widget.AppCompatTextView implements Node.ValueChangedListener, View.OnClickListener, Node.PathChangedListener {
    Node node;
    static LinearLayout.LayoutParams layoutParam;

    static {
        layoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(3, 3, 3, 3);
    }

    public Cell(Context context, Node node) {
        super(context);
        this.node = node;
        this.node.addValueChangedListener(this);
        this.node.addIsPathChangedListener(this);
        this.setText("");
        this.setOnClickListener(this);
        this.setStyles();
    }

    void setStyles() {
        this.setGravity(Gravity.CENTER);
        this.setBackgroundColor(Color.GRAY);
//        this.setLayoutParams(layoutParam);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }

    @Override
    public void valueChanged(String value) {
        this.setText(value);
    }

    @Override
    public void onClick(View view) {
        this.node.set();
    }

    @Override
    public void pathChanged(boolean isPath) {
        if (isPath)
            this.setBackgroundColor(Color.GREEN);
        else
            this.setBackgroundColor(Color.GRAY);
    }
}

public class AStarAdapter extends BaseAdapter {

    private final Context context;
    private final int size;
    private Cell[] cells;
    private Calculator calculator;

    public AStarAdapter(Context context, int size) {
        super();
        this.context = context;
        this.size = size;
        this.calculator = new Calculator(this.size);
        this.initializeCells();
    }

    private void initializeCells() {
        this.cells = new Cell[this.size * this.size];
        int index = 0;
        for (Cell cell : this.cells) {
            this.cells[index] = new Cell(this.context, this.calculator.nodes[index]);
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

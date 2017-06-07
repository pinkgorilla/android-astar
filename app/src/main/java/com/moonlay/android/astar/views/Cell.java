package com.moonlay.android.astar.views;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.moonlay.android.astar.components.Node;

/**
 * Created by Tris Setiawan on 5/30/2017.
 */

public class Cell extends android.support.v7.widget.AppCompatTextView implements Node.ValueChangedListener, View.OnClickListener, Node.IsPathChangedListener {
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
        this.node.cycleValue();
    }

    @Override
    public void isPathChanged(boolean isPath) {
        if (isPath)
            this.setBackgroundColor(Color.GREEN);
        else
            this.setBackgroundColor(Color.GRAY);
    }
}

package com.moonlay.android.astar.adapters;

import android.content.Context;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.moonlay.android.astar.components.AStar;
import com.moonlay.android.astar.views.CellView;

/**
 * Created by Tris Setiawan on 6/8/2017.
 */

public class AStarRVAdapter extends android.support.v7.widget.RecyclerView.Adapter<AStarRVAdapter.ViewHolder> {
    AStar aStar;

    public AStarRVAdapter(int size) {
        this.aStar = new AStar(size);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ViewHolder(new CellView(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cell.setNode(this.aStar.nodes[position]);
    }

    @Override
    public int getItemCount() {
        return this.aStar.nodes.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CellView cell;

        public ViewHolder(CellView cell) {
            super(cell);
            this.cell = cell;
        }
    }
}

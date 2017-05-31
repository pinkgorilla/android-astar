package com.moonlay.android.astar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.moonlay.android.astar.R;
import com.moonlay.android.astar.adapters.AStarAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AStarGridFragment extends Fragment {

    AStarAdapter adapter;

    public AStarGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.adapter = new AStarAdapter(this.getContext(), 10);
        GridView grid =  (GridView)inflater.inflate(R.layout.fragment_astar_grid, container, false);
        grid.setNumColumns(10);
        grid.setAdapter(this.adapter);
        return  grid;
    }

}

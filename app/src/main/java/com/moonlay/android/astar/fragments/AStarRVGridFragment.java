package com.moonlay.android.astar.fragments;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonlay.android.astar.R;
import com.moonlay.android.astar.adapters.AStarRVAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AStarRVGridFragment extends Fragment {


    public AStarRVGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int size = 10;
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_astar_rvgrid, container, false);

        GridLayoutManager gridLayout = new GridLayoutManager(this.getContext(), size);
        RecyclerView.LayoutParams lp = gridLayout.generateDefaultLayoutParams();
        lp.setMargins(3, 3, 3, 3);


        recyclerView.setLayoutManager(gridLayout);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//R.dimen.cellSpacing

                int space = (int) getResources().getDimension(R.dimen.cellSpacing);
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;

                // Add top margin only for the first item to avoid double space between items
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.top = space;
                } else {
                    outRect.top = 0;
                }
            }
        });
        recyclerView.setAdapter(new AStarRVAdapter(size));
        return recyclerView;
    }

}

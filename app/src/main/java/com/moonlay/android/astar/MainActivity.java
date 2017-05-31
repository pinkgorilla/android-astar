package com.moonlay.android.astar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.moonlay.android.astar.fragments.AStarGridFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AStarGridFragment fragment = new AStarGridFragment();
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.astar_fragment_container, fragment).commit();
    }
}

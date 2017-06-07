package com.moonlay.android.astar;

import com.moonlay.android.astar.components.AStar;
import com.moonlay.android.astar.components.Node;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tris Setiawan on 6/7/2017.
 */

public class AStarUnitTest {
    AStar getInstance(int size) {
        return new AStar(size);
    }

    @Test
    public void initialisation_isValid() {
        int size = 10;
        AStar aStar = getInstance(size);
        assertEquals(Math.pow(size, 2), aStar.nodes.length, 0);

        int index = 0;
        for (Node node : aStar.nodes) {
            assertEquals("", node.value);
            assertEquals(index++, node.index);
        }
    }
}

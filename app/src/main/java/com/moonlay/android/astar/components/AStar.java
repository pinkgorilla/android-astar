package com.moonlay.android.astar.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tris Setiawan on 5/30/2017.
 */

public class AStar implements Node.CycleValueListener {
    int size;
    public Node[] nodes;
    static String[] values = {"", "S", "F", "2", "3", "5", "#"};

    boolean startIsSet = false;
    Node startNode;
    boolean finishIsSet = false;
    Node endNode;


    public AStar(int size) {
        this.size = size;
        this.nodes = new Node[this.size * this.size];
        int index = 0;
        int row = 0;
        int col = 0;
        for (Node cell : this.nodes) {
            row = index / this.size;
            col = index % this.size;
            this.nodes[index] = new Node(row, col);
            this.nodes[index].index = index;
            this.nodes[index].addCycleValueListener(this);
            index++;
        }
    }

    @Override
    public void set(Node node) {
        this.resetNodes();
        int currentValueIndex = Arrays.asList(values).indexOf(node.value);
        int newValueIndex = (currentValueIndex + 1) % values.length;
        String newValue = values[newValueIndex];

        while ((startNode != null && newValue == "S") || (endNode != null && newValue == "F")) {
            newValueIndex = (newValueIndex + 1) % values.length;
            newValue = values[newValueIndex];
        }

        node.setValue(newValue);

        if (node == startNode)
            startNode = null;
        if (node == endNode)
            endNode = null;

        if (newValue == "S")
            startNode = node;
        if (newValue == "F")
            endNode = node;

        if (startNode != null && endNode != null)
            this.calculate(startNode, endNode);
    }

    public void calculate(Node startNode, Node endNode) {
        List<Node> openList = new ArrayList<>();
        boolean[] closed = new boolean[this.nodes.length];
        for (Node node : this.nodes)
            closed[node.index] = false;

        openList.add(startNode);
        boolean openListIsEmpty = openList.toArray().length == 0;

        while (!openListIsEmpty) {
            Node currentNode = null;
            for (Node node : openList) {
                if (currentNode == null)
                    currentNode = node;
                else if (node.totalCost < currentNode.totalCost)
                    currentNode = node;
            }

            if (currentNode == endNode) {
                this.setPath(endNode);
                return;
            }

            openList.remove(currentNode);
            closed[currentNode.index] = true;

            List<Node> neighbours = this.getNeighbours(currentNode);
            for (Node neighbour : neighbours) {
                if (closed[neighbour.index] || isNodeBlocked(neighbour))
                    continue;

                boolean bestScore = false;
                int gScore = currentNode.cost + getNodeCost(neighbour);

                if (openList.indexOf(neighbour) < 0) {
                    bestScore = true;
                    neighbour.heuristic = getManhattanDistance(neighbour, endNode);
                    openList.add(neighbour);
                } else if (gScore < neighbour.cost) {
                    bestScore = true;
                }
                if (bestScore) {
                    neighbour.prev = currentNode;
                    neighbour.cost = gScore;
                    neighbour.totalCost = neighbour.cost + neighbour.heuristic;
                }
            }
            openListIsEmpty = openList.toArray().length == 0;
        }
    }

    int getManhattanDistance(Node source, Node destination) {
        int distance = Math.abs(source.col - destination.col) + Math.abs(source.row - destination.row);
        return distance;
    }

    void resetNodes() {
        for (Node node : this.nodes)
            node.reset();
    }

    void setPath(Node endNode) {
        Node currentNode = endNode;
        do {
            currentNode.setIsPath(true);
            currentNode = currentNode.prev;
        }
        while (currentNode != null && currentNode.prev != currentNode);
    }

    Node getNode(String value) {
        for (Node node : this.nodes)
            if (node.value == value)
                return node;
        return null;
    }

    boolean isNodeBlocked(Node node) {
        return this.getNodeCost(node) == -1;
    }

    List<Node> getNeighbours(Node node) {
        int east = ((node.index / this.size)) == (((node.index + 1) / this.size)) ? node.index + 1 : -1;
        int west = ((node.index / this.size)) == (((node.index - 1) / this.size)) ? node.index - 1 : -1;
        int south = node.index + this.size;
        int north = node.index - this.size;

        int[] indices = {east, south, west, north};
        List<Node> neighbours = new ArrayList();
        for (int index : indices) {
            if (index >= 0 && index <= (this.size * this.size) - 1)
                neighbours.add(this.nodes[index]);
        }
        return neighbours;
    }

    int getNodeCost(Node node) {
        int valueIndex = Arrays.asList(values).indexOf(node.value);
        String newValue = values[valueIndex];
        switch (newValue) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "5":
                return 5;
            case "#":
                return -1;
            default:
                return 1;
        }
    }
}

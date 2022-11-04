package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {
    private Graph g;
    private ArrayList<Chain> chains;
    private ArrayList<Vertex> arr;
    private int numberOfVertices;

    @Override
    public String findMST(String pathToFile) {
        g = new Graph();
        Utils u = new Utils();
        u.readFile(g, pathToFile);
        g.checkIfGraphIsCoherent();
        ArrayList<Vertex> vertices = g.getVertices();
        numberOfVertices = vertices.size();
        inizialize();
        verticesToChains(vertices);
        Collections.sort(chains);
        findMst();
        return getMSTinString();
    }

    private void findMst() {
        Edge e;
        int i = 0, j = 0;
        while (chains.size() != 0 && arr.size() > 1) {
            e = chains.remove(0);
            if ((i = find(e.getSource().getName())) != (j = find(e.getDestination().getName()))) {
                if (i > j) {
                    int t = i;
                    i = j;
                    j = t;
                }
                if (arr.get(j).getEdgesSize() != 0)
                    arr.get(i).addEdges(arr.remove(j).getEdges());
                else
                    arr.remove(j);
                arr.get(i).addEdge(e);
            }
        }
    }

    private int find(String name) {
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            Vertex v = arr.get(i);
            if (v.getEdgesSize() == 0) {
                if (v.getName().equals(name))
                    return i;
            } else {
                for (Edge e : v.getEdges()) {
                    if (e.getSource().getName().equals(name) || e.getDestination().getName().equals(name))
                        return i;
                }
            }
        }
        return -1;
    }

    private String getMSTinString() {
        String result = "";
        int i = 0;
        Collections.sort(arr.get(0).getEdges());
        for (Edge e : arr.get(0).getEdges()) {
            if (i == 0)
                result += e.toString();
            else
                result += "|" + e.toString();
            i++;
        }
        return result;
    }

    private void verticesToChains(ArrayList<Vertex> vertices) {
        for (Vertex v : vertices) {
            arr.add(new Vertex(v.getName()));
            for (Edge e : v.getEdges()) {
                Chain c = new Chain(v, e.getDestination(), e.getCost());
                if (!chains.contains(c))
                    chains.add(c);
            }
        }
    }

    private void inizialize() {
        chains = new ArrayList<>();
        arr = new ArrayList<>(numberOfVertices);
    }

    private class Chain extends Edge {

        public Chain(Vertex src, Vertex dest, int cost) {
            super(src, dest, cost);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Chain) {
                Chain c = (Chain) obj;
                return getSource().equals(c.getSource()) && getDestination().equals(c.getDestination())
                        || getSource().equals(c.getDestination()) && getDestination().equals(c.getSource());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

    }
}

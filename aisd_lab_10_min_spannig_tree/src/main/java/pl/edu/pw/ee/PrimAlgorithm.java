package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    private Graph g;
    private ArrayList<Vertex> vertices;
    private ArrayList<String> visited;
    private Heap<Edge> heap;
    private String result;

    public String findMST(String pathToFile) {
        g = new Graph();
        Utils u = new Utils();
        u.readFile(g, pathToFile);
        g.checkIfGraphIsCoherent();
        vertices = g.getVertices();
        inizialize();
        findMst();
        return result;
    }

    private void findMst() {
        addEdgesFromVertexToHeap(vertices.get(0));
        Edge e;
        while (visited.size() != vertices.size()) {
            e = heap.pop();
            validateEdge(e);
            while (isVisited(e.getDestination().getName())) {
                e = heap.pop();
                validateEdge(e);
            }
            if (visited.size() == 0) {
                addToVisited(e.getSource().getName());
                addToVisited(e.getDestination().getName());
                result += e.toString();
            } else {
                result += '|' + e.toString();
                addToVisited(e.getDestination().getName());
            }
            addEdgesFromVertexToHeap(e.getDestination());
        }
    }

    private void addEdgesFromVertexToHeap(Vertex v) {
        for (Edge e : v.getEdges()) {
            if (!isVisited(e.getDestination().getName()))
                heap.put(e);
        }
    }

    private void addToVisited(String name) {
        visited.add(name);
    }

    private boolean isVisited(String name) {
        for (String s : visited)
            if (s.equals(name))
                return true;
        return false;
    }

    private void validateEdge(Edge e) {
        if (e == null)
            throw new IllegalStateException("Mistake in graph");
    }

    private void inizialize() {
        heap = new Heap<>();
        visited = new ArrayList<>(vertices.size());
        result = "";
    }

}

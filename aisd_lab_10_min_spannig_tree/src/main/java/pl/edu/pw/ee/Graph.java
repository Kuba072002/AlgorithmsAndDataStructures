package pl.edu.pw.ee;

import java.util.ArrayList;

public class Graph {
    ArrayList<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public void add(String[] values) throws IllegalArgumentException {
        validateArguments(values);
        if (!vertices.contains(new Vertex(values[0])) && !vertices.contains(new Vertex(values[1])))
            add2NewVertex(values, true);
        else if (!vertices.contains(new Vertex(values[0])))
            addWithOneContainingVertex(values, 1);
        else if (vertices.contains(new Vertex(values[1])))
            add2NewVertex(values, false);
        else
            addWithOneContainingVertex(values, 0);
    }

    private void add2NewVertex(String[] values, boolean isNew) {
        int cost = Integer.parseInt(values[2]);
        if (isNew) {
            Vertex v1 = new Vertex(values[0]);
            Vertex v2 = new Vertex(values[1]);
            v1.addEdge(new Edge(v1, v2, cost));
            v2.addEdge(new Edge(v2, v1, cost));
            vertices.add(v1);
            vertices.add(v2);
        } else {
            Vertex v1 = getVertex(values[0]);
            Vertex v2 = getVertex(values[1]);
            if (v1.getEdges().contains(new Edge(v1, v2, 0)))
                return;
            v1.addEdge(new Edge(v1, v2, cost));
            v2.addEdge(new Edge(v2, v1, cost));
        }
    }

    private void addWithOneContainingVertex(String[] values, int i) {
        int cost = Integer.parseInt(values[2]);
        int j = 0;
        if (i == 0)
            j = 1;
        Vertex v1 = getVertex(values[i]);
        Vertex v2 = new Vertex(values[j]);
        v1.addEdge(new Edge(v1, v2, cost));
        v2.addEdge(new Edge(v2, v1, cost));
        vertices.add(v2);
    }

    private Vertex getVertex(String name) {
        for (Vertex v : vertices)
            if (v.getName().equals(name))
                return v;
        return null;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void printGraph() {
        for (Vertex v : vertices) {
            System.out.print(v.getName() + "  ");
            ArrayList<Edge> edges = (ArrayList<Edge>) v.getEdges();
            for (Edge e : edges) {
                System.out.print(e.getDestination() + " " + e.getCost() + " ");
            }
            System.out.print('\n');
        }
    }

    public void checkIfGraphIsCoherent() {
        if (vertices.size() == 0)
            throw new IllegalStateException("Graph is empty");
        ArrayList<String> visited = new ArrayList<>(vertices.size());
        visit(vertices.get(0), visited);
        if (!(visited.size() == vertices.size()))
            throw new IllegalStateException("The graph is not cohesive");
    }

    private void visit(Vertex v, ArrayList<String> visited) {
        visited.add(v.getName());
        for (Edge e : v.getEdges())
            if (!isVisited(visited, e.getDestination().getName()))
                visit(e.getDestination(), visited);
    }

    private boolean isVisited(ArrayList<String> visited, String name) {
        for (String s : visited)
            if (s.equals(name))
                return true;
        return false;
    }

    private void validateArguments(String[] values) throws IllegalArgumentException {
        if (values.length != 3)
            throw new IllegalArgumentException(
                    "Wrong number of arguments\nPattern:\nString String Int\nIn String only letters");
        boolean result = true;
        for (int i = 0; i < 2; i++)
            if (!values[i].matches("[a-zA-Z]+"))
                result = false;
        if (!values[2].matches("[0-9]+"))
            result = false;
        if (!result)
            throw new IllegalArgumentException(
                    "Mistake in arguments\n" + values[0] + " " + values[1] + " " + values[2]
                            + "\nPattern:\nString String Int\nIn String only letters");
        if (values[0].equals(values[1]))
            throw new IllegalArgumentException("Not handled for edge with that same start and end");
    }

}

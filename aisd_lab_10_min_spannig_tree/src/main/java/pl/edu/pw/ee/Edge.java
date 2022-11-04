package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private Vertex source;
    private Vertex destination;
    private int cost;

    public Edge(Vertex src, Vertex dest, int cost) {
        this.source = src;
        this.destination = dest;
        this.cost = cost;
    }

    public Vertex getSource() {
        return source;
    }

    public int getCost() {
        return cost;
    }

    public Vertex getDestination() {
        return destination;
    }

    @Override
    public int compareTo(Edge e) {
        if (cost > e.getCost())
            return 1;
        if (cost < e.getCost())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return source.getName() + "_" + cost + "_" + destination.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge e = (Edge) obj;
            if (e.getSource().equals(source) && e.getDestination().equals(destination))
                return true;
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

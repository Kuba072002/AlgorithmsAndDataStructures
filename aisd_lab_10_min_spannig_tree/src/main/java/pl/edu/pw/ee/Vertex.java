package pl.edu.pw.ee;

import java.util.ArrayList;

public class Vertex {
    private String name;
    private ArrayList<Edge> edges;

    public Vertex(String name){
        this.name = name;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge e){
        edges.add(e);
    }

    public void addEdges(ArrayList<Edge> e){
        edges.addAll(e);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public int getEdgesSize(){
        return edges.size();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vertex){
            Vertex v = (Vertex) obj;
            if(v.getName().equals(name))
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

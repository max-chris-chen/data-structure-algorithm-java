package graph.weight;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Max Chen on 2018/8/18.
 */
public class Vertex {
    private String name;
    private List<Edge> edgeLinkedList=new LinkedList<>();
    private boolean visted;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdgeLinkedList() {
        return edgeLinkedList;
    }

    public void setEdgeLinkedList(List<Edge> edgeLinkedList) {
        this.edgeLinkedList = edgeLinkedList;
    }

    public boolean isVisted() {
        return visted;
    }

    public void setVisted(boolean visted) {
        this.visted = visted;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return !(name != null ? !name.equals(vertex.name) : vertex.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

package graph.direct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Max Chen on 2018/8/18.
 */
public class Vertex {
    private String name;
    private List<Edge> edgeLinkedList=new LinkedList<>();
    private List<Edge> visitEdges=new LinkedList<>();
    private boolean visted;
    private int totalWeight=0;
    private List<VertexVisitPath> vertexVisitPathList=new ArrayList<>();

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

    public List<Edge> getVisitEdges() {
        return visitEdges;
    }

    public void setVisitEdges(List<Edge> visitEdges) {
        this.visitEdges = visitEdges;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<VertexVisitPath> getVertexVisitPathList() {
        return vertexVisitPathList;
    }

    public void setVertexVisitPathList(List<VertexVisitPath> vertexVisitPathList) {
        this.vertexVisitPathList = vertexVisitPathList;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", totalWeight=" + totalWeight +
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

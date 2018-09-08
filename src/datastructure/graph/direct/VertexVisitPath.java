package datastructure.graph.direct;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Max Chen on 2018/8/26.
 */
public class VertexVisitPath {

    private int totalWeight=Integer.MAX_VALUE;
    private Vertex vertex;
    private List<Vertex> path=new LinkedList<>();


    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<Vertex> getPath() {
        return path;
    }

    public void setPath(List<Vertex> path) {
        this.path = path;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return "VertexVisitPath{" +
                "totalWeight=" + totalWeight +
                ", vertex=" + vertex +
                ", path=" + path +
                '}';
    }
}

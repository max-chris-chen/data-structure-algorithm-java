package graph.nodirect;

import java.util.LinkedList;

/**
 * Created by Max Chen on 2018/8/12.
 */
public class Vertex {
    private  String name;
    private LinkedList<Vertex> adjacencyVertexList=new LinkedList<>();
    private boolean visted;


    public void addAdjanceyVertex(Vertex vertex){
        adjacencyVertexList.add(vertex);
    }


    public Vertex getUnVisitedAdjacency(){
        for (Vertex vertex:adjacencyVertexList){
            if (!vertex.isVisted()){
                return vertex;
            }
        }
        return null;
    }

    public boolean isVisted() {
        return visted;
    }

    public void setVisted(boolean visted) {
        this.visted = visted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Vertex> getAdjacencyVertexList() {
        return adjacencyVertexList;
    }

    public void setAdjacencyVertexList(LinkedList<Vertex> adjacencyVertexList) {
        this.adjacencyVertexList = adjacencyVertexList;
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

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}

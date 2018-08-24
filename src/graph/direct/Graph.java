package graph.direct;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Max Chen on 2018/8/18.
 */
public class Graph {
    List<Vertex> vertexList = new ArrayList<>();

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);

    }



    public void addAdjacencyVertex(Vertex vertex, Vertex adjacencyVertex, int weight) {
        if (vertex.getEdgeLinkedList().stream().noneMatch(edge -> edge.getDestVertex().equals(adjacencyVertex))) {
            Edge edge = new Edge(vertex, adjacencyVertex, weight);
            vertex.getEdgeLinkedList().add(edge);
        }
    }

    /**
     * Dijkstra
     */
    public void shortestPath(){
        Vertex currentVertext=vertexList.get(0);
        currentVertext.setVisted(true);
        int picked=0;
        while (picked<vertexList.size()-1){
           for (Edge edge:currentVertext.getEdgeLinkedList()){
               Edge edgeWithSameDest=getEdgeByDest(edge.getDestVertex().getVisitEdges(),edge.getDestVertex());
               if (edgeWithSameDest==null){
                   edge.getDestVertex().getVisitEdges().addAll(currentVertext.getVisitEdges());
                   edge.getDestVertex().getVisitEdges().add(edge);
                   edge.getDestVertex().setTotalWeight(currentVertext.getTotalWeight()+edge.getWeight());
               }else {
                   if (currentVertext.getTotalWeight()+edge.getWeight()<edge.getDestVertex().getTotalWeight()){
                       edge.getDestVertex().getVisitEdges().clear();
                       edge.getDestVertex().getVisitEdges().addAll(currentVertext.getVisitEdges());
                       edge.getDestVertex().setTotalWeight(currentVertext.getTotalWeight());
                       edge.getDestVertex().getVisitEdges().add(edge);
                       edge.getDestVertex().setTotalWeight(edge.getDestVertex().getTotalWeight()+edge.getWeight());
                   }
               }
           }
            currentVertext=getMinTotalWeightVisitedVertex();
            currentVertext.setVisted(true);
            picked+=1;
         }

    }
    private Vertex getMinTotalWeightVisitedVertex(){
        return vertexList.stream().filter(vertex -> vertex.getTotalWeight()!=0 && !vertex.isVisted())
                .sorted((v1,v2)-> v1.getTotalWeight()-v2.getTotalWeight()).findFirst().get();



    }
    private int getTotalWeight(List<Edge> edgeList){
        int totalWeight=0;
        for (Edge edge : edgeList) {
            totalWeight+= edge.getWeight();
        }
        return totalWeight;
    }

    private Edge getEdgeByDest(List<Edge> priorityQueue,Vertex vertex){
        return priorityQueue.stream().filter(e -> e.getDestVertex().equals(vertex)).findFirst().orElse(null);

    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public static void main(String[] args) {
        Graph graph=new Graph();
        Vertex a=new Vertex("A");
        Vertex b=new Vertex("B");
        Vertex c=new Vertex("C");
        Vertex d=new Vertex("D");
        Vertex e=new Vertex("E");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addAdjacencyVertex(a, d, 80);
        graph.addAdjacencyVertex(a,b,50);
        graph.addAdjacencyVertex(b,d,90);
        graph.addAdjacencyVertex(b,c,60);
        graph.addAdjacencyVertex(e,b,50);
        graph.addAdjacencyVertex(d,c,20);
        graph.addAdjacencyVertex(d,e,70);
        graph.addAdjacencyVertex(c,e,40);
        graph.shortestPath();
        for (Vertex vertex : graph.getVertexList()) {
            System.out.println(vertex);
            System.out.println(vertex.getVisitEdges());
            System.out.println("-------------");
        }




    }
}

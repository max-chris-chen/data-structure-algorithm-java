package datastructure.graph.weight;

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
        if (!vertex.getEdgeLinkedList().stream().anyMatch(edge -> edge.getDestVertex().equals(adjacencyVertex))) {
            Edge edge = new Edge(vertex, adjacencyVertex, weight);
            vertex.getEdgeLinkedList().add(edge);
        }

        if (!adjacencyVertex.getEdgeLinkedList().stream().anyMatch(edge -> edge.getDestVertex().equals(vertex))) {
            Edge edge = new Edge(adjacencyVertex, vertex, weight);
            adjacencyVertex.getEdgeLinkedList().add(edge);
        }
    }

    public void minimumSpanningTreeWithWeight(){
        int picked=0;
        PriorityQueue<Edge> priorityQueue=new PriorityQueue<>((edge1,edge2)->edge1.getWeight()-edge2.getWeight());
        Vertex currentVertex=vertexList.get(0);
        //count of edge equals to vertexList.size()-1
        while (picked<vertexList.size()-1){

            for (Edge e:currentVertex.getEdgeLinkedList()){
                if (e.getDestVertex().isVisted()){
                    continue;
                }

                Edge edgeInQueue=getEdgeByDest(priorityQueue,e.getDestVertex());
                if (edgeInQueue!=null){
                    if (edgeInQueue.getWeight()>e.getWeight()){
                        removeEdge(priorityQueue,edgeInQueue);
                        priorityQueue.add(e);
                    }
                }else {
                    priorityQueue.add(e);
                }

            }


            Edge minEdge=priorityQueue.poll();
            minEdge.getSrcVertex().setVisted(true);
            minEdge.getDestVertex().setVisted(true);
            currentVertex=minEdge.getDestVertex();
            picked++;

            System.out.println(minEdge.getSrcVertex()+" "+minEdge.getDestVertex());
        }

    }

    private Edge getEdgeByDest(PriorityQueue<Edge> priorityQueue,Vertex vertex){
        return priorityQueue.stream().filter(e -> e.getDestVertex().equals(vertex)).findFirst().orElse(null);
    }

    private void removeEdge(PriorityQueue<Edge> priorityQueue,Edge edge){
        priorityQueue.remove(edge);
    }

    public static void main(String[] args) {
        Graph graph=new Graph();
        Vertex a=new Vertex("A");
        Vertex b=new Vertex("B");
        Vertex c=new Vertex("C");
        Vertex d=new Vertex("D");
        Vertex e=new Vertex("E");
        Vertex f=new Vertex("F");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addAdjacencyVertex(a, d, 4);
        graph.addAdjacencyVertex(a,b,6);
        graph.addAdjacencyVertex(b,d,7);
        graph.addAdjacencyVertex(b,e,7);
        graph.addAdjacencyVertex(b,c,10);
        graph.addAdjacencyVertex(d,c,8);
        graph.addAdjacencyVertex(d,e,12);
        graph.addAdjacencyVertex(c,e,5);
        graph.addAdjacencyVertex(c,f,6);
        graph.addAdjacencyVertex(e,f,7);


        graph.minimumSpanningTreeWithWeight();


    }
}

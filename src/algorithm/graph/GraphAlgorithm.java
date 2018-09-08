package algorithm.graph;


import datastructure.graph.nodirect.Graph;
import datastructure.graph.nodirect.Vertex;
import datastructure.graph.weight.Edge;

import java.util.*;

/**
 * @author Max
 */
public class GraphAlgorithm {

    /**
     * @param graph
     */
    public List<List<Vertex>> hamiltonianCycles(Graph graph) {
        List<List<Vertex>> cycles = new ArrayList<>();
        Vertex[] cycle = new Vertex[graph.getVertexList().size()];
        hamiltonianCyclesRecursive(graph,cycles,cycle,graph.getVertexList().get(0),0);
        return cycles;
    }

    private void hamiltonianCyclesRecursive(Graph graph, List<List<Vertex>> cycles, Vertex[] cycle , Vertex vertex, int cycleIndex) {
        if (!vertexExistInCycleArray(cycle, vertex, cycleIndex)){
            cycle[cycleIndex]=vertex;
            if (isCycleArrayFullAndExistCycle(graph, cycle, cycleIndex)){
                addCycleToCycles(cycles, cycle);
                return;
            }
        }else {
            return;
        }
        for (Vertex adjacencyVertex : vertex.getAdjacencyVertexList()) {
            hamiltonianCyclesRecursive(graph,cycles,cycle,adjacencyVertex,cycleIndex+1);
        }

    }

    private void addCycleToCycles(List<List<Vertex>> cycles, Vertex[] cycle) {
        List<Vertex>oneCycle=new ArrayList<>();
        oneCycle.addAll(Arrays.asList(cycle));
        cycles.add(oneCycle);
    }

    private boolean isCycleArrayFullAndExistCycle(Graph graph, Vertex[] cycle, int cycleIndex) {
        return cycleIndex==graph.getVertexList().size()-1&&cycle[0].getAdjacencyVertexList().contains(cycle[cycleIndex]);
    }

    private boolean vertexExistInCycleArray(Vertex[] cycle, Vertex vertex, int index){

        for (int i=0;i<index;i++){
            if (vertex.equals(cycle[i])){
                return true;
            }
        }
        return false;
    }
    //todo need refactor
    private boolean vertexExistInCycleArrayForWeight(datastructure.graph.weight.Vertex[] cycle, datastructure.graph.weight.Vertex vertex, int index){

        for (int i=0;i<index;i++){
            if (vertex.equals(cycle[i])){
                return true;
            }
        }
        return false;
    }
    //todo need refactor
    private Optional<Edge> getLastEdgeForCycle(datastructure.graph.weight.Graph graph, datastructure.graph.weight.Vertex[] cycle, int cycleIndex) {
        if(cycleIndex==graph.getVertexList().size()-1&&cycle[0].getEdgeLinkedList().stream().anyMatch(edge -> edge.getDestVertex().equals(cycle[cycleIndex]))){
            return cycle[0].getEdgeLinkedList().stream().filter(edge -> edge.getDestVertex().equals(cycle[cycleIndex])).findFirst();
        }
        return Optional.empty();
    }

    public List<datastructure.graph.weight.Edge> travelingSalesMan(datastructure.graph.weight.Graph graph){
        datastructure.graph.weight.Vertex[] cycle=new datastructure.graph.weight.Vertex[graph.getVertexList().size()];

        return travelingSalesManRecursive(graph, graph.getVertexList().get(0), cycle, 0);
    }

    private List<datastructure.graph.weight.Edge>travelingSalesManRecursive(datastructure.graph.weight.Graph graph,datastructure.graph.weight.Vertex vertex,datastructure.graph.weight.Vertex[] cycle,int cycleIndex){
        List edges=new ArrayList<>();

        if (!vertexExistInCycleArrayForWeight(cycle, vertex, cycleIndex)){
            cycle[cycleIndex]=vertex;
            Optional<Edge>lastEdgeForCycle=getLastEdgeForCycle(graph, cycle, cycleIndex);
            if (lastEdgeForCycle.isPresent()){
                edges.add(lastEdgeForCycle.get());
                return edges;
            }
        }else {
            return edges;
        }
        PriorityQueue<List<Edge>> queue=new PriorityQueue<>((o1, o2) -> o1.stream().mapToInt(e->e.getWeight()).sum()-o2.stream().mapToInt(e->e.getWeight()).sum());
        for (datastructure.graph.weight.Edge adjacencyEdge:vertex.getEdgeLinkedList()){
            datastructure.graph.weight.Vertex adjacencyVertex=adjacencyEdge.getDestVertex();
            List<Edge> subEdges=travelingSalesManRecursive(graph,adjacencyVertex,cycle,cycleIndex+1);
            if (!subEdges.isEmpty()){
                subEdges.add(0,adjacencyEdge);
                queue.add(subEdges);
            }
        }

        if (!queue.isEmpty()){
            edges.addAll(queue.peek());
        }
        return edges;
    }


    public static void main(String[] args) {
        GraphAlgorithm graphAlgorithm=new GraphAlgorithm();
//        Graph graph = new Graph();
//        Vertex a = new Vertex("A");
//        Vertex b = new Vertex("B");
//        Vertex c = new Vertex("C");
//        Vertex d = new Vertex("D");
//        Vertex e = new Vertex("E");
//        graph.addVertex(a);
//        graph.addVertex(b);
//        graph.addVertex(c);
//        graph.addVertex(d);
//        graph.addVertex(e);
//        graph.addAdjacencyVertex(a, b);
//        graph.addAdjacencyVertex(a, c);
//        graph.addAdjacencyVertex(a, e);
//        graph.addAdjacencyVertex(b, e);
//        graph.addAdjacencyVertex(b, c);
//        graph.addAdjacencyVertex(b, d);
//        graph.addAdjacencyVertex(d, c);
//        graph.addAdjacencyVertex(e, d);
//        for(List<Vertex> list:graphAlgorithm.hamiltonianCycles(graph)){
//            System.out.println(list);
//        }


        datastructure.graph.weight.Graph graph=new datastructure.graph.weight.Graph();
        datastructure.graph.weight.Vertex a = new datastructure.graph.weight.Vertex("A");
        datastructure.graph.weight.Vertex b = new datastructure.graph.weight.Vertex("B");
        datastructure.graph.weight.Vertex c = new datastructure.graph.weight.Vertex("C");
        datastructure.graph.weight.Vertex d = new datastructure.graph.weight.Vertex("D");
        datastructure.graph.weight.Vertex e = new datastructure.graph.weight.Vertex("E");
        datastructure.graph.weight.Vertex f = new datastructure.graph.weight.Vertex("F");
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

        System.out.println(graphAlgorithm.travelingSalesMan(graph));

    }
}

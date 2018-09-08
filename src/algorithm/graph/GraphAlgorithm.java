package algorithm.graph;


import datastructure.graph.nodirect.Graph;
import datastructure.graph.nodirect.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addAdjacencyVertex(a, b);
        graph.addAdjacencyVertex(a, c);
        graph.addAdjacencyVertex(a, e);
        graph.addAdjacencyVertex(b, e);
        graph.addAdjacencyVertex(b, c);
        graph.addAdjacencyVertex(b, d);
        graph.addAdjacencyVertex(d, c);
        graph.addAdjacencyVertex(e, d);
        GraphAlgorithm graphAlgorithm=new GraphAlgorithm();
        for(List<Vertex> list:graphAlgorithm.hamiltonianCycles(graph)){
            System.out.println(list);
        }

    }
}

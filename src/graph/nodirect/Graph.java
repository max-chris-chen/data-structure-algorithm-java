package graph.nodirect;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Max Chen on 2018/8/12.
 */
public class Graph {
    private List<Vertex> vertexList=new ArrayList<>();

    public void addVertex(Vertex vertex){
        vertexList.add(vertex);

    }
    public void addAdjacencyVertex(Vertex vertex,Vertex adjacencyVertex){
        if (!vertex.getAdjacencyVertexList().contains(adjacencyVertex)){
            vertex.addAdjanceyVertex(adjacencyVertex);
        }
        if (!adjacencyVertex.getAdjacencyVertexList().contains(vertex)){
            adjacencyVertex.getAdjacencyVertexList().add(vertex);
        }
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }


    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }


    public void deepFirstSearch(Vertex firstVertex){
        visitVertex(firstVertex);
        Stack<Vertex> vertexStack=new Stack<>();
        vertexStack.push(firstVertex);
        while (!vertexStack.empty()){
            Vertex vertex=vertexStack.peek();
            Vertex unVistedAdjacencyVertex=vertex.getUnVisitedAdjacency();
            if (unVistedAdjacencyVertex==null){
                vertexStack.pop();
            }else {
                visitVertex(unVistedAdjacencyVertex);
                vertexStack.push(unVistedAdjacencyVertex);
            }

        }
    }

    public void breadthFirthSearch(Vertex firstVertex){
        visitVertex(firstVertex);
        Queue<Vertex> vertextQueue=new LinkedList<>();
        vertextQueue.add(firstVertex);
        while (!vertextQueue.isEmpty()){
            Vertex vertex=vertextQueue.peek();
            Vertex unVistedAdjacencyVertex=vertex.getUnVisitedAdjacency();
            if (unVistedAdjacencyVertex==null){
                vertextQueue.remove();
            }else {
                visitVertex(unVistedAdjacencyVertex);
                vertextQueue.add(unVistedAdjacencyVertex);
            }
        }

    }

    public void breadthFirthSearch2(Vertex firstVertex){
        Queue<Vertex> vertextQueue=new LinkedList<>();
        vertextQueue.add(firstVertex);
        while (!vertextQueue.isEmpty()){
            Vertex vertex=vertextQueue.remove();
            visitVertex(vertex);
            vertextQueue.addAll(
                    vertex.getAdjacencyVertexList().stream()
                            .filter(v -> !v.isVisted())
                            .collect(Collectors.toList()));
        }

    }


    public void visitVertex(Vertex vertex){
        vertex.setVisted(true);
        System.out.println(vertex);
    }
    public static void main(String[] args) {

        Graph graph=new Graph();

        Vertex vertexa=new Vertex();
        vertexa.setName("a");
        Vertex vertexb=new Vertex();
        vertexb.setName("b");
        Vertex vertexc=new Vertex();
        vertexc.setName("c");
        Vertex vertexd=new Vertex();
        vertexd.setName("d");
        Vertex vertexe=new Vertex();
        vertexe.setName("e");
        Vertex vertexf=new Vertex();
        vertexf.setName("f");
        Vertex vertexg=new Vertex();
        vertexg.setName("g");
        Vertex vertexh=new Vertex();
        vertexh.setName("h");
        Vertex vertexi=new Vertex();
        vertexi.setName("i");

        graph.addAdjacencyVertex(vertexa, vertexb);
        graph.addAdjacencyVertex(vertexa, vertexc);
        graph.addAdjacencyVertex(vertexa, vertexd);
        graph.addAdjacencyVertex(vertexa, vertexe);

        graph.addAdjacencyVertex(vertexb, vertexf);

        graph.addAdjacencyVertex(vertexd, vertexg);

        graph.addAdjacencyVertex(vertexf, vertexh);

        graph.addAdjacencyVertex(vertexg, vertexi);




        graph.getVertexList().add(vertexa);
        graph.getVertexList().add(vertexb);
        graph.getVertexList().add(vertexc);
        graph.getVertexList().add(vertexd);
        graph.getVertexList().add(vertexe);
        graph.getVertexList().add(vertexf);
        graph.getVertexList().add(vertexg);
        graph.getVertexList().add(vertexh);
        graph.getVertexList().add(vertexi);

        //graph.deepFirstSearch(graph.getVertexList().get(0));

        graph.breadthFirthSearch(graph.getVertexList().get(0));
    }

}

package graph.direct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void shortestPath() {
        Vertex currentVertext = vertexList.get(0);
        currentVertext.setVisted(true);
        int picked = 0;
        while (picked < vertexList.size() - 1) {
            for (Edge edge : currentVertext.getEdgeLinkedList()) {
                Edge edgeWithSameDest = getEdgeByDest(edge.getDestVertex().getVisitEdges(), edge.getDestVertex());
                if (edgeWithSameDest == null) {
                    edge.getDestVertex().getVisitEdges().addAll(currentVertext.getVisitEdges());
                    edge.getDestVertex().getVisitEdges().add(edge);
                    edge.getDestVertex().setTotalWeight(currentVertext.getTotalWeight() + edge.getWeight());
                } else {
                    if (currentVertext.getTotalWeight() + edge.getWeight() < edge.getDestVertex().getTotalWeight()) {
                        edge.getDestVertex().getVisitEdges().clear();
                        edge.getDestVertex().getVisitEdges().addAll(currentVertext.getVisitEdges());
                        edge.getDestVertex().setTotalWeight(currentVertext.getTotalWeight());
                        edge.getDestVertex().getVisitEdges().add(edge);
                        edge.getDestVertex().setTotalWeight(edge.getDestVertex().getTotalWeight() + edge.getWeight());
                    }
                }
            }
            currentVertext = getMinTotalWeightVisitedVertex();
            currentVertext.setVisted(true);
            picked += 1;
        }

    }

    private Vertex getMinTotalWeightVisitedVertex() {
        return vertexList.stream().filter(vertex -> vertex.getTotalWeight() != 0 && !vertex.isVisted())
                .sorted((v1, v2) -> v1.getTotalWeight() - v2.getTotalWeight()).findFirst().get();


    }


    private Edge getEdgeByDest(List<Edge> priorityQueue, Vertex vertex) {
        return priorityQueue.stream().filter(e -> e.getDestVertex().equals(vertex)).findFirst().orElse(null);

    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    /**
     * Floyd
     * 任意两点的最短路径就是找出两点srcVertex,destVertex之间所有可能路径中的最小的那条路径
     * 找所有可能路径则考虑遍历以所有点为中间连接点
     * 以第一个点A为中间点时 srcVertex->desVertex的最短路径为
     *  min{srcVertex->destVertex,srcVertex->A->destVertex}
     * 以第二个点B为中间点时srcVertex->B的最短路径为
     *  min{srcVertex->B,srcVertex->A->B}
     * 以第三个点C为中间点时 srcVertex->C的最短路径为
     *  min{srcVertex->C,srcVertex->A->C,srcVertex->A->B->C}
     * ......
     *
     * 以第n个点为中间点时 srcVertex->destVertex的最短路径就选出了所有可能路径中最小的那条
     *
     */
    public void allPairShortestPath() {
        for (Vertex middleVertex : vertexList) {
            for (Vertex srcVertex : vertexList) {
                for (Vertex destVertex:vertexList) {
                    if (srcVertex.equals(middleVertex)) {
                        continue;
                    }
                    if (srcVertex.equals(destVertex)){
                        continue;
                    }
                    if (middleVertex.equals(destVertex)) {
                        continue;
                    }
                    VertexVisitPath srcToDestVertexVisitPath = getVertexVisitPathByDestVertex(srcVertex, destVertex);
                    boolean srcToDestVertexVistPathExist=true;
                    if (srcToDestVertexVisitPath==null){
                        srcToDestVertexVistPathExist=false;
                        srcToDestVertexVisitPath=new VertexVisitPath();
                    }
                    VertexVisitPath srcVertexConnectToMiddleVisitPath = getVertexVisitPathByDestVertex(srcVertex, middleVertex);
                    VertexVisitPath middleVertexConnectToDestVisitPath = getVertexVisitPathByDestVertex(middleVertex, destVertex);

                    if (srcVertexConnectToMiddleVisitPath != null && middleVertexConnectToDestVisitPath != null) {

                        if (srcToDestVertexVisitPath.getTotalWeight() > srcVertexConnectToMiddleVisitPath.getTotalWeight() + middleVertexConnectToDestVisitPath.getTotalWeight()) {
                            srcToDestVertexVisitPath.setTotalWeight(srcVertexConnectToMiddleVisitPath.getTotalWeight() + middleVertexConnectToDestVisitPath.getTotalWeight());
                            srcToDestVertexVisitPath.getPath().clear();
                            srcToDestVertexVisitPath.getPath().addAll(srcVertexConnectToMiddleVisitPath.getPath());
                            srcToDestVertexVisitPath.getPath().addAll(middleVertexConnectToDestVisitPath.getPath().subList(1, middleVertexConnectToDestVisitPath.getPath().size()));
                            srcToDestVertexVisitPath.setVertex(destVertex);
                            if (!srcToDestVertexVistPathExist){
                                srcVertex.getVertexVisitPathList().add(srcToDestVertexVisitPath);
                            }
                        }
                    }
                }
            }
        }
    }


    public void initVertexVisitPath() {
        for (Vertex srcVertex : getVertexList()) {
            for (Edge edge : srcVertex.getEdgeLinkedList()) {
                Vertex destVertex = edge.getDestVertex();
                VertexVisitPath srcToDestVertexVisitPath = new VertexVisitPath();
                    srcToDestVertexVisitPath.setPath(new ArrayList<>(Arrays.asList(srcVertex, destVertex)));
                    srcToDestVertexVisitPath.setTotalWeight(edge.getWeight());
                    srcToDestVertexVisitPath.setVertex(destVertex);
                    srcVertex.getVertexVisitPathList().add(srcToDestVertexVisitPath);
            }
        }
    }
    private VertexVisitPath getVertexVisitPathByDestVertex(Vertex src, Vertex dest) {
        for (VertexVisitPath vertexVisitPath : src.getVertexVisitPathList()) {
            if (vertexVisitPath.getVertex().equals(dest)) {
                return vertexVisitPath;
            }
        }
        return null;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
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
        graph.addAdjacencyVertex(a, d, 80);
        graph.addAdjacencyVertex(a, b, 50);
        graph.addAdjacencyVertex(b, d, 90);
        graph.addAdjacencyVertex(b, c, 60);
        graph.addAdjacencyVertex(e, b, 50);
        graph.addAdjacencyVertex(d, c, 20);
        graph.addAdjacencyVertex(d, e, 70);
        graph.addAdjacencyVertex(c, e, 40);
//        graph.shortestPath();
//        for (Vertex vertex : graph.getVertexList()) {
//            System.out.println(vertex);
//            System.out.println(vertex.getVisitEdges());
//            System.out.println("-------------");
//        }

        graph.initVertexVisitPath();
        graph.allPairShortestPath();
        for (Vertex vertex : graph.getVertexList()){
            System.out.println(vertex);
            vertex.getVertexVisitPathList().stream().forEach(System.out::println);
            System.out.println("-------------");
        }


    }
}

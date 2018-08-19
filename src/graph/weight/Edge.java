package graph.weight;

/**
 * Created by Max Chen on 2018/8/18.
 */
public class Edge {
    private Vertex srcVertex;
    private Vertex destVertex;
    private int weight;

    public Edge(Vertex srcVertex, Vertex destVertex, int weight) {
        this.srcVertex = srcVertex;
        this.destVertex = destVertex;
        this.weight = weight;
    }

    public Vertex getSrcVertex() {
        return srcVertex;
    }

    public void setSrcVertex(Vertex srcVertex) {
        this.srcVertex = srcVertex;
    }

    public Vertex getDestVertex() {
        return destVertex;
    }

    public void setDestVertex(Vertex destVertex) {
        this.destVertex = destVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (weight != edge.weight) return false;
        if (srcVertex != null ? !srcVertex.equals(edge.srcVertex) : edge.srcVertex != null) return false;
        return !(destVertex != null ? !destVertex.equals(edge.destVertex) : edge.destVertex != null);

    }

    @Override
    public int hashCode() {
        int result = srcVertex != null ? srcVertex.hashCode() : 0;
        result = 31 * result + (destVertex != null ? destVertex.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }
}

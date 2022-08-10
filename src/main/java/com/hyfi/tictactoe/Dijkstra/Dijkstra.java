package com.hyfi.tictactoe.Dijkstra;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/////////////////////////////////////////////////////////////////////

/**
 * The cost or weight of traveling between edges.
 * @param <Value> the value of the weight.
 */
class Distance<Value>
{
    Value value;
    Distance(Value value){ this.value = value; }

    /**
     * Represents the weight/cost/distance between two nodes.
     * @return the weight/cost/distance between two nodes.
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets the weight/cost/distance between two nodes.
     * @param value the weight/cost/distance between two nodes.
     */
    public void setValue(Object value) {
        this.value = (Value) value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Distance)) return false;
        final Distance<?> other = (Distance<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (!Objects.equals(this$value, other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Distance;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "{\"value\":" + this.getValue() + "}";
    }
}
/////////////////////////////////////////////////////////////////////
/**
 * The connections between nodes are called edges (sometimes called arcs).
 * In a weighted graph these edges have a weight (or cost) associated with
 * them. In a graph representing a map, the edges could represent a road or
 * rail connection between cities and the weight may represent the time to
 * travel between the cities. In a network graph the edges usually represent
 * the connections between devices such as Ethernet or fiber optic cables.
 * In this case the weight could represent the delay introduced by the connection.
 * @param <D>  the cost of weight of traveling to this node form some
 *                  source node
 * @param <N> the destination node
 */
class Edge<D extends Distance, N extends Node>
{
    private D distance;
    private N node;

    public Edge(){}

    public Edge(D distance, N node) {
        this.distance = distance;
        this.node = node;
    }

    public D getDistance() {
        return this.distance;
    }

    public N getNode() {
        return this.node;
    }


    public void setDistance(D distance) {
        this.distance = distance;
    }

    public void setNode(N node) {
        this.node = node;
    }

    public List<Edge<D, N>> getEdges(){
        return this.getNode().getEdges();
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Edge)) return false;
        final Edge<?, ?> other = (Edge<?, ?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$source = this.getDistance();
        final Object other$source = other.getDistance();
        if (!Objects.equals(this$source, other$source)) return false;
        final Object this$destination = this.getNode();
        final Object other$destination = other.getNode();
        if (!Objects.equals(this$destination, other$destination))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Edge;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $source = this.getDistance();
        result = result * PRIME + ($source == null ? 43 : $source.hashCode());
        final Object $destination = this.getNode();
        result = result * PRIME + ($destination == null ? 43 : $destination.hashCode());
        return result;
    }

    public String toString() {
        return "{\n\tdistance:" + this.getDistance().toString() +
                ",\n\tdestination:" + this.getNode().toString() +
                "\n}";
    }
}

/////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////

/**
 * Represents the node coordinates.
 * @param <Row> the row
 * @param <Column> the column
 */
class Coordinate<Row,Column> implements Serializable
{
    private Row row;
    private Column column;

    public Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public Row getRow() {
        return this.row;
    }

    public Column getColumn() {
        return this.column;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Coordinate)) return false;
        final Coordinate<?, ?> other = (Coordinate<?, ?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$row = this.getRow();
        final Object other$row = other.getRow();
        if (!Objects.equals(this$row, other$row)) return false;
        final Object this$column = this.getColumn();
        final Object other$column = other.getColumn();
        if (!Objects.equals(this$column, other$column)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Coordinate;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $row = this.getRow();
        result = result * PRIME + ($row == null ? 43 : $row.hashCode());
        final Object $column = this.getColumn();
        result = result * PRIME + ($column == null ? 43 : $column.hashCode());
        return result;
    }

    public String toString() {

        return "{" +
                "\n\t\"row\":" + this.getRow() +
                "\n\t\",\"column\":" + this.getColumn() +
                "\n}";
    }
}
/////////////////////////////////////////////////////////////////////
/**
 * Nodes (also called vertices) are represented in the diagram by labelled
 * coordinates/name/id. Each node will store data relevant to the scenario. For example,
 * if you use a graph to store data for a map, each node might represent a
 * city and could store the city name. If you use a graph to store data about
 * a local area network, each node might represent a network device, and could
 * store the IP address and physical location of the device.
 * @param <Data> the data stored in the node
 */
class Node<Data> {

    private Data name;

    private Data data;

    private Coordinate coordinate;

    private List<Node<?>> shortestPath = new LinkedList<>();

    private Distance<?> distance = new Distance<>(Integer.MAX_VALUE);

    private List<Edge<Distance<?>, Node<?>>> edges = new LinkedList<>();

    public Node(Data name){ this.name = name; }

    public Node(Data name, Data data)
    {
        this.name = name;
        this.data = data;
    }

    public Node(Data name, Data data, Coordinate coordinate)
    {
        this.name = name;
        this.data = data;
        this.coordinate = coordinate;
    }

    public void addAdjacentNode(Node<?> destination, Distance<?> distance) { this.edges.add(new Edge<>(distance,destination)); }

    public void setDistance(Distance<?> distance) { this.distance = distance; }

    public Distance<?> getDistance() { return this.distance; }

    public Data getName() {
        return this.name;
    }

    public Data getData() {
        return this.data;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public List<Node<?>> getShortestPath() {
        return this.shortestPath;
    }

    public List<Edge<Distance<?>, Node<?>>> getEdges() {
        return this.edges;
    }

    public void setName(Data name) {
        this.name = name;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setShortestPath(List<Node<?>> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setEdges(List<Edge<Distance<?>, Node<?>>> edges) {
        this.edges = edges;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Node)) return false;
        final Node<?> other = (Node<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (!Objects.equals(this$name, other$name)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (!Objects.equals(this$data, other$data)) return false;
        final Object this$coordinate = this.getCoordinate();
        final Object other$coordinate = other.getCoordinate();
        if (!Objects.equals(this$coordinate, other$coordinate))
            return false;
        final Object this$shortestPath = this.getShortestPath();
        final Object other$shortestPath = other.getShortestPath();
        if (!Objects.equals(this$shortestPath, other$shortestPath))
            return false;
        final Object this$distance = this.getDistance();
        final Object other$distance = other.getDistance();
        if (!Objects.equals(this$distance, other$distance)) return false;
        final Object this$edges = this.getEdges();
        final Object other$edges = other.getEdges();
        if (!Objects.equals(this$edges, other$edges)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Node;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final Object $coordinate = this.getCoordinate();
        result = result * PRIME + ($coordinate == null ? 43 : $coordinate.hashCode());
        final Object $shortestPath = this.getShortestPath();
        result = result * PRIME + ($shortestPath == null ? 43 : $shortestPath.hashCode());
        final Object $distance = this.getDistance();
        result = result * PRIME + ($distance == null ? 43 : $distance.hashCode());
        final Object $edges = this.getEdges().stream();
        result = result * PRIME + ($edges == null ? 43 : $edges.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\"name\":\"" + this.getName() +
                "\",\n\t\"data\":\"" + this.getData() +
                "\",\n\t\"coordinate\":" + this.getCoordinate() +
                ",\n\t\"shortestPath\":" + this.getShortestPath() +
                ", \n\t\"distance\":" + this.getDistance() +
                ", \n\t\"edges\":" + this.getEdges().stream().map(edge -> edge) +
                "}";
    }
}
/////////////////////////////////////////////////////////////////////
class Graph<Node> {

    private Set<Node> nodes =  new HashSet<>();

    private Map<Object, List<Node>> mapOfShortestPaths = new TreeMap<>();

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph(List<Node> nodes) {
        this.nodes.addAll(nodes);
    }

    public Graph(Map<Object, List<Node>> mapOfShortestPaths){ this.mapOfShortestPaths = mapOfShortestPaths; }

    public Graph() {}

    public void addNode(Node nodeA) { this.nodes.add(nodeA); }

    public Map<Object, List<Node>> getMapOfShortestPaths() { return mapOfShortestPaths; }

    public void setMapOfShortestPaths(Map<Object, List<Node>> mapOfShortestPaths) { this.mapOfShortestPaths = mapOfShortestPaths; }

    public Set<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Graph<?>)) return false;
        final Graph<?> other = (Graph<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nodes = this.getNodes();
        final Object other$nodes = other.getNodes();
        if (!Objects.equals(this$nodes, other$nodes)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Graph;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nodes = this.getNodes();
        result = result * PRIME + ($nodes == null ? 43 : $nodes.hashCode());
        return result;
    }

    public String toString() {
        return "\nGraph{" +
                "\n\tnodes:" + this.getNodes().stream().map(node -> node) +
                "\n\t}";
    }
}
/////////////////////////////////////////////////////////////////////
/**
 * Dijkstra’s algorithm has one motivation: to find the shortest paths
 * from a start node to all other nodes on the graph.
 *
 * The cost of a path that connects two nodes is calculated by adding
 * the weights of all the edges that belong to the path.
 *
 * The shortest path is the sequence of nodes, in the order they are
 * visited, which results in the minimum cost to travel between the
 * start and end node.
 *
 * When the algorithm has finished running, it produces a list that
 * holds the following information for each node:
 *  <ol>
 *      <li>The node label</li>
 *      <li>The cost of the shortest path to that node (from the start node)</li>
 *      <li>The label of the previous node in the path</li>
 *  </ol>
 * Using the information in this list you can backtrack through the
 * previous nodes back to the start node. This will give you the
 * shortest path (sequence of visited nodes) from the start node to
 * each node and the cost of each path. This can be seen in the
 * worked example that follows.
 * @since 2022-08-6
 */
public class Dijkstra {
    private static final Logger log = LoggerFactory.getLogger(Dijkstra.class);

    public Dijkstra(){}

    /**
     * <pre>
     * Calculates the shortest path from the start node to
     * destination node from a graph/digraph of size n.
     * ---------------------------------------------------------
     * Procedure Algorithm Dijkstra’sAlgorithm:
     *      Dijkstra(G,a,z):= shortest path to z
     *
     * {<strong>Below:</strong> G has vertices/edges from a to z,
     *  a => v<sub>0</sub>,v<sub>1</sub>,...,v<sub>n</sub> => z
     *  and lengths w(v<sub>i</sub>, v<sub>j</sub>)
     *  where w(v<sub>i</sub>, v<sub>j</sub>) = +Infinity
     *  if {v<sub>i</sub>, v<sub>j</sub>} is not an edge in G}
     *
     * Dijkstra(
     *        G: weighted connected graph/digraph with all weights
     *          positive,
     *        a: start node,
     *        z: destination node) : shortest path to z
     *
     *
     * {<strong>Below:</strong> Node labels/weights are now initialized so that the
     * label/weight of the start node/vertex a is 0 and all other
     * node labels/weights are set to  +Infinity, and S is the
     * empty set that represents the set of nodes in the shortest
     * path from a to z. Optionally: You can set a default value
     * of +Infinity to omit this step}
     *
     * for i := 1 to n;
     *      L(v<sub>i</sub>) := +Infinity;
     * L(a) := 0;
     * S := 0;
     *
     * {<strong>Below:</strong> while the destination node/vertex z is not part of
     * the shortest path set, get the node u with the minimum
     * weight and add set S }
     *
     * while z ∉ S;
     *      u := a {vertex not in S with L(u) minimal distance
     *          node/vertex}
     *      S := S ∪ {u} {add u to set S}
     *
     *      {<strong>Below:</strong> for all adjacent nodes/vertices not in
     *      shortest path set, compare the
     *      weights L(u) + w(u,v) < L(v)}
     *
     *      for all vertices v not in S;
     *          if L(u) + w(u, v) < L(v)
     *          then L(v) := L(u) + w(u, v);
     *          {this adds a vertex to S with minimal label and
     *          updates the labels of vertices not in S}
     *
     * {<strong>Below:</strong> this should return back up a list of nodes leading
     * to the root; the list represents the shortest path from
     * the start to destination node}
     * 
     * return L(z) {L(z) = length of a shortest path from a to z}
     * </pre>
     *
     * @param graph the graph or digraph
     * @param distance the distance of the node from origin/start node
     * @param startNode the start node
     * @param destinationNode the destination node
     * @return a sorted {@link List< Node >} representing the
     * shortest path from start node to destination node z.
     * @implSpec  {@link Node}
     */
    public static List<Node<?>> calculateShortestPath(
            Graph<Node<?>> graph,
            Integer distance,
            Node<?> startNode,
            Node<?> destinationNode){

        log.info("\nInitialization......\nStart Node: {}\n",startNode);

        // create two lists, undiscovered/unsettled nodes and discovered/settled nodes;
        // contains all unexplored nodes
        List<Edge<Distance<?>, Node<?>>> unsettledNodeEdges = new LinkedList<>();
        // contains the path explored to the destination node
        List<Node<?>> settledNodes = new LinkedList<>();

        // Initialization of all nodes with distance "infinite";
        graph.getNodes().forEach(
                gNode -> gNode.setDistance(new Distance<>(Integer.MAX_VALUE))
        );

        // initialization of the starting node with 0
        startNode.setDistance(new Distance<>(distance));

        // add the start node to unsettled node list
        unsettledNodeEdges.add(new Edge<>(new Distance<>(0),startNode));

        // do while destination node not found
        while (unsettledNodeEdges.size() > 0)
        {
            // get the node on with the smallest/minimum distance
            Edge<Distance<?>, Node<?>> nodeEdge = getMinimumDistanceNodeEdge(unsettledNodeEdges);

            // remove minimal node from the undiscovered frontier/unsettled nodes list
            unsettledNodeEdges.remove(nodeEdge);

            // for each neighbor adjacent node of the minimum distance node, where n has not yet been
            // removed from unsettled nodes do;
            for ( Edge<Distance<?>, Node<?>> evaluationNode : nodeEdge.getNode().getEdges())
            {
                // temp container for nodes discovered on the frontier aka settled nodes
                List<Node<?>> tempSettled = new LinkedList<>();

                // evaluate the next adjacent node
                // node adjacent to start node
                Node<?> adjacentNode = evaluationNode.getNode();

                // edge distance = dist[minimum dist node] + dist_between([minimum dist node] , [evaluation node])
                Integer edgeWeight = ((Integer) nodeEdge.getDistance().getValue()) +
                        ((Integer) evaluationNode.getDistance().getValue());

                log.info("{\n\tCurrent Minimal Node: {} \n\n\tAdjacent Node: {}" +
                                "\n\n\tDistance Between U & V: {} \n\n\tUnsettled Nodes: {} " +
                                "\n\n\tSettled Nodes: {} \n}",
                        nodeEdge, adjacentNode, edgeWeight, unsettledNodeEdges, settledNodes);

                // update adjacent node distance, if edgeDistance < adjacentNode distance;
                if (!settledNodes.contains(adjacentNode) && edgeWeight < (Integer) adjacentNode.getDistance().getValue())
                {
                    // update adjacentNode distance = edgeDistance;
                    adjacentNode.setDistance(new Distance<>(edgeWeight));

                    // add the adjacent node to the undiscovered frontier/unsettled nodes list for later processing
                    unsettledNodeEdges.add(new Edge<>(new Distance<>(edgeWeight),adjacentNode));

                    log.info("\nUpdated Adjacent Node: {}\n", evaluationNode);
                }
            }

            // our look back or back up; add previous node to discovered list of settled nodes;
            if ( !settledNodes.contains(nodeEdge.getNode()) ){ settledNodes.add(nodeEdge.getNode());}
            // terminate when we find our destination node
            if (nodeEdge.getNode().equals(destinationNode)) break;
        }
        log.info("\nReturned Settled Nodes: {}\n", settledNodes);

        return settledNodes;
    }

    /**
     * <pre>
     * Recursive implementation of Dijkstra Algorithm.
     * Calculates the shortest path from the start node to
     * destination node from a graph/digraph of size n.
     * ---------------------------------------------------------
     * Procedure Algorithm Dijkstra’sAlgorithm:
     *      Dijkstra(G,a,z):= shortest path to z
     *
     * {<strong>Below:</strong> G has vertices/edges from a to z,
     *  a => v<sub>0</sub>,v<sub>1</sub>,...,v<sub>n</sub> => z
     *  and lengths w(v<sub>i</sub>, v<sub>j</sub>)
     *  where w(v<sub>i</sub>, v<sub>j</sub>) = +Infinity
     *  if {v<sub>i</sub>, v<sub>j</sub>} is not an edge in G}
     *
     * Dijkstra(
     *        G: weighted connected graph/digraph with all weights
     *          positive,
     *        a: start node,
     *        z: destination node) : shortest path to z
     *
     *
     * {<strong>Below:</strong> Node labels/weights are now initialized so that the
     * label/weight of the start node/vertex a is 0 and all other
     * node labels/weights are set to  +Infinity, and S is the
     * empty set that represents the set of nodes in the shortest
     * path from a to z. Optionally: You can set a default value
     * of +Infinity to omit this step}
     *
     * for i := 1 to n;
     *      L(v<sub>i</sub>) := +Infinity;
     * L(a) := 0;
     * S := 0;
     *
     * {<strong>Below:</strong> while the destination node/vertex z is not part of
     * the shortest path set, get the node u with the minimum
     * weight and add set S }
     *
     * while z ∉ S; {here is where we recursively traverse down the tree of nodes}
     *      u := a {vertex not in S with L(u) minimal distance
     *          node/vertex}
     *      S := S ∪ {u} {add u to set S}
     *
     *      {<strong>Below:</strong> for all adjacent nodes/vertices not in
     *      shortest path set, compare the
     *      weights L(u) + w(u,v) < L(v)}
     *
     *      for all vertices v not in S;
     *          if L(u) + w(u, v) < L(v)
     *          then L(v) := L(u) + w(u, v);
     *          {this adds a vertex to S with minimal label and
     *          updates the labels of vertices not in S}
     *
     * {<strong>Below:</strong> this should return back up a list of nodes leading
     * to the root; the list represents the shortest path from
     * the start to destination node}
     *
     * return L(z) {L(z) = length of a shortest path from a to z}
     *</pre>
     * @param graph the graph or digraph
     * @param distance the distance of the node from origin/start node
     * @param startNode the start node
     * @param destinationNode the destination node
     * @return a sorted {@link List}<{@link Node}> representing the
     * shortest path from start node to destination node z.
     * @see Node
     */
    public static List<Node<?>> calculateShortestPathRecur(
            Graph<Node<?>> graph,
            Integer distance,
            Node<?> startNode,
            Node<?> destinationNode)
    {
        log.info("\nInitialization......\nStart Node: {}\n",startNode);

        // create two lists, undiscovered/unsettled nodes and discovered/settled nodes;
        // contains all unexplored nodes
        List<Edge<Distance<?>, Node<?>>> unsettledNodeEdges = new LinkedList<>();
        // contains the path explored to the destination node
        List<Node<?>> settledNodes = new LinkedList<>();
        // initialize all nodes distance to +Infinity;
        // and start node distance to 0
        if (distance == 0)
        {
            // Initialization of all nodes with distance "infinite";
            graph.getNodes().forEach(
                    gNode -> gNode.setDistance(new Distance<>(Integer.MAX_VALUE))
            );

            // initialization of the starting node with 0
            startNode.setDistance(new Distance<>(distance));
        }

        // add the start node to unsettled node list
        unsettledNodeEdges.add(new Edge<>(new Distance<>(distance),startNode));

        // get the node on with the smallest/minimum distance
        Edge<Distance<?>, Node<?>> nodeEdge = getMinimumDistanceNodeEdge(unsettledNodeEdges);

        // for each neighbor adjacent node of the minimum distance node, where n has not yet been
        // removed from unsettled nodes do;
        for (Edge<Distance<?>, Node<?>> evaluationNode : nodeEdge.getEdges())
        {

            // temp container for nodes discovered on the frontier aka settled nodes
            List<Node<?>> tempSettled = new LinkedList<>();

            // node adjacent to start node
            Node<?> adjacentNode = evaluationNode.getNode();

            // edge distance = dist[minimum dist node] + dist_between([minimum dist node] , [evaluation node])
            Integer edgeWeight = ((Integer) nodeEdge.getDistance().getValue()) +
                    ((Integer) evaluationNode.getDistance().getValue());

            // remove minimal node from the undiscovered frontier/unsettled nodes list
            unsettledNodeEdges.remove(nodeEdge);

            log.info("{\n\tCurrent Minimal Node: {} \n\n\tAdjacent Node: {}" +
                            "\n\n\tDistance Between U & V: {} \n\n\tUnsettled Nodes: {} " +
                            "\n\n\tSettled Nodes: {} \n}",
                    nodeEdge,adjacentNode,edgeWeight,unsettledNodeEdges,settledNodes);

            // update adjacent node distance, if edgeDistance < adjacentNode distance;
            if (edgeWeight < (Integer) adjacentNode.getDistance().getValue())
            {
                // update adjacentNode distance = edgeDistance;
                adjacentNode.setDistance(new Distance<>(edgeWeight));

                log.info("\nUpdated Adjacent Node: {}\n", evaluationNode);

                // add the adjacent node to the undiscovered frontier/unsettled nodes list for later processing
                unsettledNodeEdges.add(new Edge<>(new Distance<>(edgeWeight),adjacentNode));

                // if destination node not in discovered/settled node initiate recursive call
                if (!settledNodes.contains(destinationNode))
                {
                    // continue as long as we have undiscovered nodes on the frontier
                    // add unexplored node to settledNode list
                    tempSettled.addAll(calculateShortestPathRecur(
                            graph,
                            (Integer) adjacentNode.getDistance().getValue(),
                            adjacentNode,
                            destinationNode)
                    );
                }
            }
            // remove any undesired paths with accumulated distance greater than the
            // accumulated distance of the destination node
            if (tempSettled.contains(destinationNode))
            {
                // add the cleansed temp list to the settled node
                settledNodes.addAll(tempSettled);
            }
        }

        // our look back or back up; add previous node to discovered list of settled nodes;
        settledNodes.addAll(List.of(nodeEdge.getNode()));

        log.info("\nReturned Settled Nodes: {}\n",
                settledNodes);

        return settledNodes;
    }
    /**
     * Retrieve the edge with the minimum distance node.
     * @param nodes the list of node.
     * @return the node {@link Edge}<{@link Distance,Node}>with the lowest weight/cost edge.
     * @see Distance
     * @see Node
     */
    private static Edge<Distance<?>, Node<?>> getMinimumDistanceNodeEdge(List<Edge<Distance<?>, Node<?>>> nodes){
        Node<?> minimumDistanceNode = null;
        Distance<?> minimumDistance = new Distance<>(Integer.MAX_VALUE);
        // get the minimal distance node
        for (Edge<Distance<?>, Node<?>> n : nodes)
        {
            Integer nodeDistance = (Integer) n.getDistance().getValue();
            if( nodeDistance < (Integer) minimumDistance.getValue() )
            {
                minimumDistance = n.getDistance();
                minimumDistanceNode = n.getNode();
            }
        }
        log.info("\nGet Minimum Node: {} \nMinimum Distance: {} " +
                        "\nUnsettled Nodes: {} \n",
                minimumDistance, minimumDistanceNode, nodes );

        return new Edge<>(minimumDistance,minimumDistanceNode);
    }

    ////////////////////////    MAIN    ////////////////////////
    public static void main(String[] args)
    {

        Node<?> nodeA = new Node("A", "AA");
        Node<?> nodeB = new Node("B", "BB");
        Node<?> nodeC = new Node("C", "CC");
        Node<?> nodeD = new Node("D", "DD");
        Node<?> nodeE = new Node("E", "EE");
        Node<?> nodeF = new Node("F", "FF");
        Node<?> nodeG = new Node("G", "GG");
        Node<?> nodeH = new Node("H", "HH");
        Node<?> nodeI = new Node("I", "II");
        Node<?> nodeJ = new Node("J", "JJ");
        Node<?> nodeK = new Node("K", "KK");
        Node<?> nodeL = new Node("L", "LL");

        nodeA.addAdjacentNode(nodeB, new Distance<>(10));
        nodeA.addAdjacentNode(nodeC, new Distance<>(15));
        nodeB.addAdjacentNode(nodeD, new Distance<>(12));
        nodeB.addAdjacentNode(nodeC, new Distance<>(15));
        nodeC.addAdjacentNode(nodeA, new Distance<>(10));
        nodeD.addAdjacentNode(nodeA, new Distance<>(2));
        nodeD.addAdjacentNode(nodeG, new Distance<>(1));
        nodeF.addAdjacentNode(nodeE, new Distance<>(5));
        nodeF.addAdjacentNode(nodeI, new Distance<>(5));
        nodeG.addAdjacentNode(nodeD, new Distance<>(10));
        nodeG.addAdjacentNode(nodeJ, new Distance<>(15));
        nodeH.addAdjacentNode(nodeL, new Distance<>(12));
        nodeH.addAdjacentNode(nodeK, new Distance<>(15));
        nodeI.addAdjacentNode(nodeG, new Distance<>(10));
        nodeI.addAdjacentNode(nodeK, new Distance<>(10));
        nodeJ.addAdjacentNode(nodeH, new Distance<>(2));
        nodeJ.addAdjacentNode(nodeI, new Distance<>(4));
        nodeK.addAdjacentNode(nodeH, new Distance<>(1));
        nodeK.addAdjacentNode(nodeB, new Distance<>(1));
        nodeL.addAdjacentNode(nodeJ, new Distance<>(5));
        nodeL.addAdjacentNode(nodeD, new Distance<>(5));

        Graph<Node<?>> graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeG);
        graph.addNode(nodeH);
        graph.addNode(nodeI);
        graph.addNode(nodeJ);
        graph.addNode(nodeK);
        graph.addNode(nodeL);

        try {
            log.info("\nGraph Created...");

            Long startTime = System.currentTimeMillis();

            List<Node<?>> results0 = Dijkstra
                    .calculateShortestPathRecur(
                            graph,
                            0,
                            nodeA,
                            nodeK);

            Long results0EndTime = System.currentTimeMillis() - startTime;

            JSONArray shortestPath0 = new JSONArray(Arrays.deepToString(results0.toArray()));

            Thread.sleep(500L);

            startTime = System.currentTimeMillis();

            List<Node<?>> results1 = Dijkstra
                    .calculateShortestPath(
                            graph,
                            0,
                            nodeA,
                            nodeK);
            Long results1EndTime = System.currentTimeMillis() - startTime;

            JSONArray shortestPath1 = new JSONArray(Arrays.deepToString(results1.toArray()));

            log.info("\nRecursive Shortest Path 0: {} \nRuntime: {}\n", shortestPath0.toString(2), results0EndTime);

            log.info("\nBrute Force Shortest Path 1: {} \nRuntime: {}\n\n", shortestPath1.toString(2), results1EndTime);

            FileWriter fos0 = new FileWriter("docs/results0.json");
            fos0.write(shortestPath0.toString(2));
            fos0.close();
            FileWriter fos1 = new FileWriter("docs/results1.json");
            fos1.write(shortestPath1.toString(2));
            fos1.close();

            // verify the results
            String result;
            File file0 = new File("docs/results0.json");
            Scanner input0 = new Scanner(file0);
            log.info("\nRecursive Shortest Path 0: \n");
            while (input0.hasNextLine())
                System.out.printf("%s\n",input0.nextLine());

            File file1 = new File("docs/results1.json");
            Scanner input1 = new Scanner(file1);
            log.info("\nBrute Force Shortest Path 1: {} \n",input1);
            while (input1.hasNextLine())
                System.out.printf("%s\n",input1.nextLine());


        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

}
/////////////////////////////////////////////////////////////////////
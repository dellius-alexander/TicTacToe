package com.hyfi.tictactoe;
// Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Class imports
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

/*****************************************************************************************************************
 * Let the node at which we are starting be called the initial node. Let the distance of node Y be the
 * distance from the initial node to Y. Dijkstra's algorithm will assign some initial distance values
 * and will try to improve them step by step.
 *      1. [ INITIALIZE UNVISITED/UNSETTLED NODES CONTAINER MAPPING ]: Mark all nodes unvisited = . Create a set
 *          of all the unvisited nodes called the unvisited set.
 *
 *      2. [ INITIALIZE VISITED/SETTLED NODES CONTAINER MAPPING ]: Assign to every node a tentative distance value:
 *          set the initial it to zero for our initial node and to INFINITY (∞) for all other nodes.
 *          Set the initial node as current.
 *
 *      3. For the current node, consider all of its unvisited neighbours and calculate their tentative
 *          distances through the current node. Compare the newly calculated tentative distance to the current
 *          assigned value and assign the smaller one. For example, if the current node A is marked with a
 *          distance of 6, and the edge connecting it with a neighbour B has length 2, then the distance to B
 *          through A will be 6 + 2 = 8. If B was previously marked with a distance greater than 8 then change
 *          it to 8. Otherwise, the current value will be kept.
 *          [ MINIMUM_VALUE(S + D, D) ]
 *
 *      4. When we are done considering all of the unvisited neighbours of the current node, mark the
 *          current node as visited and remove it from the unvisited set. A visited node will never be
 *          checked again.
 *
 *      5. If the destination node has been marked visited (when planning a route between two specific nodes)
 *          or if the smallest tentative distance among the nodes in the unvisited set is infinity (when
 *          planning a complete traversal; occurs when there is no connection between the initial node and
 *          remaining unvisited nodes), then stop. The algorithm has finished.
 *
 *      6. Otherwise, select the unvisited node that is marked with the smallest tentative distance, set
 *          it as the new "current node", and go back to step 3.
 *
 *      #  When planning a route, it is actually not necessary to wait until the destination node is "visited"
 *          as above: the algorithm can stop once the destination node has the smallest tentative distance
 *          among all "unvisited" nodes (and thus could be selected as the next "current").
 ****************************************************************************************************************/
public class Dijkstra{

    private static final Logger log = LoggerFactory.getLogger(Dijkstra.class);  
    // Infinity
    private static final Integer INF = 99999;
    // Root node initial value
    private static final Integer ROOT = 0;
    // The graph
    private Integer[][] graph = null;
    // V = number of vertices
    private Integer V = null;
    private Integer count = null;
    //private Integer[][] settledNodes = null;
    //private Integer[][] unSettleNodes = null;
    private Hashtable<String, Integer[]> visited = null;
    private Hashtable<String, Integer[]> unvisited = null;
    //private Integer sourceNodeValue;
    //private Integer destNodeValue;
    //private Integer parentNode;


    // Default Constructor
    public Dijkstra(){}

    /**
     * Initializes our class attributes
     * @param graph
     */
    public Dijkstra(Integer[][] graph, Integer startNode){
        V = graph.length;
        count = V;
        this.graph = graph;
        unvisited = setUnvisitedNodes(graph);
        System.out.println("\n");
        System.out.println("Unvisited Nodes: \n");
        printHash_1D(unvisited);
        visited = initVisitedNodes(V,startNode);
        System.out.println("Visited Nodes: \n");
        printHash_1D(visited);

        String key = String.valueOf(startNode);
        //System.out.println(key);

        Dijkstra_Algorithm(key, startNode, visited, unvisited);

        //visited.replace("2", evaluateCurrentNodes(visited.get("2"),unvisited.get("2"),visited.get("2")[0]));
        //System.out.println(printNode(evaluateCurrentNodes(visited.get("2"),unvisited.get("2"),visited.get("2")[0])));
        //printNode(evaluateCurrentNodes(visited.get("1"),unvisited.get("0"),unvisited.get("0")[1]));


    }

    public void Dijkstra_Algorithm(String key, Integer startNode, Hashtable<String, Integer[]> visited, Hashtable<String, Integer[]> unvisited) {

        System.out.println(count);
        Enumeration ky = unvisited.keys();
        while (ky.hasMoreElements()) {
            if (count == 2)
                break;
            log.info(marker, format, arg);
            visited.replace(key, addUnvisitedNodeToVisitedNode(visited.get(key), unvisited.get(key), visited.get(key)[startNode]));
            unvisited.remove(key);
            Integer nextKey = findNextShortestPath(visited);
            String nextKeyStr = String.valueOf(nextKey);
            Dijkstra_Algorithm(nextKeyStr, nextKey, visited, unvisited);
            printHash_1D(visited);
            printHash_1D(unvisited);
            count--;
        }

        printHash_1D(visited);

    }

    /**
     * Find the shortest path and the next node to traverse
     * @param visited
     * @return
     */
    public Integer findNextShortestPath(Hashtable<String, Integer[]> visited)
    {
        Integer shortestPathNode = INF;
        Integer[] node = new Integer[V];

        for (int i = 0; i < V; i++) {
            String key = String.valueOf(i+1);
            node = visited.get(key);
            //log.info(key);
            for (int j = 0; j < V; j++) {
                if (shortestPathNode > node[j])
                {
                    shortestPathNode = i;
                }
                //log.info(node[j]+"");
            }
        }
        return  shortestPathNode;
    }
    /**
     * Print hashtable {String, Integer[]}
     * @param nodes  Param must be of Hashtable<String, Integer[]>
     */
    public void printHash_1D(Hashtable<String, Integer[]> nodes){
        String key = null;
        Enumeration ky = nodes.keys();
        while (ky.hasMoreElements())
        {
            key = ky.nextElement().toString();
            System.out.println(key + "  " +printNode(nodes.get(key)));
        }
    }
    /**
     * prints the edges of a given node
     * @param node
     */
    public String printNode(Integer[] node)
    {
        int V = node.length;
        int j = 0;
        String edges = "";
        edges += String.format("\n");
        for (int i = 0; i < V; i++) {
            edges += String.format("____%1d___", i+1);
        }
        edges += "\n";
        for ( j = 0; j < V; j++)
        {
            edges += String.format("| %-6d",node[j]);
        }
        edges += String.format("|\n");
        for (int i = 0; i < V; i++) {
            edges += String.format("────────");
        }
        edges += String.format("\n\n");
        return edges;
    }
    /**
     * captures all unsettled nodes
     * @param graph
     * @return
     */
    public Hashtable<String,Integer[]> setUnvisitedNodes(Integer[][] graph)
    {
        int V = graph.length;
        Hashtable<String,Integer[]> unVisited = new Hashtable<>();
        Integer[] node = null;
        for (int i = 0; i < graph.length; i++)
        {
            node = new Integer[V];
            for (int j = 0; j < graph.length; j++) {
                node[j] = graph[i][j];
            }
            unVisited.put(String.valueOf(i+1),node);
        }
        return unVisited;
    }
    /**
     * Initializes the start node to 0 and all other nodes to INFINITY
     * @param vertices
     * @param startNode
     * @return
     */
    public Hashtable<String,Integer[]> initVisitedNodes(Integer vertices, Integer startNode)
    {
        int V = vertices;
        int S = startNode -1;
        Hashtable<String,Integer[]> visited = new Hashtable<>();
        Integer[] node = null;
        for (int i = 0; i < V; i++)
        {
            node = new Integer[V];
            for (int j = 0; j < V; j++) {
                node[j] = (i==S && j==S ? ROOT:INF);
            }
            visited.put(String.valueOf(i+1),node);
        }
        return visited;
    }

    /**
     *
     * @param sourceNode
     * @param sourceElement
     * @param nodes
     * @return
     */
    public Integer getSourceNodeElementValue(String sourceNode, Integer sourceElement, Hashtable<String,Integer[]> nodes)
    {
        return nodes.get(sourceNode)[sourceElement];
    }
    /**
     *
     * @param visited
     * @param unvisited
     * @param sourceNode
     * @return
     */
    public Integer[] addUnsettleNode(Hashtable<String, Integer[]> visited, Hashtable<String, Integer[]> unvisited, String sourceNode)
    {
        // V = vertices
        Integer[] node = new Integer[V];
        for (int i = 0; i < V; i++) {


            node[i] = (unvisited.get(sourceNode)[i] + getSourceNodeElementValue(sourceNode,i,visited) <
            visited.get(sourceNode)[i] ? unvisited.get(sourceNode)[i] +
            getSourceNodeElementValue(sourceNode,i,visited): getSourceNodeElementValue(sourceNode,i,visited));
        }
        return node;
    }
    public Integer[] addUnvisitedNodeToVisitedNode(Integer[] settled, Integer[] unsettled, Integer sourceNodeValue){

        Integer[] node = new Integer[V];
        for (int i = 0; i < V; i++) {
            node[i] = (unsettled[i] < settled[i] ? unsettled[i]:settled[i]);
        }
        return node;
    }
    /**
     * Evaluates the elements of two nodes; it adds the distance/weight of the source node/element to the
     * distance/weight to the unvisited node/element and assigns the minimum value to the visited node/element
     * to find the shortest path of that node/element between parent and edge node
     * @param visited
     * @param unvisited
     * @param sourceNodeValue
     * @return
     */
    public Integer[] evaluateCurrentNodes(Integer[] visited, Integer[] unvisited, Integer sourceNodeValue)
    {
        int V = visited.length;
        //int S = startNode -1;
        Integer[] node = new Integer[V];
        for (int i = 0; i < V; i++) {
            node[i] = (unvisited[i]+sourceNodeValue < visited[i] ? unvisited[i]+sourceNodeValue : visited[i]);
        }
        return node;
    }

    public static void main(String[] args) {

                            // A , B , C , D
                            // 1 , 2 , 3 , 4
        Integer[][] graph = {{INF, 1, 7, 5},     // 1
                            {3, INF, INF, 3},    // 2
                            {7, INF, INF, 2},    // 3
                            {5, 3, 2, INF}};     // 4

                            // Test
        Dijkstra dj = new Dijkstra(graph,2);

    }

}
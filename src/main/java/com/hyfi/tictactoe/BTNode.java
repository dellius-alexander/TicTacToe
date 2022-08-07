package com.hyfi.tictactoe;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Binary Tree Node Class with Generic GameBoard
 * @param <GameBoard> type parameter GameBoard
 */
public class BTNode<GameBoard> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(BTNode.class);
    protected BTNode<GameBoard> left, right, rootNode; // the branching node to the left and right of this node
    protected GameBoard root; // the game board associated with this node
    protected Point point; // value assigned to node
    private int index = 0;
    private static int order = 0;

    /**
     * Binary Tree Node
     * @param board the game board
     */
    public BTNode(GameBoard board){
        this.rootNode = null;
        this.root = board;
        this.index = 1;
        this.setIndex(this.rootNode.size() + 1);
        this.left = null;
        this.right = null;
    }

    /**
     * Binary Tree Node
     * @param board the game board
     * @param index the index of this node
     */
    public BTNode(GameBoard board, int index){
        this.rootNode = null;
        this.root =  board;
        this.index = index;
        this.left = null;
        this.right = null;
    }

    /**
     * Binary Tree Node
     * @param rootNode the root node of this branching factor
     * @param board the game board
     * @param index the index of this node

     */
    public BTNode(BTNode<GameBoard> rootNode,GameBoard board, int index){
        this.rootNode = rootNode;
        this.root =  board;
        this.index = index;
        this.left = null;
        this.right = null;
    }

    /**
     * Binary Tree Node
     * @param index the index of this node
     */
    public BTNode(int index){
        this.rootNode = null;
        this.root = null;
        this.index = index;
        this.left = null;
        this.right = null;
    }

    /**
     * Binary Tree Node
     * @param board the game board
     * @param left the left node
     * @param right the right node
     * @param index the index of this node
     */
    public BTNode(GameBoard board, BTNode<GameBoard> left, BTNode<GameBoard> right, int index){
        this.rootNode = null;
        this.root = board;
        this.left = left;
        this.right = right;
        this.index = index;
        if (this.right != null){
            this.right.setHead(this.rootNode);
        }
        if (this.left != null){
            this.left.setHead(this.rootNode);
        }
    }

    /**
     * Get the point
     * @return the {@linkplain Point}
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Set the node point
     * @param point the {@linkplain Point}
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Get the head node of the vertex
     * @return a {@linkplain BTNode}
     */
    public BTNode<GameBoard> getHead() {
        return this.rootNode;
    }

    /**
     * Set the head node
     * @param root the root node
     */
    public void setHead(BTNode<GameBoard> root){
        this.rootNode = root;
    }

    /**
     * Get the game board
     * @return the {@linkplain GameBoard}
     */
    public GameBoard getBoard() {
        return root;
    }

    /**
     * Set the game board
     * @param board the game board
     */
    public void setBoard(GameBoard board){
        this.root = board;
    }

    /**
     * Set the left node
     * @param board the left node
     */
    public synchronized void setLeft(BTNode<GameBoard> board){
        this.left = board;
        this.left.setHead(this);
    }

    /**
     * Get the left node
     * @return the left {@linkplain BTNode}
     */
    public synchronized BTNode<GameBoard> getLeft(){
        return this.left;
    }

    /**
     * Set the right node
     * @param board the right node
     */
    public synchronized void setRight(BTNode<GameBoard> board){
        this.right = board;
        this.right.setHead(this);
    }

    /**
     * Get the right node
     * @return the {@linkplain BTNode} node
     */
    public synchronized BTNode<GameBoard> getRight(){
        return this.right;
    }

    /**
     * Set the node index
     * @param index the node index
     */
    public void setIndex(int index){
        this.index = index;
    }

    /**
     * Get the node index
     * @return the node index
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * Get the root node.
     * @return the root {@linkplain BTNode} node
     */
    public BTNode<GameBoard> getRootNode(){
        BTNode<GameBoard> temp = this;
        while (temp.getHead() != null){
//            System.out.printf("\nTemp Index: %s", temp.getIndex());
            temp = temp.getHead();
            if (temp.getIndex() == 0) break;
        }
        return temp;
    }

    /**
     * Get the terminal node
     * @return the {@linkplain BTNode} terminal node
     */
    public BTNode<GameBoard> getTerminalNode(){
        return getRootNode().getNode(getRootNode().size()-1);
    }

    /**
     * Searches for and retrieves the node, if it exists.
     * @param index the index of the node we are in search for.
     * @return the node or null node with index of -1
     */
    public BTNode<GameBoard> getNode(int index){
        return getNode(index, this);
    }

    /**
     * Searches for and retrieves the node, if it exists.
     * @param index the index of the node we are in search for.
     * @param node the current node being traversed.
     * @return {@linkplain BTNode} node
     */
    public BTNode<GameBoard> getNode(int index, BTNode<GameBoard> node){

        if (node != null){
            if (index == node.index) {
//                System.out.printf("\nFound Node: %s", node.index);
                return node;
            }
            // Recursively look in left subtree.
            if (contains(index, node.left)) {
                return getNode(index,node.left);
            }
            // Recursively look in right subtree.
            if (contains(index, node.right)) {
                return getNode(index,node.right);
            }
        }
        return new BTNode<>(-1);
    }

    /**
     * The amount of nodes the extends this node, current node inclusive.
     * @return numbers of nodes inclusive.
     */
    public int size(){
        int count =  size( this, 0);
//        System.out.printf("\nTotal Count: %s",count);
        return count;
    }

    /**
     * Traverses each node and counts how many nodes exist.
     * @param node root/head node
     * @param sum current sum of nodes or 0
     * @return the size of the tree
     */
    private synchronized int size(BTNode<?> node, int sum){
//        System.out.printf("\nCount: %s", count);
        // If the tree is empty do nothing
        if (node != null)
        {
            // increment node count
            sum++;
            // Traverse the left subtree
            sum = size(node.left,sum);
            // Traverse the right subtree
            sum = size(node.right, sum);
        }
        return sum;
    }

    /**
     * Checks to see if nodes has a child node with the following index.
     * @param index the index of the node we are searching for.
     * @return true if node exist, false otherwise.
     */
    public boolean contains(int index){
        return contains(index, this);
    }

    /**
     * Checks to see if nodes has a child node with the following index.
     * @param index the index of the nodde
     * @param node
     * @return
     */
    private synchronized boolean contains(int index, BTNode<?> node)
    {
        boolean exists = false;
        if (node != null){
            if (index == node.index) {
//                System.out.printf("\nFound Node: %s", node.index);
                return true;
            }
            // Recursively look in left subtree.
            if (contains(index, node.left)) exists = true;
            // Recursively look in right subtree.
            if (contains(index, node.right)) exists = true;
        }
        return exists;
    }
    /**
     * Process the data at the root node, traverse the
     * left subtree, and then traverse the right subtree.
     * @param node binary tree
     */
    public static void preOrder(BTNode<?> node) {
        // If the tree is empty do nothing
        if (node != null)
        {
            // Visit the node
//            System.out.printf("\nIndex: %s",node.index);
            // Traverse the left subtree
            preOrder(node.left);
            // Traverse the right subtree
            preOrder(node.right);
        }
    }
    /**
     * Traverse the left subtree, process the data at the
     * root node, and then traverse the right subtree.
     * @param node binary tree
     */
    public static void inOrder(BTNode<?> node) {
        // If the tree is empty do nothing
        if (node != null)
        {
            // Visit the node
//            System.out.printf("\nIndex: %s",node.index);
            // Traverse the left subtree
            inOrder(node.left);
            // Traverse the right subtree
            inOrder(node.right);
        }
    }
    /**
     * Traverses the left subtree, traverse the right subtree,
     * and then process the data at the root node.
     * @param node binary tree
     */
    public static void postOrder(BTNode<?> node) {
        // If the tree is empty do nothing
        if (node != null)
        {
            // Traverse the left subtree
            postOrder(node.left);
            // Traverse the right subtree
            postOrder(node.right);
            // Visit the node
//            System.out.printf("\nIndex: %s",node.index);
        }
    }



//    /**
//     * Main Driver
//     * @param args Command line arguments
//     * @throws CloneNotSupportedException
//     */
//    public static void main(String[] args) throws CloneNotSupportedException {
//        List<BTNode<Board>> nodeList = new ArrayList<>();
//        Object[][] gb6 = {
//                {"X","O","O"},
//                {"","O","X"},
//                {"O","","X"},
//        };
//        BTNode<Board> n6 = new BTNode<>(new Board(gb6),6);
//        Object[][] gb5 = {
//                {"X","O","O"},
//                {"","","X"},
//                {"O","","X"},
//        };
//        BTNode<Board> n5 = new BTNode<>(new Board(gb5),5);
//        Object[][] gb4 = {
//                {"","O","O"},
//                {"","","X"},
//                {"O","","X"},
//
//        };
////        GameBoardNode<Board> n4 = new GameBoardNode<>(new Board(gb4),n6,n5,4);
//        BTNode<Board> n4 = new BTNode<>(new Board(gb4),4);
//        n4.setLeft(n6);
//        n4.setRight(n5);
//        n6.setHead(n4);
//        n5.setHead(n4);
//        Object[][] gb3 = {
//                {"","O",""},
//                {"","","X"},
//                {"O","","X"},
//        };
//        BTNode<Board> n3 = new BTNode<>(new Board(gb3),3);
//        Object[][] gb2 = {
//                {"","O",""},
//                {"","","X"},
//                {"O","",""},
//        };
////        GameBoardNode<Board> n2 = new GameBoardNode<>(new Board(gb2), n4, n3,2);
//        BTNode<Board> n2 = new BTNode<>(new Board(gb2),2);
//        n2.setLeft(n4);
//        n2.setRight(n3);
//        n4.setHead(n2);
//        n3.setHead(n2);
//        Object[][] gb1 = {
//                {"","O",""},
//                {"","","X"},
//                {"","",""},
//        };
//        BTNode<Board> n1 = new BTNode<>(new Board(gb1),1);
//        Object[][] gb0 = {
//                {"","O",""},
//                {"","",""},
//                {"","",""},
//        };
////        GameBoardNode<Board> root = new GameBoardNode<>(new Board(gb0),n2,n1,0);
//        BTNode<Board> root = new BTNode<>(new Board(gb0),0);
//        root.setLeft(n2);
//        root.setRight(n1);
//        n2.setHead(root);
//        n1.setHead(root);
//        BTNode.inOrder(root);
//        System.out.printf("\nContains %s: %s", 6, root.contains(6));
//        System.out.printf("\nLength: %s", root.size());
//        System.out.printf("\nNode Search Result: %s",root.getNode(6).index);
//        // search for leaf node
//        BTNode<Board> terminalNode = n2.getTerminalNode();
//        System.out.printf("\nTerminal Node: ");
//        terminalNode.getBoard().displayBoard();
//        // search for root node
//        BTNode<Board> rootNode = n5.getRootNode();
//        System.out.printf("\nRoot Node: ");
//        rootNode.getBoard().displayBoard();
//
//    }
}

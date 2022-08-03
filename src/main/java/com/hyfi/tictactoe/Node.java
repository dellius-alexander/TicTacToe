package com.hyfi.tictactoe;

public class Node {
        int value; // Value to be stored
        Node left, right; // Left and right children

        Node(int val)
        {
            value = val; left = null; right = null;
        }
        Node(int val, Node left1, Node right1) {
            value = val;
            left = left1;
            right = right1;
        }
}
package org.example;

import java.awt.Point;
import java.util.ArrayList;

/**
 * The TwoDTree Class consists of an inner private TwoDTreeNode class and implementations of insert and search.
 * The class creates a Binary Search Tree that consists of Points. The Root compares using the Y and then each
 * level alternates which coordinate it checks. The insert method is how the comparisons will be implemented. There is
 * a boolean in each node that will determine this. If the boolean is false then we will be comparing with Y's.
 * Otherwise, we will be comparing with X's.
 *
 * @Author Mark Barsky
 * Class: CSCI-340
 * Date: 4/11/2023
 * Known Bugs: searchRange doesn't print the second array
 */
public class TwoDTree {

    //Global variable holding the root of the Tree
    private TwoDTreeNode root;
    //Global variable holding the boolean that is the comparison switch
    private boolean splitX;

    /**
     * Private Node class that contains the Point, left and right nodes, and the boolean switch
     */
    private class TwoDTreeNode {
        private Point key;

        private TwoDTreeNode left;
        private TwoDTreeNode right;
        private boolean splitX;

        /**
         * Constructor that initializes all the variables
         * @param key takes a Point as a parameter
         * @param splitX takes a boolean switch as a parameter
         */
        public TwoDTreeNode(Point key, boolean splitX) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.splitX = splitX;
        }

        /**
         *This insert method takes a Point as an argument. If the root is null then it will make a new Node.
         * The first check is to see if splitX is false. If so, then we are splitting the tree by comparing
         * the Y from the Point. New nodes are created as needed and added to the tree.
         * @param p Takes a point as an argument
         */
        public void insert(Point p) {
            if (root == null) {
                root = new TwoDTreeNode(p, splitX);
            } else if (!splitX) {
                if (p.getY() <= key.getY()) {
                    if (left == null) {
                        left = new TwoDTreeNode(p, splitX);
                    } else {
                        left.splitX = !splitX;
                        left.insert(p);

                    }
                }
                if (p.getY() > key.getY()) {
                    if (right == null) {
                        right = new TwoDTreeNode(p, splitX);
                    } else {
                        right.splitX = !splitX;
                        right.insert(p);

                    }
                }
            } else {
                if (p.getX() <= key.getX()) {
                    if (left == null) {
                        left = new TwoDTreeNode(p, splitX);
                    } else {
                        left.splitX = !splitX;
                        left.insert(p);
                    }
                }
                if (p.getX() > key.getX()) {
                    if (right == null) {
                        right = new TwoDTreeNode(p, splitX);
                    } else {
                        right.splitX = !splitX;
                        right.insert(p);
                    }
                }
            }
        }

        /**
         * This search method calls the helper method to do all the work and returns a boolean
         * @param p takes a Point as a parameter
         * @return returns true or false if the Point is found
         */
        public boolean search(Point p) {
            return search(root, p);
        }

        /**
         * Helper method to search for a point. It takes a node as a parameter and a point. It will traverse
         * the tree and check to see if the point being taken in matches any of the points in the tree.
         * @param node takes a Node as a parameter
         * @param p takes a Point as a parameter
         * @return returns true or false if the Point is found
         */
        private boolean search(TwoDTreeNode node, Point p) {
            if (node == null) {
                return false;
            }
            if (node.key.equals(p)) {
                return true;
            }
            if (node.splitX) {
                if (p.x <= node.key.x) {
                    return search(node.left, p);
                } else {
                    return search(node.right, p);
                }
            } else {
                if (p.y <= node.key.y) {
                    return search(node.left, p);
                } else {
                    return search(node.right, p);
                }
            }
        }

        /**
         * The searchRange method checks the Tree to find the points within a certain range. The range is determined
         * from the two Points that are taken in as an argument.
         * @param node takes a Node as a parameter
         * @param pointOne takes a Point as a parameter
         * @param pointTwo takes a Point as a parameter
         * @param pointRange takes an ArrayList as a parameter
         */
        private void searchRange(TwoDTreeNode node, Point pointOne, Point pointTwo, ArrayList<Point> pointRange) {
            if (node == null) {
                return;
            }
            if (node.key.x >= pointOne.x && node.key.x <= pointTwo.x && node.key.y >= pointOne.y && node.key.y <= pointTwo.y) {
                pointRange.add(node.key);
            }
            if (node.splitX) {
                if (node.key.x >= pointOne.x) {
                    searchRange(node.left, pointOne, pointTwo, pointRange);
                }
                if (node.key.x <= pointTwo.x) {
                    searchRange(node.right, pointOne, pointTwo, pointRange);
                }
            } else {
                if (node.key.y >= pointOne.y) {
                    searchRange(node.left, pointOne, pointTwo, pointRange);
                }
                if (node.key.y <= pointTwo.y) {
                    searchRange(node.right, pointOne, pointTwo, pointRange);
                }
            }
        }
    }


    /**
     * General Constructor
     */
    public TwoDTree() {
        root = null;
    }

    /**
     * Creates a new 2D tree and inserts all the points in the given list.
     * The points are inserted in the order they appear in the list.
     *
     * @param points The list of points to insert.
     */
    public TwoDTree(ArrayList<Point> points) {
        root = null;
        for (Point p : points) {
            insert(p);
        }
    }

    /**
     * Inserts a new point into the tree. The splitX boolean will determine if the node is inserted either in the
     * left or right tree.
     *
     * @param p The point to insert.
     */
    public void insert(Point p) {
        TwoDTreeNode newNode = new TwoDTreeNode(p, splitX);
        if (root == null) {
            root = newNode;
        } else {
            root.insert(p);
        }
    }

    /**
     * Searches the tree for the given point. Returns true if the point is found, false if it is not found. The search
     * is done by checking if the point taken in is equal to the current node or not. Then traversing in the appropriate
     * tree until it is found or reaches the end of the tree.
     *
     * @param p The point to search for.
     * @return True if the point is found, false otherwise.
     */
    public boolean search(Point p) {
        return root.search(p);
    }

    /**
     * searchRange will search the tree for all the points that fall into a range determined by the two points]
     * that are taken in as arguments. Those points are then put into an ArrayList. It then returns said list.
     * @param pointOne takes Point as a parameter
     * @param pointTwo takes a Point as a parameter
     * @return returns the ArrayList filled with points in range.
     */
    public ArrayList<Point> searchRange(Point pointOne, Point pointTwo){
        ArrayList<Point> pointRange = new ArrayList<>();
        root.searchRange(root, pointOne,pointTwo, pointRange);
        return pointRange;
    }
}
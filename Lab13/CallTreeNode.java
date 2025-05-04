// CSCI 1913 - Lab 13
// @author: Ashwin Kalyan

/**
 * This class represents a node in a call tree.
 * Each node contains a name and references to two child nodes.
 */
public class CallTreeNode {
    private String name;
    private CallTreeNode left;
    private CallTreeNode right;

    /**
     * Constructor to create a CallTreeNode with a name and no children.
     * @param name - the name of the node
     * @param first - the first child node
     * @param second - the second child node
     */
    public CallTreeNode(String name, CallTreeNode first, CallTreeNode second) {
        this.name = name;
        this.left = first;
        this.right = second;
    }

    public String getName() {
        return name;
    }

    public CallTreeNode getFirst() {
        return left;
    }

    public CallTreeNode getSecond() {
        return right;
    }

    public void setFirst(CallTreeNode first) {
        this.left = first;
    }

    public void setSecond(CallTreeNode second) {
        this.right = second;
    }

    /**
     * Checks if the node is a caller.
     * A caller is defined as a node that has at least one child.
     * @return - true if the node is a caller, false otherwise
     */
    public boolean isCaller() {
        return left != null || right != null;
    }
}

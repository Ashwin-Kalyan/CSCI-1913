// CSCI 1913 - Lab 13
// @author: Ashwin Kalyan

/**
 * This class represents a call tree structure.
 * Each node in the tree represents a person who can call two other people.
 * The binary tree is structured such that each node can have at most two children.
 */
public class CallTree {
    private CallTreeNode root;
    /**
     * Constructor to create a CallTree with a root node.
     * @param rootName - the name of the root node
     */
    public CallTree(String rootName) {
        this.root = new CallTreeNode(rootName, null, null);
    }

    /**
     * Recursively searches for a node with the given name in the call tree.
     * @param name - the name of the node to search for
     * @param node - the current node being examined
     * @return - the node with the given name, or null if not found
     */
    private CallTreeNode find(String name, CallTreeNode node) {
        if (node == null) return null;
        if (node.getName().equals(name)) return node;

        CallTreeNode foundNode = find(name, node.getFirst());
        if (foundNode != null) return foundNode;

        return find(name, node.getSecond());
    }

    private CallTreeNode find(String name) {
        return find(name, root);
    }

    public boolean inCallTree(String person) {
        return find(person) != null;
    }

    /**
     * Adds a new call to the call tree.
     * It checks if the person is already in the tree and if the first and second calls are valid.
     * @param person - the name of the person making the call
     * @param first - the name of the first person to call
     * @param second - the name of the second person to call
     * @return - true if the call was added successfully, false otherwise
     */
    public boolean shouldCall(String person, String first, String second) {
        CallTreeNode node = find(person);
        if (node == null || node.isCaller() || inCallTree(first) || inCallTree(second)) return false;

        node.setFirst(new CallTreeNode(first, null, null));
        node.setSecond(new CallTreeNode(second, null, null));

        return true;
    }

    /**
     * Retrieves the first call made by a person in the call tree.
     * @param name - the name of the person
     * @return - the name of the first person called, or null if not found
     */
    public String getFirstCall(String name) {
        CallTreeNode node = find(name);
        return (node == null || node.getFirst() == null) ? null : node.getFirst().getName();
    }

    /**
     * Retrieves the second call made by a person in the call tree.
     * @param name - the name of the person
     * @return - the name of the second person called, or null if not found
     */
    public String getSecondCall(String name) {
        CallTreeNode node = find(name);
        return (node == null || node.getSecond() == null) ? null : node.getSecond().getName();
    }

    public int getCallCount() {
        return getHeight(root);
    }

    /**
     * Calculates the height of the call tree.
     * Which is defined as the maximum number of edges from the root to a leaf node.
     * @param node - the current node being examined
     * @return - the height of the tree rooted at the given node
     */
    private int getHeight(CallTreeNode node) {
        if (node == null) return -1;
        if (!node.isCaller()) return 0;
        return Math.max(getHeight(node.getFirst()), getHeight(node.getSecond())) + 1;
    }
    
    public int terminalContactCount() {
        return terminalContactHelper(root);
    }

    /**
     * Recursively counts the number of terminal contacts in the call tree.
     * @param node - the current node being examined
     * @return - the number of terminal contacts in the subtree rooted at the given node
     */
    private int terminalContactHelper(CallTreeNode node) {
        if (node == null) return 0;
        if (!node.isCaller()) return 1;
        return terminalContactHelper(node.getFirst()) + terminalContactHelper(node.getSecond());
    }
}

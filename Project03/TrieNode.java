// CSCI 1913 - Project 3
// @author: Ashwin Kalyan

/**
 * TrieNode is a specialized node for the Trie data structure.
 * It contains a data field and an array of child nodes.
 * Each child node corresponds to a letter in the alphabet.
 */
public class TrieNode<T> {
    private T data;
    private TrieNode<T>[] children;

    /**
     * Constructs a new TrieNode with null data and an array of child nodes.
     */
    public TrieNode() {
        this.data = null;
        this.children = (TrieNode<T>[]) new TrieNode[26]; // 26 letters in alphabet
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the child node corresponding to the given letter.
     * If the child node does not exist, it creates a new one.
     * @param letter - the letter corresponding to the child node
     * @return - the child node corresponding to the letter
     */
    public TrieNode<T> getChild(char letter) {
        if (letter < 'a' || letter > 'z') return null;

        int index = letter - 'a';
        if (children[index] == null) children[index] = new TrieNode<>();
        return children[index];
    }

    /**
     * Returns the number of child nodes.
     * @return - the number of child nodes
     */
    public int getTreeSize() {
        int size = 1; // count this node
        for (TrieNode<T> child : children) {
            if (child != null) size += child.getTreeSize();
        }
        return size;
    }
}
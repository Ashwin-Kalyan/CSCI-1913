// CSCI 1913 - Project 3
// @author: Ashwin Kalyan

/**
 * Trie is a specialized, Tree-type datastructre for implementing the Map/Dictionary ADT.
 * This class provides methods to insert, retrieve, and manage the trie structure.
 */
public class Trie<T> {
    private TrieNode<T> root;

    /**
     * Constructs a new Trie with a root node.
     */
    public Trie() {
        this.root = new TrieNode<>();
    }

    /**
     * Returns the number of nodes in the trie.
     * @param word - the word to search for
     * @return - the number of nodes in the trie
     */
    private TrieNode<T> getNode(String word) {
        TrieNode<T> currentNode = root;
        for (char letter : word.toCharArray()) {
            currentNode = currentNode.getChild(letter);
            if (currentNode == null) return null;
        }
        return currentNode;
    }

    /**
     * Returns the number of nodes in the trie.
     * @param str - the string to search for
     * @return - the number of nodes in the trie
     */
    public T get(String str) {
        TrieNode<T> node = getNode(str);
        return (node != null) ? node.getData() : null;
    }

    /**
     * Inserts a new string into the trie.
     * If the string already exists, it updates the data associated with it.
     * @param str - the string to insert
     * @param data - the data to associate with the string
     * @return - the old data associated with the string, or null if it was not present
     */
    public T put(String str, T data) {
        TrieNode<T> node = getNode(str);
        if (node == null) {
            node = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                node = node.getChild(c);
            }
        }
        T oldData = node.getData();
        node.setData(data);
        return oldData;
    }
}

import java.util.ArrayList;

/*
 * Main method.
 */
class HelloWorld {
    public static void main(String[] args) {
        int[] testData = {12,11,90,82,7,9}; // Insert data to test here
        Tree tree = new Tree(testData);
        
        // Insert desired tree operations here.
        Node[] deepestNodes = tree.GetDeepestNodes();
        System.out.println("Deepest Nodes:");
        for (int i = 0; i < deepestNodes.length; i++) {
        	System.out.println("Value: " + deepestNodes[i].GetValue() + ", Depth: " + deepestNodes[i].GetDepth());
        }
        
        return;
    }
}

/*
 * Tree object represents a binary tree structure.
 * Initialize tree object with integer array to create binary tree from array.
 * NOTE: this is NOT a BALANCED binary search tree.
 * 		Test scenario below returns '4' for max depth.
 * 		Provided instructions suggest it should return '3'.
 * 		'3' would be correct solution for a balanced binary search tree.
 * 
 * Supported operations:
 * 		InsertNode
 * 		GetDeepestNodes
 * 		FindNode
 */
class Tree {
    private Node treeRoot;
    private ArrayList<Node> allNodes = new ArrayList<Node>();
    
    public Tree(int[] treeData) {
        this.treeRoot = null;
        for (int i = 0; i < treeData.length; i++) {
            InsertNode(treeData[i]);
        }
    }
    
    // Create a new Node in the tree for a given value.
    public void InsertNode(int value) {
        if (treeRoot == null) {
            treeRoot = new Node(value, 1);
            allNodes.add(treeRoot);
            return;
        }
        Node n = treeRoot.InsertNode(value, 2);
        if (n != null) allNodes.add(n);
        return;
    }
    
    // Get array of Nodes with the deepest depth in the tree.
    public Node[] GetDeepestNodes() {
        int deepestDepth = 0;
        ArrayList<Node> deepestNodes = new ArrayList<Node>();
        for (int i = 0; i < allNodes.size(); i++) {
            Node n = allNodes.get(i);
            int nodeDepth = n.GetDepth();
            if (nodeDepth > deepestDepth) {
                deepestNodes.clear();
                deepestDepth = nodeDepth;
                deepestNodes.add(n);
            } else if (nodeDepth == deepestDepth) {
                deepestNodes.add(n);
            }
        }
        return deepestNodes.toArray(new Node[deepestNodes.size()]);
    }
    
    // Search for Node object given search value.
    // Returns NULL if node does not exist.
    public Node FindNode(int value) {
        return FindNode(value, treeRoot);
    }
    
    private Node FindNode(int value, Node refNode) {
        if (refNode == null) return null;
        int refNodeValue = refNode.GetValue();
        if (refNodeValue == value) return refNode;
        if (refNodeValue < value) return FindNode(value, refNode.GetLessThanNode());
        return FindNode(value, refNode.GetGreaterThanNode());
    }
}

/*
 * Node object represents a node in a Tree object.
 * 
 * Supported operations:
 * 		InsertNode
 * 		GetValue
 * 		GetDepth
 * 		GetLessThanNode
 * 		GetGreaterThanNode
 */
class Node {
    
    private Node lessThanNode;
    private Node greaterThanNode;
    private int value;
    private int depth;
    
    public Node(int value, int depth) {
        this.value = value;
        this.depth = depth;
        lessThanNode = null;
        greaterThanNode = null;
    }
    
    // Create node lines for a given node value and node depth
    public Node InsertNode(int nValue, int nDepth) {
        Node n = null;
    	if (nValue < value) {
            if (lessThanNode == null) {
            	n = new Node(nValue, nDepth);
                lessThanNode = n;
            }
            else
                return lessThanNode.InsertNode(nValue, nDepth + 1);
        } else if (nValue > value) {
            if (greaterThanNode == null) {
            	n = new Node(nValue, nDepth);
                greaterThanNode = n;
            }
            else
                return greaterThanNode.InsertNode(nValue, nDepth + 1);
        }
        return n;
    }
    
    // Get node value (setter is private)
    public int GetValue() {
        return value;
    }
    
    // Get node depth (setter is private)
    public int GetDepth() {
        return depth;
    }
    
    // Get the node object with value less than current node (setter is private).
    // Returns NULL if none exists.
    public Node GetLessThanNode() {
        return lessThanNode;
    }
    
    // Get the node object with value greater than current node (setter is private).
    // Returns NULL if none exists.
    public Node GetGreaterThanNode() {
        return greaterThanNode;
    }
    
}
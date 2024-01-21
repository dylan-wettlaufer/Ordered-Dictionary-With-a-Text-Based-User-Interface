/* This class represents a BSTNode. Each node has an item, parent, left Child, and right Child */
public class BSTNode {

    /* variables to store the values of item, parent, left child and right child */
    private Record item;
    private BSTNode parent;
    private BSTNode leftChild;
    private BSTNode rightChild;

    /* constructor for BSTNode. item is set to the given item, the rest are null */
    public BSTNode(Record item) {
        this.item = item;
        parent = null;
        leftChild = null;
        rightChild = null;
    }

    /* Gets the record */
    public Record getRecord() {
        return item;
    }

    /* Sets the record */
    public void setRecord(Record d) {
        this.item = d;
    }

    /* Gets the left child */
    public BSTNode getLeftChild() {
        return leftChild;
    }

    /* gets the right child*/
    public BSTNode getRightChild() {
        return rightChild;
    }

    /* gets the parent */
    public BSTNode getParent() {
        return parent;
    }

    /* Sets the left child */
    public void setLeftChild(BSTNode u) {
        leftChild = u;
    }

    /* sets the right child */
    public void setRightChild(BSTNode u) {
        rightChild = u;
    }

    /* sets the parent */
    public void setParent(BSTNode u) {
        parent = u;
    }

    /* checks if the BSTNode is a leaf. it's a leaf if both of the nodes children are null */
    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }
}

/* This class represents a binary search tree. Each tree has a root node */
public class BinarySearchTree {

    /* the root of the tree */
    private BSTNode root;

    /* Constructor: initializes the root node */
    public BinarySearchTree() {
        root = new BSTNode(null);
    }

    /* gets the root */
    public BSTNode getRoot() {
        return root;
    }

    /* returns the BSTNode with the given key in the tree */
    public BSTNode get(BSTNode r, Key k) {
        if (r.isLeaf()) return r; //leaf nodes do not store values which means the key wasn't found
        else {
            if (r.getRecord().getKey().compareTo(k) == 0) return r;
            else if (r.getRecord().getKey().compareTo(k) == -1) return get(r.getRightChild(), k);
            else return get(r.getLeftChild(), k);
        }
    }

    /* inserts the given record into the tree */
    public void insert(BSTNode r, Record d) throws DictionaryException {
        BSTNode p = get(r, d.getKey());
        if (p.getRecord() != null && p.getRecord().getKey().compareTo(d.getKey()) == 0) throw new DictionaryException("A record with the given key (" + d.getKey().getLabel() + ", " + d.getKey().getType() +  ") is already in the dictionary");
        if (p.isLeaf()) { // if p is a leaf the record can be added to the dictionary
            p.setRecord(d); //update the record of the leaf
            p.setLeftChild(new BSTNode(null)); //create its children and set their parents to p
            p.setRightChild(new BSTNode(null));
            p.getLeftChild().setParent(p);
            p.getRightChild().setParent(p);
        }
    }

    /* remove the given key from the tree */
    public void remove (BSTNode r, Key k) throws DictionaryException {
        BSTNode child;
        BSTNode parent;
        BSTNode p = get(r, k);
        if (p.isLeaf()) throw new DictionaryException("No Record in the dictionary has key (" + k.getLabel() + ", " +  k.getType() + ")" );
        else {
            if (p.getRightChild().isLeaf() || p.getLeftChild().isLeaf()) { // checks if one of the node children is a leaf
                if (p.getLeftChild().isLeaf()) child = p.getRightChild(); // determines which child is the leaf
                else child = p.getLeftChild();
                parent = p.getParent();
                if (parent != null) { // if the parent isn't null then the paren of p's child is set to the other child of p
                    if (parent.getRightChild().equals(p)) { // checks to see which child is p
                        parent.setRightChild(child);
                        parent.getRightChild().setParent(parent);
                    }
                    else {
                        parent.setLeftChild(child);
                        parent.getLeftChild().setParent(parent);
                    }
                }
                else root = child;
                }
            else { //if it's not a leaf then the node is updated with the smallest keys record and the smallest key is removed
                BSTNode s = smallest(p.getRightChild());
                p.setRecord(s.getRecord());
                remove(s, s.getRecord().getKey());
            }
        }
    }
    /* returns the smallest node in the tree */
    public BSTNode smallest(BSTNode r) {
        if (r == null) return null;
        else {
            BSTNode p = r;
            while(!p.isLeaf()) { //loops until there are no more internal nodes
                p = p.getLeftChild();
            }
            return p.getParent(); // since p is a leaf the parent is returned
        }
    }
    /* return the largest node in the tree */
    public BSTNode largest(BSTNode r) {
        if (r == null) return null;
        else {
            BSTNode p = r;
            while(!p.isLeaf()) { // loops until there are no more internal nodes
                p = p.getRightChild();
            }
            return p.getParent(); //since p is a leaf return the parent
        }
    }

    /* returns the successor of the given key */
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode p = get(r, k);
        if (p.isLeaf()) return null; // if p is a leaf the given key is not in the tree
        if (!p.getRightChild().isLeaf()) return smallest(p.getRightChild()); // returns the smallest node in the right subtree
        else { // if the right subtree is a leaf then we go up the tree until we get a node that isn't equal to its parents right child
            BSTNode parent = p.getParent();
            while (parent != null && p == parent.getRightChild()) { // loops until null or until p isn't equal to it's parents righ child
                p = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    /* returns the predecessor of the given key in the tree */
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode p = get(r, k);
        if (p.isLeaf()) return null;
        if (!p.getLeftChild().isLeaf())  return largest(p.getLeftChild()); // returns the largest node in the left subtree
        else {
            BSTNode parent = p.getParent();
            while (parent != null && p == parent.getLeftChild()) { // loops until null or until p isn't equal to its parents left child
                p = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }



}

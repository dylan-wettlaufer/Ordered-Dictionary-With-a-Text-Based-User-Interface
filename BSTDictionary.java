/* This class represents an ordered dictionary. Each dictionary contains a tree */
public class BSTDictionary implements BSTDictionaryADT {

    private BinarySearchTree tree;

    /* initializes the tree */
    public BSTDictionary() {
        tree = new BinarySearchTree();
    }

    /* Returns the Record with key k, or null if the Record is not in the
dictionary. */
    @Override
    public Record get(Key k) {
        BSTNode node = tree.get(tree.getRoot(), k);
        if (node.isLeaf()) return null;
        else return node.getRecord();
    }

    /* Inserts d into the ordered dictionary. It throws a DictionaryException if a
Record with the same Key as d is already in the dictionary. */
    @Override
    public void put(Record d) throws DictionaryException {
        tree.insert(tree.getRoot(), d);
    }

    /* Removes the Record with Key k from the dictionary. It throws a
DictionaryException if the Record is not in the dictionary. */
    @Override
    public void remove(Key k) throws DictionaryException {
        tree.remove(tree.getRoot(), k);
    }

    /* Returns the successor of k (the Record from the ordered dictionary with
the smallest Key larger than k); it returns null if the given Key has no
successor. The given Key DOES NOT need to be in the dictionary. */
    @Override
    public Record successor(Key k) {
        BSTNode node = tree.successor(tree.getRoot(), k);
        if (node != null) return node.getRecord();
        else {
            node = successorHelper(tree.getRoot(), k);
            if (node != null) return node.getRecord();
            else return null;

        }
    }

    /* Returns the predecessor of k (the Record from the ordered dictionary with
the largest key smaller than k; it returns null if the given Key has no
predecessor. The given Key DOES NOT need to be in the dictionary. */
        @Override
        public Record predecessor (Key k) {
            BSTNode node = tree.predecessor(tree.getRoot(), k);
            if (node != null) return node.getRecord();
            else {
                node = predecessorHelper(tree.getRoot(), k);
                if (node != null) return node.getRecord();
                else return null;
            }
        }

    /* Returns the Record with the smallest key in the ordered dictionary. Returns
null if the dictionary is empty. */
        @Override
        public Record smallest () {
            return tree.smallest(tree.getRoot()).getRecord();
        }

        /* Returns the Record with the largest key in the ordered dictionary. Returns
null if the dictionary is empty. */
    @Override
        public Record largest () {
            return tree.largest(tree.getRoot()).getRecord();
        }

        /* finds the successor if the key is not in the dictionary */
        private BSTNode successorHelper (BSTNode r, Key k){
            if (k.compareTo(r.getRecord().getKey()) == -1) { // if k is less than r traverse the left side of th tree
                if (r.getLeftChild().isLeaf()) { // return the node if it's child is a leaf
                    return r;
                } else return successorHelper(r.getLeftChild(), k);
            } else { // runs if k is greater than r
                if (r.getRightChild().isLeaf()) { // if its leaf, traverse the tree upwards until there is a node greater than k
                    BSTNode p = r.getParent();
                    while (p != null && p.getRecord().getKey().compareTo(k) == -1) p = p.getParent();
                    return p;
                } else return successorHelper(r.getRightChild(), k);
            }
        }

        /* find the predecessor if the key isn't in the dictionary */
        private BSTNode predecessorHelper (BSTNode r, Key k) {
            if (k.compareTo(r.getRecord().getKey()) == -1) { //if k is less than r traverse the left side of the tree
                if (r.getLeftChild().isLeaf()) { // if its leaf, traverse the tree upwards until there is a node less than k
                    BSTNode p = r;
                    while (p != null && p.getRecord().getKey().compareTo(k) == 1) p = p.getParent();
                    return p;
                } else return predecessorHelper(r.getLeftChild(), k);
            } else { //runs if k is greater than r
                if (r.getRightChild().isLeaf()) { // if r's right child is a leaf return r if not call the next node
                    return r;
                } else return predecessorHelper(r.getRightChild(), k);
            }
        }
}

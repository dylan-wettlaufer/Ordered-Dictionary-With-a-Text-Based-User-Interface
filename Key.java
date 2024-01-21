/* This class represent a key, each key has a label and a type */
public class Key {

    /* label that contains a string */
    private String label;
    /* type that contains an int */
    private int type;

    /* Constructor for Key */
    public Key(String theLabel, int theType) {
        label = theLabel.toLowerCase();
        type = theType;
    }

    /* returns the label */
    public String getLabel() {
        return label;

    /* returns the type */
    }
    public int getType() {
        return type;
    }

    /* Compares two keys, returns 0 if they are equal, -1 one if this key is less than k, or 1 if this key is greater than k */
    public int compareTo(Key k) {
        if (this.label.equals(k.label) && this.type == k.type) return 0;
        else {
            int labelComparison = this.label.compareTo(k.label);
            if (labelComparison < 0 || (labelComparison == 0 && this.type < k.type)) return -1;
            else return 1;
        }
    }

}

/* The class represents a record. each record contains a key and data */
public class Record {

    /* the key of the record */
    private Key theKey;
    /* String that represents data */
    private String data;

    /* constructor for key */
    public Record(Key k, String theData) {
        theKey = k;
        data = theData;
    }
    /* getter for key */
    public Key getKey() {
        return theKey;
    }
    /* getter for data */
    public String getDataItem() {
        return data;
    }
}

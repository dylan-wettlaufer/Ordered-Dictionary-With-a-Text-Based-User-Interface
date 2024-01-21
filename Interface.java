import java.io.*;
/* This class represents an interface. Each interface contains a dictionary  */
public class Interface {

    BSTDictionary dictionary;

    /* Contractor: Initializes the dictionary and loads it with the data from the input file */
    public Interface(String inputFile) throws IOException, DictionaryException {
        dictionary = new BSTDictionary();
        loadDictionary(inputFile);
    }

    /* gets the dictionary */
    private BSTDictionary getDictionary() {
        return dictionary;
    }

    public static void main(String[] args) throws IOException, DictionaryException, MultimediaException {
        if (args.length != 1) System.out.println("Error opening file");
        else{
            String command;
            Key key;
            Record record;
            String label;
            Interface dictionary = new Interface(args[0]);
            StringReader keyboard = new StringReader();

            while (true) { // runs until the user types in exit
                command = keyboard.read("Enter next command: ");

                if (command.startsWith("define")) { // returns the definition of the word
                    label = command.substring(7).trim(); // splits up the string to get the label
                    key = new Key(label, 1);
                    record = dictionary.getDictionary().get(key); // gets the key

                    if (record != null) System.out.println(record.getDataItem()); // prints the data item of the record
                    else System.out.println("The word " + label + " is not in the ordered dictionary");
                }
                else if (command.startsWith("translate")) { //translates to french
                    label = command.substring(10).trim(); //splits up the string to get the label
                    key = new Key(label, 2);
                    record = dictionary.getDictionary().get(key);

                    if (record != null) System.out.println(record.getDataItem());
                    else System.out.println("There is no definition for the word " + label);
                }
                else if (command.startsWith("sound")) { // plays the given sound
                    label = command.substring(6).trim(); //trims the input to get the label
                    key = new Key(label, 3); //uses the label to create the key
                    record = dictionary.getDictionary().get(key);

                    if (record != null) {
                        try {
                            SoundPlayer player = new SoundPlayer();
                            player.play(record.getDataItem());
                        }
                        catch (MultimediaException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    else System.out.println("There is no sound file for " + label);
                }
                else if (command.startsWith("play")) { // plays the given music
                    label = command.substring(5).trim();
                    key = new Key(label, 4);
                    record = dictionary.getDictionary().get(key);

                    if (record != null) {
                        try {
                            SoundPlayer player = new SoundPlayer();
                            player.play(record.getDataItem());
                        } catch (MultimediaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else System.out.println("There is no music file for " + label);
                }
                else if (command.startsWith("say")) { // says the given voice line
                    label = command.substring(4).trim();
                    key = new Key(label, 5);
                    record = dictionary.getDictionary().get(key);

                    if (record != null) {
                        try {
                            SoundPlayer player = new SoundPlayer();
                            player.play(record.getDataItem());
                        }
                        catch (MultimediaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else System.out.println("There is no voice file for " + label);
                }
                else if (command.startsWith("show")) { // show the given image
                    label = command.substring(5).trim();
                    key = new Key(label, 6);
                    record = dictionary.getDictionary().get(key);

                    if (record != null) {
                        try {
                            PictureViewer picture = new PictureViewer();
                            picture.show(record.getDataItem());
                        }
                        catch (MultimediaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else System.out.println( "There is no image file for " + label);
                }
                else if (command.startsWith("animate")) { // animate the given image
                    label = command.substring(8).trim();
                    key = new Key(label, 7);
                    record = dictionary.getDictionary().get(key);

                    if (record != null) {
                        try {
                            PictureViewer picture = new PictureViewer();
                            picture.show(record.getDataItem());
                        }
                        catch (MultimediaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else System.out.println("There is no animated image file for " + label);
                }
                else if (command.startsWith("browse")) { // opens the given website
                    label = command.substring(7).trim();
                    key = new Key(label, 8);
                    record = dictionary.getDictionary().get(key);

                    if (record != null) {
                        try {
                            ShowHTML html = new ShowHTML();
                            html.show(record.getDataItem());
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else System.out.println("There is no webpage called " + label);
                }
                else if (command.startsWith("delete")) { // deletes the given key from the dictionary
                    String[] str = command.split(" ", 3); // splits up the input into an array
                    if (str.length == 3) {
                        key = new Key(str[1], Integer.parseInt(str[2]));
                        try {
                            dictionary.getDictionary().remove(key); // removes the key
                        }
                        catch (DictionaryException e) {
                            System.out.println(e);
                        }
                    }
                    else System.out.println("Invalid Input");
                }
                else if (command.startsWith("add")) { // adds the given record to the dictionary
                    String[] strParts = command.split(" ", 4); //splits up the input into an array
                    if (strParts.length == 4) {
                        key = new Key(strParts[1], Integer.parseInt(strParts[2]));
                        try {
                            dictionary.getDictionary().put(new Record(key, strParts[3])); //adds the record
                        }
                        catch (DictionaryException e) {
                            System.out.println(e);
                        }
                    }
                }
                else if (command.startsWith("list")) { // list all values starting with the given prefix
                    String prefix = command.substring(5).trim();
                    boolean prefixFound = false;
                    record = dictionary.getDictionary().smallest(); //returns the smallest
                    while (record != null) { // loops until null
                        if (record.getKey().getLabel().startsWith(prefix)) { // if the label starts with the prefix the label is printed
                            prefixFound = true;
                            System.out.print(record.getKey().getLabel() + " ");
                        }
                        record = dictionary.getDictionary().successor(record.getKey()); // gets the next record in the dictionary
                    }
                    System.out.print("\n");
                    if(!prefixFound) System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
                }
                else if (command.equals("first")) { // returns the first element in te dictionary
                    record = dictionary.getDictionary().smallest();
                    System.out.println(record.getKey().getLabel() +", " + record.getKey().getType() + ", " + record.getDataItem());
                }
                else if (command.equals("last")) { // returns the lst element in the dictionary
                    record = dictionary.getDictionary().largest();
                    System.out.println(record.getKey().getLabel() +", " + record.getKey().getType() + ", " + record.getDataItem());
                }
                else if (command.equals("exit")) break;
                else System.out.println("Invalid command");


            }
        }
    }

    /* load the dictionary with the data from the input file */
    private void loadDictionary(String inputFile) throws IOException, DictionaryException {
        BufferedReader in = new BufferedReader(new FileReader(inputFile)); // reads the input file
        String label;
        while ((label = in.readLine()) != null) { // sets label to the next line and loops until it isn't null
            String data = in.readLine();
            Record record = createRecord(label.toLowerCase(), data); // take the given label and data to creat the record
            dictionary.put(record); //puts the created record in the dictionary
        }
        in.close();
    }

    /* create the record with the given label, type, and data */
    private Record createRecord(String label, String typeData) {
        String data;
        int type;
        // checks for each case and sets data to the given number based on the type of data
        if (typeData.startsWith("-")) {
            type = 3;
            data = typeData.substring(1); // removes the - from the data
        }
        else if(typeData.startsWith("+")) {
            type = 4;
            data = typeData.substring(1); // removes + from the data
        }
        else if (typeData.startsWith("*")) {
            type = 5;
            data = typeData.substring(1); // removes * from the data
        }
        else if (typeData.startsWith("/")) {
            type = 2;
            data = typeData.substring(1);
        }
        else { // if data has a dot, split up the data into an array and check if th last index is equal to gif, jpg, html
            if (typeData.endsWith("gif")) type = 7;
            else if (typeData.endsWith("jpg")) type = 6;
            else if (typeData.endsWith("html")) type = 8;
            else type = 1; // this means the data is just a definition
            data = typeData;
        }

        Key key = new Key(label, type);
        return new Record(key, data); // creates the key and return the new record.
    }

}

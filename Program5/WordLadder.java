import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This program solves Word Ladder puzzles. The user will input a starting word and an ending word of the same length.
 * Then the output will be a word ladder. A word ladder consists of words where the starting word changes one letter
 * at a time to turn into the end word.
 *
 * Author: Mark Barsky
 * Class: CSCI 340
 * Date: 5/7/2023
 */

public class WordLadder {
    //Global variable holding the original List for words from text file
    private static ArrayList<String> wordlist;

    /**
     * This is the main method. It will ask for user inputs for starting word and ending word and then call
     * the methods within the class to complete the word ladder.
     *
     * @param args takes an argument for the text file you wish to read.
     * @throws FileNotFoundException Exception handling for no file found
     */

    public static void main(String[] args) throws FileNotFoundException {
        //Choose text file by using the argument
        String filename = args[0];
        //Start word and end word
        String start, end;
        //Scanner for user input
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the beginning word");
        start = in.next();
        System.out.println("Enter the ending word (must be same length as first word)");
        end = in.next();

        //readFile method call
        readFile(filename);
        //This linked list will contain the result of the breadthFirstSearch and contain the word ladder
        LinkedList<String> result = breadthFirstSearch(start, end, wordlist);
        if (result != null) {
            System.out.println("Word Ladder from " + start + " to " + end + " is:");
            for (int i = 0; i < result.size(); i++) {
                if(Objects.equals(result.get(i), result.getLast())){
                    System.out.print(result.get(i));
                }
                else {
                    System.out.print(result.get(i) + "->");
                }
            }
        }
        else{
            System.out.println("No word ladder found from " + start + " to " + end);
        }
    }

    /**
     * This method will read the text file to fill in words and add it to the HashMap.
     * @param filename takes the filename a parameter
     * @throws FileNotFoundException if no file found the exception is handled
     */
    private static void readFile(String filename) throws FileNotFoundException {
        wordlist = new ArrayList<>();

        //Text File
        File text = new File(filename);

        //Try-Catch block to try and read the file
        try {
            //Scanner to read text file
            Scanner reader = new Scanner(text);

            //Loop to loop through the text file. If the word is not in the HashMap then add it
            while(reader.hasNext()){
                String word = reader.next();
                if(!wordlist.contains(word)){
                    wordlist.add(word);
                }
            }
        }
        catch (FileNotFoundException e){
            throw  new FileNotFoundException("no such file");
        }
    }

    /**
     * This method performs a breadthFirstSearch to find the shortest word ladder from the given List. It will then
     * create a new HashMap that holds a word and a LinkedList as its value. The LinkedList will contain
     * the patterns from the Key. Replacing each letter with a "_". The ket and value pairs are then added to the
     * HashMap. To perform the search a Queued LinkedList is created and a new parent HashMap. The queue starts with the
     * starting word and puts it into the new parent HashMap. A loop is then done that is similar to the graph building
     * loop. It will take the patterns of the start word and then create a new LinkedList that contains all the words
     * that have that pattern. Then  it will loop through the new LinkedList and if the parent Map does not contain
     * the Key (which is an entry in the LinkedList) it will add it to the Map and its value is the starting word. The
     * last step is to create a new LinkedList that will hold the resulting word ladder. This is done by taking the
     * end word and adding its value (which is a word that contains a similar pattern. Meaning the words are one letter
     * apart). Then the String variable is updated to have the new word and its value its checked. At the end of it
     * the word ladder is complete.
     * @param startWord starting word for word ladder
     * @param endWord ending word for word ladder
     * @param wordlist linkedlist that contains all words from text file
     * @return returns the word ladder
     */
    public static LinkedList<String> breadthFirstSearch(String startWord, String endWord,
                                                            ArrayList<String> wordlist) {
        //HashMap for graph representation
        HashMap<String, LinkedList<String>> wordGraph = new HashMap<>();

        // Builds the word graph
        for (String word : wordlist) {
            for (int i = 0; i < word.length(); i++) {
                //Attains patterns from words by replacing each letter with a "_" one letter at a time
                String pattern = word.substring(0, i) + "_" + word.substring(i + 1);
                //Initialize a new LinkedList
                LinkedList<String> words = wordGraph.getOrDefault(pattern, new LinkedList<>());
                //Add the pattern to LinkedList
                words.add(word);
                //Add the pattern as the key and the word as the value to the HasMap
                wordGraph.put(pattern,words);
            }
        }

        //Queued LinkedList is created to start the search
        Queue<String> queue = new LinkedList<>();

        //New HashMap is made to be a parent Map to compare neighboring words
        HashMap<String, String> parentMap = new HashMap<>();

        //Start queue with user chosen starting word
        queue.offer(startWord);

        //Put that word into the parent Map
        parentMap.put(startWord, null);

        //Loops to attain patterns from the queued words and gets all words with the same pattern
        while (!queue.isEmpty()) {
            String currWord = queue.poll();
            for (int i = 0; i < currWord.length(); i++) {
                //Attains patterns from words by replacing each letter with a "_" one letter at a time
                String pattern = currWord.substring(0, i) + "_" + currWord.substring(i + 1);
                //Initializes a new LinkedList that holds all words containing the given pattern
                LinkedList<String> neighborWords = wordGraph.getOrDefault(pattern, new LinkedList<>());
                //Loops and checks to see if the parent Map contains the key from the neighbor Map
                for (String neighborWord : neighborWords) {
                    if (!parentMap.containsKey(neighborWord)) {
                        //Puts word in queue
                        queue.offer(neighborWord);
                        //Adds the neighboring word with the current word to the parent Map
                        parentMap.put(neighborWord, currWord);
                    }
                }
            }
        }

        // Loop to make word ladder
        if (parentMap.containsKey(endWord)) {
            //Initializes new LinkedList
            LinkedList<String> ladder = new LinkedList<>();
            String word = endWord;
            //Loop to go through all words and gets their values. The values are neighboring words and build the ladder
            while (word != null) {
                ladder.add(0, word);
                word = parentMap.get(word);
            }
            return ladder;
        } else {
            return null;
        }
    }
}

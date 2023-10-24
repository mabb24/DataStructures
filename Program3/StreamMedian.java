import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The StreamMedian class will sort Integers by putting them inside 2 priority queues. After doing so
 * we are able to figure out the median of the Integers. One queue is a min heap and the other is a max heap.
 * smaller holds the lesser half of integers and allows 1 extra integer that bigger. bigger holds
 * the larger half of integers.
 *
 * Name: Mark Barsky
 * Class:CSCI 340-002
 * Date:3/17/2023
 */
public class StreamMedian {
    //Global variable holding the max heap
    private static PriorityQueue<Integer> smaller;
    //GLobal variable holding the min heap
    private static PriorityQueue<Integer> bigger;

    /**
     * Constructor which initializes the global variables.
     */
    public StreamMedian() {
        //min heap
        bigger = new PriorityQueue<>();
        //max heap which implements Comparator to reverse natural order
        smaller = new PriorityQueue<>(Comparator.reverseOrder());
    }

    /**
     * The insert method inserts an Integer into the correct heap
     * @param i takes an Integer as a parameter to be inserted
     */
    public void insert(Integer i) {
        //If both heaps are empty insert into smaller
        if (smaller.isEmpty() && bigger.isEmpty())  {
            smaller.add(i);
        }
        //If smaller is less or equal to the size of bigger of the max Integer in smaller is less than
        //Integer to be inserted, add to smaller
        else if(smaller.size() <= (bigger.size()+1) || smaller.peek()<=i){
            smaller.add(i);
        }

        //If smaller's size is 2 larger than bigger than take largest Integer in smaller and add to bigger
        if(smaller.size()>bigger.size()+1){
            bigger.add(smaller.poll());
        }

        //If bigger isnt empty check to see if smaller's largest Integer is larger than bigger smallest Integer
        // If so then swap
        if(!bigger.isEmpty()){
            if(smaller.peek()>bigger.peek()){
                smaller.add(bigger.poll());
                bigger.add(smaller.poll());
            }
        }

    }

    /**
     * The getMedian method finds the median and returns it
     * @return returns the median of the stream of Integers
     */
    public double getMedian() {
        //Try-catch block to get median. Possibility of null value using peek()
        try {
            double median;
            if (smaller.size() == bigger.size()) {
                median = (smaller.peek() + bigger.peek()) / 2.0;
            }
            else {
                median = smaller.peek();
            }
            return median;
        }
        catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }
}

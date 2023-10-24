package org.example;

/**
 * This class has methods that partition and array and then find the
 * median of the array. findMedian will call the median() method to do this
 * while the within the median method the array gets partitioned and
 * important indexes will be saved.
 *
 * Name: Mark Barsky
 * Class: CSCI 340
 * Date: 2/24/23
 */
public class FindMedian {
    //global variable that holds a position of an array
    private static int positionOne;
    //global variable that holds another or same position of an array
    private static int positionTwo;

    /**
     * This method finds the median. It takes an array as a parameter.
     * This method instantiates the global variables with -1, thus holding
     * a position just before the start of an array. It checks to see if the
     * array holds an even or odd amount of values and calls the median() method. The answer(or median)
     * is saved from the global variables.
     * @param array array of integers
     * @return returns the median
     */
    public static double findMedian(int[] array){
        //array length
        int length = array.length;
        //variable that will hold the median
        int answer;

        positionOne = -1;
        positionTwo = -1;

        //If array length is odd
        if(length%2==1){
            median(array,0,length-1,length/2);
            answer = positionTwo;
        }
        //If array length is even
        else {
            median(array,0,length -1, length/2);
            answer = (positionOne + positionTwo) / 2;
        }
        return answer;
    }

    /**
     * The median() method finds the median and saves the index of it accordingly.
     * It calls upon itself recursively until the array is partitioned and in case of
     * nothing being returned or the program not working properly it will, by default, return
     * Inter.MIN_VALUE
     * @param array array of ints
     * @param first first index
     * @param next last index
     * @param key the pivot point
     * @return recursive call on itself until partitioned entirely.
     */
    public static int median(int [] array, int first, int next, int key){
        if(first<=next){
            //Random pivot and receives the index of the pivot
            int partIndex = randomPart(array,first,next);

            //If index is equal to key then save it to global variable. the array is then odd
            if(partIndex == key){
                positionTwo = array[partIndex];
                if(positionOne != -1)
                    return Integer.MIN_VALUE;

            }
            //else both global variables are the middle. array is even
            else if(partIndex == key - 1){
                positionOne = array[partIndex];
                if(positionTwo != -1)
                    return Integer.MIN_VALUE;

            }
            //get the index from the first half of the partitioned array
            if(partIndex >= key){
                return median(array,first,partIndex - 1,key);
            }
            //get the index from the second half of the partitioned array
            else{
                return median(array,partIndex + 1, next, key);
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * This method moves array indexes around as needed.
     * @param array array of ints
     * @param first first index needed to be moved
     * @param next second index needed to be moved
     * @return returns the array with the moved around indexes
     */
    public static int [] move(int[] array, int first, int next){
        int temp = array[first];
        array[first] = array[next];
        array[next] = temp;
        return array;
    }

    /**
     * This method partitions an array so that the indexes of the array are less than or equal
     * to a pivot on the left hand side of it and greater than the pivot on the right hand side.
     * @param array array of ints
     * @param first starting place
     * @param next  either last position or random pivot
     * @return returns an index
     */
    public static int partition(int [] array, int first, int next){
        //Pivot point
        int last = array[next];
        //Holding variable for an index
        int indexOne = first;
        //Holding variable for an index
        int indexTwo = first;

        //Loop to check the array
        while(indexTwo < next){
            //If the value of the index is less than the pivot move it and increment the other variable
            if(array[indexTwo]<last){
                array = move(array,indexOne,indexTwo);
                indexOne++;
            }
            indexTwo++;
        }
        //After all incrementing is done move array with the final index
        array = move(array,indexOne,next);
        return indexOne;
    }

    /**
     * This method calls partition but uses a random value for the pivot.
     * @param array array of ints
     * @param first first index
     * @param next last index or pivot
     * @return return the call for parition() which is an index
     */
    public static int randomPart(int [] array, int first, int next){
        int pivot = next - first +1;
        int randomPivot = (int) (Math.random() % pivot);
        array = move(array,first + randomPivot, next);
        return partition(array,first,next);
    }

}


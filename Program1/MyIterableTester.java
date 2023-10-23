package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is meant to read in a file and take the contents of that file and put it into an appropriate ArrayList.
 * From there, that List will then utilize the MyIterable class to create a MyIterable object. The constructor of this
 * object will take the given array (any type) and reverse the order of the contents. The info will then be printed.
 *
 * Name: Mark Barsky
 * Class: CSCI 340-002
 * Date:2/14/2023
 */

public class MyIterableTester{
    //Global variable holding the scanner
    private static Scanner input;
    //Global variable for the ArrayList holding Strings
    private static ArrayList<String> stringArray;
    //Global variable for the ArrayList holding Integers
    private static ArrayList<Integer> integerArray;
    //Global variable for the ArrayList holding Doubles
    private static ArrayList<Double> doubleArray;

    /**
     * The main method takes in a commandline argument for the filename and then calls the ReadFile method.
     * After the method runs, each ArrayList should be filled. 3 new MyIterable class variables are made
     * for each ArrayList. MyIterable takes an Array as a parameter so the ArrayLists are changed to an Array.
     * In MyIterables constructor the Array is turned into a List and the order is reversed. The main method then
     * iterates through each returned List with a for-each loop and prints the contents.
     * @param args Commandline argument
     */
    public static void main(String[] args) {
        //Commandline argument for the filename
        String fileName = args[0];
        //ReadFile Method call
        ReadFile(fileName);
        //MyIterable class variablefor the Double ArrayList
        MyIterable <Double> doub = new MyIterable<>(doubleArray.toArray(new Double[0]));
        //MyIterable class variable for the Integer ArrayList
        MyIterable <Integer> num = new MyIterable<>(integerArray.toArray(new Integer[0]));
        //MyIterable class variable for the String ArrayList
        MyIterable <String> words = new MyIterable<>(stringArray.toArray(new String[0]));

        //Iteration by for-each loop for Strings
        System.out.println("My Friends:");
        for(String letters:words){
            System.out.print(letters + " ");
        }
        System.out.println();
        System.out.println("----------+---------");

        //Iteration by for-each loop for Integers
        System.out.println("My Numbers:");
        for(Integer numbers:num){
            System.out.print(numbers + " ");
        }
        System.out.println();
        System.out.println("----------+---------");

        //Iteration by for-each loop for Doubles
        System.out.println("My Scores:");
        for(Double decimals:doub){
            System.out.print(decimals + " ");
        }

    }

    /**
     * The ReadFile method takes a String parameter that is the name of the file that needs to be read.
     * The 3 global variables for each ArrayList are initialized and then a try-catch block will read the file
     * and put the appropriate inputs into the appropriate ArrayList.
     * @param file Takes a String parameter for the filename
     */
    public static void ReadFile(String file){
        //Creates a File from the parameter String
        File txt = new File(file);

        //Initialize ArrayLists
        stringArray = new ArrayList<>();
        doubleArray = new ArrayList<>();
        integerArray = new ArrayList<>();

        //Try-catch block to try and read the file
        try{
            input = new Scanner(txt);

            //While loop to add appropriate contents to appropriate ArrayList
            while(input.hasNext()) {
                if (input.hasNextInt()) {
                    int number = input.nextInt();
                    integerArray.add(number);
                } else if (input.hasNextDouble()) {
                    double decimal = input.nextDouble();
                    doubleArray.add(decimal);
                } else {
                    String word = input.next();
                    stringArray.add(word);
                }
            }

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }
    }
}

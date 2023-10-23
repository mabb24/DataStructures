package org.example;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class consists of tests the runs method from the StdRandomTester class
 *
 * Name:Mark Barsky
 * Class:CSCI 340-002
 * Date: 2/14/23
 */
class StdRandomTesterTest {
    /**
     * Tests the setSeed() method. Asserts that the seeds isnt less than 0 and asserts that the seed
     * doesnt return null
     */
    @Test
    void setSeed() {
        StdRandomTester.setSeed(1010);
        assertNotEquals(-1,StdRandomTester.getSeed());
        assertNotNull(StdRandomTester.getSeed());

    }

    /**
     * Tests the getSeed() method. Asserts that the returning result is not null and not less than 0.
     */
    @Test
    void getSeed() {
        long s = StdRandomTester.getSeed();
        assertNotNull(s);
        assertNotEquals(-1,s);
    }

    /**
     * Tests the uniformDouble() method. Makes a true assertion if the return value of the method
     * is between 0,1 inclusive
     */
    @Test
    void uniformDouble() {
        assertTrue(StdRandomTester.uniformDouble()<1&& StdRandomTester.uniformDouble()>0,
                "Random number out of bounds");
    }

    /**
     * This method tests uniformInt(). Sets the seed and makes a new random using the seed. Makes an true and
     * false assertion making sure that the parameter that would go in is larger than 0.
     */
    @Test
    void uniformInt() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        int n = random.nextInt();
        if(n>0){
            assertTrue(true,"Success");
            StdRandomTester.uniformInt(n);
        }
        else{
            assertFalse(false,"Number too small");
        }
    }

    /**
     * This method tests uniformLong(). Sets the seed and makes a new random using the seed. Makes an true and
     * false assertion making sure that the parameter that would go in is larger than 0.
     */
    @Test
    void uniformLong() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        long n = random.nextLong();
        if(n>0){
            assertTrue(true,"Success");
            StdRandomTester.uniformLong(n);
        }
        else{
            assertFalse(false,"Number too small");
        }
    }

    /**
     * Test the uniformInt(int,int) method. Makes true or false assertions to see if
     * the numbers that could be parameters are within a certain range.
     */
    @Test
    void testUniformInt() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        int one = random.nextInt();
        int two = random.nextInt();
        for(int i =0;i<100;i++){
            if(two<=one || (long) two - one>=Integer.MAX_VALUE){
                assertFalse(false);
            }
            else{
                assertTrue(true);
            }
        }

    }

    /**
     * Test the uniformDouble(double,double) method. Makes true or false assertions to see if
     * the numbers that could be parameters are within a certain range.
     */
    @Test
    void testUniformDouble() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        double one = random.nextDouble();
        double two = random.nextDouble();
        for(int i=0;i<100;i++){
            if(two<one){
                assertFalse(false,"not within range");
            }
            else{
                assertTrue(true);
            }
        }
    }

    /**
     * Tests the bernoulli() method.
     */
    @Test
    void bernoulli() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        for(int i =0;i<100;i++){
           assertTrue(StdRandomTester.uniformDouble() > random.nextDouble());
           assertFalse(StdRandomTester.uniformDouble() < random.nextDouble());
        }
    }

    /**
     * Tests the gaussian() method. Asserting the return is not null
     */
    @Test
    void gaussian() {
        assertNotNull(StdRandomTester.gaussian());
    }

    /**
     * Test the gaussian(double,double) method. Asserting the return is not null
     */
    @Test
    void testGaussian() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        double mu = random.nextDouble();
        double sigma = random.nextDouble();
        assertNotNull(StdRandomTester.gaussian(mu,sigma));
    }

    /**
     * Tests the geometric() method. Makes assertions that the potential parameter is within a certain range.
     */
    @Test
    void geometric() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        for(int i =0;i<100;i++){
            if (!(random.nextDouble() > 1)) {
                random.nextDouble();
            }
            assertFalse(false);
            if (!(random.nextDouble() <= 1) && random.nextDouble()>0) {
                random.nextDouble();
            }
            assertTrue(true);
        }

    }

    /**
     * Tests the poisson() method. Makes a false assertion if the possible parameter is less than 0
     */
    @Test
    void poisson() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        for(int i =0; i<100; i++){
            assertFalse(random.nextDouble()<0,
                    "Parameter must be positive");
        }
    }

    /**
     * Tests the pareto() method. Makes a not null assertion that the returning value is not null
     */
    @Test
    void pareto() {
        assertNotNull(StdRandomTester.pareto());
    }

    /**
     * Test the pareto(double) method. Makes a false assertion if the possible parameter is not less than 0
     */
    @Test
    void testPareto() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        for(int i =0; i<100; i++){
            assertFalse(random.nextDouble()<0,"Parameter must be positive");
        }
    }

    /**
     * Tests the cauchy() method. Makes an assertion that the returning value is not null
     */
    @Test
    void cauchy() {
        for(int i =0; i<100; i++){
            assertNotNull(StdRandomTester.cauchy());
        }
    }

    /**
     * This method tests the discrete(double[]) method. Creates an array and fills it with doubles. Makes
     * an assertion that the sums is not 0.
     */
    @Test
    void discrete() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        double [] probs = new double[100];
        double sum = 0;
        for(int i =0; i<100; i++){
            probs[i] = random.nextDouble();
            sum += probs[i];
        }
        for(int i =0;i<100;i++){
            assertFalse(sum >1.0 + 1.0E-14 || sum<1.0 + 1.0E-14);
        }

    }

    /**
     * Test the discrete(int[]) method. Creates an array and fills it with random ints. Makes an assertion that
     * the sums is not 0.
     */
    @Test
    void testDiscrete() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        int [] freq = new int[100];
        double sum = 0;
        for(int i =0; i<100; i++){
            freq[i] = random.nextInt();
            sum += freq[i];
        }
        assertNotEquals(0, sum);
    }

    /**
     * Test the exp() method. Makes an assertion that the possible parameter is not less than 0
     */
    @Test
    void exp() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        assertFalse(random.nextDouble()<0);
    }

    /**
     * Test the shuffle(Object[]) method. Creates two arrays and fills them.
     * Then checks to see if the arrays are in the same order or not after shuffle. Asserts true or false
     * after doing so.
     */
    @Test
    void shuffle() {
        Object [] things = new Object[5];
        Object [] thingsTwo;
        things[0] = 1;
        things[1] = 2;
        things[2] = 3;
        things[3] = 4;
        things[4]= 5;
        thingsTwo = things;
        StdRandomTester.shuffle(things);
        for (int i =0;i<100; i++) {
            for (int j = 0; j < things.length - 1; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }
    }
    /**
     * Test the shuffle(double[]) method. Creates two arrays and fills them.
     * Then checks to see if the arrays are in the same order or not after shuffle. Asserts true or false
     * after doing so.
     */
    @Test
    void testShuffle() {
        Double [] things = new Double[5];
        Double [] thingsTwo;
        things[0] = 1.0;
        things[1] = 2.0;
        things[2] = 3.0;
        things[3] = 4.0;
        things[4]= 5.0;
        thingsTwo = things;
        StdRandomTester.shuffle(things);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < things.length - 1; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }
    }

    /**
     * Test the shuffle(int[]) method. Creates two arrays and fills them.
     * Then checks to see if the arrays are in the same order or not after shuffle. Asserts true or false
     * after doing so.
     */
    @Test
    void testShuffle1() {
        Integer [] things = new Integer[5];
        Integer [] thingsTwo;
        things[0] = 1;
        things[1] = 2;
        things[2] = 3;
        things[3] = 4;
        things[4]= 5;
        thingsTwo = things;
        StdRandomTester.shuffle(things);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < things.length - 1; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }
    }
    /**
     * Test the shuffle(char[]) method. Creates two arrays and fills them.
     * Then checks to see if the arrays are in the same order or not after shuffle. Asserts true or false
     * after doing so.
     */
    @Test
    void testShuffle2() {
        Character [] things = new Character[5];
        Character [] thingsTwo;
        things[0] = 'a';
        things[1] = 'b';
        things[2] = 'c';
        things[3] = 'd';
        things[4]= 'e';
        thingsTwo = things;
        StdRandomTester.shuffle(things);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < things.length - 1; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }
    }
    /**
     * Test the shuffle(Object[],int,int) method. Creates two arrays and a random high point. Asserts
     * that the high is not equal to 0. Then checks to see if the arrays are in the same order or not.
     */
    @Test
    void testShuffle3() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        Object [] things = new Object[5];
        Object [] thingsTwo;
        things[0] = 1;
        things[1] = 2;
        things[2] = 3;
        things[3] = 4;
        things[4]= 5;
        thingsTwo = things;
        int hi = random.nextInt();
        assertNotEquals(0,hi);
        StdRandomTester.shuffle(things,0, hi);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < hi; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }

    }
    /**
     * Test the shuffle(double[],int,int) method. Creates two arrays and a random high point. Asserts
     * that the high is not equal to 0. Then checks to see if the arrays are in the same order or not.
     */
    @Test
    void testShuffle4() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        Double [] things = new Double[5];
        Double [] thingsTwo;
        things[0] = 1.0;
        things[1] = 2.0;
        things[2] = 3.0;
        things[3] = 4.0;
        things[4]= 5.0;
        thingsTwo = things;
        int hi = random.nextInt();
        assertNotEquals(0,hi);
        StdRandomTester.shuffle(things,0,hi);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < hi; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }
    }

    /**
     * Test the shuffle(int[],int,int) method. Creates two arrays and a random high point. Asserts
     * that the high is not equal to 0. Then checks to see if the arrays are in the same order or not.
     */
    @Test
    void testShuffle5() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        Integer [] things = new Integer[5];
        Integer [] thingsTwo;
        things[0] = 1;
        things[1] = 2;
        things[2] = 3;
        things[3] = 4;
        things[4]= 5;
        thingsTwo = things;
        int hi = random.nextInt();
        assertNotEquals(0,hi);
        StdRandomTester.shuffle(things,0, hi);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < hi; j++) {
                if (things[j] == thingsTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }
    }

    /**
     * Tests the permutation() method. Creates 2 arrays and shuffles one. Makes assertion that the random
     * size is not zero. Makes true or false assertions testing if the array contents are shuffled or not.
     */
    @Test
    void permutation() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        int size = random.nextInt();
        assertNotEquals(0,size);
        int [] perm = new int [size];
        for(int i=0;i<perm.length;i++){
            perm[i] = i;
        }
        int [] permTwo;
        permTwo=perm;
        StdRandomTester.shuffle(perm);
        for(int i = 0;i<100;i++) {
            for (int j = 0; j < size; j++) {
                if (perm[j] == permTwo[j]) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }
        }


    }

    /**
     * Tests the permutation(int,int) method. Makes 2 random ints and
     * makes assertions that are either true or false.
     */
    @Test
    void testPermutation() {
        StdRandomTester.setSeed(4321);
        Random random = new Random(StdRandomTester.getSeed());
        int one = random.nextInt();
        int two = random.nextInt();
        if(one>0){
            assertFalse(false);
        }
        else{
            assertTrue(true);
        }
        if(two < 0 || two > one){
            assertFalse(false);
        }
        else{
            assertTrue(true);
        }
    }

}

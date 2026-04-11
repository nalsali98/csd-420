/*
 * Name: Noor Alsalihi
 * Course: CSD420-340A Advanced Java Programming
 * Assignment: Module 4.2 Programming Assignment
 * Date: 04/10/2026
 *
 * Description:
 * This program stores integers in a LinkedList and compares the time
 * required to traverse the list using:
 * 1. An iterator
 * 2. The get(index) method
 *
 * The program tests both 50,000 integers and 500,000 integers.
 * It also includes simple test code to make sure the traversal
 * methods work correctly.
 *
 * Results Discussion:
 * The iterator approach is much faster than using get(index) with a LinkedList.
 * This happens because LinkedList is designed for sequential access.
 * An iterator moves through the list one node at a time efficiently.
 *
 * On the other hand, get(index) is much slower because each call to get(index)
 * must walk through the list to find that position. When this happens inside
 * a loop, the total time becomes very large.
 *
 * With 50,000 integers, the difference is noticeable.
 * With 500,000 integers, the difference becomes much bigger because the
 * inefficiency of get(index) grows as the list gets larger.
 *
 * In short, iterator traversal is the better choice for LinkedList,
 * especially when working with large amounts of data.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Alsalihi_Mod4_2 {

    public static void main(String[] args) {
        System.out.println("LinkedList Traversal Time Test");
        System.out.println("--------------------------------------");

        // Test with 50,000 integers
        runTest(50000);

        System.out.println();

        // Test with 500,000 integers
        runTest(500000);
    }

    /**
     * Runs the traversal timing test for a given number of integers.
     *
     * @param size number of integers to store in the LinkedList
     */
    public static void runTest(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        // Fill the list
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Simple test code to ensure the list was created correctly
        if (list.size() != size) {
            System.out.println("Error: List size is incorrect.");
            return;
        }

        if (!list.getFirst().equals(0) || !list.getLast().equals(size - 1)) {
            System.out.println("Error: List values are incorrect.");
            return;
        }

        // Time traversal using iterator
        long iteratorStart = System.nanoTime();
        long iteratorSum = traverseWithIterator(list);
        long iteratorEnd = System.nanoTime();
        long iteratorTime = iteratorEnd - iteratorStart;

        // Time traversal using get(index)
        long indexStart = System.nanoTime();
        long indexSum = traverseWithGetIndex(list);
        long indexEnd = System.nanoTime();
        long indexTime = indexEnd - indexStart;

        // Test code to ensure both traversal methods process the same data
        if (iteratorSum != indexSum) {
            System.out.println("Error: Traversal methods produced different results.");
            return;
        }

        System.out.println("Test with " + size + " integers:");
        System.out.println("Iterator traversal time: " + iteratorTime + " ns");
        System.out.println("get(index) traversal time: " + indexTime + " ns");
        System.out.println("Traversal sums match: " + iteratorSum);
    }

    /**
     * Traverses the LinkedList using an iterator.
     *
     * @param list the LinkedList to traverse
     * @return sum of all values in the list
     */
    public static long traverseWithIterator(LinkedList<Integer> list) {
        long sum = 0;
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    /**
     * Traverses the LinkedList using get(index).
     *
     * @param list the LinkedList to traverse
     * @return sum of all values in the list
     */
    public static long traverseWithGetIndex(LinkedList<Integer> list) {
        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }
}
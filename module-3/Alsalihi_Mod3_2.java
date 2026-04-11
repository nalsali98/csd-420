/*
 * Name: Noor Alsalihi
 * Course: CSD420-340A Advanced Java Programming
 * Assignment: Module 3.2 Programming Assignment
 * Date: 04/10/2026
 *
 * Description:
 * This program creates an ArrayList of 50 random Integer values from 1 to 20.
 * It then calls a generic static method named removeDuplicates() that returns
 * a new ArrayList containing the original values with duplicates removed.
 */

import java.util.ArrayList;
import java.util.Random;

public class Alsalihi_Mod3_2 {

    /**
     * Returns a new ArrayList containing all original values
     * from the input list, but without duplicates.
     *
     * @param list the original ArrayList
     * @param <E> the generic type
     * @return a new ArrayList with duplicates removed
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }

        return uniqueList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // Fill the original ArrayList with 50 random values from 1 to 20
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Remove duplicates
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        // Display results
        System.out.println("Original ArrayList:");
        System.out.println(originalList);

        System.out.println("\nArrayList after removing duplicates:");
        System.out.println(uniqueList);
    }
}
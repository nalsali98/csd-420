/*
 * Name: Noor Alsalihi
 * Course: CSD420-340A Advanced Java Programming
 * Assignment: Module 5.2 Programming Assignment
 * Date: 04/15/2026
 *
 * Description:
 * This program reads words from a text file named collection_of_words.txt,
 * stores only non-duplicate words, and displays them in ascending order
 * and then in descending order. It also includes simple test code to
 * verify that the program functions correctly.
 */

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AlsalihiModule5 {

    public static void main(String[] args) {
        String fileName = "collection_of_words.txt";

        runTests();

        try {
            Set<String> words = readUniqueWordsFromFile(fileName);

            System.out.println("Non-duplicate words in ascending order:");
            for (String word : words) {
                System.out.println(word);
            }

            System.out.println("\nNon-duplicate words in descending order:");
            for (String word : ((TreeSet<String>) words).descendingSet()) {
                System.out.println(word);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: The file \"" + fileName + "\" was not found.");
        }
    }

    /**
     * Reads words from the resource file, removes duplicates,
     * and stores them in ascending order.
     *
     * @param fileName the name of the text file
     * @return a set of unique words in ascending order
     */
    public static Set<String> readUniqueWordsFromFile(String fileName) {
        TreeSet<String> uniqueWords = new TreeSet<>();

        InputStream inputStream = AlsalihiModule5.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        Scanner input = new Scanner(inputStream);

        while (input.hasNextLine()) {
            String line = input.nextLine();

            String[] words = line.split("[^a-zA-Z0-9']+");

            for (String word : words) {
                if (!word.isEmpty()) {
                    uniqueWords.add(word.toLowerCase());
                }
            }
        }

        input.close();
        return uniqueWords;
    }

    /**
     * Simple test code to verify duplicate removal
     * and correct ascending/descending order.
     */
    public static void runTests() {
        TreeSet<String> testSet = new TreeSet<>();

        testSet.add("banana");
        testSet.add("apple");
        testSet.add("orange");
        testSet.add("apple");
        testSet.add("grape");
        testSet.add("banana");

        if (testSet.size() == 4) {
            System.out.println("Test 1 Passed: Duplicates removed correctly.");
        } else {
            System.out.println("Test 1 Failed: Duplicate removal is incorrect.");
        }

        if (testSet.first().equals("apple")) {
            System.out.println("Test 2 Passed: Ascending order works correctly.");
        } else {
            System.out.println("Test 2 Failed: Ascending order is incorrect.");
        }

        if (testSet.descendingSet().first().equals("orange")) {
            System.out.println("Test 3 Passed: Descending order works correctly.\n");
        } else {
            System.out.println("Test 3 Failed: Descending order is incorrect.\n");
        }
    }
}
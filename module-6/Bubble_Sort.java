/*
Name: Noor Alsalihi
Date: April 22, 2026
Course: CSD420-340A Advanced Java Programming
Assignment: Module 6.2 Programming Assignment

Description:
For Assignment Two I created two generic bubble sort methods using Java.
The first is Comparable<> and compares elements based on their natural ordering.
The second is Comparator<> which uses custom sort rules.

Bubble sort works by comparing two elements in the array. If they are out of order,
they are swapped. This process repeats until the array is sorted.
I tested both methods with Integer and Strings variables.

Comparable shows how java naturally sorts numbers and strings with predefined rules.
Comparator allows us to define our own rules such as sorting integers from greatest to least,
or strings by alphabetical spelling or by length.

This assignment gave me a better understanding of generics, sorting,
Comparable vs Comparator, and reusable code.
*/

import java.util.Comparator;

public class Bubble_Sort {

    // Generic bubble sort using Comparable
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;

        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }

            // stop early if already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Generic bubble sort using Comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean swapped;

        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }

            // stop if no swaps happened
            if (!swapped) {
                break;
            }
        }
    }

    // Method to print array
    public static <E> void printList(E[] list) {
        for (E item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Test Comparable with Integer
        Integer[] numbers = {5, 3, 8, 1, 2};
        System.out.println("Original Integer array:");
        printList(numbers);

        bubbleSort(numbers);
        System.out.println("Sorted using Comparable:");
        printList(numbers);

        System.out.println();

        // Test Comparable with String
        String[] words = {"banana", "apple", "orange", "grape"};
        System.out.println("Original String array:");
        printList(words);

        bubbleSort(words);
        System.out.println("Sorted using Comparable:");
        printList(words);

        System.out.println();

        // Test Comparator with Integer (descending)
        Integer[] scores = {90, 75, 88, 100, 67};
        System.out.println("Original scores:");
        printList(scores);

        bubbleSort(scores, (a, b) -> b - a);
        System.out.println("Sorted using Comparator (descending):");
        printList(scores);

        System.out.println();

        // Test Comparator with String length
        String[] names = {"Noor", "Alexander", "Sam", "Mia", "Jonathan"};
        System.out.println("Original names:");
        printList(names);

        bubbleSort(names, Comparator.comparingInt(String::length));
        System.out.println("Sorted using Comparator (by length):");
        printList(names);
    }
}
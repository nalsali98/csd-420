/*
 * Name: Noor Al Salihi
 * Course: CSD420 Advanced Java Programming
 * Module: 2.2 Programming Assignment
 * Assignment: Write random integer and double arrays to a data file
 * Date: April 1, 2026
 *
 * Description:
 * This program creates an array of five random integers and an array of
 * five random double values. It writes the data to a file named
 * alsalihi_datafile.dat. If the file already exists, the new data is appended.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class WriteDataFile {
    public static void main(String[] args) {
        String fileName = "alsalihi_datafile.dat";

        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        Random random = new Random();

        // Fill integer array with random values
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(100); // 0 to 99
        }

        // Fill double array with random values
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = random.nextDouble() * 100; // 0.0 to 100.0
        }

        // Write data to file in append mode
        try (PrintWriter output = new PrintWriter(new FileWriter(fileName, true))) {
            output.println("Integers:");
            for (int value : intArray) {
                output.println(value);
            }

            output.println("Doubles:");
            for (double value : doubleArray) {
                output.println(value);
            }

            output.println("--------------------");

            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
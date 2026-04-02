/*
 * Name: Noor Al Salihi
 * Course: CSD420 Advanced Java Programming
 * Module: 2.2 Programming Assignment
 * Assignment: Read and display data from a data file
 * Date: April 1, 2026
 *
 * Description:
 * This program reads the contents of alsalihi_datafile.dat and displays
 * the stored integer and double values on the console.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadDataFile {
    public static void main(String[] args) {
        String fileName = "alsalihi_datafile.dat";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("The file does not exist yet.");
            return;
        }

        try (Scanner input = new Scanner(file)) {
            System.out.println("Contents of " + fileName + ":");
            System.out.println();

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
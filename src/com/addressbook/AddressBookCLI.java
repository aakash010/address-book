package com.addressbook;

import java.io.IOException;
import java.util.Scanner;

public class AddressBookCLI {
    public static void main(String[] args) {
        try {
            AddressBook addressBook = new AddressBook("address_book.dat");

            // Mock data
            addressBook.addContact(new Contact("Zamp", "test", "1123 street, New Delhi", "9988776655"));
            addressBook.addContact(new Contact("first", "last", "4554 test address ", "9876543321"));

            // CLI
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Add Contact");
                System.out.println("2. Search by Phone Number");
                System.out.println("3. Search by Name");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();

                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();

                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scanner.nextLine();

                        addressBook.addContact(new Contact(firstName, lastName, address, phoneNumber));
                        System.out.println("Contact added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Phone Number to Search: ");
                        String searchPhoneNumber = scanner.nextLine();
                        long startTime = System.nanoTime();
                        Contact resultByPhoneNumber = addressBook.searchByPhoneNumber(searchPhoneNumber);
                        long endTime = System.nanoTime();

                        if (resultByPhoneNumber != null) {
                            System.out.println("Search Result: " + resultByPhoneNumber);
                        } else {
                            System.out.println("Contact not found.");
                        }

                        System.out.println("Search time: " + (endTime - startTime) / 1_000_000.0 + " milliseconds");
                        break;

                    case 3:
                        System.out.print("Enter Name to Search: ");
                        String searchName = scanner.nextLine();
                        long nameStartTime = System.nanoTime();
                        Contact resultByName = addressBook.searchByName(searchName);
                        long nameEndTime = System.nanoTime();

                        if (resultByName != null) {
                            System.out.println("Search Result: " + resultByName);
                        } else {
                            System.out.println("Contact not found.");
                        }

                        System.out.println("Search time: " + (nameEndTime - nameStartTime) / 1_000_000.0 + " milliseconds");
                        break;

                    case 4:
                        addressBook.close();
                        System.out.println("Exiting Address Book. Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

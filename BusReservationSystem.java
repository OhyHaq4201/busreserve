
package com.mycompany.bus;

import java.util.ArrayList;
import java.util.Scanner;

public class BusReservationSystem {
    private static ArrayList<Bus> buses = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;

    public static void main(String[] args) {
        users.add(new User("admin", "admin"));
        users.add(new User("BimanB", "BB232"));
        users.add(new User("cse123", "cse123"));

        buses.add(new Bus(1, "Dhaka", "Chittagong", 50, 500.0));
        buses.add(new Bus(2, "Rajshahi", "Sylhet", 40, 400.0));
        buses.add(new Bus(3, "Barishal", "Khulna", 30, 300.0));

        while (true) {
            if (loggedInUser == null) {
                displayMainMenu();
            } else {
                displayUserMenu();
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                System.out.println("Exiting the program.");
                System.exit(0);
            default:
                System.out.println("Invalid choice! Try again.");
        }
    }

    private static void login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome, " + username + ".");
                return;
            }
        }
        System.out.println("Login failed! Incorrect username or password.");
    }

    private static void displayUserMenu() {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. Book a Ticket");
        System.out.println("2. Cancel a Ticket");
        System.out.println("3. Check Bus Status");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bookTicket();
                break;
            case 2:
                cancelTicket();
                break;
            case 3:
                checkBusStatus();
                break;
            case 4:
                System.out.println("Logging out...");
                loggedInUser = null;
                break;
            default:
                System.out.println("Invalid choice! Try again.");
        }
    }

    private static void bookTicket() {
        System.out.print("Enter Bus Number: ");
        int busNumber = scanner.nextInt();
        Bus bus = findBus(busNumber);

        if (bus == null) {
            System.out.println("Bus not found!");
            return;
        }

        System.out.print("Enter number of seats to book: ");
        int seatsToBook = scanner.nextInt();

        if (seatsToBook > bus.getAvailableSeats()) {
            System.out.println("Sorry, only " + bus.getAvailableSeats() + " seats are available.");
        } else {
            bus.bookSeats(seatsToBook);
            System.out.println("Booking successful! " + seatsToBook + " seats booked on Bus Number " + busNumber);
        }
    }

    private static void cancelTicket() {
        System.out.print("Enter Bus Number: ");
        int busNumber = scanner.nextInt();
        Bus bus = findBus(busNumber);

        if (bus == null) {
            System.out.println("Bus not found!");
            return;
        }

        System.out.print("Enter number of seats to cancel: ");
        int seatsToCancel = scanner.nextInt();

        int bookedSeats = bus.getTotalSeats() - bus.getAvailableSeats();
        if (seatsToCancel > bookedSeats) {
            System.out.println("Error: You can't cancel more seats than were booked.");
        } else {
            bus.cancelSeats(seatsToCancel);
            System.out.println("Cancellation successful! " + seatsToCancel + " seats canceled on Bus Number " + busNumber);
        }
    }

    private static void checkBusStatus() {
        System.out.print("Enter Bus Number: ");
        int busNumber = scanner.nextInt();
        Bus bus = findBus(busNumber);

        if (bus != null) {
            bus.displayBusInfo();
        } else {
            System.out.println("Bus not found!");
        }
    }

    private static Bus findBus(int busNumber) {
        for (Bus bus : buses) {
            if (bus.getBusNumber() == busNumber) {
                return bus;
            }
        }
        return null;
    }
}

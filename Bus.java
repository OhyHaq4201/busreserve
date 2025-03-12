
package com.mycompany.bus;


public class Bus {
    private int busNumber;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double fare;

    public Bus(int busNumber, String source, String destination, int totalSeats, double fare) {
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fare = fare;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void bookSeats(int seats) {
        this.availableSeats -= seats;
    }

    public void cancelSeats(int seats) {
        this.availableSeats += seats;
    }

    public void displayBusInfo() {
        System.out.println("\nBus Number: " + busNumber);
        System.out.println("Route: " + source + " -> " + destination);
        System.out.println("Total Seats: " + totalSeats + " | Available Seats: " + availableSeats);
        System.out.println("Fare per seat: $" + fare);
    }
}

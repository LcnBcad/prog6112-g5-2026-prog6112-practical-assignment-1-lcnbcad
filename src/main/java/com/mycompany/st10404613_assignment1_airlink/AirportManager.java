package com.mycompany.st10404613_assignment1_airlink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AirportManager {
    private ArrayList<Flight> flights = new ArrayList<>();
    
    // 5 gates (G01 to G05) x 8 time slots (06:00 to 20:00)
    private String[][] gateSchedule = new String[5][8];
    private final String[] timeSlots = {"06:00", "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
    private final String[] gates = {"G01", "G02", "G03", "G04", "G05"};

    public AirportManager() {
        // Initialize 2D array with dashes ("—") representing empty slots
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                gateSchedule[i][j] = "—";
            }
        }
    }

    public boolean registerFlight(Flight flight) {
        // Prevent duplicate IDs
        for (Flight f : flights) {
            if (f.getFlightId().equalsIgnoreCase(flight.getFlightId())) {
                return false;
            }
        }
        // Prevent booked passengers from exceeding capacity
        if (flight.getBookedPassengers() > flight.getPassengerCapacity()) {
            return false;
        }
        flights.add(flight);
        return true;
    }

    public Flight searchFlight(String flightId) {
        for (Flight f : flights) {
            if (f.getFlightId().equalsIgnoreCase(flightId)) {
                return f;
            }
        }
        return null;
    }

    public boolean deleteFlight(String flightId) {
        Flight f = searchFlight(flightId);
        if (f != null) {
            releaseGateForFlight(flightId);
            flights.remove(f);
            return true;
        }
        return false;
    }

    public void displayAllFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights registered.");
            return;
        }
        for (Flight f : flights) {
            f.displayDetails();
        }
    }

    // Feature 2: Gate Allocation Methods
    public boolean allocateGate(String flightId, int gateIndex, int timeSlotIndex) {
        Flight f = searchFlight(flightId);
        if (f == null) return false; // Flight doesn't exist
        
        if (gateIndex < 0 || gateIndex >= 5 || timeSlotIndex < 0 || timeSlotIndex >= 8) return false;
        
        // Prevent slot being occupied
        if (!gateSchedule[gateIndex][timeSlotIndex].equals("—")) {
            return false; 
        }

        // Prevent flight from being scheduled more than once
        if (isFlightAlreadyScheduled(flightId)) {
            return false;
        }

        gateSchedule[gateIndex][timeSlotIndex] = flightId;
        return true;
    }

    public boolean releaseGate(int gateIndex, int timeSlotIndex) {
        if (gateIndex >= 0 && gateIndex < 5 && timeSlotIndex >= 0 && timeSlotIndex < 8) {
            gateSchedule[gateIndex][timeSlotIndex] = "—";
            return true;
        }
        return false;
    }

    public void releaseGateForFlight(String flightId) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (gateSchedule[i][j].equalsIgnoreCase(flightId)) {
                    gateSchedule[i][j] = "—";
                }
            }
        }
    }

    public boolean isFlightAlreadyScheduled(String flightId) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (gateSchedule[i][j].equalsIgnoreCase(flightId)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Display 2D array schedule using nested loops
    public void displayGateScheduleMatrix() {
        System.out.print("Gate\t");
        for (String slot : timeSlots) {
            System.out.print(slot + "\t");
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.print("Gate " + gates[i] + "\t");
            for (int j = 0; j < 8; j++) {
                System.out.print(gateSchedule[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Feature 3: Reports
    public void displayScheduledFlights() {
        for (Flight f : flights) {
            if (f.getFlightStatus() == FlightStatus.SCHEDULED) {
                f.displayDetails();
            }
        }
    }

    public void displayDelayedFlights() {
        for (Flight f : flights) {
            if (f.getFlightStatus() == FlightStatus.DELAYED) {
                f.displayDetails();
            }
        }
    }

    public void identifyBusiestGate() {
        int maxCount = -1;
        String busiest = "G01";
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 8; j++) {
                if (!gateSchedule[i][j].equals("—")) count++;
            }
            if (count > maxCount) {
                maxCount = count;
                busiest = gates[i];
            }
        }
        System.out.println("Busiest Gate: Gate " + busiest + " (" + maxCount + " flights allocated)");
    }

    public void sortFlightsById() {
        flights.sort(Comparator.comparing(Flight::getFlightId));
        System.out.println("Flights sorted by Flight ID.");
    }
}
package com.mycompany.st10404613_assignment1_airlink;

import java.util.Scanner;

public class ST10404613_Assignment1_Airlink {

    public static void main(String[] args) {
        AirportManager manager = new AirportManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- AeroLink International Airport Schedule System ---");
            System.out.println("1. Register Flight");
            System.out.println("2. Search Flight by ID");
            System.out.println("3. Display All Flights");
            System.out.println("4. Display Gate Schedule Matrix");
            System.out.println("5. Allocate Gate & Time Slot");
            System.out.println("6. Display Operational Reports");
            System.out.println("7. Delete Flight");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Flight ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Airline Name: ");
                    String airline = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String dest = scanner.nextLine();
                    System.out.print("Enter Departure Time (e.g., 08:00): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter Passenger Capacity: ");
                    int cap = scanner.nextInt();
                    System.out.print("Enter Booked Passengers: ");
                    int booked = scanner.nextInt();
                    scanner.nextLine();
                    
                    Flight newFlight = new Flight(id, airline, dest, time, cap, booked, FlightCategory.DOMESTIC, FlightStatus.SCHEDULED);
                    if (manager.registerFlight(newFlight)) {
                        System.out.println("Flight registered successfully!");
                    } else {
                        System.out.println("Error: Duplicate ID or booked passengers exceed capacity.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Flight ID to search: ");
                    String searchId = scanner.nextLine();
                    Flight found = manager.searchFlight(searchId);
                    if (found != null) {
                        found.displayDetails();
                    } else {
                        System.out.println("Flight not found.");
                    }
                    break;
                case 3:
                    manager.displayAllFlights();
                    break;
                case 4:
                    manager.displayGateScheduleMatrix();
                    break;
                case 5:
                    System.out.print("Enter Flight ID to allocate: ");
                    String fId = scanner.nextLine();
                    System.out.print("Enter Gate Index (0 to 4 for G01-G05): ");
                    int gIdx = scanner.nextInt();
                    System.out.print("Enter Time Slot Index (0 to 7 for 06:00-20:00): ");
                    int tIdx = scanner.nextInt();
                    if (manager.allocateGate(fId, gIdx, tIdx)) {
                        System.out.println("Gate successfully allocated!");
                    } else {
                        System.out.println("Allocation failed (Slot occupied, flight already scheduled, or invalid indices).");
                    }
                    break;
                case 6:
                    manager.identifyBusiestGate();
                    break;
                case 7:
                    System.out.print("Enter Flight ID to delete: ");
                    String delId = scanner.nextLine();
                    if (manager.deleteFlight(delId)) {
                        System.out.println("Flight deleted and gate released.");
                    } else {
                        System.out.println("Flight not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }
}

/**
 * ============================================================================
 * CODE ATTRIBUTION
 * ============================================================================
 * 
 * 1. Bro Code Java ArrayList Tutorial [YouTube]. Available at:
 *    https://www.youtube.com/watch?v=Zs342ePFvRI [Accessed 27 September 2026].
 * 
 * 2. Bro Code Java Inheritance Tutorial [YouTube]. Available at:
 *    https://www.youtube.com/watch?v=GTP5lVEKXaU [Accessed 27 September 2026].
 * 
 * 3. CodeWithWilbur, Java Bubble Sort Tutorial [YouTube]. Available at:
 *    https://www.youtube.com/watch?v=uNoLQIpk26Y [Accessed 27 September 2026].
 * 
 * 4. Farrell, J. (2020) Java Programming. 10th ed. Boston: Cengage Learning.
 * 
 * 5. Oracle Java Platform SE 8, Class String, method compareTo().
 *    Available at: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 *    [Accessed 27 September 2026].
 * 
 * 6. W3Schools Java 2D Arrays, Multidimensional Arrays. Available at:
 *    https://www.w3schools.com/java/ [Accessed 27 September 2026].
 * 
 * 7. W3Schools Java ArrayList. Available at:
 *    https://www.w3schools.com/java/ [Accessed 27 September 2026].
 * 
 * 8. W3Schools Java Try Catch, Exception Handling. Available at:
 *    https://www.w3schools.com/java/ [Accessed 27 September 2026].
 * 
 * 9. W3Schools Java Sorting Arrays. Available at:
 *    https://www.w3schools.com/java/ [Accessed 27 September 2026].
 * ============================================================================
 */
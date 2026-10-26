
package com.mycompany.st10404613_assignment1_airlink;


public class Flight {
    
    private String flightId;
    private String airlineName;
    private String destination;
    private String departureTime;
    private int passengerCapacity;
    private int bookedPassengers;
    private FlightCategory flightCategory; 
    private FlightStatus flightStatus;
    
    public Flight(String flightId, String airlineName, String destination, String departureTime, int passengerCapacity, int bookedPassengers, FlightCategory flightCategory, FlightStatus flightStatus) {
        this.flightId = flightId;
        this.airlineName = airlineName;
        this.destination = destination;
        this.departureTime = departureTime;
        this.passengerCapacity = passengerCapacity;
        this.bookedPassengers = bookedPassengers;
        this.flightCategory = flightCategory;
        this.flightStatus = flightStatus;
    }
    
    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) {this.flightId = flightId; }
    
    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) {this.airlineName = airlineName; }
    
    public String getDestination() { return destination; }
    public void setDestination(String departureTime) { this.destination = destination; }
    
    public int getPassengerCapacity() { return bookedPassengers; }
    public void setPassengerCapacity(int passengerCapacity) { this.passengerCapacity = passengerCapacity; }
    
    public int getBookedPassengers() { return bookedPassengers; }
    public void setBookedPassengers(int bookedPassengers) { this.bookedPassengers = bookedPassengers; }

    public FlightCategory getFlightCategory() { return flightCategory; }
    public void setFlightCategory(FlightCategory flightCategory) { this.flightCategory = flightCategory; }

    public FlightStatus getFlightStatus() { return flightStatus; }
    public void setFlightStatus(FlightStatus flightStatus) { this.flightStatus = flightStatus; }

    public double calculateLoadPercentage() {
        if (passengerCapacity == 0) return 0.0;
        return ((double) bookedPassengers / passengerCapacity) * 100;
    }

    public void displayDetails() {
        System.out.println("Flight ID: " + flightId + " | Airline: " + airlineName + 
                           " | Destination: " + destination + " | Time: " + departureTime + 
                           " | Status: " + flightStatus + " | Category: " + flightCategory + 
                           " | Load: " + bookedPassengers + "/" + passengerCapacity + 
                           " (" + String.format("%.1f", calculateLoadPercentage()) + "%)");
    }
}
            
        
    


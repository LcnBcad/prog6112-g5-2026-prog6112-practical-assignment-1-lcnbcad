package com.mycompany.st10404613_assignment1_airlink;

public class InternationalFlight extends Flight {
    private String departureTerminal;
    private String boardingGate;
    private boolean customsClearanceRequired;

    public InternationalFlight(String flightId, String airlineName, String destination, String departureTime, 
                               int passengerCapacity, int bookedPassengers, FlightStatus flightStatus, 
                               String departureTerminal, String boardingGate, boolean customsClearanceRequired) {
        super(flightId, airlineName, destination, departureTime, passengerCapacity, bookedPassengers, FlightCategory.INTERNATIONAL, flightStatus);
        this.departureTerminal = departureTerminal;
        this.boardingGate = boardingGate;
        this.customsClearanceRequired = customsClearanceRequired;
    }

    public String getDepartureTerminal() { return departureTerminal; }
    public void setDepartureTerminal(String departureTerminal) { this.departureTerminal = departureTerminal; }

    public String getBoardingGate() { return boardingGate; }
    public void setBoardingGate(String boardingGate) { this.boardingGate = boardingGate; }

    public boolean isCustomsClearanceRequired() { return customsClearanceRequired; }
    public void setCustomsClearanceRequired(boolean customsClearanceRequired) { this.customsClearanceRequired = customsClearanceRequired; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("   --> [International Info] Terminal: " + departureTerminal + 
                           " | Gate: " + boardingGate + 
                           " | Customs Required: " + (customsClearanceRequired ? "Yes" : "No"));
    }
}
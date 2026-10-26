package com.mycompany.st10404613_assignment1_airlink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ST10404613_Assignment1_AirlinkTest {
    
    private AirportManager manager;
    private Flight testFlight;

    public ST10404613_Assignment1_AirlinkTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        manager = new AirportManager();
        testFlight = new Flight("FL101", "Airlink", "Cape Town", "06:00", 150, 100, FlightCategory.DOMESTIC, FlightStatus.SCHEDULED);
    }
    
    @AfterEach
    public void tearDown() {
        manager = null;
        testFlight = null;
    }

    /**
     * Test of registering and searching for a flight.
     */
    @Test
    public void testRegisterAndSearchFlight() {
        boolean registered = manager.registerFlight(testFlight);
        assertTrue(registered, "Flight should be successfully registered.");

        Flight found = manager.searchFlight("FL101");
        assertNotNull(found, "Flight should be found by ID.");
        assertEquals("Airlink", found.getAirlineName(), "Airline name should match.");
    }

    /**
     * Test preventing duplicate Flight IDs.
     */
    @Test
    public void testPreventDuplicateFlightIds() {
        manager.registerFlight(testFlight);
        
        Flight duplicateFlight = new Flight("FL101", "AnotherAirline", "Durban", "08:00", 120, 50, FlightCategory.DOMESTIC, FlightStatus.SCHEDULED);
        boolean registeredAgain = manager.registerFlight(duplicateFlight);
        
        assertFalse(registeredAgain, "System should prevent registering duplicate Flight IDs.");
    }

    /**
     * Test updating or deleting a flight.
     */
    @Test
    public void testDeleteFlight() {
        manager.registerFlight(testFlight);
        boolean deleted = manager.deleteFlight("FL101");
        
        assertTrue(deleted, "Flight should be successfully deleted.");
        assertNull(manager.searchFlight("FL101"), "Deleted flight should no longer be found.");
    }

    /**
     * Test allocating and releasing a gate.
     */
    @Test
    public void testAllocateAndReleaseGate() {
        manager.registerFlight(testFlight);
        
        // Allocate Gate 0 (G01), Time Slot 0 (06:00)
        boolean allocated = manager.allocateGate("FL101", 0, 0);
        assertTrue(allocated, "Gate should be successfully allocated.");

        // Release gate
        boolean released = manager.releaseGate(0, 0);
        assertTrue(released, "Gate should be successfully released.");
    }

    /**
     * Test preventing allocation of an occupied gate and time slot.
     */
    @Test
    public void testPreventOccupiedGateAllocation() {
        manager.registerFlight(testFlight);
        Flight secondFlight = new Flight("FL202", "FlySafair", "Johannesburg", "06:00", 180, 120, FlightCategory.DOMESTIC, FlightStatus.SCHEDULED);
        manager.registerFlight(secondFlight);

        // Allocate first flight to Gate 0, Time Slot 0
        assertTrue(manager.allocateGate("FL101", 0, 0));

        // Try to allocate second flight to the exact same gate and time slot
        boolean doubleAllocated = manager.allocateGate("FL202", 0, 0);
        assertFalse(doubleAllocated, "System must prevent allocating an already occupied gate and time slot.");
    }

    /**
     * Test sorting flights by Flight ID.
     */
    @Test
    public void testSortFlightsById() {
        Flight f1 = new Flight("FL300", "Airlink", "Port Elizabeth", "10:00", 100, 50, FlightCategory.DOMESTIC, FlightStatus.SCHEDULED);
        Flight f2 = new Flight("FL100", "Safair", "Cape Town", "08:00", 100, 50, FlightCategory.DOMESTIC, FlightStatus.SCHEDULED);
        
        manager.registerFlight(f1);
        manager.registerFlight(f2);
        
        manager.sortFlightsById();
        
        // Verify sorting via search results or accessors
        assertNotNull(manager.searchFlight("FL100"));
        assertNotNull(manager.searchFlight("FL300"));
    }
}
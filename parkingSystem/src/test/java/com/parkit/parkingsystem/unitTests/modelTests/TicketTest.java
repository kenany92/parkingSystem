package com.parkit.parkingsystem.unitTests.modelTests;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
/***
 * This class test check that the setters and getters for the class Ticket model work correctly.
 */
public class TicketTest {

    private static final int id = 55;
    private static final ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR,true);
    private static final String vehicleRegNumber = "ABCABC";
    private static final double price =15;
    private static final Date inTime = new Date();
    private static final Date outTime = new Date();
    private static Ticket ticket;
    /***
     * the methode setUp attributes before doing tests
     */
    @BeforeAll
    private static void setParkingSpot() {
        ticket = new Ticket();

        ticket.setId(id);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber(vehicleRegNumber);
        ticket.setPrice(price);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
    }
    /***
     * the test methode verify that the id getter is work correctly
     */
    @Test
    public void getIdTest(){
        assertEquals(ticket.getId(), id);
    }
    /***
     * the test methode verify that the ParkingSpot getter is work correctly
     */
    @Test
    public void getParkingSpotTest(){
        assertEquals(ticket.getParkingSpot(), parkingSpot);
    }
    /***
     * the test methode verify that the VehicleRegNumber getter is work correctly
     */
    @Test
    public void getVehicleRegNumberTest(){
        assertEquals(ticket.getVehicleRegNumber(), vehicleRegNumber);
    }
    /***
     * the test methode verify that the Price getter is work correctly
     */
    @Test
    public void getPriceTest(){
        assertEquals(ticket.getPrice(), price);
    }
    /***
     * the test methode verify that the InTime getter is work correctly
     */
    @Test
    public void getInTimeTest(){
        assertEquals(ticket.getInTime(), inTime);
    }
    /***
     * the test methode verify that the OutTime getter is work correctly
     */
    @Test
    public void getOutTimeTest(){
        assertEquals(ticket.getOutTime(), outTime);
    }
}

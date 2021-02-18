package com.parkit.parkingsystem.unitTests.modelTests;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * This class test check that the setters and getters for the class ParkingSpot model work correctly.
 */
public class ParkingSpotTest {

    private static final int id =55;
    private static final ParkingType parkingType = ParkingType.CAR;
    private static final boolean isAvailable = true;
    private static ParkingSpot parkingSpot;

    /***
     * the methode setUp attributes before doing tests
     */
    @BeforeAll
    private static void setParkingSpot() {
        parkingSpot = new ParkingSpot();
        parkingSpot.setId(id);
        parkingSpot.setParkingType(parkingType);
        parkingSpot.setAvailable(isAvailable);
    }
    /***
     * the test methode verify that the id getter is work correctly
     */
    @Test
    public void getIdTest(){
       assertEquals(parkingSpot.getId(), id);
    }
    /***
     * the test methode verify that the ParkingType getter is work correctly
     */
    @Test
    public void getParkingTypeTest(){
        assertEquals(parkingSpot.getParkingType(), parkingType);
    }
    /***
     * the test methode verify that the IsAvailable getter is work correctly
     */
    @Test
    public void getIsAvailableTest(){
        assertEquals(parkingSpot.isAvailable(), isAvailable);
    }
}

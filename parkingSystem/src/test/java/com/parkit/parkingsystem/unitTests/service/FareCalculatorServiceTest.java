package com.parkit.parkingsystem.unitTests.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/***
 * this test class contains the tests of the class FareCalculator
 */
@ExtendWith(MockitoExtension.class)
public class FareCalculatorServiceTest {


    private Ticket ticket;
    /***
     * This is the spy of the object FareCalculatorService we'll use in ours tests
     */
    @Spy
    private static FareCalculatorService fareCalculatorService;
    /***
     * This methode sets up FareCalculatorService before executing all tests
     */
    @BeforeAll
    private static void setUp() {
        fareCalculatorService = new FareCalculatorService();
    }

    /***
     * this methode initiate ticket before each test
     */
    @BeforeEach
    private void setUpPerTest() {
        ticket = new Ticket();
    }

    /***
     * this test check that the fares are correctly generated for cars
     */
    @Test
    public void calculateFareCar(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) );
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals(ticket.getPrice(), Fare.CAR_RATE_PER_HOUR);
    }
    /***
     * this test check that the fares are correctly generated for bikes
     */
    @Test
    public void calculateFareBike(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) );
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals(ticket.getPrice(), Fare.BIKE_RATE_PER_HOUR);
    }
    /***
     * this test check that nos fares are calculated and no DB calls to update, when vehicle the vehicle type is unknown
     */
    @Test
    public void calculateFareUnknownType(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) );
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, null,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        assertThrows(NullPointerException.class, () -> fareCalculatorService.calculateFare(ticket));
    }
    /***
     * this test check the class test FareCalculatorService insure that no possible inTimes are in the future
     */
    @Test
    public void calculateFareBikeWithFutureInTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() + (  60 * 60 * 1000) );
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        assertThrows(IllegalArgumentException.class, () -> fareCalculatorService.calculateFare(ticket));
    }
    /***
     * this test check that the algorithm of calculation is working correctly for 45 minuter of parking duration a bike
     */
    @Test
    public void calculateFareBikeWithLessThanOneHourParkingTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  45 * 60 * 1000) );//45 minutes parking time should give 3/4th parking fare
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals((0.75 * Fare.BIKE_RATE_PER_HOUR), ticket.getPrice() );
    }
    /***
     * this test check that the algorithm of calculation is working correctly for 45 minuter of parking duration a car
     */
    @Test
    public void calculateFareCarWithLessThanOneHourParkingTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  45 * 60 * 1000) );//45 minutes parking time should give 3/4th parking fare
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals( (0.75 * Fare.CAR_RATE_PER_HOUR) , ticket.getPrice());
    }
    /***
     * this test check that the algorithm of calculation is working correctly for 15 minuter of parking duration a car
     */
    @Test
    public void calculateFareCarWithLessThanHalfOfHourParkingTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  15 * 60 * 1000) );//15 minutes parking time should give 0 parking fare
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals( 0 , ticket.getPrice());
    }
    /***
     * this test check that the algorithm of calculation is working correctly for 15 minuter of parking duration a bike
     */
    @Test
    public void calculateFareBikeWithLessThanHalfOfHourParkingTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  15 * 60 * 1000) );//15 minutes parking time should give 0 parking fare
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(5, ParkingType.BIKE,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals( 0 , ticket.getPrice());
    }
    /***
     * this test check that the algorithm of calculation is working correctly for 24 hours of parking duration a car
     */
    @Test
    public void calculateFareCarWithMoreThanADayParkingTime(){
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  24 * 60 * 60 * 1000) );//24 hours parking time should give 24 * parking fare per hour
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals( (24 * Fare.CAR_RATE_PER_HOUR) , ticket.getPrice());
    }
    /***
     * this test check that the algorithm of calculation is discounting regular car customers
     */
    @Test
    public void calculateFareCarForRegularCostumer(){
        String reg = "TestReg";
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) ); //1 hour parking time should give 1 * parking fare per hour * (1-discount percentage)
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        when(fareCalculatorService.testingIfTheDiscountsApplicable(reg,60)).thenReturn(true);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber(reg);

        fareCalculatorService.calculateFare(ticket);
        double timeTobePaid =Fare.CAR_RATE_PER_HOUR * (1 - Fare.PERCENTAGE_DISCOUNT_FOR_RECURRING_CUSTOMER);
        timeTobePaid = Math.round(timeTobePaid*1000.0) / 1000.0;
        assertEquals(timeTobePaid,ticket.getPrice());
    }
    /***
     * this test check that the algorithm of calculation is discounting regular bike customers
     */
    @Test
    public void calculateFareBikeForRegularCostumer(){
        String reg = "TestReg";
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) );
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE,false);
        when(fareCalculatorService.testingIfTheDiscountsApplicable(reg,60)).thenReturn(true);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber(reg);

        fareCalculatorService.calculateFare(ticket);
        double timeTobePaid =Fare.BIKE_RATE_PER_HOUR * (1 - Fare.PERCENTAGE_DISCOUNT_FOR_RECURRING_CUSTOMER);
        timeTobePaid = Math.round(timeTobePaid*1000.0) / 1000.0;
        assertEquals(timeTobePaid,ticket.getPrice());
    }
}

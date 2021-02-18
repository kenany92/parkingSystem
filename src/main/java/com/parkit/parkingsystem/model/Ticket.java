package com.parkit.parkingsystem.model;

import java.util.Date;

/***
 * this class defines the Ticket attributes
 */
public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;

    /***
     * ID getter
     * @return the id of the ticket
     */
    public int getId() {
        return id;
    }
    /***
     * ID setter
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Parking spot getter
     * @return The parking spot of the object Ticket
     */
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    /***
     * Parking spot setter
     */
    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
    /***
     * VehicleRegNumber getter
     * @return String of the vehicle registration number
     */
    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }
    /***
     * VehicleRegNumber setter
     */
    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }
    /***
     * price getter
     * @return price
     */
    public double getPrice() {
        return price;
    }
    /***
     * Price setter
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /***
     * InTime getter
     * @return InTime
     */
    public Date getInTime() {
        if(inTime != null)
            return new Date(inTime.getTime());
        return null;
    }
    /***
     * Price setter
     */
    public void setInTime(Date inTime) {
        if(inTime != null) {
            this.inTime = new Date(inTime.getTime());
        } else this.inTime = null;
    }
    /***
     * OutTime getter
     * @return OutTime
     */
    public Date getOutTime() {
        if(outTime != null)
            return new Date(outTime.getTime());
        return null;
    }
    /***
     * OutTime setter
     */
    public void setOutTime(Date outTime) {
        if(outTime != null) {
            this.outTime = new Date(outTime.getTime());
        } else this.outTime = null;
    }
}

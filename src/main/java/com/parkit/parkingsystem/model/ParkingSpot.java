package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;

/***
 * this class defines the Parking Spot attributes
 */
public class ParkingSpot {
    private int number;
    private ParkingType parkingType;
    private boolean isAvailable;

    public ParkingSpot(int number, ParkingType parkingType, boolean isAvailable) {
        this.number = number;
        this.parkingType = parkingType;
        this.isAvailable = isAvailable;
    }

    /***
     * Constructor
     */
    public ParkingSpot() {
    }

    /***
     * Id getter
     * @return Id
     */
    public int getId() {
        return number;
    }

    /***
     * Id setter
     * @param number Id
     */
    public void setId(int number) {
        this.number = number;
    }
    /***
     * ParkingType getter
     * @return ParkingType
     */
    public ParkingType getParkingType() {
        return parkingType;
    }

    /***
     * ParkingType setter
     * @param parkingType the current parking type
     */
    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }
    /***
     * isAvailable getter
     * @return isAvailable
     */
    public boolean isAvailable() {
        return isAvailable;
    }
    /***
     * Available setter
     * @param available true or false
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}

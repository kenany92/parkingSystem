package com.parkit.parkingsystem.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This class is dedicated to the input readers
 * @author Nabil Boudjellal
 */
public class InputReaderUtil {

    private static final Scanner scan = new Scanner(System.in,"UTF-8");
    private static final Logger logger = LogManager.getLogger("InputReaderUtil");
    /**
     * This method scan and return the an int of the console entry
     * @return int
     */
    public int readSelection() {
        try {
            return Integer.parseInt(scan.nextLine());
        } catch(Exception e){
            logger.error("Error while reading user input from Shell", e);
            System.out.println("Error reading input. Please enter valid number for proceeding further");
            return -1;
        }
    }
    /**
     * This method scan and return the a String of the console entry
     * @return String
     */
    public String readVehicleRegistrationNumber() {
        try {
            String vehicleRegNumber= scan.nextLine();
            if(vehicleRegNumber == null || vehicleRegNumber.trim().length()==0) {
                throw new IllegalArgumentException("Invalid input provided");
            }
            return vehicleRegNumber;
        }catch(Exception e){
            logger.error("Error while reading user input from Shell", e);
            System.out.println("Error reading input. Please enter a valid string for vehicle registration number");
            throw e;
        }
    }


}

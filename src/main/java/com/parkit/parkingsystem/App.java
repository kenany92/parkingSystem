package com.parkit.parkingsystem;

import com.parkit.parkingsystem.service.InteractiveShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/***
 * This class contain the methode to execute parking system in the console
 */
public class App {
    private static final Logger logger = LogManager.getLogger("App");
    /***This is the main method of this application
     * @param args array of string arguments
     */
    public static void main(String[] args){
        logger.info("Initializing Parking System");
        InteractiveShell interactiveShell = new InteractiveShell();
        interactiveShell.loadInterface();
    }
}

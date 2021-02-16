package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
/***
 * this class contains the query methods calls of the ticket table
 */
public class TicketDAO {

    private static final Logger logger = LogManager.getLogger("TicketDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    /***
     * This methode save the ticket in the table ticket
     * @param ticket ticket we have to save in the DB
     * @return true if the DB was update or false if something goes wrong
     */
    public boolean saveTicket(Ticket ticket){
        Connection con = null;
        boolean result = false;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_TICKET);
            //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
            //ps.setInt(1,ticket.getId());
            ps.setInt(1,ticket.getParkingSpot().getId());
            ps.setString(2, ticket.getVehicleRegNumber());
            ps.setDouble(3, ticket.getPrice());
            ps.setTimestamp(4, new Timestamp(ticket.getInTime().getTime()));
            ps.setTimestamp(5, (ticket.getOutTime() == null)?null: (new Timestamp(ticket.getOutTime().getTime())) );
            result =  ps.execute();
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error fetching next available slot",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

    /***
     * this methode get the ticket of the vehicle Registration Number
     * @param vehicleRegNumber Registration Number of the vehicle
     * @return the ticket saved in the DB
     */
    public Ticket getTicket(String vehicleRegNumber) {
        Connection con;
        Ticket ticket = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_TICKET);
            //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
            ps.setString(1,vehicleRegNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ticket = new Ticket();
                ParkingSpot parkingSpot = new ParkingSpot(rs.getInt(1), ParkingType.valueOf(rs.getString(6)),false);
                ticket.setParkingSpot(parkingSpot);
                ticket.setId(rs.getInt(2));
                ticket.setVehicleRegNumber(vehicleRegNumber);
                ticket.setPrice(rs.getDouble(3));
                ticket.setInTime(rs.getTimestamp(4));
                ticket.setOutTime(rs.getTimestamp(5));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }catch (Exception ex){
            logger.error("Error fetching next available slot",ex);
        }
        return ticket;
    }

    /***
     * This methode update the ticket in the table ticket
     * @param ticket ticket we have to update in the DB
     * @return true if the DB was update or false if something goes wrong
     */
    public boolean updateTicket(Ticket ticket) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            if(checkIfIdExist(ticket.getId())) {
                PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_TICKET);
                ps.setDouble(1, ticket.getPrice());
                ps.setTimestamp(2, new Timestamp(ticket.getOutTime().getTime()));
                ps.setInt(3, ticket.getId());
                ps.execute();
                dataBaseConfig.closePreparedStatement(ps);
                return true;
            }else return false;
        }catch (Exception ex){
            logger.error("Error saving ticket info",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return false;
    }

    /***
     * this methode check in the DB if the the vehicle with the RegNumber parked in the parking before
     * @param vehicleRegNumber the vehicle registration number
     * @return true if the DB was update or false if something goes wrong
     */
    public boolean checkIfRegularCustomer(String vehicleRegNumber) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.CHECK_IF_REGULAR_CUSTOMER);
            ps.setString(1,vehicleRegNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error during recurring customer checking ",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return false;
    }
    /***
     * this methode check in the id of a ticket exist in the DB
     * @param id ID of a ticket saved in the DB
     * @return true if exist and false if he doesn't
     */
    private boolean checkIfIdExist(int  id) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.CHECK_IF_ID_EXIST);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if (rs.getInt(1) > 0) {
                    logger.info("The ID is in the DB");
                    return true;
                }else logger.info("The ID is not in the DB");
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error during recurring customer checking ",ex);
        }finally {
            dataBaseConfig.closeConnection(con);

        }
        return false;
    }
    /***
     * this methode check in the DB if a reg number is exist in the DB with outTime = null
     * @param vehicleRegNumber Registration Number of the vehicle
     * @return true if there is a vehicle and false if there is not
     */
    public boolean checkIfRegNumberIsInTheParking(String vehicleRegNumber) {
        Connection con = null;
        boolean result = false;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.CHECK_IF_REGNUMBER_IS_IN_THE_PARKING);
            ps.setString(1,vehicleRegNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt(1) > 0) result = true;
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error when when checking if the vehicle is already parked ",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }
}

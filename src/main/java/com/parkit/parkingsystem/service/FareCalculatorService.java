package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.parkit.parkingsystem.config.DbConstants.checkOccurenceQuery;

/**
 * Calculates the fare once out.
 */
public class FareCalculatorService {

    DataBaseConfig dataBaseTestConfig = new DataBaseConfig();

    public Connection connection;

    public void connecting() throws SQLException, ClassNotFoundException {
        connection = dataBaseTestConfig.getConnection();
    }

    public boolean checkIfCameBefore(String vehicleRegNumber){
        try{
            connecting();
            //set parking entries to available
            ResultSet hasCame = connection.createStatement().executeQuery(checkOccurenceQuery(vehicleRegNumber));
            hasCame.next();
            if (hasCame.getInt(1) > 0) {
                return true;
            }
            else {
                return false;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dataBaseTestConfig.closeConnection(connection);
        }
        return false;
    }

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        double inHour = ticket.getInTime().getTime();
        double outHour = ticket.getOutTime().getTime();
        double pourcent = 1;

        //TODO: Some tests are failing here. Need to check if this logic is correct
        double duration = (outHour - inHour) / (60 * 60 * 1000);

        if (checkIfCameBefore(ticket.getVehicleRegNumber())) {
            pourcent = 0.95;
        }

        if (duration <= 0.5) {
            ticket.setPrice(0);
        }
        else {

            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * pourcent);
                    break;
                }
                case BIKE: {
                    ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * pourcent);
                    break;
                }
                default:
            }

        }
    }
}

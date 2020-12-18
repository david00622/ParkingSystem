package com.parkit.parkingsystem.config;

public class DbConstants {

    /**
     * Returns a query to check the ticket occurence of a dynamic vehicle regNumber.
     * @param vehicleRegNumber
     * @return
     */
    public static String checkOccurenceQuery(String vehicleRegNumber) {
        return "SELECT COUNT(*) AS TOTAL FROM ticket " +
                "WHERE VEHICLE_REG_NUMBER = '" + vehicleRegNumber + "' AND OUT_TIME IS NOT NULL";
    }

}

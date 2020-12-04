package com.parkit.parkingsystem.config;

public class DbConstants {

    public static String checkOccurenceQuery(String vehicleRegNumber) {
        return "SELECT COUNT(*) AS TOTAL FROM ticket " +
                "WHERE VEHICLE_REG_NUMBER = '" + vehicleRegNumber + "' AND OUT_TIME IS NOT NULL";
    }

}

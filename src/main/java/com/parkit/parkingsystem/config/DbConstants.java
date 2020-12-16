package com.parkit.parkingsystem.config;

public class DbConstants {

    public static String checkOccurenceQuery(String vehicleRegNumber) {
        return "SELECT COUNT(*) AS TOTAL FROM ticket " +
                "WHERE VEHICLE_REG_NUMBER = '" + vehicleRegNumber + "' AND OUT_TIME IS NOT NULL";
    }

    //public static String lockParkingSpots = "UPDATE test.parking SET AVAILABLE=0 WHERE PARKING_NUMBER > 1;";

    //public static String freeParkingSpots = "UPDATE test.parking SET AVAILABLE=1 WHERE PARKING_NUMBER > 1;";

}

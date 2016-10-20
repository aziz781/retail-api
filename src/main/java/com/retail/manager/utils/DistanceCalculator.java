package com.retail.manager.utils;

/**
 * Created by abdulaziz on 20/10/2016.
 */
public class DistanceCalculator {

    final static double EARTH_RADIUS_IN_MILES = 3958.75;

    /*
     NOTE: for this application, the code for 'calculateDistance' is from
     http://stackoverflow.com/questions/18170131/comparing-two-locations-using-their-longitude-and-latitude
    */
    public  static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = EARTH_RADIUS_IN_MILES * c;

        return dist;

    }
}

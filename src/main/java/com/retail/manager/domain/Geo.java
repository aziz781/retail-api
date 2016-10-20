package com.retail.manager.domain;

/**
 * Created by abdulaziz on 20/10/2016.
 */
public class Geo {

    private Double latitude;
    private Double longitude;

    public Geo()
    {

    }
    public Geo(Double latitude,Double longitude)
    {
        this.longitude =longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

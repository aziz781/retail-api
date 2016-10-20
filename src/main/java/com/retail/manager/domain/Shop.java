package com.retail.manager.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shop {

    private String shopName;

    private Address shopAddress;

    private Geo shopGeo;

    private String status;
    private String error;

    public Shop()
    {

    }

    public Shop(String name,Integer number, String postcode)
    {
        this.shopName = name;
        shopAddress = new Address(number,postcode);

    }

    public Shop(String name,Integer number, String postcode,Double longitude,Double latitude)
    {
        this(name, number, postcode);
        this.shopGeo = new Geo(longitude,latitude);

    }


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public Address getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(Address shopAddress) {
        this.shopAddress = shopAddress;
    }


    public Geo getShopGeo() {
        return shopGeo;
    }

    public void setShopGeo(Geo shopGeo) {
        this.shopGeo = shopGeo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (!shopName.equals(shop.shopName)) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = shopName.hashCode();
        return result;
    }




}

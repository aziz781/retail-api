package com.retail.manager.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shop {

    @JsonProperty(required = true)
    private String shopName;

    @JsonProperty(required = true)
    private Address shopAddress;

    private Geo shopGeo;

    private List<Link> links;

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


    @ApiModelProperty(position = 0,required = true, value = "a unique shop name")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    @ApiModelProperty(position = 1,required = true, value = "a address for the shop.")
    public Address getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(Address shopAddress) {
        this.shopAddress = shopAddress;
    }

    @ApiModelProperty(position = 3,value = "Shop geo Location.")
    public Geo getShopGeo() {
        return shopGeo;
    }

    public void setShopGeo(Geo shopGeo) {
        this.shopGeo = shopGeo;
    }

    @ApiModelProperty(position = 4,value = "response status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ApiModelProperty(position = 5,value = "response error  details if any")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @ApiModelProperty(position = 6,value = "resource related links")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
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

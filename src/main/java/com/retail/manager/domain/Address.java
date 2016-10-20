package com.retail.manager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by abdulaziz on 20/10/2016.
 */
public class Address {

    @JsonProperty(required = true)
    private Integer number;

    @JsonProperty(required = true)
    private String postcode;

    public Address()
    {

    }

    public Address(Integer number, String postcode)
    {
        this.number = number;
        this.postcode = postcode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}

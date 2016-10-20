package com.retail.manager.controller;

import com.retail.manager.domain.Shop;
import com.retail.manager.service.ShopService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@Api(value = "shops", description = "Endpoint for Retail Manager API")
@RestController
@RequestMapping(value="/api/v1/shops")
public class ShopsController {


    @Autowired
    private ShopService shopService;


    @ApiOperation(httpMethod = "GET", value = "Returns Shops list with details", notes = "Returns a complete list of shops with details.", response = Shop.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of shops details.", response = Shop.class),
            @ApiResponse(code = 500, message = "Internal server error.")}
    )
    @RequestMapping( method= RequestMethod.GET ,produces = "application/json;charset=UTF-8")
    public ResponseEntity getShops() {

        return new ResponseEntity( shopService.getShops(), null, HttpStatus.OK);

    }

    @ApiOperation(httpMethod = "GET", value = "Returns Shop details", notes = "Returns a shop details for the given shopName.", response = Shop.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of the shop details.", response = Shop.class),
            @ApiResponse(code = 500, message = "Internal server error.")}
    )

    @RequestMapping(value = "/{shopName}",method= RequestMethod.GET ,produces = "application/json;charset=UTF-8")
    public ResponseEntity getShop(@PathVariable("shopName") String shopName) {

        Optional<Shop> foundShop = shopService.getShop(shopName);

        if(foundShop.isPresent())
         return new ResponseEntity(foundShop.get(), null, HttpStatus.OK);
        else {
            Shop shop = new Shop();
            shop.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
            shop.setError("No data found");
            return new ResponseEntity(null, null, HttpStatus.NOT_FOUND);
        }

    }


    @ApiOperation(httpMethod = "POST", value = "Add a new  Shops", notes = "Add a new shop with shop name and address data.", response = Shop.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful created the new shop.", response = Shop.class),
            @ApiResponse(code = 400, message = "Invalid data for the shop to create.", response = Shop.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    @RequestMapping( method= RequestMethod.POST ,consumes = "application/json;charset=UTF-8")
    public ResponseEntity addShop(
            @ApiParam(required = true,value = "Shop details",example = "{\"shopName\": \"Shop231\", \"shopAddress\": {\"number\": 296, \"postcode\": 333}}")
            @RequestBody Shop shop) throws Exception{

        boolean isDataAdded = shopService.addShop(shop);

        if(isDataAdded) {
            shop.setStatus(HttpStatus.CREATED.getReasonPhrase());
            return new ResponseEntity(shop, null, HttpStatus.CREATED);
        }else {
            shop.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
            return new ResponseEntity(shop, null, HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(httpMethod = "GET", value = "Returns Shops nearby", notes = "Returns nearby shops to the customer Longitude and customer Latitude.", response = Shop.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of shops detail", response = Shop.class),
            @ApiResponse(code = 500, message = "Internal server error")}
    )

    @RequestMapping(value = "/nearby", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getShopsNearby(
            @ApiParam(value = "Customer Geo Location : Latitude.",example = "51.409512")
            @RequestParam("customerLatitude") Double latitude,
            @ApiParam(value = "Customer Geo Location : Longitude.",example = "0.30363")
            @RequestParam("customerLongitude") Double longitude) {

         Set<Shop> foundShops = shopService.getShopsNearby(latitude,longitude);

         return new ResponseEntity( foundShops, null, HttpStatus.OK);
    }


}

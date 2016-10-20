package com.retail.manager.controller;

import com.retail.manager.domain.Shop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@RestController
@RequestMapping(value="/api/v1/shops")
public class ShopsController {


    @RequestMapping( method= RequestMethod.GET ,produces = "application/json")
    public ResponseEntity getShops() {

       return new ResponseEntity( null, null, HttpStatus.OK);

    }


    @RequestMapping( method= RequestMethod.POST ,consumes = "application/json")
    public ResponseEntity addShop(
            @RequestBody Shop shop) throws Exception{

        return new ResponseEntity( shop, null, HttpStatus.CREATED);
    }

     @RequestMapping(value = "/nearby", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity getShopsNearby(
            @RequestParam("customerLatitude") Double latitude,
            @RequestParam("customerLongitude") Double longitude) {

         return new ResponseEntity( null, null, HttpStatus.OK);
    }


}

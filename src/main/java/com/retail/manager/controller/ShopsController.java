package com.retail.manager.controller;

import com.retail.manager.domain.Shop;
import com.retail.manager.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@RestController
@RequestMapping(value="/api/v1/shops")
public class ShopsController {


    @Autowired
    private ShopService shopService;


    @RequestMapping( method= RequestMethod.GET ,produces = "application/json")
    public ResponseEntity getShops() {

        return new ResponseEntity( shopService.getShops(), null, HttpStatus.OK);

    }


    @RequestMapping( method= RequestMethod.POST ,consumes = "application/json")
    public ResponseEntity addShop(
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

    @RequestMapping(value = "/nearby", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity getShopsNearby(
            @RequestParam("customerLatitude") Double latitude,
            @RequestParam("customerLongitude") Double longitude) {

         Set<Shop> foundShops = shopService.getShopsNearby(latitude,longitude);

         return new ResponseEntity( foundShops, null, HttpStatus.OK);
    }


}

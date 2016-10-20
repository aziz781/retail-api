package com.retail.manager.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.retail.manager.domain.Address;
import com.retail.manager.domain.Geo;
import com.retail.manager.domain.Link;
import com.retail.manager.domain.Shop;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.retail.manager.utils.DistanceCalculator.calculateDistance;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@Service
public class ShopService {


    private Set<Shop> retailShops ;

    @Value("${google.maps.geo.api.key}")
    private String apiKey;



    Function<Shop,Shop> addLinks = (e) -> {
        e.getShopName();
        List<Link> links = Arrays.asList(
                // self
                new Link("/api/v1/shops/"+e.getShopName(),"self"),
                // nearby
                new Link("/api/v1/shops/nearby?customerLatitude="+e.getShopGeo().getLatitude()+"&customerLongitude="+e.getShopGeo().getLongitude(),"Shops Nearby"));
        e.setLinks(links);
        return e;
    };

    ShopService()
    {
        initData();
    }

    public Set<Shop> getShops()
    {
         return retailShops
                 .stream()
                 .map(addLinks)
                 .collect(Collectors.toSet());
    }


    public Optional<Shop> getShop(String shopName)
    {
            return retailShops
                    .stream()
                    .filter(e -> e.getShopName().equals(shopName))
                    .map(addLinks)
                    .findFirst();
    }

    public Set<Shop> getShopsNearby(Double latitude,Double longitude)
    {
        // find the shops nearby (within 10 miles of the customer location)
        return retailShops
                .stream()
                .filter(e-> calculateDistance(e.getShopGeo().getLatitude(),e.getShopGeo().getLongitude(),latitude,longitude)<10)
                .collect(Collectors.toSet());
    }

    public boolean addShop(Shop newShop)
    {

        // validate data before processing
        if(validateData(newShop)==false)
            return false;

        Integer number = newShop.getShopAddress().getNumber();
        String postcode = newShop.getShopAddress().getPostcode();

        // google map geolocation call
        try {
                GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
                GeocodingResult[] results = GeocodingApi.geocode(context,
                        number.toString() + "," + postcode).await();

                if(results.length==0) {
                    newShop.setError("No location found for the give address : " + number.toString() + "," + postcode);
                    return  false;
                }

                // get first available data
                Double lat = results[0].geometry.location.lat;
                Double lng = results[0].geometry.location.lng;

                Geo geo = new Geo(lat,lng);
                newShop.setShopGeo(geo);

        }catch(Exception e)
        {
            newShop.setError(e.getMessage());
            return false;
        }

        return retailShops.add(newShop);

    }

    private boolean validateData(Shop newShop)
    {
        String name = newShop.getShopName();
        Integer number = null;
        String postcode = null;

        Address address = newShop.getShopAddress();

        if(null != address) {
            number = address.getNumber();
             postcode = address.getPostcode();
        }

        if(null==name || null == number || null==postcode) {
            newShop.setError("required data is missing. please provide shop name and address both.");
            return false;
        }

        Optional<Shop> shop =
                retailShops
                .stream()
                .filter(e -> e.getShopName().equals(name))
                .findFirst();

        if(shop.isPresent()) {
            newShop.setError("Shop with name '"+name+"' already exits.");
            return false;
        }else
            return true;
    }



    private void initData()
    {
        retailShops = new HashSet<>();

        retailShops.add(new Shop("Shop1",11,"KT1 1AN",51.411857,-0.300983));
        retailShops.add(new Shop("Shop2",142,"KT1 1BJ",51.409671,-0.304645));
        retailShops.add(new Shop("Shop3",13,"KT1 1BP",51.409512,0.30363));
        retailShops.add(new Shop("Shop4",15,"EC1V 0AH",51.524007,-0.099769));
        retailShops.add(new Shop("Shop5",163,"EC1V 0AJ",51.527411,-0.101689));

    }

}

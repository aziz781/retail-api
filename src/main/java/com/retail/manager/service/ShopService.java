package com.retail.manager.service;

import com.retail.manager.domain.Shop;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by abdulaziz on 20/10/2016.
 */

@Service
public class ShopService {


    private Set<Shop> retailShops ;

    ShopService()
    {
        initData();
    }

    public Set<Shop> getShops()
    {
        return retailShops;
    }

    public Set<Shop> getShopsNearby(Double latitude,Double longitude)
    {
        // TODO implement it
        return retailShops;
    }

    public boolean addShop(Shop newShop)
    {

        return retailShops.add(newShop);

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

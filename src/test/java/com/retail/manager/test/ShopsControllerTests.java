package com.retail.manager.test;

import com.retail.manager.config.MainApplication;
import com.retail.manager.controller.ShopsController;
import com.retail.manager.domain.Shop;
import com.retail.manager.service.ShopService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
/**
 * Created by abdulaziz on 20/10/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
public class ShopsControllerTests {

    private MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @InjectMocks
    private ShopsController shopsController;

    private MockMvc mockMvc;

    @Mock
    @Autowired
    ShopService shopService;

    @Before
    public void setup() throws Exception {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        this.mockMvc =  MockMvcBuilders.standaloneSetup(shopsController).build();

    }

    @Test
    public void getShopsTest() throws Exception {

        Set<Shop> data = new HashSet<>();
        data.add(new Shop("shop-1",1001,"334"));
        data.add(new Shop("shop-2",1002,"335"));

        // define return value for method getShops()
        when(shopService.getShops())
                .thenReturn(data);

        mockMvc.perform(get("/api/v1/shops"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shopName", is("shop-1")))
                .andExpect(jsonPath("$[1].shopName", is("shop-2")));

        verify(shopService, times(1)).getShops();
        verifyNoMoreInteractions(shopService);

    }


    @Test
    public void getShopsNearbyTest() throws Exception {

        mockMvc.perform(get("/api/v1/shops/nearby?customerLatitude=77.6624264&customerLongitude=12.8383616"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void addShopNotValidTest() throws Exception {
        mockMvc.perform(post("/api/v1/shops")
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addShopValidTest() throws Exception {

        Shop newShop = new Shop("Shop-3",296,"333");
        when(shopService.addShop(newShop))
                .thenReturn(true);

        String jsonData = "{\"shopName\": \"Shop-3\", \"shopAddress\": {\"number\": 296, \"postcode\": 333}}";
        mockMvc.perform(post("/api/v1/shops")
                .contentType(contentType).content(jsonData.getBytes()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(contentType));
    }

}

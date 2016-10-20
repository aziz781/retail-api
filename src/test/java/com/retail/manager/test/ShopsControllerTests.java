package com.retail.manager.test;

import com.retail.manager.config.MainApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by abdulaziz on 20/10/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
public class ShopsControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void getShopsTest() throws Exception {

        mockMvc.perform(get("/api/v1/shops"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
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

        String jsonData = "{\"shopName\": \"Shop231\", \"shopAddress\": {\"number\": 296, \"postcode\": 333}}";
        mockMvc.perform(post("/api/v1/shops")
                .contentType(contentType).content(jsonData.getBytes()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(contentType));
    }

}

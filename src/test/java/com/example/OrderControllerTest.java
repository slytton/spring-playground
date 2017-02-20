package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gschool on 2/19/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void postPizzaOrdersTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/orders/pizza")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "BBQ Pizza")
                .param("size", "Large")
                .param("shape", "Circular")
                .param("crust", "Cheezy");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Thanks for your order of a Large Circular Cheezy crusted BBQ Pizza."));

        request = post("/orders/pizza")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "Ranch")
                .param("size", "Large")
                .param("shape", "Circular")
                .param("crust", "Thin");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Thanks for your order of a Large Circular Thin crusted Ranch."));

    }

//    @Test
//    public void postSandwichOrdersTest() throws Exception {
//        MockHttpServletRequestBuilder request = post("/orders/sandwich")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("type", "Grilled Cheese")
//                .param("bread", "Rye")
//                .param("cheese", "Swiss");
//
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string("Thanks for your order of a Rye and Swiss Grilled Cheese"));
//    }

    @Test
    public void postCandyOrdersTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/orders/candy")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "Gummy Bears");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Thanks for your order of type=Gummy+Bears"));
    }


}